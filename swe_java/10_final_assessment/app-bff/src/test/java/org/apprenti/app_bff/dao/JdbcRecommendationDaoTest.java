package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apprenti.app_bff.model.Recommendation;
import org.apprenti.app_bff.model.VibeSession;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class JdbcRecommendationDaoTest {

    @Autowired
    private RecommendationDao recommendationDao;

    @Autowired
    private VibeSessionDao vibeSessionDao;

    @Autowired
    private JdbcClient jdbcClient;

    private String testUsername;
    private Long vibeSessionId;

    @BeforeEach
    void setUp() {

        testUsername
                = "recommendation_test_"
                + UUID.randomUUID()
                        .toString()
                        .substring(0, 8);

        var userSql = """
                INSERT INTO users (
                    username,
                    password,
                    enabled
                )
                VALUES (
                    :username,
                    :password,
                    true
                )
                """;

        jdbcClient.sql(userSql)
                .param("username", testUsername)
                .param("password", "{noop}test-password")
                .update();

        VibeSession vibeSession
                = new VibeSession(
                        null,
                        testUsername,
                        "STRESSED",
                        "MAKE_ME_LAUGH",
                        "LIGHT_AND_FUNNY",
                        "ROMANTIC",
                        "CHILL",
                        "STANDARD",
                        null
                );

        VibeSession createdSession
                = vibeSessionDao.create(vibeSession);

        vibeSessionId
                = createdSession.vibeSessionId();
    }

    @Test
    void createShouldSaveRecommendation() {

        Recommendation recommendation
                = new Recommendation(
                        null,
                        vibeSessionId,
                        550L,
                        21,
                        2,
                        1,
                        null
                );

        Recommendation created
                = recommendationDao.create(recommendation);

        assertNotNull(created);
        assertNotNull(created.recommendationId());
        assertNotNull(created.createdAt());

        assertEquals(
                vibeSessionId,
                created.vibeSessionId()
        );

        assertEquals(
                550L,
                created.tmdbMovieId()
        );

        assertEquals(
                21,
                created.matchScore()
        );

        assertEquals(
                2,
                created.ratingBonus()
        );

        assertEquals(
                1,
                created.recommendationRank()
        );
    }

    @Test
    void findByIdShouldReturnExistingRecommendation() {

        Recommendation created
                = recommendationDao.create(
                        createTestRecommendation(
                                550L,
                                21,
                                2,
                                1
                        )
                );

        Optional<Recommendation> found
                = recommendationDao.findById(
                        created.recommendationId()
                );

        assertTrue(found.isPresent());

        assertEquals(
                created.recommendationId(),
                found.get().recommendationId()
        );

        assertEquals(
                vibeSessionId,
                found.get().vibeSessionId()
        );

        assertEquals(
                550L,
                found.get().tmdbMovieId()
        );
    }

    @Test
    void findByIdShouldReturnEmptyWhenRecommendationDoesNotExist() {

        Optional<Recommendation> found
                = recommendationDao.findById(
                        999999999L
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void findBySessionIdShouldReturnRecommendationsInRankOrder() {

        recommendationDao.create(
                createTestRecommendation(
                        680L,
                        18,
                        1,
                        2
                )
        );

        recommendationDao.create(
                createTestRecommendation(
                        550L,
                        22,
                        2,
                        1
                )
        );

        recommendationDao.create(
                createTestRecommendation(
                        13L,
                        16,
                        0,
                        3
                )
        );

        List<Recommendation> recommendations
                = recommendationDao.findBySessionId(
                        vibeSessionId
                );

        assertEquals(
                3,
                recommendations.size()
        );

        assertEquals(
                1,
                recommendations.get(0)
                        .recommendationRank()
        );

        assertEquals(
                2,
                recommendations.get(1)
                        .recommendationRank()
        );

        assertEquals(
                3,
                recommendations.get(2)
                        .recommendationRank()
        );

        assertEquals(
                550L,
                recommendations.get(0)
                        .tmdbMovieId()
        );

        assertEquals(
                680L,
                recommendations.get(1)
                        .tmdbMovieId()
        );

        assertEquals(
                13L,
                recommendations.get(2)
                        .tmdbMovieId()
        );
    }

    @Test
    void deleteByIdShouldDeleteRecommendation() {

        Recommendation created
                = recommendationDao.create(
                        createTestRecommendation(
                                550L,
                                21,
                                2,
                                1
                        )
                );

        boolean deleted
                = recommendationDao.deleteById(
                        created.recommendationId()
                );

        assertTrue(deleted);

        Optional<Recommendation> found
                = recommendationDao.findById(
                        created.recommendationId()
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void deleteByIdShouldReturnFalseWhenRecommendationDoesNotExist() {

        boolean deleted
                = recommendationDao.deleteById(
                        999999999L
                );

        assertFalse(deleted);
    }

    private Recommendation createTestRecommendation(
            Long tmdbMovieId,
            Integer matchScore,
            Integer ratingBonus,
            Integer recommendationRank
    ) {

        return new Recommendation(
                null,
                vibeSessionId,
                tmdbMovieId,
                matchScore,
                ratingBonus,
                recommendationRank,
                null
        );
    }
}
