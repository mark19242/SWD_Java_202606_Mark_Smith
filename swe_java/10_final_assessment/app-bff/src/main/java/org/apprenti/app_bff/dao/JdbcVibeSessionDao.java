package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.VibeSession;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcVibeSessionDao implements VibeSessionDao {

    private final JdbcClient jdbcClient;

    public JdbcVibeSessionDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public VibeSession create(VibeSession vibeSession) {
        var keyHolder = new GeneratedKeyHolder();

        var sql = """
                INSERT INTO vibe_sessions (
                    username,
                    current_feeling,
                    desired_feeling,
                    primary_movie_vibe,
                    secondary_movie_vibe,
                    intensity,
                    runtime_preference
                )
                VALUES (
                    :username,
                    :currentFeeling,
                    :desiredFeeling,
                    :primaryMovieVibe,
                    :secondaryMovieVibe,
                    :intensity,
                    :runtimePreference
                )
                """;

        jdbcClient.sql(sql)
                .param("username", vibeSession.username())
                .param("currentFeeling", vibeSession.currentFeeling())
                .param("desiredFeeling", vibeSession.desiredFeeling())
                .param("primaryMovieVibe", vibeSession.primaryMovieVibe())
                .param("secondaryMovieVibe", vibeSession.secondaryMovieVibe())
                .param("intensity", vibeSession.intensity())
                .param("runtimePreference", vibeSession.runtimePreference())
                .update(keyHolder, "vibe_session_id");

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new IllegalStateException("Unable to create vibe session.");
        }

        return findById(generatedId.longValue())
                .orElseThrow(()
                        -> new IllegalStateException("Created vibe session could not be found."));
    }

    @Override
    public Optional<VibeSession> findById(Long vibeSessionId) {
        var sql = """
            SELECT
                vibe_session_id AS "vibeSessionId",
                username,
                current_feeling AS "currentFeeling",
                desired_feeling AS "desiredFeeling",
                primary_movie_vibe AS "primaryMovieVibe",
                secondary_movie_vibe AS "secondaryMovieVibe",
                intensity,
                runtime_preference AS "runtimePreference",
                created_at AS "createdAt"
            FROM vibe_sessions
            WHERE vibe_session_id = :vibeSessionId
            """;

        return jdbcClient.sql(sql)
                .param("vibeSessionId", vibeSessionId)
                .query(VibeSession.class)
                .optional();
    }

    @Override
    public List<VibeSession> findByUsername(String username) {
        var sql = """
            SELECT
                vibe_session_id AS "vibeSessionId",
                username,
                current_feeling AS "currentFeeling",
                desired_feeling AS "desiredFeeling",
                primary_movie_vibe AS "primaryMovieVibe",
                secondary_movie_vibe AS "secondaryMovieVibe",
                intensity,
                runtime_preference AS "runtimePreference",
                created_at AS "createdAt"
            FROM vibe_sessions
            WHERE username = :username
            ORDER BY created_at DESC
            """;

        return jdbcClient.sql(sql)
                .param("username", username)
                .query(VibeSession.class)
                .list();
    }

    @Override
    public boolean deleteByIdAndUsername(Long vibeSessionId, String username) {
        var sql = """
            DELETE FROM vibe_sessions
            WHERE vibe_session_id = :vibeSessionId
              AND username = :username
            """;

        int rowsAffected = jdbcClient.sql(sql)
                .param("vibeSessionId", vibeSessionId)
                .param("username", username)
                .update();

        return rowsAffected > 0;
    }
}
