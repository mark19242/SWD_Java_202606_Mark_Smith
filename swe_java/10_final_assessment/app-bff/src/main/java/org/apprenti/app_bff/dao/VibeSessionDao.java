package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.VibeSession;

public interface VibeSessionDao {

    VibeSession create(VibeSession vibeSession);

    Optional<VibeSession> findById(Long vibeSessionId);

    List<VibeSession> findByUsername(String username);

    boolean deleteByIdAndUsername(Long vibeSessionId, String username);
}
