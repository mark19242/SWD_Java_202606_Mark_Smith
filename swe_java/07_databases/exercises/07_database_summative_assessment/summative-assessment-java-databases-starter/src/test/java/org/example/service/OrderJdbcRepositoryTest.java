package org.example.service;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.OrderJdbcRepository;
import org.example.model.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.KeyHolder;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderJdbcRepositoryTest extends BaseJdbcTest{

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private OrderJdbcRepository repository;

    private Order testOrder;

    @BeforeEach
    void setUp() {
        testOrder = new Order();
        testOrder.setOrderID(1);
        testOrder.setServerID(5);
        testOrder.setOrderDate(LocalDateTime.now());
        testOrder.setSubTotal(new BigDecimal("20.00"));
        testOrder.setTax(new BigDecimal("1.25"));
        testOrder.setTip(new BigDecimal("3.00"));
        testOrder.setTotal(new BigDecimal("24.25"));
    }

    @Nested
    @DisplayName("getOrderById Tests")
    class GetOrderByIdTests {

        @Test
        @DisplayName("Should return Order when valid ID exists")
        void getOrderById_success() throws Exception {
            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(1)))
                    .thenReturn(testOrder);

            Order result = repository.getOrderById(1);

            assertNotNull(result);
            assertEquals(1, result.getOrderID());
            assertEquals(5, result.getServerID());
        }

        @Test
        @DisplayName("Should throw RecordNotFoundException when order does not exist")
        void getOrderById_notFound() {
            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(99)))
                    .thenThrow(new EmptyResultDataAccessException(1));

            assertThrows(RecordNotFoundException.class, () -> repository.getOrderById(99));
        }

        @Test
        @DisplayName("Should throw InternalErrorException on DataAccessException")
        void getOrderById_databaseError() {
            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(1)))
                    .thenThrow(new DataAccessException("Database timeout") {});

            assertThrows(InternalErrorException.class, () -> repository.getOrderById(1));
        }
    }

    @Nested
    @DisplayName("getAllOrders Tests")
    class GetAllOrdersTests {

        @Test
        @DisplayName("Should return list of orders when present")
        void getAllOrders_success() throws Exception {
            when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                    .thenReturn(List.of(testOrder));

            List<Order> results = repository.getAllOrders();

            assertEquals(1, results.size());
            assertEquals(1, results.get(0).getOrderID());
        }

        @Test
        @DisplayName("Should throw RecordNotFoundException when table is empty")
        void getAllOrders_emptyTable() {
            when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                    .thenReturn(Collections.emptyList());

            assertThrows(RecordNotFoundException.class, () -> repository.getAllOrders());
        }

        @Test
        @DisplayName("Should throw InternalErrorException on DataAccessException")
        void getAllOrders_databaseError() {
            when(jdbcTemplate.query(anyString(), any(RowMapper.class)))
                    .thenThrow(new DataAccessException("Query failure") {});

            assertThrows(InternalErrorException.class, () -> repository.getAllOrders());
        }
    }

    @Nested
    @DisplayName("addOrder Tests")
    class AddOrderTests {

        @Test
        @DisplayName("Should insert order and assign generated key")
        void addOrder_success() throws Exception {
            doAnswer(invocation -> {
                KeyHolder keyHolder = invocation.getArgument(1, KeyHolder.class);
                keyHolder.getKeyList().add(Map.of("order_id", 10));
                return 1;
            }).when(jdbcTemplate).update(any(PreparedStatementCreator.class), any(KeyHolder.class));

            Order result = repository.addOrder(testOrder);

            assertNotNull(result);
            assertEquals(10, result.getOrderID());
        }

        @Test
        @DisplayName("Should throw InternalErrorException on insert failure")
        void addOrder_databaseError() {
            when(jdbcTemplate.update(any(PreparedStatementCreator.class), any(KeyHolder.class)))
                    .thenThrow(new DataAccessException("Syntax error") {});

            assertThrows(InternalErrorException.class, () -> repository.addOrder(testOrder));
        }
    }

    @Nested
    @DisplayName("updateOrder Tests")
    class UpdateOrderTests {

        @Test
        @DisplayName("Should succeed when 1 row updated")
        void updateOrder_success() {
            when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any()))
                    .thenReturn(1);

            assertDoesNotThrow(() -> repository.updateOrder(testOrder));
        }

        @Test
        @DisplayName("Should throw InternalErrorException when 0 rows updated")
        void updateOrder_zeroRowsAffected() {
            when(jdbcTemplate.update(anyString(), any(), any(), any(), any(), any(), any(), any()))
                    .thenReturn(0);

            assertThrows(InternalErrorException.class, () -> repository.updateOrder(testOrder));
        }
    }

    @Nested
    @DisplayName("deleteOrder Tests")
    class DeleteOrderTests {

        @Test
        @DisplayName("Should delete order and dependent tables when found")
        void deleteOrder_success() throws Exception {
            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(1)))
                    .thenReturn(testOrder);
            when(jdbcTemplate.update(anyString(), eq(1))).thenReturn(1);

            Order deletedOrder = repository.deleteOrder(1);

            assertNotNull(deletedOrder);
            assertEquals(1, deletedOrder.getOrderID());
            verify(jdbcTemplate, times(3)).update(anyString(), eq(1));
        }

        @Test
        @DisplayName("Should throw InternalErrorException when order to delete does not exist")
        void deleteOrder_notFound() {
            when(jdbcTemplate.queryForObject(anyString(), any(RowMapper.class), eq(99)))
                    .thenThrow(new EmptyResultDataAccessException(1));

            assertThrows(InternalErrorException.class, () -> repository.deleteOrder(99));
        }
    }
}