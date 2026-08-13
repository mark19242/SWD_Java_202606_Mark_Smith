package org.example.data.impl;

import org.example.data.TaxRepo;
import org.example.model.Tax;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;

import java.time.LocalDate;

@Repository
public class TaxJdbcRepository implements TaxRepo {

    private final JdbcTemplate jdbcTemplate;

    public TaxJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Tax> taxMapper = (rs, rowNum) -> {
        Tax tax = new Tax();

        tax.setTaxID(rs.getInt("tax_id"));
        tax.setTaxPercentage(rs.getBigDecimal("tax_percentage"));
        tax.setStartDate(rs.getObject("start_date", java.time.LocalDate.class));
        tax.setEndDate(rs.getObject("end_date", java.time.LocalDate.class));

        return tax;
    };

    @Override
    public Tax getCurrentTax(LocalDate dateOf)
            throws InternalErrorException, RecordNotFoundException {

        String sql = """
            SELECT
                tax_id,
                tax_percentage,
                start_date,
                end_date
            FROM tax
            WHERE start_date <= ?
              AND (
                    end_date IS NULL
                    OR end_date >= ?
                  )
            """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    taxMapper,
                    dateOf,
                    dateOf
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    "No tax record found for date: " + dateOf
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve tax information.",
                    ex
            );
        }
    }
}