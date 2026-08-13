package org.example.data.impl;

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

    @Override
    public Order getOrderById(int id) throws RecordNotFoundException, InternalErrorException {
        final String sql = "SELECT order_id, server_id, order_date, sub_total, tax, tip, total " +
                "FROM orders WHERE order_id = ?;";
        try {
            return jdbcTemplate.queryForObject(sql, orderMapper, id);
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException("Order with ID " + id + " was not found.");
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error retrieving order ID " + id, ex);
        }
    }

    @Override
    public List<Order> getAllOrders() throws InternalErrorException, RecordNotFoundException {
        final String sql = "SELECT order_id, server_id, order_date, sub_total, tax, tip, total FROM orders;";
        try {
            List<Order> orders = jdbcTemplate.query(sql, orderMapper);
            if (orders.isEmpty()) {
                throw new RecordNotFoundException("No orders found in database.");
            }
            return orders;
        } catch (RecordNotFoundException ex) {
            throw ex;
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error fetching all orders.", ex);
        }
    }

    @Override
    public Order addOrder(Order order) throws InternalErrorException {
        final String sql = "INSERT INTO orders (server_id, order_date, sub_total, tax, tip, total) " +
                "VALUES (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
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
            return order;
        } catch (DataAccessException ex) {
            throw new InternalErrorException("Database error inserting order.", ex);
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