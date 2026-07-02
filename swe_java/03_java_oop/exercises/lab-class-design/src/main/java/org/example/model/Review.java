package org.example.model;

import java.util.Objects;

public class Review {

    private int id;
    private Album album;
    private User author;
    private String text;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        // Store the review id so the repository can track and update reviews.
        this.id = id;
    }

    public Album getAlbum() {
        return album;
    }

    public void setAlbum(Album album) {
        // Store which album this review belongs to.
        this.album = album;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        // Store which logged-in user wrote the review.
        this.author = author;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        // Store the actual review message.
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Review review)) return false;
        return getId() == review.getId()
                && getAlbum().equals(review.getAlbum())
                && getAuthor().equals(review.getAuthor())
                && getText().equals(review.getText());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getAlbum(), getAuthor(), getText());
    }
}