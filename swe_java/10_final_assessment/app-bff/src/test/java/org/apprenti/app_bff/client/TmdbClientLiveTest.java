package org.apprenti.app_bff.client;

import java.util.List;

import org.apprenti.app_bff.dto.MovieResult;
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
class TmdbClientLiveTest {

    @Autowired
    private TmdbClient tmdbClient;

    @Test
    void discoverMoviesShouldReturnRealTmdbMovies() {

        List<MovieResult> movies
                = tmdbClient.discoverMovies(
                        List.of(
                                35,
                                10749
                        ),
                        120
                );

        assertNotNull(movies);
        assertFalse(movies.isEmpty());

        MovieResult firstMovie
                = movies.get(0);

        assertNotNull(firstMovie.id());
        assertNotNull(firstMovie.title());
        assertFalse(firstMovie.title().isBlank());
        assertNotNull(firstMovie.genreIds());
        assertNotNull(firstMovie.voteAverage());

        System.out.println(
                "TMDB returned "
                + movies.size()
                + " movies."
        );

        System.out.println(
                "First movie: "
                + firstMovie.title()
        );
    }
}
