package org.apprenti.app_bff.dao;

import org.apprenti.app_bff.model.Note;
import java.util.List;
import java.util.Optional;

public interface NoteDao {
    Note create(Note note);
    List<Note> findByUsername(String username);
    Optional<Note> findById(Long id);
    boolean deleteByIdAndUsername(Long id, String username);
}
