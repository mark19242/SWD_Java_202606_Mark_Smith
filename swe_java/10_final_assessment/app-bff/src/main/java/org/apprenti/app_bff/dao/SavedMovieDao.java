package org.apprenti.app_bff.dao;

import java.util.List;
import java.util.Optional;

import org.apprenti.app_bff.model.SavedMovie;

public interface SavedMovieDao {

    SavedMovie create(SavedMovie savedMovie);

    Optional<SavedMovie> findById(Long savedMovieId);

    List<SavedMovie> findByUsername(String username);

    boolean update(SavedMovie savedMovie);

    boolean deleteByIdAndUsername(Long savedMovieId, String username);
}
