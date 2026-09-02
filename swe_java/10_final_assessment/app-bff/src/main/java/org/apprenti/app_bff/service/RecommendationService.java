package org.apprenti.app_bff.service;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.VibeProfile;
import org.springframework.stereotype.Service;

@Service
public class RecommendationService {

    public VibeProfile buildVibeProfile(CurrentFeeling currentFeeling) {

        if (currentFeeling == null) {
            throw new IllegalArgumentException(
                    "Current feeling is required."
            );
        }

        VibeProfile profile = new VibeProfile();

        applyCurrentFeelingRules(
                profile,
                currentFeeling
        );

        return profile;
    }

    private void applyCurrentFeelingRules(
            VibeProfile profile,
            CurrentFeeling currentFeeling
    ) {

        switch (currentFeeling) {

            case RELAXED -> {
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.ROMANCE, 1);
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
            }

            case STRESSED -> {
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.FAMILY, 1);
                profile.adjustGenreWeight(Genre.ANIMATION, 1);
                profile.adjustGenreWeight(Genre.HORROR, -1);
                profile.adjustGenreWeight(Genre.THRILLER, -1);
            }

            case TIRED -> {
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.ROMANCE, 1);
                profile.adjustGenreWeight(Genre.FAMILY, 1);
                profile.adjustGenreWeight(Genre.ACTION, -1);
                profile.adjustGenreWeight(Genre.HORROR, -1);
            }

            case HAPPY -> {
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
                profile.adjustGenreWeight(Genre.ROMANCE, 1);
            }

            case DOWN -> {
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.ANIMATION, 1);
                profile.adjustGenreWeight(Genre.FAMILY, 1);
            }

            case BORED -> {
                profile.adjustGenreWeight(Genre.ACTION, 1);
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
                profile.adjustGenreWeight(Genre.MYSTERY, 1);
                profile.adjustGenreWeight(Genre.THRILLER, 1);
            }

            case ENERGETIC -> {
                profile.adjustGenreWeight(Genre.ACTION, 1);
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
                profile.adjustGenreWeight(Genre.COMEDY, 1);
            }

            case RESTLESS -> {
                profile.adjustGenreWeight(Genre.ACTION, 1);
                profile.adjustGenreWeight(Genre.THRILLER, 1);
                profile.adjustGenreWeight(Genre.MYSTERY, 1);
            }
        }
    }
}
