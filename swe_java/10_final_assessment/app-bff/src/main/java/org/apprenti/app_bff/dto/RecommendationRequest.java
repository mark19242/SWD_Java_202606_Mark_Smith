package org.apprenti.app_bff.dto;

import java.util.List;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Intensity;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.RuntimePreference;

public record RecommendationRequest(
        CurrentFeeling currentFeeling,
        DesiredFeeling desiredFeeling,
        List<MovieVibe> movieVibes,
        Intensity intensity,
        RuntimePreference runtimePreference
        ) {

}
