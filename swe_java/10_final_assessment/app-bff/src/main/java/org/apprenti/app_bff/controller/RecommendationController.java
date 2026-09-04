package org.apprenti.app_bff.controller;

import java.security.Principal;

import org.apprenti.app_bff.dto.RecommendationRequest;
import org.apprenti.app_bff.dto.RecommendationResponse;
import org.apprenti.app_bff.service.RecommendationWorkflowService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/recommendations")
public class RecommendationController {

    private final RecommendationWorkflowService recommendationWorkflowService;

    public RecommendationController(
            RecommendationWorkflowService recommendationWorkflowService
    ) {
        this.recommendationWorkflowService
                = recommendationWorkflowService;
    }

    @PostMapping
    public RecommendationResponse generateRecommendations(
            Principal principal,
            @RequestBody RecommendationRequest request
    ) {
        return recommendationWorkflowService
                .generateRecommendations(
                        principal.getName(),
                        request
                );
    }
}
