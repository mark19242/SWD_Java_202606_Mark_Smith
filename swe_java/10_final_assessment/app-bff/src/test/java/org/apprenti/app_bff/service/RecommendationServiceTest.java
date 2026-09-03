package org.apprenti.app_bff.service;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
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

}
