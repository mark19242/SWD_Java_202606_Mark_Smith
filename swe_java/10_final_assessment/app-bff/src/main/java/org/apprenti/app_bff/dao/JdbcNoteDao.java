package org.apprenti.app_bff.dao;

import org.apprenti.app_bff.model.Note;
import java.util.List;
import java.util.Optional;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcNoteDao implements NoteDao {

    private final JdbcClient jdbcClient;

    public JdbcNoteDao(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Note create(Note note) {
        var keyHolder = new GeneratedKeyHolder();
        var sql = "INSERT INTO notes (username, title, content) VALUES (:username, :title, :content)";

        jdbcClient.sql(sql)
                .param("username", note.username())
                .param("title", note.title())
                .param("content", note.content())
                .update(keyHolder, "id");

        Number generatedId = keyHolder.getKey();
        Long id = (generatedId != null) ? generatedId.longValue() : null;
        return new Note(id, note.username(), note.title(), note.content());
    }

    @Override
    public List<Note> findByUsername(String username) {
        var sql = "SELECT id, username, title, content FROM notes WHERE username = :username";
        return jdbcClient.sql(sql)
                .param("username", username)
                .query(Note.class)
                .list();
    }

    @Override
    public Optional<Note> findById(Long id) {
        var sql = "SELECT id, username, title, content FROM notes WHERE id = :id";
        return jdbcClient.sql(sql)
                .param("id", id)
                .query(Note.class)
                .optional();
    }

    @Override
    public boolean deleteByIdAndUsername(Long id, String username) {
        var sql = "DELETE FROM notes WHERE id = :id AND username = :username";
        int rowsAffected = jdbcClient.sql(sql)
                .param("id", id)
                .param("username", username)
                .update();
        return rowsAffected > 0;
    }
}