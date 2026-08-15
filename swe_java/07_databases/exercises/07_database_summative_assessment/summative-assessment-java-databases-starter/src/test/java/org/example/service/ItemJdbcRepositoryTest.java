package org.example.service;

import org.example.data.impl.ItemJdbcRepository;
import org.example.model.Item;
import org.example.model.ItemCategory;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ItemJdbcRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ItemJdbcRepository repository;

    @Test
    void getItemByIdReturnsItemForValidId() throws Exception {

        ItemCategory category = new ItemCategory(1, "Appetizers");

        Item expected = new Item();
        expected.setItemID(1);
        expected.setItemCategoryID(1);
        expected.setItemName("Mozzarella Sticks");
        expected.setItemDescription("Lightly breaded cheese sticks");
        expected.setStartDate(LocalDate.of(2020, 1, 1));
        expected.setEndDate(null);
        expected.setUnitPrice(new BigDecimal("8.00"));
        expected.setItemCategory(category);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(1)))
                .thenReturn(expected);

        Item actual = repository.getItemById(1);

        assertNotNull(actual);
        assertEquals(expected, actual);

        assertNotNull(actual.getItemCategory());
        assertEquals(
                "Appetizers",
                actual.getItemCategory().getItemCategoryName()
        );
    }

    @Test
    void getItemByIdThrowsRecordNotFoundWhenItemDoesNotExist() {

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(999)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(
                RecordNotFoundException.class,
                () -> repository.getItemById(999)
        );
    }

    @Test
    void getItemByIdThrowsInternalErrorWhenDatabaseFails() {

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(1)))
                .thenThrow(new DataAccessException("Database error") {});

        assertThrows(
                InternalErrorException.class,
                () -> repository.getItemById(1)
        );
    }

}