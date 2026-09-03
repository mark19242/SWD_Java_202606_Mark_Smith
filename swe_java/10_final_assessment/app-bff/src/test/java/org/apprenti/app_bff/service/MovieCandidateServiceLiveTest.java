package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Intensity;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.RuntimePreference;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfSystemProperty(
        named = "runLiveTmdbTest",
        matches = "true"
)
class MovieCandidateServiceLiveTest {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private MovieCandidateService movieCandidateService;

    @Test
    void fullVibeProfileShouldReturnRealMovieCandidates() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(
                                MovieVibe.LIGHT_AND_FUNNY,
                                MovieVibe.ROMANTIC
                        ),
                        Intensity.CHILL,
                        RuntimePreference.STANDARD
                );

        List<MovieResult> movies
                = movieCandidateService.findCandidates(profile);

        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        MovieResult firstMovie = movies.get(0);

        assertNotNull(firstMovie.id());
        assertNotNull(firstMovie.title());
        assertFalse(firstMovie.title().isBlank());

        System.out.println(
                "ReelVibe candidate count: "
                + movies.size()
        );

        movies.stream()
                .limit(5)
                .forEach(movie
                        -> System.out.println(
                        movie.title()
                        + " | Genres: "
                        + movie.genreIds()
                        + " | Rating: "
                        + movie.voteAverage()
                )
                );
    }
}
