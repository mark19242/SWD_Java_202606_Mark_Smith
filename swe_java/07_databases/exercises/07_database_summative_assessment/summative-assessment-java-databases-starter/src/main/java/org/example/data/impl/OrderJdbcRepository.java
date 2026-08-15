package org.example.data.impl;

import org.example.model.Item;
import org.example.model.ItemCategory;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.example.model.PaymentType;
import org.example.model.Server;
import org.example.data.OrderRepo;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.model.Order;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.time.LocalDate;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.List;

@Repository
public class OrderJdbcRepository implements OrderRepo {

    private final JdbcTemplate jdbcTemplate;

    public OrderJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Order> orderMapper = (rs, rowNum) -> {
        Order order = new Order();
        order.setOrderID(rs.getInt("order_id"));
        order.setServerID(rs.getInt("server_id"));

        Timestamp timestamp = rs.getTimestamp("order_date");
        if (timestamp != null) {
            order.setOrderDate(timestamp.toLocalDateTime());
        }

        order.setSubTotal(rs.getBigDecimal("sub_total"));
        order.setTax(rs.getBigDecimal("tax"));
        order.setTip(rs.getBigDecimal("tip"));
        order.setTotal(rs.getBigDecimal("total"));
        return order;
    };

    private final RowMapper<Order> orderWithServerMapper = (rs, rowNum) -> {
        Order order = new Order();

        order.setOrderID(rs.getInt("order_id"));
        order.setServerID(rs.getInt("server_id"));

        Timestamp timestamp = rs.getTimestamp("order_date");
        if (timestamp != null) {
            order.setOrderDate(timestamp.toLocalDateTime());
        }

        order.setSubTotal(rs.getBigDecimal("sub_total"));
        order.setTax(rs.getBigDecimal("tax"));
        order.setTip(rs.getBigDecimal("tip"));
        order.setTotal(rs.getBigDecimal("total"));

        Server server = new Server();
        server.setServerID(rs.getInt("server_id"));
        server.setFirstName(rs.getString("server_first_name"));
        server.setLastName(rs.getString("server_last_name"));
        server.setHireDate(
                rs.getObject("server_hire_date", LocalDate.class)
        );
        server.setTermDate(
                rs.getObject("server_term_date", LocalDate.class)
        );

        order.setServer(server);

        return order;
    };

    private final RowMapper<OrderItem> orderItemMapper = (rs, rowNum) -> {
        OrderItem orderItem = new OrderItem();

        orderItem.setOrderItemID(rs.getInt("order_item_id"));
        orderItem.setOrderID(rs.getInt("order_id"));
        orderItem.setItemID(rs.getInt("item_id"));
        orderItem.setQuantity(rs.getInt("quantity"));
        orderItem.setPrice(rs.getBigDecimal("price"));

        Item item = new Item();
        item.setItemID(rs.getInt("item_id"));
        item.setItemCategoryID(rs.getInt("item_category_id"));
        item.setItemName(rs.getString("item_name"));
        item.setItemDescription(rs.getString("item_description"));
        item.setStartDate(
                rs.getObject("item_start_date", LocalDate.class)
        );
        item.setEndDate(
                rs.getObject("item_end_date", LocalDate.class)
        );
        item.setUnitPrice(rs.getBigDecimal("unit_price"));

        ItemCategory category = new ItemCategory();
        category.setItemCategoryID(rs.getInt("item_category_id"));
        category.setItemCategoryName(
                rs.getString("item_category_name")
        );

        item.setItemCategory(category);
        orderItem.setItem(item);

        return orderItem;
    };

    private final RowMapper<Payment> paymentMapper = (rs, rowNum) -> {
        Payment payment = new Payment();

        payment.setPaymentID(rs.getInt("payment_id"));
        payment.setPaymentTypeID(rs.getInt("payment_type_id"));
        payment.setOrderID(rs.getInt("order_id"));
        payment.setAmount(rs.getBigDecimal("amount"));

        PaymentType paymentType = new PaymentType();
        paymentType.setPaymentTypeID(rs.getInt("payment_type_id"));
        paymentType.setPaymentTypeName(
                rs.getString("payment_type_name")
        );

        payment.setPaymentType(paymentType);

        return payment;
    };

    private List<OrderItem> loadOrderItems(int orderID) {

        String sql = """
            SELECT
                oi.order_item_id,
                oi.order_id,
                oi.item_id,
                oi.quantity,
                oi.price,
                i.item_category_id,
                i.item_name,
                i.item_description,
                i.start_date AS item_start_date,
                i.end_date AS item_end_date,
                i.unit_price,
                ic.item_category_name
            FROM order_item oi
            INNER JOIN item i
                ON oi.item_id = i.item_id
            INNER JOIN item_category ic
                ON i.item_category_id = ic.item_category_id
            WHERE oi.order_id = ?
            ORDER BY oi.order_item_id
            """;

        return jdbcTemplate.query(
                sql,
                orderItemMapper,
                orderID
        );
    }

    private List<Payment> loadPayments(int orderID) {

        String sql = """
            SELECT
                p.payment_id,
                p.payment_type_id,
                p.order_id,
                p.amount,
                pt.payment_type_name
            FROM payment p
            INNER JOIN payment_type pt
                ON p.payment_type_id = pt.payment_type_id
            WHERE p.order_id = ?
            ORDER BY p.payment_id
            """;

        return jdbcTemplate.query(
                sql,
                paymentMapper,
                orderID
        );
    }

