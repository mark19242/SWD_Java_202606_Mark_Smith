package org.apprenti.app_bff.service;

import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;

@Service
public class AdminUserService {

    private final JdbcClient jdbcClient;

    public AdminUserService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    public boolean updateEnabledStatus(
            String currentAdmin,
            String username,
            boolean enabled
    ) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (currentAdmin.equals(username)
                && !enabled) {
            throw new IllegalArgumentException(
                    "You cannot disable your own account."
            );
        }

        int rowsUpdated = jdbcClient
                .sql("""
                        UPDATE users
                        SET enabled = :enabled
                        WHERE username = :username
                        """)
                .param("enabled", enabled)
                .param("username", username)
                .update();

        return rowsUpdated == 1;
    }
}
