package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.apprenti.app_bff.model.SavedMovie;
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
class JdbcSavedMovieDaoTest {

    @Autowired
    private SavedMovieDao savedMovieDao;

    @Autowired
    private JdbcClient jdbcClient;

    private String testUsername;

    @BeforeEach
    void setUp() {
        testUsername
                = "saved_movie_test_"
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
    void createShouldSaveMovie() {
        SavedMovie savedMovie
                = createTestSavedMovie(550L);

        SavedMovie created
                = savedMovieDao.create(savedMovie);

        assertNotNull(created);
        assertNotNull(created.savedMovieId());
        assertNotNull(created.savedAt());
        assertNotNull(created.updatedAt());

        assertEquals(
                testUsername,
                created.username()
        );

        assertEquals(
                550L,
                created.tmdbMovieId()
        );

        assertEquals(
                "WANT_TO_WATCH",
                created.watchStatus()
        );

        assertEquals(
                4,
                created.personalRating()
        );

        assertEquals(
                "Looks like a great fit.",
                created.notes()
        );
    }

    @Test
    void findByIdShouldReturnExistingSavedMovie() {
        SavedMovie created
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        created.savedMovieId()
                );

        assertTrue(found.isPresent());

        assertEquals(
                created.savedMovieId(),
                found.get().savedMovieId()
        );

        assertEquals(
                testUsername,
                found.get().username()
        );

        assertEquals(
                550L,
                found.get().tmdbMovieId()
        );
    }

    @Test
    void findByIdShouldReturnEmptyWhenMovieDoesNotExist() {
        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        999999999L
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void findByUsernameShouldReturnUsersSavedMovies() {
        SavedMovie first
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        SavedMovie second
                = savedMovieDao.create(
                        createTestSavedMovie(680L)
                );

        List<SavedMovie> savedMovies
                = savedMovieDao.findByUsername(
                        testUsername
                );

        assertEquals(
                2,
                savedMovies.size()
        );

        assertTrue(
                savedMovies.stream()
                        .anyMatch(movie
                                -> movie.savedMovieId()
                                .equals(
                                        first.savedMovieId()
                                )
                        )
        );

        assertTrue(
                savedMovies.stream()
                        .anyMatch(movie
                                -> movie.savedMovieId()
                                .equals(
                                        second.savedMovieId()
                                )
                        )
        );
    }

    @Test
    void updateShouldModifyOwnedSavedMovie() {
        SavedMovie created
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        SavedMovie updatedMovie
                = new SavedMovie(
                        created.savedMovieId(),
                        testUsername,
                        created.tmdbMovieId(),
                        "WATCHED",
                        5,
                        "Loved this movie.",
                        created.savedAt(),
                        created.updatedAt()
                );

        boolean updated
                = savedMovieDao.update(updatedMovie);

        assertTrue(updated);

        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        created.savedMovieId()
                );

        assertTrue(found.isPresent());

        assertEquals(
                "WATCHED",
                found.get().watchStatus()
        );

        assertEquals(
                5,
                found.get().personalRating()
        );

        assertEquals(
                "Loved this movie.",
                found.get().notes()
        );
    }

    @Test
    void updateShouldNotModifyAnotherUsersSavedMovie() {
        SavedMovie created
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        SavedMovie unauthorizedUpdate
                = new SavedMovie(
                        created.savedMovieId(),
                        "wrong_user",
                        created.tmdbMovieId(),
                        "WATCHED",
                        1,
                        "This should not update.",
                        created.savedAt(),
                        created.updatedAt()
                );

        boolean updated
                = savedMovieDao.update(
                        unauthorizedUpdate
                );

        assertFalse(updated);

        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        created.savedMovieId()
                );

        assertTrue(found.isPresent());

        assertEquals(
                "WANT_TO_WATCH",
                found.get().watchStatus()
        );

        assertEquals(
                4,
                found.get().personalRating()
        );
    }

    @Test
    void deleteByIdAndUsernameShouldDeleteOwnedMovie() {
        SavedMovie created
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        boolean deleted
                = savedMovieDao.deleteByIdAndUsername(
                        created.savedMovieId(),
                        testUsername
                );

        assertTrue(deleted);

        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        created.savedMovieId()
                );

        assertTrue(found.isEmpty());
    }

    @Test
    void deleteByIdAndUsernameShouldNotDeleteAnotherUsersMovie() {
        SavedMovie created
                = savedMovieDao.create(
                        createTestSavedMovie(550L)
                );

        boolean deleted
                = savedMovieDao.deleteByIdAndUsername(
                        created.savedMovieId(),
                        "wrong_user"
                );

        assertFalse(deleted);

        Optional<SavedMovie> found
                = savedMovieDao.findById(
                        created.savedMovieId()
                );

        assertTrue(found.isPresent());
    }

    private SavedMovie createTestSavedMovie(
            Long tmdbMovieId
    ) {
        return new SavedMovie(
                null,
                testUsername,
                tmdbMovieId,
                "WANT_TO_WATCH",
                4,
                "Looks like a great fit.",
                null,
                null
        );
    }
}
