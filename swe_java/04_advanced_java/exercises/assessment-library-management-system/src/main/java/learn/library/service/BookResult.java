package learn.library.service;

import learn.library.model.Book;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Carries either a successful book result or one or more validation messages.
 */
public class BookResult {

    private Book payload;
    private final List<String> messages = new ArrayList<>();

    public boolean isSuccess() {
        return messages.isEmpty();
    }

    public Book getPayload() {
        return payload;
    }

    public void setPayload(Book payload) {
        this.payload = payload;
    }

    public List<String> getMessages() {
        return Collections.unmodifiableList(messages);
    }

    public void addMessage(String message) {
        messages.add(message);
    }
}
