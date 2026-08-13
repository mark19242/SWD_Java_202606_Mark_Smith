package org.example.service;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.impl.PaymentTypeJdbcRepository;
import org.example.model.PaymentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PaymentTypeJdbcRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private PaymentTypeJdbcRepository repository;

    @Test
    void getAllReturnsPaymentTypes() throws InternalErrorException {

        PaymentType paymentType = new PaymentType();
        paymentType.setPaymentTypeID(1);
        paymentType.setPaymentTypeName("Cash");

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class)))
                .thenReturn(List.of(paymentType));

        List<PaymentType> result = repository.getAll();

        assertEquals(1, result.size());
        assertEquals(1, result.get(0).getPaymentTypeID());
        assertEquals("Cash", result.get(0).getPaymentTypeName());
    }

    @Test
    void getAllReturnsEmptyListWhenNoPaymentTypesExist()
            throws InternalErrorException {

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class)))
                .thenReturn(Collections.emptyList());

        List<PaymentType> result = repository.getAll();

        assertNotNull(result);
        assertTrue(result.isEmpty());
    }

    @Test
    void getAllThrowsInternalErrorExceptionWhenDatabaseFails() {

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class)))
                .thenThrow(new DataAccessException("Database error") {});

        assertThrows(
                InternalErrorException.class,
                () -> repository.getAll()
        );
    }
}