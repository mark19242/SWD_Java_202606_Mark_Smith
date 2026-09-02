package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
class JdbcVibeSessionDaoTest {

    @Autowired
    private VibeSessionDao vibeSessionDao;

    @Autowired
    private JdbcClient jdbcClient;

    private String testUsername;

    @BeforeEach
    void setUp() {
        testUsername
                = "reelvibe_test_"
                + UUID.randomUUID()
                        .toString()
                        .substring(0, 8);

        var sql = """
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

        jdbcClient.sql(sql)
                .param("username", testUsername)
                .param("password", "{noop}test-password")
                .update();
    }

    @Test
    void createShouldSaveVibeSession() {

        VibeSession vibeSession = new VibeSession(
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

        VibeSession created
                = vibeSessionDao.create(vibeSession);

        assertNotNull(created);
        assertNotNull(created.vibeSessionId());
        assertNotNull(created.createdAt());

        assertEquals(
                testUsername,
                created.username()
        );

        assertEquals(
                "STRESSED",
                created.currentFeeling()
        );

        assertEquals(
                "MAKE_ME_LAUGH",
                created.desiredFeeling()
        );

        assertEquals(
                "LIGHT_AND_FUNNY",
                created.primaryMovieVibe()
        );

        assertEquals(
                "ROMANTIC",
                created.secondaryMovieVibe()
        );

        assertEquals(
                "CHILL",
                created.intensity()
        );

        assertEquals(
                "STANDARD",
                created.runtimePreference()
        );
    }

    @Test
    void findByIdShouldReturnExistingVibeSession() {

        VibeSession created
                = vibeSessionDao.create(
                        createTestVibeSession()
                );

        Optional<VibeSession> found
                = vibeSessionDao.findById(
                        created.vibeSessionId()
                );

        assertTrue(found.isPresent());

        assertEquals(
                created.vibeSessionId(),
                found.get().vibeSessionId()
        );

        assertEquals(
                testUsername,
                found.get().username()
        );

        assertEquals(
                "STRESSED",
                found.get().currentFeeling()
        );
    }

    @Test
    void findByIdShouldReturnEmptyWhenSessionDoesNotExist() {

        Optional<VibeSession> found
                = vibeSessionDao.findById(
                        999999999L
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void findByUsernameShouldReturnUsersVibeSessions() {

        VibeSession first
                = vibeSessionDao.create(
                        createTestVibeSession()
                );

        VibeSession second
                = vibeSessionDao.create(
                        new VibeSession(
                                null,
                                testUsername,
                                "HAPPY",
                                "GET_ME_EXCITED",
                                "ACTION_PACKED",
                                null,
                                "BRING_IT_ON",
                                "EXTENDED",
                                null
                        )
                );

        List<VibeSession> sessions
                = vibeSessionDao.findByUsername(
                        testUsername
                );

        assertEquals(
                2,
                sessions.size()
        );

        assertTrue(
                sessions.stream()
                        .anyMatch(session
                                -> session.vibeSessionId()
                                .equals(
                                        first.vibeSessionId()
                                )
                        )
        );

        assertTrue(
                sessions.stream()
                        .anyMatch(session
                                -> session.vibeSessionId()
                                .equals(
                                        second.vibeSessionId()
                                )
                        )
        );
    }

    @Test
    void deleteByIdAndUsernameShouldDeleteOwnedSession() {

        VibeSession created
                = vibeSessionDao.create(
                        createTestVibeSession()
                );

        boolean deleted
                = vibeSessionDao
                        .deleteByIdAndUsername(
                                created.vibeSessionId(),
                                testUsername
                        );

        assertTrue(deleted);

        Optional<VibeSession> found
                = vibeSessionDao.findById(
                        created.vibeSessionId()
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void deleteByIdAndUsernameShouldNotDeleteAnotherUsersSession() {

        VibeSession created
                = vibeSessionDao.create(
                        createTestVibeSession()
                );

        boolean deleted
                = vibeSessionDao
                        .deleteByIdAndUsername(
                                created.vibeSessionId(),
                                "wrong_user"
                        );

        assertFalse(deleted);

        Optional<VibeSession> found
                = vibeSessionDao.findById(
                        created.vibeSessionId()
                );

        assertTrue(found.isPresent());
    }

    private VibeSession createTestVibeSession() {

        return new VibeSession(
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
    }
}
