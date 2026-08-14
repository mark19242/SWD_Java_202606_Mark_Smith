package org.example.data.impl;

import java.time.LocalDate;
import java.util.List;
import org.example.data.ServerRepo;
import org.example.model.Server;
import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class ServerJdbcRepository implements ServerRepo {

    private final JdbcTemplate jdbcTemplate;

    public ServerJdbcRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Server> serverMapper = (rs, rowNum) -> {
        Server server = new Server();

        server.setServerID(rs.getInt("server_id"));
        server.setFirstName(rs.getString("first_name"));
        server.setLastName(rs.getString("last_name"));
        server.setHireDate(
                rs.getObject("hire_date", java.time.LocalDate.class)
        );
        server.setTermDate(
                rs.getObject("term_date", java.time.LocalDate.class)
        );

        return server;
    };

    @Override
    public Server getServerById(int id)
            throws InternalErrorException, RecordNotFoundException {

        String sql = """
            SELECT
                server_id,
                first_name,
                last_name,
                hire_date,
                term_date
            FROM server
            WHERE server_id = ?
            """;

        try {
            return jdbcTemplate.queryForObject(
                    sql,
                    serverMapper,
                    id
            );
        } catch (EmptyResultDataAccessException ex) {
            throw new RecordNotFoundException(
                    "No server found with ID: " + id
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve server information.",
                    ex
            );
        }
    }

    @Override
    public List<Server> getAllAvailableServers(LocalDate date)
            throws InternalErrorException {

        String sql = """
            SELECT
                server_id,
                first_name,
                last_name,
                hire_date,
                term_date
            FROM server
            WHERE hire_date <= ?
              AND (
                    term_date IS NULL
                    OR term_date >= ?
                  )
            ORDER BY server_id
            """;

        try {
            return jdbcTemplate.query(
                    sql,
                    serverMapper,
                    date,
                    date
            );
        } catch (DataAccessException ex) {
            throw new InternalErrorException(
                    "Unable to retrieve available servers.",
                    ex
            );
        }
    }
}