package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.Intensity;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.RuntimePreference;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class RecommendationServiceTest {

    private final RecommendationService recommendationService
            = new RecommendationService();

    @Test
    void stressedShouldAdjustExpectedGenreWeights() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED
                );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.COMEDY)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.FAMILY)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.ANIMATION)
        );

        assertEquals(
                -1,
                profile.getGenreWeight(Genre.HORROR)
        );

        assertEquals(
                -1,
                profile.getGenreWeight(Genre.THRILLER)
        );

        assertEquals(
                0,
                profile.getGenreWeight(Genre.ACTION)
        );
    }

    @Test
    void relaxedShouldAdjustExpectedGenreWeights() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.RELAXED
                );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.COMEDY)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.ROMANCE)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.ADVENTURE)
        );

        assertEquals(
                0,
                profile.getGenreWeight(Genre.HORROR)
        );
    }

    @Test
    void nullCurrentFeelingShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService
                                .buildVibeProfile(null)
                );

        assertEquals(
                "Current feeling is required.",
                exception.getMessage()
        );
    }

    @Test
    void makeMeLaughShouldCombineWithCurrentFeelingWeights() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH
                );

        assertEquals(
                6,
                profile.getGenreWeight(Genre.COMEDY)
        );

        assertEquals(
                3,
                profile.getGenreWeight(Genre.ANIMATION)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.FAMILY)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.ROMANCE)
        );

        assertEquals(
                -1,
                profile.getGenreWeight(Genre.HORROR)
        );

        assertEquals(
                -1,
                profile.getGenreWeight(Genre.THRILLER)
        );
    }

    @Test
    void scareMeShouldOverrideStressedHorrorPenalty() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.SCARE_ME
                );

        assertEquals(
                4,
                profile.getGenreWeight(Genre.HORROR)
        );

        assertEquals(
                2,
                profile.getGenreWeight(Genre.THRILLER)
        );

        assertEquals(
                2,
                profile.getGenreWeight(Genre.MYSTERY)
        );
    }

    @Test
    void nullDesiredFeelingShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService
                                .buildVibeProfile(
                                        CurrentFeeling.HAPPY,
                                        null
                                )
                );

        assertEquals(
                "Desired feeling is required.",
                exception.getMessage()
        );
    }

    @Test
    void twoMovieVibesShouldAccumulateGenreWeights() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(
                                MovieVibe.LIGHT_AND_FUNNY,
                                MovieVibe.ROMANTIC
                        )
                );

        assertEquals(
                11,
                profile.getGenreWeight(Genre.COMEDY)
        );

        assertEquals(
                6,
                profile.getGenreWeight(Genre.ROMANCE)
        );

        assertEquals(
                5,
                profile.getGenreWeight(Genre.ANIMATION)
        );

        assertEquals(
                3,
                profile.getGenreWeight(Genre.FAMILY)
        );

        assertEquals(
                1,
                profile.getGenreWeight(Genre.DRAMA)
        );
    }

    @Test
    void surpriseMeShouldActivateSurpriseMode() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.HAPPY,
                        DesiredFeeling.HELP_ME_ESCAPE,
                        List.of(MovieVibe.SURPRISE_ME)
                );

        assertTrue(profile.isSurpriseMe());
    }

    @Test
    void surpriseMeWithSecondVibeShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService.buildVibeProfile(
                                CurrentFeeling.HAPPY,
                                DesiredFeeling.HELP_ME_ESCAPE,
                                List.of(
                                        MovieVibe.SURPRISE_ME,
                                        MovieVibe.ROMANTIC
                                )
                        )
                );

        assertEquals(
                "Surprise Me must be selected by itself.",
                exception.getMessage()
        );
    }

    @Test
    void moreThanTwoMovieVibesShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService.buildVibeProfile(
                                CurrentFeeling.HAPPY,
                                DesiredFeeling.GET_ME_EXCITED,
                                List.of(
                                        MovieVibe.ACTION_PACKED,
                                        MovieVibe.SUSPENSEFUL,
                                        MovieVibe.EPIC_AND_ADVENTUROUS
                                )
                        )
                );

        assertEquals(
                "A maximum of two movie vibes may be selected.",
                exception.getMessage()
        );
    }

    @Test
    void chillShouldProduceExpectedFullVibeProfile() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(
                                MovieVibe.LIGHT_AND_FUNNY,
                                MovieVibe.ROMANTIC
                        ),
                        Intensity.CHILL
                );

        assertEquals(13, profile.getGenreWeight(Genre.COMEDY));
        assertEquals(8, profile.getGenreWeight(Genre.ROMANCE));
        assertEquals(6, profile.getGenreWeight(Genre.ANIMATION));
        assertEquals(5, profile.getGenreWeight(Genre.FAMILY));
        assertEquals(1, profile.getGenreWeight(Genre.DRAMA));

        assertEquals(-1, profile.getGenreWeight(Genre.CRIME));
        assertEquals(-2, profile.getGenreWeight(Genre.WAR));
        assertEquals(-3, profile.getGenreWeight(Genre.THRILLER));
        assertEquals(-4, profile.getGenreWeight(Genre.HORROR));
    }

    @Test
    void goAllOutShouldCreateStrongHorrorThrillerProfile() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.SCARE_ME,
                        List.of(MovieVibe.DARK_AND_INTENSE),
                        Intensity.GO_ALL_OUT
                );

        assertEquals(
                9,
                profile.getGenreWeight(Genre.HORROR)
        );

        assertEquals(
                9,
                profile.getGenreWeight(Genre.THRILLER)
        );

        assertEquals(
                5,
                profile.getGenreWeight(Genre.CRIME)
        );

        assertEquals(
                3,
                profile.getGenreWeight(Genre.ACTION)
        );
    }

    @Test
    void anyIntensityShouldNotChangeGenreWeights() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.HAPPY,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(MovieVibe.LIGHT_AND_FUNNY),
                        Intensity.ANY_INTENSITY
                );

        assertEquals(
                10,
                profile.getGenreWeight(Genre.COMEDY)
        );

        assertEquals(
                4,
                profile.getGenreWeight(Genre.ANIMATION)
        );

        assertEquals(
                2,
                profile.getGenreWeight(Genre.FAMILY)
        );
    }

    @Test
    void nullIntensityShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService.buildVibeProfile(
                                CurrentFeeling.HAPPY,
                                DesiredFeeling.MAKE_ME_LAUGH,
                                List.of(
                                        MovieVibe.LIGHT_AND_FUNNY
                                ),
                                null
                        )
                );

        assertEquals(
                "Intensity is required.",
                exception.getMessage()
        );
    }

    @Test
    void quickRuntimeShouldSetMaximumTo89Minutes() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.HAPPY,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(MovieVibe.LIGHT_AND_FUNNY),
                        Intensity.ANY_INTENSITY,
                        RuntimePreference.QUICK
                );

        assertEquals(
                89,
                profile.getMaxRuntimeMinutes()
        );
    }

    @Test
    void standardRuntimeShouldSetMaximumTo120Minutes() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(
                                MovieVibe.LIGHT_AND_FUNNY,
                                MovieVibe.ROMANTIC
                        ),
                        Intensity.CHILL,
                        RuntimePreference.STANDARD
                );

        assertEquals(
                120,
                profile.getMaxRuntimeMinutes()
        );

        // Genre calculations should still be intact.
        assertEquals(
                13,
                profile.getGenreWeight(Genre.COMEDY)
        );
    }

    @Test
    void extendedRuntimeShouldSetMaximumTo150Minutes() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.ENERGETIC,
                        DesiredFeeling.GET_ME_EXCITED,
                        List.of(MovieVibe.ACTION_PACKED),
                        Intensity.BRING_IT_ON,
                        RuntimePreference.EXTENDED
                );

        assertEquals(
                150,
                profile.getMaxRuntimeMinutes()
        );
    }

    @Test
    void anyRuntimeShouldHaveNoMaximumRuntime() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.RELAXED,
                        DesiredFeeling.COMFORT_ME,
                        List.of(MovieVibe.ROMANTIC),
                        Intensity.CHILL,
                        RuntimePreference.ANY_RUNTIME
                );

        assertNull(
                profile.getMaxRuntimeMinutes()
        );
    }

    @Test
    void nullRuntimePreferenceShouldThrowException() {

        IllegalArgumentException exception
                = assertThrows(
                        IllegalArgumentException.class,
                        ()
                        -> recommendationService.buildVibeProfile(
                                CurrentFeeling.HAPPY,
                                DesiredFeeling.MAKE_ME_LAUGH,
                                List.of(MovieVibe.LIGHT_AND_FUNNY),
                                Intensity.ANY_INTENSITY,
                                null
                        )
                );

        assertEquals(
                "Runtime preference is required.",
                exception.getMessage()
        );
    }

    @Test
    void shouldFindStrongestPositiveGenresInRankOrder() {

        VibeProfile profile
                = recommendationService.buildVibeProfile(
                        CurrentFeeling.STRESSED,
                        DesiredFeeling.MAKE_ME_LAUGH,
                        List.of(
                                MovieVibe.LIGHT_AND_FUNNY,
                                MovieVibe.ROMANTIC
                        ),
                        Intensity.CHILL,
                        RuntimePreference.STANDARD
                );

        List<Genre> strongestGenres
                = recommendationService
                        .findStrongestPositiveGenres(
                                profile,
                                3
                        );

        assertEquals(
                List.of(
                        Genre.COMEDY,
                        Genre.ROMANCE,
                        Genre.ANIMATION
                ),
                strongestGenres
        );
    }

    @Test
    void strongestGenresShouldOnlyIncludePositiveWeights() {

        VibeProfile profile = new VibeProfile();

        profile.adjustGenreWeight(Genre.COMEDY, 5);
        profile.adjustGenreWeight(Genre.HORROR, -3);
        profile.adjustGenreWeight(Genre.THRILLER, -1);

        List<Genre> strongestGenres
                = recommendationService
                        .findStrongestPositiveGenres(
                                profile,
                                3
                        );

        assertEquals(
                List.of(Genre.COMEDY),
                strongestGenres
        );
    }

}
