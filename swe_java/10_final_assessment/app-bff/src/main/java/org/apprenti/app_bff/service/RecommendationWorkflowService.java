package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.dao.RecommendationDao;
import org.apprenti.app_bff.dao.VibeSessionDao;
import org.apprenti.app_bff.dto.RecommendationRequest;
import org.apprenti.app_bff.dto.RecommendationResponse;
import org.apprenti.app_bff.dto.ScoredMovie;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.Recommendation;
import org.apprenti.app_bff.model.VibeProfile;
import org.apprenti.app_bff.model.VibeSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RecommendationWorkflowService {

    private final RecommendationService recommendationService;
    private final RecommendationEngineService recommendationEngineService;
    private final VibeSessionDao vibeSessionDao;
    private final RecommendationDao recommendationDao;

    public RecommendationWorkflowService(
            RecommendationService recommendationService,
            RecommendationEngineService recommendationEngineService,
            VibeSessionDao vibeSessionDao,
            RecommendationDao recommendationDao
    ) {
        this.recommendationService = recommendationService;
        this.recommendationEngineService = recommendationEngineService;
        this.vibeSessionDao = vibeSessionDao;
        this.recommendationDao = recommendationDao;
    }

    @Transactional
    public RecommendationResponse generateRecommendations(
            String username,
            RecommendationRequest request
    ) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException(
                    "Username is required."
            );
        }

        if (request == null) {
            throw new IllegalArgumentException(
                    "Recommendation request is required."
            );
        }

        List<MovieVibe> movieVibes
                = request.movieVibes();

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        request.currentFeeling(),
                        request.desiredFeeling(),
                        movieVibes,
                        request.intensity(),
                        request.runtimePreference()
                );

        List<ScoredMovie> scoredMovies
                = recommendationEngineService.recommend(
                        profile
                );

        String primaryMovieVibe
                = movieVibes.get(0).name();

        String secondaryMovieVibe
                = movieVibes.size() > 1
                ? movieVibes.get(1).name()
                : null;

        VibeSession vibeSession
                = vibeSessionDao.create(
                        new VibeSession(
                                null,
                                username,
                                request.currentFeeling().name(),
                                request.desiredFeeling().name(),
                                primaryMovieVibe,
                                secondaryMovieVibe,
                                request.intensity().name(),
                                request.runtimePreference().name(),
                                null
                        )
                );

        for (int i = 0; i < scoredMovies.size(); i++) {

            ScoredMovie scoredMovie
                    = scoredMovies.get(i);

            recommendationDao.create(
                    new Recommendation(
                            null,
                            vibeSession.vibeSessionId(),
                            scoredMovie.movie().id(),
                            scoredMovie.vibeScore(),
                            scoredMovie.ratingBonus(),
                            i + 1,
                            null
                    )
            );
        }

        return new RecommendationResponse(
                vibeSession.vibeSessionId(),
                scoredMovies
        );
    }
}
