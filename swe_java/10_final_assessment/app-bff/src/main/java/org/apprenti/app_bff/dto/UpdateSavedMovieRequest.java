package org.apprenti.app_bff.dto;

public record UpdateSavedMovieRequest(
        String watchStatus,
        Integer personalRating,
        String notes
        ) {

}
