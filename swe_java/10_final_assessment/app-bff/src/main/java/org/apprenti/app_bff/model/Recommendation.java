package org.apprenti.app_bff.model;

import java.time.LocalDateTime;

public record Recommendation(
        Long recommendationId,
        Long vibeSessionId,
        Long tmdbMovieId,
        Integer matchScore,
        Integer ratingBonus,
        Integer recommendationRank,
        LocalDateTime createdAt
        ) {

}