    @Override
    public Order getOrderById(int id)
            throws RecordNotFoundException, InternalErrorException {

        String sql = """
            SELECT
                o.order_id,
                o.server_id,
                o.order_date,
                o.sub_total,
                o.tax,
                o.tip,
                o.total,
                s.first_name AS server_first_name,
                s.last_name AS server_last_name,
                s.hire_date AS server_hire_date,
                s.term_date AS server_term_date
            FROM orders o
            INNER JOIN server s
                ON o.server_id = s.server_id
            WHERE o.order_id = ?
            """;

        try {
            Order order = jdbcTemplate.queryForObject(
                    sql,
                    orderWithServerMapper,
                    id
            );

            order.setItems(loadOrderItems(order.getOrderID()));
            order.setPayments(loadPayments(order.getOrderID()));

            return order;

        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    "Order with ID " + id + " was not found."
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Database error retrieving order ID " + id,
                    ex
            );
        }
    }

    @Override
    public List<Order> getAllOrders()
            throws InternalErrorException, RecordNotFoundException {

        String sql = """
            SELECT
                o.order_id,
                o.server_id,
                o.order_date,
                o.sub_total,
                o.tax,
                o.tip,
                o.total,
                s.first_name AS server_first_name,
                s.last_name AS server_last_name,
                s.hire_date AS server_hire_date,
                s.term_date AS server_term_date
            FROM orders o
            INNER JOIN server s
                ON o.server_id = s.server_id
            ORDER BY o.order_id
            """;

        try {
            List<Order> orders = jdbcTemplate.query(
                    sql,
                    orderWithServerMapper
            );

            if (orders.isEmpty()) {
                throw new RecordNotFoundException(
                        "No orders found in database."
                );
            }

            for (Order order : orders) {
                order.setItems(
                        loadOrderItems(order.getOrderID())
                );

                order.setPayments(
                        loadPayments(order.getOrderID())
                );
            }

            return orders;

        } catch (RecordNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Database error fetching all orders.",
                    ex
            );
        }
    }

    @Override
    @Transactional
    public Order addOrder(Order order) throws InternalErrorException {
        final String sql = "INSERT INTO orders (server_id, order_date, sub_total, tax, tip, total) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(
                        sql,
                        new String[]{"order_id"}
                );
                ps.setInt(1, order.getServerID());
                ps.setTimestamp(2, Timestamp.valueOf(order.getOrderDate()));
                ps.setBigDecimal(3, order.getSubTotal());
                ps.setBigDecimal(4, order.getTax());
                ps.setBigDecimal(5, order.getTip());
                ps.setBigDecimal(6, order.getTotal());
                return ps;
            }, keyHolder);

            if (keyHolder.getKey() != null) {
                order.setOrderID(keyHolder.getKey().intValue());
            }

            addOrderItems(order);
            addPayments(order);

            return order;
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error inserting order.", ex);
        }
    }

    private void addOrderItems(Order order) {

        if (order.getItems() == null) {
            return;
        }

        String sql = """
            INSERT INTO order_item (
                order_id,
                item_id,
                quantity,
                price
            )
            VALUES (?, ?, ?, ?)
            """;

        for (OrderItem orderItem : order.getItems()) {

            jdbcTemplate.update(
                    sql,
                    order.getOrderID(),
                    orderItem.getItemID(),
                    orderItem.getQuantity(),
                    orderItem.getPrice()
            );

            orderItem.setOrderID(order.getOrderID());
        }
    }

    private void addPayments(Order order) {

        if (order.getPayments() == null) {
            return;
        }

        String sql = """
            INSERT INTO payment (
                payment_type_id,
                order_id,
                amount
            )
            VALUES (?, ?, ?)
            """;

        for (Payment payment : order.getPayments()) {

            jdbcTemplate.update(
                    sql,
                    payment.getPaymentTypeID(),
                    order.getOrderID(),
                    payment.getAmount()
            );

            payment.setOrderID(order.getOrderID());
        }
    }

    @Override
    public void updateOrder(Order order) throws InternalErrorException {
        final String sql = "UPDATE orders SET server_id = ?, order_date = ?, sub_total = ?, " +
                "tax = ?, tip = ?, total = ? WHERE order_id = ?;";
        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    order.getServerID(),
                    Timestamp.valueOf(order.getOrderDate()),
                    order.getSubTotal(),
                    order.getTax(),
                    order.getTip(),
                    order.getTotal(),
                    order.getOrderID());

            if (rowsAffected == 0) {
                throw new InternalErrorException("Update failed. Order ID " + order.getOrderID() + " does not exist.");
            }
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error updating order ID " + order.getOrderID(), ex);
        }
    }

    @Override
    @Transactional
    public Order deleteOrder(int id) throws InternalErrorException {
        try {
            Order orderToDelete = getOrderById(id);

            // Delete foreign key dependents prior to deleting main order record
            jdbcTemplate.update("DELETE FROM payment WHERE order_id = ?;", id);
            jdbcTemplate.update("DELETE FROM order_item WHERE order_id = ?;", id);

            jdbcTemplate.update("DELETE FROM orders WHERE order_id = ?;", id);
            return orderToDelete;
        } catch (RecordNotFoundException ex) {
            throw new InternalErrorException("Cannot delete. Order ID " + id + " was not found.", ex);
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error deleting order ID " + id, ex);
        }
    }
}