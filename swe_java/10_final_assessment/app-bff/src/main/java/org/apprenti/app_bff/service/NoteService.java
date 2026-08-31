package org.apprenti.app_bff.service;

import org.apprenti.app_bff.dao.NoteDao;
import org.apprenti.app_bff.model.Note;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class NoteService {

    private final NoteDao noteDao;

    public NoteService(NoteDao noteDao) {
        this.noteDao = noteDao;
    }

    public Note createNote(String username, String title, String content) {
        return noteDao.create(new Note(null, username, title, content));
    }

    @Transactional(readOnly = true)
    public List<Note> getNotesForUser(String username) {
        return noteDao.findByUsername(username);
    }

    public boolean deleteNote(Long id, String username) {
        return noteDao.deleteByIdAndUsername(id, username);
    }
}
