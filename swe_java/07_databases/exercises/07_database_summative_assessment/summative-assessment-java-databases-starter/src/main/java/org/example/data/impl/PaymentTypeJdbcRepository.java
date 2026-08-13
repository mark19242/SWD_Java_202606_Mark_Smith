package org.example.data.impl;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.PaymentTypeRepo;
import org.example.model.PaymentType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class PaymentTypeJdbcRepository implements PaymentTypeRepo {

    private final JdbcTemplate jdbcTemplate;

    public PaymentTypeJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PaymentType> paymentTypeMapper = (rs, rowNum) -> {
        PaymentType paymentType = new PaymentType();

        paymentType.setPaymentTypeID(rs.getInt("payment_type_id"));
        paymentType.setPaymentTypeName(rs.getString("payment_type_name"));

        return paymentType;
    };

    @Override
    public List<PaymentType> getAll() throws InternalErrorException {

        String sql = """
                SELECT
                    payment_type_id,
                    payment_type_name
                FROM payment_type
                ORDER BY payment_type_id;
                """;

        try {
            return jdbcTemplate.query(sql, paymentTypeMapper);
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve payment types.",
                    ex
            );
        }
    }

}