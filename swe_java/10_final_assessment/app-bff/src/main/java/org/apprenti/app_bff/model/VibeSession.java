package org.apprenti.app_bff.model;

import java.time.LocalDateTime;

public record VibeSession(
        Long vibeSessionId,
        String username,
        String currentFeeling,
        String desiredFeeling,
        String primaryMovieVibe,
        String secondaryMovieVibe,
        String intensity,
        String runtimePreference,
        LocalDateTime createdAt
        ) {

}
