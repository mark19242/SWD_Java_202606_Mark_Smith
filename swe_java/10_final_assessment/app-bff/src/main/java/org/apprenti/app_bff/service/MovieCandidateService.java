package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.client.TmdbClient;
import org.apprenti.app_bff.client.TmdbGenreMapper;
import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.VibeProfile;
import org.springframework.stereotype.Service;

@Service
public class MovieCandidateService {

    private static final int GENRE_LIMIT = 3;

    private final RecommendationService recommendationService;
    private final TmdbGenreMapper tmdbGenreMapper;
    private final TmdbClient tmdbClient;

    public MovieCandidateService(
            RecommendationService recommendationService,
            TmdbGenreMapper tmdbGenreMapper,
            TmdbClient tmdbClient
    ) {
        this.recommendationService = recommendationService;
        this.tmdbGenreMapper = tmdbGenreMapper;
        this.tmdbClient = tmdbClient;
    }

    public List<MovieResult> findCandidates(
            VibeProfile profile
    ) {
        if (profile == null) {
            throw new IllegalArgumentException(
                    "Vibe profile is required."
            );
        }

        List<Genre> strongestGenres
                = recommendationService
                        .findStrongestPositiveGenres(
                                profile,
                                GENRE_LIMIT
                        );

        List<Integer> tmdbGenreIds
                = strongestGenres.stream()
                        .map(tmdbGenreMapper::toTmdbId)
                        .toList();

        return tmdbClient.discoverMovies(
                tmdbGenreIds,
                profile.getMaxRuntimeMinutes()
        );
    }
}
