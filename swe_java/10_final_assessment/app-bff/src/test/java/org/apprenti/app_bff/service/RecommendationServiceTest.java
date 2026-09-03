package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Genre;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertEquals;
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

}
