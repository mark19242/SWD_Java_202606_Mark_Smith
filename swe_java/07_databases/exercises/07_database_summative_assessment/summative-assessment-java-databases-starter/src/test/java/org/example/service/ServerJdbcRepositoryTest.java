package org.example.service;

import java.util.List;
import java.util.Collections;

import org.example.data.exceptions.InternalErrorException;
import org.example.data.exceptions.RecordNotFoundException;
import org.example.data.impl.ServerJdbcRepository;
import org.example.model.Server;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.DataAccessException;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ServerJdbcRepositoryTest {

    @Mock
    private JdbcTemplate jdbcTemplate;

    @InjectMocks
    private ServerJdbcRepository repository;

    @Test
    void getServerByIdReturnsServerForValidId() throws Exception {

        Server expected = new Server();
        expected.setServerID(1);
        expected.setFirstName("Mersey");
        expected.setLastName("Giacometti");
        expected.setHireDate(LocalDate.of(2020, 2, 27));
        expected.setTermDate(null);

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(1)))
                .thenReturn(expected);

        Server actual = repository.getServerById(1);

        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void getServerByIdThrowsRecordNotFoundWhenServerDoesNotExist() {

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(999)))
                .thenThrow(new EmptyResultDataAccessException(1));

        assertThrows(
                RecordNotFoundException.class,
                () -> repository.getServerById(999)
        );
    }

    @Test
    void getServerByIdThrowsInternalErrorWhenDatabaseFails() {

        when(jdbcTemplate.queryForObject(
                anyString(),
                any(RowMapper.class),
                eq(1)))
                .thenThrow(new DataAccessException("Database error") {});

        assertThrows(
                InternalErrorException.class,
                () -> repository.getServerById(1)
        );
    }

    @Test
    void getAllAvailableServersReturnsServersForValidDate() throws Exception {

        LocalDate date = LocalDate.of(2022, 6, 1);

        Server server = new Server();
        server.setServerID(1);
        server.setFirstName("Mersey");
        server.setLastName("Giacometti");
        server.setHireDate(LocalDate.of(2020, 2, 27));
        server.setTermDate(null);

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq(date),
                eq(date)))
                .thenReturn(List.of(server));

        List<Server> actual = repository.getAllAvailableServers(date);

        assertNotNull(actual);
        assertEquals(1, actual.size());
        assertEquals(server, actual.get(0));
    }

    @Test
    void getAllAvailableServersReturnsEmptyListWhenNoneAreAvailable()
            throws Exception {

        LocalDate date = LocalDate.of(2019, 12, 31);

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq(date),
                eq(date)))
                .thenReturn(Collections.emptyList());

        List<Server> actual = repository.getAllAvailableServers(date);

        assertNotNull(actual);
        assertTrue(actual.isEmpty());
    }

    @Test
    void getAllAvailableServersThrowsInternalErrorWhenDatabaseFails() {

        LocalDate date = LocalDate.of(2022, 6, 1);

        when(jdbcTemplate.query(
                anyString(),
                any(RowMapper.class),
                eq(date),
                eq(date)))
                .thenThrow(new DataAccessException("Database error") {});

        assertThrows(
                InternalErrorException.class,
                () -> repository.getAllAvailableServers(date)
        );
    }

}