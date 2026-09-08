package org.apprenti.app_bff.dto;

import org.apprenti.app_bff.model.SavedMovie;

public record SavedMovieDetails(
        SavedMovie savedMovie,
        MovieResult movie
        ) {

}
