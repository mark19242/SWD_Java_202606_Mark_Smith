package org.apprenti.app_bff.service;

import java.util.List;

import org.apprenti.app_bff.dto.ScoredMovie;
import org.apprenti.app_bff.model.CurrentFeeling;
import org.apprenti.app_bff.model.DesiredFeeling;
import org.apprenti.app_bff.model.Intensity;
import org.apprenti.app_bff.model.MovieVibe;
import org.apprenti.app_bff.model.RuntimePreference;
import org.apprenti.app_bff.model.VibeProfile;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfSystemProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@EnabledIfSystemProperty(
        named = "runLiveTmdbTest",
        matches = "true"
)
class RecommendationEngineServiceLiveTest {

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationEngineService recommendationEngineService;

    @Test
    void fullQuestionnaireShouldReturnRankedTopRecommendations() {

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

        List<ScoredMovie> recommendations
                = recommendationEngineService.recommend(profile);

        assertNotNull(recommendations);
        assertFalse(recommendations.isEmpty());

        assertTrue(
                recommendations.size() <= 10
        );

        for (int i = 0;
                i < recommendations.size() - 1;
                i++) {

            assertTrue(
                    recommendations.get(i).finalScore()
                    >= recommendations.get(i + 1).finalScore()
            );
        }

        recommendations.forEach(
                recommendation -> {
                    assertNotNull(
                            recommendation.movie().id()
                    );

                    assertNotNull(
                            recommendation.movie().title()
                    );

                    assertFalse(
                            recommendation.movie()
                                    .title()
                                    .isBlank()
                    );
                }
        );

        System.out.println(
                "\n*** REELVIBE TOP RECOMMENDATIONS ***"
        );

        for (int i = 0;
                i < recommendations.size();
                i++) {

            ScoredMovie recommendation
                    = recommendations.get(i);

            System.out.println(
                    (i + 1)
                    + ". "
                    + recommendation.movie().title()
                    + " | Vibe Score: "
                    + recommendation.vibeScore()
                    + " | Rating Bonus: "
                    + recommendation.ratingBonus()
                    + " | Final Score: "
                    + recommendation.finalScore()
            );
        }
    }
}
