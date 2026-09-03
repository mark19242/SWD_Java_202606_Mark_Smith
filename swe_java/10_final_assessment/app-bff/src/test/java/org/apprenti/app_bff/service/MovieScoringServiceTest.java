package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.client.TmdbGenreMapper;
import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.dto.ScoredMovie;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class MovieScoringServiceTest {

    private final MovieScoringService movieScoringService
            = new MovieScoringService(
                    new TmdbGenreMapper()
            );

    @Test
    void shouldCalculateVibeScoreAndRatingBonus() {

        VibeProfile profile = createExampleProfile();

        MovieResult movie
                = createMovie(
                        1L,
                        "Movie A",
                        List.of(
                                35,
                                10749
                        ),
                        7.8
                );

        ScoredMovie scored
                = movieScoringService.scoreMovie(
                        movie,
                        profile
                );

        assertEquals(
                21,
                scored.vibeScore()
        );

        assertEquals(
                1,
                scored.ratingBonus()
        );

        assertEquals(
                22,
                scored.finalScore()
        );
    }

    @Test
    void ratingEightOrHigherShouldReceiveTwoPointBonus() {

        VibeProfile profile = new VibeProfile();

        MovieResult movie
                = createMovie(
                        2L,
                        "Highly Rated Movie",
                        List.of(35),
                        8.4
                );

        ScoredMovie scored
                = movieScoringService.scoreMovie(
                        movie,
                        profile
                );

        assertEquals(
                2,
                scored.ratingBonus()
        );
    }

    @Test
    void ratingBelowSevenShouldReceiveNoBonus() {

        VibeProfile profile = new VibeProfile();

        MovieResult movie
                = createMovie(
                        3L,
                        "Lower Rated Movie",
                        List.of(35),
                        6.9
                );

        ScoredMovie scored
                = movieScoringService.scoreMovie(
                        movie,
                        profile
                );

        assertEquals(
                0,
                scored.ratingBonus()
        );
    }

    @Test
    void unsupportedTmdbGenreShouldBeIgnored() {

        VibeProfile profile = createExampleProfile();

        MovieResult movie
                = createMovie(
                        4L,
                        "Unknown Genre Movie",
                        List.of(
                                35,
                                999999
                        ),
                        7.5
                );

        ScoredMovie scored
                = movieScoringService.scoreMovie(
                        movie,
                        profile
                );

        assertEquals(
                13,
                scored.vibeScore()
        );

        assertEquals(
                14,
                scored.finalScore()
        );
    }

    @Test
    void shouldRankMoviesByBestReelVibeMatch() {

        VibeProfile profile = createExampleProfile();

        MovieResult movieA
                = createMovie(
                        1L,
                        "Movie A",
                        List.of(
                                35,
                                10749
                        ),
                        7.8
                );

        MovieResult movieB
                = createMovie(
                        2L,
                        "Movie B",
                        List.of(
                                35,
                                16
                        ),
                        8.2
                );

        MovieResult movieC
                = createMovie(
                        3L,
                        "Movie C",
                        List.of(
                                10749,
                                18
                        ),
                        6.9
                );

        List<ScoredMovie> ranked
                = movieScoringService
                        .scoreAndRankMovies(
                                List.of(
                                        movieC,
                                        movieB,
                                        movieA
                                ),
                                profile
                        );

        assertEquals(
                "Movie A",
                ranked.get(0).movie().title()
        );

        assertEquals(
                22,
                ranked.get(0).finalScore()
        );

        assertEquals(
                "Movie B",
                ranked.get(1).movie().title()
        );

        assertEquals(
                21,
                ranked.get(1).finalScore()
        );

        assertEquals(
                "Movie C",
                ranked.get(2).movie().title()
        );

        assertEquals(
                9,
                ranked.get(2).finalScore()
        );
    }

    private VibeProfile createExampleProfile() {

        VibeProfile profile = new VibeProfile();

        profile.adjustGenreWeight(
                Genre.COMEDY,
                13
        );

        profile.adjustGenreWeight(
                Genre.ROMANCE,
                8
        );

        profile.adjustGenreWeight(
                Genre.ANIMATION,
                6
        );

        profile.adjustGenreWeight(
                Genre.FAMILY,
                5
        );

        profile.adjustGenreWeight(
                Genre.DRAMA,
                1
        );

        return profile;
    }

    private MovieResult createMovie(
            Long id,
            String title,
            List<Integer> genreIds,
            Double voteAverage
    ) {

        return new MovieResult(
                id,
                title,
                "Test overview",
                "/poster.jpg",
                genreIds,
                voteAverage,
                1000,
                "2026-01-01"
        );
    }
}
