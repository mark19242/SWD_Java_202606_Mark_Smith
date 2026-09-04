package org.apprenti.app_bff.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.dao.SavedMovieDao;
import org.apprenti.app_bff.model.SavedMovie;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.ArgumentMatchers.any;
import org.mockito.Mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;

class SavedMovieServiceTest {

    @Mock
    private SavedMovieDao savedMovieDao;

    private SavedMovieService savedMovieService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        savedMovieService
                = new SavedMovieService(
                        savedMovieDao
                );
    }

    @Test
    void shouldSaveMovieWithDefaultWatchStatus() {

        SavedMovie created
                = new SavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null,
                        LocalDateTime.now(),
                        LocalDateTime.now()
                );

        when(
                savedMovieDao.create(
                        any(SavedMovie.class)
                )
        ).thenReturn(created);

        SavedMovie result
                = savedMovieService.saveMovie(
                        "testuser",
                        12345L
                );

        assertEquals(
                "testuser",
                result.username()
        );

        assertEquals(
                12345L,
                result.tmdbMovieId()
        );

        assertEquals(
                "WANT_TO_WATCH",
                result.watchStatus()
        );

        verify(savedMovieDao)
                .create(
                        any(SavedMovie.class)
                );
    }

    @Test
    void shouldFindMoviesForUser() {

        SavedMovie movie
                = createSavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null
                );

        when(
                savedMovieDao.findByUsername(
                        "testuser"
                )
        ).thenReturn(
                List.of(movie)
        );

        List<SavedMovie> result
                = savedMovieService.findSavedMovies(
                        "testuser"
                );

        assertEquals(
                1,
                result.size()
        );

        assertEquals(
                "testuser",
                result.get(0).username()
        );
    }

    @Test
    void shouldUpdateSavedMovie() {

        SavedMovie existing
                = createSavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null
                );

        SavedMovie updated
                = createSavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WATCHED",
                        5,
                        "Loved it."
                );

        when(
                savedMovieDao.findById(1L)
        ).thenReturn(
                Optional.of(existing),
                Optional.of(updated)
        );

        when(
                savedMovieDao.update(
                        any(SavedMovie.class)
                )
        ).thenReturn(true);

        SavedMovie result
                = savedMovieService.updateSavedMovie(
                        "testuser",
                        1L,
                        "WATCHED",
                        5,
                        "Loved it."
                );

        assertEquals(
                "WATCHED",
                result.watchStatus()
        );

        assertEquals(
                5,
                result.personalRating()
        );

        assertEquals(
                "Loved it.",
                result.notes()
        );
    }

    @Test
    void shouldRejectUpdateForAnotherUser() {

        SavedMovie existing
                = createSavedMovie(
                        1L,
                        "differentuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null
                );

        when(
                savedMovieDao.findById(1L)
        ).thenReturn(
                Optional.of(existing)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()
                -> savedMovieService.updateSavedMovie(
                        "testuser",
                        1L,
                        "WATCHED",
                        5,
                        "Not mine."
                )
        );

        verify(
                savedMovieDao,
                never()
        ).update(
                any(SavedMovie.class)
        );
    }

    @Test
    void shouldRejectInvalidPersonalRating() {

        SavedMovie existing
                = createSavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null
                );

        when(
                savedMovieDao.findById(1L)
        ).thenReturn(
                Optional.of(existing)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()
                -> savedMovieService.updateSavedMovie(
                        "testuser",
                        1L,
                        "WATCHED",
                        6,
                        null
                )
        );
    }

    @Test
    void shouldRejectInvalidWatchStatus() {

        SavedMovie existing
                = createSavedMovie(
                        1L,
                        "testuser",
                        12345L,
                        "WANT_TO_WATCH",
                        null,
                        null
                );

        when(
                savedMovieDao.findById(1L)
        ).thenReturn(
                Optional.of(existing)
        );

        assertThrows(
                IllegalArgumentException.class,
                ()
                -> savedMovieService.updateSavedMovie(
                        "testuser",
                        1L,
                        "NOT_A_REAL_STATUS",
                        4,
                        null
                )
        );
    }

    @Test
    void shouldDeleteOnlyUsersSavedMovie() {

        when(
                savedMovieDao
                        .deleteByIdAndUsername(
                                1L,
                                "testuser"
                        )
        ).thenReturn(true);

        boolean result
                = savedMovieService.deleteSavedMovie(
                        "testuser",
                        1L
                );

        assertTrue(result);

        verify(savedMovieDao)
                .deleteByIdAndUsername(
                        1L,
                        "testuser"
                );
    }

    private SavedMovie createSavedMovie(
            Long savedMovieId,
            String username,
            Long tmdbMovieId,
            String watchStatus,
            Integer personalRating,
            String notes
    ) {
        return new SavedMovie(
                savedMovieId,
                username,
                tmdbMovieId,
                watchStatus,
                personalRating,
                notes,
                LocalDateTime.now(),
                LocalDateTime.now()
        );
    }
}
