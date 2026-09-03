package org.apprenti.app_bff.service;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
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

    public VibeProfile buildVibeProfile(
            CurrentFeeling currentFeeling,
            DesiredFeeling desiredFeeling
    ) {
        if (desiredFeeling == null) {
            throw new IllegalArgumentException(
                    "Desired feeling is required."
            );
        }

        VibeProfile profile
                = buildVibeProfile(currentFeeling);

        applyDesiredFeelingRules(
                profile,
                desiredFeeling
        );

        return profile;
    }

    private void applyDesiredFeelingRules(
            VibeProfile profile,
            DesiredFeeling desiredFeeling
    ) {

        switch (desiredFeeling) {

            case MAKE_ME_LAUGH -> {
                profile.adjustGenreWeight(Genre.COMEDY, 5);
                profile.adjustGenreWeight(Genre.ANIMATION, 2);
                profile.adjustGenreWeight(Genre.ROMANCE, 1);
            }

            case COMFORT_ME -> {
                profile.adjustGenreWeight(Genre.COMEDY, 4);
                profile.adjustGenreWeight(Genre.ROMANCE, 3);
                profile.adjustGenreWeight(Genre.FAMILY, 2);
                profile.adjustGenreWeight(Genre.ANIMATION, 2);
                profile.adjustGenreWeight(Genre.FANTASY, 1);
            }

            case GET_ME_EXCITED -> {
                profile.adjustGenreWeight(Genre.ACTION, 5);
                profile.adjustGenreWeight(Genre.ADVENTURE, 4);
                profile.adjustGenreWeight(Genre.THRILLER, 2);
                profile.adjustGenreWeight(Genre.SCIENCE_FICTION, 2);
            }

            case SCARE_ME -> {
                profile.adjustGenreWeight(Genre.HORROR, 5);
                profile.adjustGenreWeight(Genre.THRILLER, 3);
                profile.adjustGenreWeight(Genre.MYSTERY, 2);
            }

            case BLOW_MY_MIND -> {
                profile.adjustGenreWeight(Genre.SCIENCE_FICTION, 5);
                profile.adjustGenreWeight(Genre.MYSTERY, 4);
                profile.adjustGenreWeight(Genre.THRILLER, 2);
                profile.adjustGenreWeight(Genre.FANTASY, 2);
            }

            case MAKE_ME_THINK -> {
                profile.adjustGenreWeight(Genre.DRAMA, 4);
                profile.adjustGenreWeight(Genre.MYSTERY, 4);
                profile.adjustGenreWeight(Genre.DOCUMENTARY, 3);
                profile.adjustGenreWeight(Genre.HISTORY, 2);
                profile.adjustGenreWeight(Genre.SCIENCE_FICTION, 2);
            }

            case GIVE_ME_THE_FEELS -> {
                profile.adjustGenreWeight(Genre.DRAMA, 5);
                profile.adjustGenreWeight(Genre.ROMANCE, 3);
                profile.adjustGenreWeight(Genre.FAMILY, 2);
                profile.adjustGenreWeight(Genre.ANIMATION, 1);
            }

            case HELP_ME_ESCAPE -> {
                profile.adjustGenreWeight(Genre.ADVENTURE, 5);
                profile.adjustGenreWeight(Genre.FANTASY, 5);
                profile.adjustGenreWeight(Genre.SCIENCE_FICTION, 3);
                profile.adjustGenreWeight(Genre.ANIMATION, 2);
                profile.adjustGenreWeight(Genre.ACTION, 1);
            }
        }
    }

}
