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
}