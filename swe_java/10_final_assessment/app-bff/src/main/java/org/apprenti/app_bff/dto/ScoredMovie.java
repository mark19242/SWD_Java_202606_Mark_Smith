package org.apprenti.app_bff.dto;

public record ScoredMovie(
        MovieResult movie,
        int vibeScore,
        int ratingBonus,
        int finalScore
        ) {

}
