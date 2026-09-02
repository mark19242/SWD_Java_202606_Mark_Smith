package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.Recommendation;

public interface RecommendationDao {

    Recommendation create(Recommendation recommendation);

    Optional<Recommendation> findById(Long recommendationId);

    List<Recommendation> findBySessionId(Long vibeSessionId);

    boolean deleteById(Long recommendationId);
}
