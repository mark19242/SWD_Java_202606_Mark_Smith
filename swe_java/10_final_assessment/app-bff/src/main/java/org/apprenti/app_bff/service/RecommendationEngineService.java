package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.dto.ScoredMovie;
import org.apprenti.app_bff.model.VibeProfile;
import org.springframework.stereotype.Service;

@Service
public class RecommendationEngineService {

    private static final int RESULT_LIMIT = 10;

    private final MovieCandidateService movieCandidateService;
    private final MovieScoringService movieScoringService;

    public RecommendationEngineService(
            MovieCandidateService movieCandidateService,
            MovieScoringService movieScoringService
    ) {
        this.movieCandidateService = movieCandidateService;
        this.movieScoringService = movieScoringService;
    }

    public List<ScoredMovie> recommend(
            VibeProfile profile
    ) {
        if (profile == null) {
            throw new IllegalArgumentException(
                    "Vibe profile is required."
            );
        }

        List<MovieResult> candidates
                = movieCandidateService.findCandidates(
                        profile
                );

        return movieScoringService
                .scoreAndRankMovies(
                        candidates,
                        profile
                )
                .stream()
                .limit(RESULT_LIMIT)
                .toList();
    }
}
