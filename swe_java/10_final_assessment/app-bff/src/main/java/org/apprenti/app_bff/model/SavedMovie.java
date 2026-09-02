package org.apprenti.app_bff.model;

import java.time.LocalDateTime;

public record SavedMovie(
        Long savedMovieId,
        String username,
        Long tmdbMovieId,
        String watchStatus,
        Integer personalRating,
        String notes,
        LocalDateTime savedAt,
        LocalDateTime updatedAt
        ) {

}
