package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.client.TmdbClient;
import org.apprenti.app_bff.dto.SavedMovieDetails;
import org.apprenti.app_bff.model.SavedMovie;
import org.springframework.stereotype.Service;

@Service
public class SavedMovieDetailsService {

    private final SavedMovieService savedMovieService;
    private final TmdbClient tmdbClient;

    public SavedMovieDetailsService(
            SavedMovieService savedMovieService,
            TmdbClient tmdbClient
    ) {
        this.savedMovieService = savedMovieService;
        this.tmdbClient = tmdbClient;
    }

    public List<SavedMovieDetails> findSavedMovieDetails(
            String username
    ) {
        List<SavedMovie> savedMovies
                = savedMovieService.findSavedMovies(username);

        return savedMovies.stream()
                .map(savedMovie
                        -> new SavedMovieDetails(
                        savedMovie,
                        tmdbClient.findMovieById(
                                savedMovie.tmdbMovieId()
                        )
                )
                )
                .toList();
    }
}
