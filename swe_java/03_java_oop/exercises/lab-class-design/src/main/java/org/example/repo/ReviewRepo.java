package org.example.repo;

import org.example.model.Album;
import org.example.model.Review;

public interface ReviewRepo {

    Review[] getAllReviews();

    Review[] getReviewsByAlbum(Album album);

    void addReview(Review review);

    void updateReview(Review review);
}