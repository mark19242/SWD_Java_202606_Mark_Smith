package org.apprenti.app_bff.service;

import java.util.List;
import java.util.Set;

import org.apprenti.app_bff.dao.SavedMovieDao;
import org.apprenti.app_bff.model.SavedMovie;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieService {

    private static final Set<String> VALID_WATCH_STATUSES
            = Set.of(
                    "WANT_TO_WATCH",
                    "WATCHING",
                    "WATCHED"
            );

    private final SavedMovieDao savedMovieDao;

    public SavedMovieService(
            SavedMovieDao savedMovieDao
    ) {
        this.savedMovieDao = savedMovieDao;
    }

    public SavedMovie saveMovie(
            String username,
            Long tmdbMovieId
    ) {
        validateUsername(username);

        if (tmdbMovieId == null || tmdbMovieId <= 0) {
            throw new IllegalArgumentException(
                    "TMDB movie ID must be positive."
            );
        }

        SavedMovie savedMovie
                = new SavedMovie(
                        null,
                        username,
                        tmdbMovieId,
                        "WANT_TO_WATCH",
                        null,
                        null,
                        null,
                        null
                );

        return savedMovieDao.create(savedMovie);
    }

    public List<SavedMovie> findSavedMovies(
            String username
    ) {
        validateUsername(username);

        return savedMovieDao.findByUsername(
                username
        );
    }

    public SavedMovie updateSavedMovie(
            String username,
            Long savedMovieId,
            String watchStatus,
            Integer personalRating,
            String notes
    ) {
        validateUsername(username);

        SavedMovie existing
                = savedMovieDao.findById(savedMovieId)
                        .orElseThrow(
                                ()
                                -> new IllegalArgumentException(
                                        "Saved movie not found."
                                )
                        );

        if (!existing.username().equals(username)) {
            throw new IllegalArgumentException(
                    "Saved movie does not belong to this user."
            );
        }

        validateWatchStatus(watchStatus);
        validatePersonalRating(personalRating);
        validateNotes(notes);

        SavedMovie updated
                = new SavedMovie(
                        existing.savedMovieId(),
                        existing.username(),
                        existing.tmdbMovieId(),
                        watchStatus,
                        personalRating,
                        notes,
                        existing.savedAt(),
                        existing.updatedAt()
                );

        boolean success
                = savedMovieDao.update(updated);

        if (!success) {
            throw new IllegalStateException(
                    "Saved movie could not be updated."
            );
        }

        return savedMovieDao.findById(
                savedMovieId
        )
                .orElseThrow(
                        ()
                        -> new IllegalStateException(
                                "Updated saved movie could not be found."
                        )
                );
    }

    public boolean deleteSavedMovie(
            String username,
            Long savedMovieId
    ) {
        validateUsername(username);

        if (savedMovieId == null) {
            throw new IllegalArgumentException(
                    "Saved movie ID is required."
            );
        }

        return savedMovieDao
                .deleteByIdAndUsername(
                        savedMovieId,
                        username
                );
    }

    private void validateUsername(
            String username
    ) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username is required."
            );
        }
    }

    private void validateWatchStatus(
            String watchStatus
    ) {
        if (watchStatus == null
                || !VALID_WATCH_STATUSES.contains(
                        watchStatus
                )) {

            throw new IllegalArgumentException(
                    "Invalid watch status."
            );
        }
    }

    private void validatePersonalRating(
            Integer personalRating
    ) {
        if (personalRating != null
                && (personalRating < 1
                || personalRating > 5)) {

            throw new IllegalArgumentException(
                    "Personal rating must be between 1 and 5."
            );
        }
    }

    private void validateNotes(
            String notes
    ) {
        if (notes != null
                && notes.length() > 2000) {

            throw new IllegalArgumentException(
                    "Notes cannot exceed 2000 characters."
            );
        }
    }
}
