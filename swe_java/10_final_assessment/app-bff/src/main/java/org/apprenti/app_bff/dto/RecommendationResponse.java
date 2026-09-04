package org.apprenti.app_bff.dto;

import java.util.List;

public record RecommendationResponse(
        Long vibeSessionId,
        List<ScoredMovie> recommendations
        ) {

}
