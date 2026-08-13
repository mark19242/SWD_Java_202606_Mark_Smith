package org.example.service;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import java.sql.SQLException;
public class BaseJdbcTest {

    private static final String TEST_DB_URL = "jdbc:postgresql://localhost:5432/simple_bistro_test";
    private static final String DB_USER = "learnacademy";
    private static final String DB_PASSWORD = "postgres1";

    protected SingleConnectionDataSource dataSource;
    protected JdbcTemplate jdbcTemplate;

    @BeforeEach
    void setupDatabase() throws SQLException {
        dataSource = new SingleConnectionDataSource();
        dataSource.setUrl(TEST_DB_URL);
        dataSource.setUsername(DB_USER);
        dataSource.setPassword(DB_PASSWORD);
        dataSource.setAutoCommit(false); // Rolls back modifications after test execution

        ResourceDatabasePopulator populator = new ResourceDatabasePopulator();

        // --- OPTION 1: Use ClassPathResource if scripts are in src/main/resources or src/test/resources ---
        // populator.addScript(new ClassPathResource("schema.sql"));
        // populator.addScript(new ClassPathResource("data.sql"));

        // --- OPTION 2: Use FileSystemResource if scripts are in root folders (e.g. ./data/) ---// Change to your schema filename/path
        populator.addScript(new FileSystemResource("src/test/sql/set_known_good_state_postgres.sql"));   // Change to your data filename/path

        populator.execute(dataSource);

        jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @AfterEach
    void rollbackTransaction() throws SQLException {
        if (dataSource != null && !dataSource.getConnection().isClosed()) {
            dataSource.getConnection().rollback();
            dataSource.destroy();
        }
    }
}
