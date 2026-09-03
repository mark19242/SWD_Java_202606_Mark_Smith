package org.apprenti.app_bff.service;

import java.util.Comparator;
import java.util.List;

import org.apprenti.app_bff.client.TmdbGenreMapper;
import org.apprenti.app_bff.dto.MovieResult;
import org.apprenti.app_bff.dto.ScoredMovie;
import org.apprenti.app_bff.model.VibeProfile;
import org.springframework.stereotype.Service;

@Service
public class MovieScoringService {

    private final TmdbGenreMapper tmdbGenreMapper;

    public MovieScoringService(
            TmdbGenreMapper tmdbGenreMapper
    ) {
        this.tmdbGenreMapper = tmdbGenreMapper;
    }

    public ScoredMovie scoreMovie(
            MovieResult movie,
            VibeProfile profile
    ) {
        if (movie == null) {
            throw new IllegalArgumentException(
                    "Movie is required."
            );
        }

        if (profile == null) {
            throw new IllegalArgumentException(
                    "Vibe profile is required."
            );
        }

        int vibeScore = 0;

        if (movie.genreIds() != null) {
            vibeScore
                    = movie.genreIds()
                            .stream()
                            .map(tmdbGenreMapper::fromTmdbId)
                            .flatMap(optionalGenre
                                    -> optionalGenre.stream()
                            )
                            .mapToInt(
                                    profile::getGenreWeight
                            )
                            .sum();
        }

        int ratingBonus
                = calculateRatingBonus(
                        movie.voteAverage()
                );

        return new ScoredMovie(
                movie,
                vibeScore,
                ratingBonus,
                vibeScore + ratingBonus
        );
    }

    public List<ScoredMovie> scoreAndRankMovies(
            List<MovieResult> movies,
            VibeProfile profile
    ) {
        if (movies == null) {
            throw new IllegalArgumentException(
                    "Movies are required."
            );
        }

        return movies.stream()
                .map(movie
                        -> scoreMovie(
                        movie,
                        profile
                )
                )
                .sorted(
                        Comparator
                                .comparingInt(
                                        ScoredMovie::finalScore
                                )
                                .reversed()
                                .thenComparing(
                                        Comparator.comparingInt(
                                                ScoredMovie::vibeScore
                                        ).reversed()
                                )
                )
                .toList();
    }

    private int calculateRatingBonus(
            Double voteAverage
    ) {
        if (voteAverage == null) {
            return 0;
        }

        if (voteAverage >= 8.0) {
            return 2;
        }

        if (voteAverage >= 7.0) {
            return 1;
        }

        return 0;
    }
}
