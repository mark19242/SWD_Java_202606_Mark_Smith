package org.example.service;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.TaxJdbcRepository;
import org.example.model.Tax;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TaxJdbcRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private TaxJdbcRepository repository;

    @Test
    void getCurrentTaxReturnsTaxForValidDate() throws Exception {

        LocalDate dateOf = LocalDate.of(2022, 6, 1);

        Tax expected = new Tax();
        expected.setTaxID(2);
        expected.setTaxPercentage(new BigDecimal("6.25"));
        expected.setStartDate(LocalDate.of(2022, 1, 1));
        expected.setEndDate(null);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(dateOf),
                eq(dateOf)))
                .thenReturn(expected);

        Tax actual = repository.getCurrentTax(dateOf);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void getCurrentTaxThrowsRecordNotFoundWhenNoTaxExists() {

        LocalDate dateOf = LocalDate.of(2019, 12, 31);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(dateOf),
                eq(dateOf)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(
                RecordNotFoundException.class,
                () -> repository.getCurrentTax(dateOf)
        );
    }

    @Test
    void getCurrentTaxThrowsInternalErrorWhenDatabaseFails() {

        LocalDate dateOf = LocalDate.of(2022, 6, 1);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(dateOf),
                eq(dateOf)))
                .thenThrow(new DataAccessException("Database error") {});

        assertThrows(
                InternalErrorException.class,
                () -> repository.getCurrentTax(dateOf)
        );
    }

}