package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.Recommendation;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcRecommendationDao implements RecommendationDao {

    private final JdbcClient jdbcClient;

    public JdbcRecommendationDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Recommendation create(Recommendation recommendation) {
        var keyHolder = new GeneratedKeyHolder();

        var sql = """
                INSERT INTO recommendations (
                    vibe_session_id,
                    tmdb_movie_id,
                    match_score,
                    rating_bonus,
                    recommendation_rank
                )
                VALUES (
                    :vibeSessionId,
                    :tmdbMovieId,
                    :matchScore,
                    :ratingBonus,
                    :recommendationRank
                )
                """;

        jdbcClient.sql(sql)
                .param("vibeSessionId", recommendation.vibeSessionId())
                .param("tmdbMovieId", recommendation.tmdbMovieId())
                .param("matchScore", recommendation.matchScore())
                .param("ratingBonus", recommendation.ratingBonus())
                .param("recommendationRank", recommendation.recommendationRank())
                .update(keyHolder, "recommendation_id");

        Number generatedId = keyHolder.getKey();

        if (generatedId == null) {
            throw new IllegalStateException(
                    "Unable to create recommendation."
            );
        }

        return findById(generatedId.longValue())
                .orElseThrow(()
                        -> new IllegalStateException(
                        "Created recommendation could not be found."
                )
                );
    }

    @Override
    public Optional<Recommendation> findById(Long recommendationId) {
        var sql = """
            SELECT
                recommendation_id AS "recommendationId",
                vibe_session_id AS "vibeSessionId",
                tmdb_movie_id AS "tmdbMovieId",
                match_score AS "matchScore",
                rating_bonus AS "ratingBonus",
                recommendation_rank AS "recommendationRank",
                created_at AS "createdAt"
            FROM recommendations
            WHERE recommendation_id = :recommendationId
            """;

        return jdbcClient.sql(sql)
                .param("recommendationId", recommendationId)
                .query(Recommendation.class)
                .optional();
    }

    @Override
    public List<Recommendation> findBySessionId(Long vibeSessionId) {
        var sql = """
            SELECT
                recommendation_id AS "recommendationId",
                vibe_session_id AS "vibeSessionId",
                tmdb_movie_id AS "tmdbMovieId",
                match_score AS "matchScore",
                rating_bonus AS "ratingBonus",
                recommendation_rank AS "recommendationRank",
                created_at AS "createdAt"
            FROM recommendations
            WHERE vibe_session_id = :vibeSessionId
            ORDER BY recommendation_rank ASC
            """;

        return jdbcClient.sql(sql)
                .param("vibeSessionId", vibeSessionId)
                .query(Recommendation.class)
                .list();
    }

    @Override
    public boolean deleteById(Long recommendationId) {
        var sql = """
            DELETE FROM recommendations
            WHERE recommendation_id = :recommendationId
            """;

        int rowsAffected = jdbcClient.sql(sql)
                .param("recommendationId", recommendationId)
                .update();

        return rowsAffected > 0;
    }
}
