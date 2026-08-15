package org.example.service;

import org.example.data.impl.OrderJdbcRepository;
import org.example.model.Order;
import org.example.model.OrderItem;
import org.example.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OrderJdbcRepositoryIntegrationTest extends BaseJdbcTest {

    private OrderJdbcRepository repository;

    @BeforeEach
    void setUpRepository() {
        repository = new OrderJdbcRepository(jdbcTemplate);
    }

    @Test
    void getOrderByIdHydratesCompleteOrderGraph() throws Exception {

        Integer orderID = jdbcTemplate.queryForObject("""
                SELECT o.order_id
                FROM orders o
                WHERE EXISTS (
                    SELECT 1
                    FROM order_item oi
                    WHERE oi.order_id = o.order_id
                )
                AND EXISTS (
                    SELECT 1
                    FROM payment p
                    WHERE p.order_id = o.order_id
                )
                ORDER BY o.order_id
                LIMIT 1
                """,
                Integer.class
        );

        assertNotNull(orderID);

        Order order = repository.getOrderById(orderID);

        // Order itself
        assertNotNull(order);

        // Server relationship
        assertNotNull(order.getServer());
        assertEquals(
                order.getServerID(),
                order.getServer().getServerID()
        );

        // Order items relationship
        assertNotNull(order.getItems());
        assertFalse(order.getItems().isEmpty());

        OrderItem firstOrderItem = order.getItems().get(0);

        assertNotNull(firstOrderItem.getItem());
        assertNotNull(
                firstOrderItem.getItem().getItemCategory()
        );

        // Payment relationship
        assertNotNull(order.getPayments());
        assertFalse(order.getPayments().isEmpty());

        Payment firstPayment = order.getPayments().get(0);

        assertNotNull(firstPayment.getPaymentType());
    }

    @Test
    void addOrderPersistsItemsAndPayments() throws Exception {

        Order order = new Order();
        order.setServerID(1);
        order.setOrderDate(
                java.time.LocalDateTime.of(2022, 6, 1, 12, 0)
        );
        order.setSubTotal(new java.math.BigDecimal("8.00"));
        order.setTax(new java.math.BigDecimal("0.50"));
        order.setTip(new java.math.BigDecimal("1.50"));
        order.setTotal(new java.math.BigDecimal("10.00"));

        OrderItem orderItem = new OrderItem();
        orderItem.setItemID(1);
        orderItem.setQuantity(1);
        orderItem.setPrice(new java.math.BigDecimal("8.00"));

        order.setItems(java.util.List.of(orderItem));

        Payment payment = new Payment();
        payment.setPaymentTypeID(1);
        payment.setAmount(new java.math.BigDecimal("10.00"));

        order.setPayments(java.util.List.of(payment));

        Order added = repository.addOrder(order);

        assertTrue(added.getOrderID() > 0);

        // Reload from Postgres
        Order reloaded =
                repository.getOrderById(added.getOrderID());

        assertNotNull(reloaded.getItems());
        assertEquals(1, reloaded.getItems().size());

        assertNotNull(reloaded.getPayments());
        assertEquals(1, reloaded.getPayments().size());

        assertEquals(
                1,
                reloaded.getItems().get(0).getItemID()
        );

        assertEquals(
                1,
                reloaded.getPayments().get(0).getPaymentTypeID()
        );
    }

}