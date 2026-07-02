package org.example.repo;

import org.example.model.Album;
import org.example.model.Review;

import java.util.ArrayList;
import java.util.List;

public class ReviewRepoInMemImpl implements ReviewRepo {

    // Using an ArrayList so reviews can grow without a fixed-size array limit.
    private List<Review> reviews = new ArrayList<>();

    @Override
    public Review[] getAllReviews() {
        // The interface returns an array, so convert the ArrayList to Review[].
        return reviews.toArray(new Review[0]);
    }

    @Override
    public Review[] getReviewsByAlbum(Album album) {
        // Return an empty array if no album was provided.
        if (album == null) {
            return new Review[0];
        }

        List<Review> results = new ArrayList<>();

        // Only collect reviews that belong to the selected album.
        for (Review review : reviews) {
            if (review.getAlbum().getId() == album.getId()) {
                results.add(review);
            }
        }

        return results.toArray(new Review[0]);
    }

    @Override
    public void addReview(Review review) {
        // Assign the next available id based on the current list size.
        review.setId(reviews.size());

        // Add the review to the in-memory list.
        reviews.add(review);
    }

    @Override
    public void updateReview(Review review) {
        // Only update the review if the id exists.
        if (review.getId() >= 0 && review.getId() < reviews.size()) {
            reviews.set(review.getId(), review);
        }
    }
}