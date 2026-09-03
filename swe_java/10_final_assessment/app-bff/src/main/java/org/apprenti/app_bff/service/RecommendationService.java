package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.Intensity;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.RuntimePreference;
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

    public VibeProfile buildVibeProfile(
            CurrentFeeling currentFeeling,
            DesiredFeeling desiredFeeling,
            List<MovieVibe> movieVibes
    ) {
        if (movieVibes == null || movieVibes.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one movie vibe is required."
            );
        }

        if (movieVibes.size() > 2) {
            throw new IllegalArgumentException(
                    "A maximum of two movie vibes may be selected."
            );
        }

        if (movieVibes.contains(MovieVibe.SURPRISE_ME)
                && movieVibes.size() > 1) {
            throw new IllegalArgumentException(
                    "Surprise Me must be selected by itself."
            );
        }

        VibeProfile profile
                = buildVibeProfile(
                        currentFeeling,
                        desiredFeeling
                );

        for (MovieVibe movieVibe : movieVibes) {
            applyMovieVibeRules(
                    profile,
                    movieVibe
            );
        }

        return profile;
    }

    private void applyMovieVibeRules(
            VibeProfile profile,
            MovieVibe movieVibe
    ) {

        switch (movieVibe) {

            case LIGHT_AND_FUNNY -> {
                profile.adjustGenreWeight(Genre.COMEDY, 4);
                profile.adjustGenreWeight(Genre.FAMILY, 2);
                profile.adjustGenreWeight(Genre.ANIMATION, 2);
                profile.adjustGenreWeight(Genre.ROMANCE, 1);
            }

            case ACTION_PACKED -> {
                profile.adjustGenreWeight(Genre.ACTION, 4);
                profile.adjustGenreWeight(Genre.ADVENTURE, 3);
                profile.adjustGenreWeight(Genre.THRILLER, 2);
                profile.adjustGenreWeight(Genre.SCIENCE_FICTION, 1);
            }

            case ROMANTIC -> {
                profile.adjustGenreWeight(Genre.ROMANCE, 4);
                profile.adjustGenreWeight(Genre.COMEDY, 1);
                profile.adjustGenreWeight(Genre.DRAMA, 1);
            }

            case SUSPENSEFUL -> {
                profile.adjustGenreWeight(Genre.THRILLER, 4);
                profile.adjustGenreWeight(Genre.MYSTERY, 3);
                profile.adjustGenreWeight(Genre.CRIME, 2);
                profile.adjustGenreWeight(Genre.HORROR, 1);
            }

            case DARK_AND_INTENSE -> {
                profile.adjustGenreWeight(Genre.THRILLER, 4);
                profile.adjustGenreWeight(Genre.CRIME, 3);
                profile.adjustGenreWeight(Genre.DRAMA, 2);
                profile.adjustGenreWeight(Genre.HORROR, 2);
                profile.adjustGenreWeight(Genre.MYSTERY, 1);
            }

            case MIND_BENDING -> {
                profile.adjustGenreWeight(
                        Genre.SCIENCE_FICTION,
                        4
                );
                profile.adjustGenreWeight(Genre.MYSTERY, 4);
                profile.adjustGenreWeight(Genre.THRILLER, 2);
                profile.adjustGenreWeight(Genre.FANTASY, 2);
            }

            case EMOTIONAL -> {
                profile.adjustGenreWeight(Genre.DRAMA, 4);
                profile.adjustGenreWeight(Genre.ROMANCE, 2);
                profile.adjustGenreWeight(Genre.FAMILY, 1);
            }

            case EPIC_AND_ADVENTUROUS -> {
                profile.adjustGenreWeight(Genre.ADVENTURE, 4);
                profile.adjustGenreWeight(Genre.ACTION, 3);
                profile.adjustGenreWeight(Genre.FANTASY, 3);
                profile.adjustGenreWeight(
                        Genre.SCIENCE_FICTION,
                        2
                );
                profile.adjustGenreWeight(Genre.HISTORY, 1);
                profile.adjustGenreWeight(Genre.WAR, 1);
            }

            case SURPRISE_ME ->
                profile.setSurpriseMe(true);
        }
    }

    public VibeProfile buildVibeProfile(
            CurrentFeeling currentFeeling,
            DesiredFeeling desiredFeeling,
            List<MovieVibe> movieVibes,
            Intensity intensity
    ) {
        if (intensity == null) {
            throw new IllegalArgumentException(
                    "Intensity is required."
            );
        }

        VibeProfile profile
                = buildVibeProfile(
                        currentFeeling,
                        desiredFeeling,
                        movieVibes
                );

        applyIntensityRules(
                profile,
                intensity
        );

        return profile;
    }

    private void applyIntensityRules(
            VibeProfile profile,
            Intensity intensity
    ) {

        switch (intensity) {

            case CHILL -> {
                profile.adjustGenreWeight(Genre.COMEDY, 2);
                profile.adjustGenreWeight(Genre.ROMANCE, 2);
                profile.adjustGenreWeight(Genre.FAMILY, 2);
                profile.adjustGenreWeight(Genre.ANIMATION, 1);

                profile.adjustGenreWeight(Genre.HORROR, -3);
                profile.adjustGenreWeight(Genre.THRILLER, -2);
                profile.adjustGenreWeight(Genre.CRIME, -1);
                profile.adjustGenreWeight(Genre.WAR, -2);
            }

            case LIGHT_INTENSITY -> {
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
                profile.adjustGenreWeight(Genre.MYSTERY, 1);
                profile.adjustGenreWeight(Genre.HORROR, -1);
            }

            case BRING_IT_ON -> {
                profile.adjustGenreWeight(Genre.ACTION, 2);
                profile.adjustGenreWeight(Genre.THRILLER, 2);
                profile.adjustGenreWeight(Genre.ADVENTURE, 1);
                profile.adjustGenreWeight(Genre.CRIME, 1);
                profile.adjustGenreWeight(Genre.HORROR, 1);
            }

            case GO_ALL_OUT -> {
                profile.adjustGenreWeight(Genre.ACTION, 3);
                profile.adjustGenreWeight(Genre.THRILLER, 3);
                profile.adjustGenreWeight(Genre.HORROR, 3);
                profile.adjustGenreWeight(Genre.CRIME, 2);
                profile.adjustGenreWeight(Genre.WAR, 2);
            }

            case ANY_INTENSITY -> {
                // No adjustment needed.
            }
        }
    }

    public VibeProfile buildVibeProfile(
            CurrentFeeling currentFeeling,
            DesiredFeeling desiredFeeling,
            List<MovieVibe> movieVibes,
            Intensity intensity,
            RuntimePreference runtimePreference
    ) {
        if (runtimePreference == null) {
            throw new IllegalArgumentException(
                    "Runtime preference is required."
            );
        }

        VibeProfile profile
                = buildVibeProfile(
                        currentFeeling,
                        desiredFeeling,
                        movieVibes,
                        intensity
                );

        applyRuntimeRules(
                profile,
                runtimePreference
        );

        return profile;
    }

    private void applyRuntimeRules(
            VibeProfile profile,
            RuntimePreference runtimePreference
    ) {

        switch (runtimePreference) {

            case QUICK ->
                profile.setMaxRuntimeMinutes(89);

            case STANDARD ->
                profile.setMaxRuntimeMinutes(120);

            case EXTENDED ->
                profile.setMaxRuntimeMinutes(150);

            case ANY_RUNTIME ->
                profile.setMaxRuntimeMinutes(null);
        }
    }

}
