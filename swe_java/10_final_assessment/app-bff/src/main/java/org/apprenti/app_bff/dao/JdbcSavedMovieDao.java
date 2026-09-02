package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.SavedMovie;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcSavedMovieDao implements SavedMovieDao {

    private final JdbcClient jdbcClient;

    public JdbcSavedMovieDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public SavedMovie create(SavedMovie savedMovie) {
        var keyHolder = new GeneratedKeyHolder();

        var sql = """
                INSERT INTO saved_movies (
                    username,
                    tmdb_movie_id,
                    watch_status,
                    personal_rating,
                    notes
                )
                VALUES (
                    :username,
                    :tmdbMovieId,
                    :watchStatus,
                    :personalRating,
                    :notes
                )
                """;

        jdbcClient.sql(sql)
                .param("username", savedMovie.username())
                .param("tmdbMovieId", savedMovie.tmdbMovieId())
                .param("watchStatus", savedMovie.watchStatus())
                .param("personalRating", savedMovie.personalRating())
                .param("notes", savedMovie.notes())
                .update(keyHolder, "saved_movie_id");

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new IllegalStateException("Unable to create saved movie.");
        }

        return findById(generatedId.longValue())
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Created saved movie could not be found."
                )
                );
    }

    @Override
    public Optional<SavedMovie> findById(Long savedMovieId) {
        var sql = """
                SELECT
                    saved_movie_id AS "savedMovieId",
                    username,
                    tmdb_movie_id AS "tmdbMovieId",
                    watch_status AS "watchStatus",
                    personal_rating AS "personalRating",
                    notes,
                    saved_at AS "savedAt",
                    updated_at AS "updatedAt"
                FROM saved_movies
                WHERE saved_movie_id = :savedMovieId
                """;

        return jdbcClient.sql(sql)
                .param("savedMovieId", savedMovieId)
                .query(SavedMovie.class)
                .optional();
    }

    @Override
    public List<SavedMovie> findByUsername(String username) {
        var sql = """
                SELECT
                    saved_movie_id AS "savedMovieId",
                    username,
                    tmdb_movie_id AS "tmdbMovieId",
                    watch_status AS "watchStatus",
                    personal_rating AS "personalRating",
                    notes,
                    saved_at AS "savedAt",
                    updated_at AS "updatedAt"
                FROM saved_movies
                WHERE username = :username
                ORDER BY saved_at DESC
                """;

        return jdbcClient.sql(sql)
                .param("username", username)
                .query(SavedMovie.class)
                .list();
    }

    @Override
    public boolean update(SavedMovie savedMovie) {
        var sql = """
                UPDATE saved_movies
                SET
                    watch_status = :watchStatus,
                    personal_rating = :personalRating,
                    notes = :notes,
                    updated_at = CURRENT_TIMESTAMP
                WHERE saved_movie_id = :savedMovieId
                  AND username = :username
                """;

        int rowsAffected = jdbcClient.sql(sql)
                .param("watchStatus", savedMovie.watchStatus())
                .param("personalRating", savedMovie.personalRating())
                .param("notes", savedMovie.notes())
                .param("savedMovieId", savedMovie.savedMovieId())
                .param("username", savedMovie.username())
                .update();

        return rowsAffected > 0;
    }

    @Override
    public boolean deleteByIdAndUsername(
            Long savedMovieId,
            String username
    ) {
        var sql = """
                DELETE FROM saved_movies
                WHERE saved_movie_id = :savedMovieId
                  AND username = :username
                """;

        int rowsAffected = jdbcClient.sql(sql)
                .param("savedMovieId", savedMovieId)
                .param("username", username)
                .update();

        return rowsAffected > 0;
    }
}
