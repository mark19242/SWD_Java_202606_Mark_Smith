package org.example.repo.factory;

import org.example.repo.ReviewRepo;
import org.example.repo.ReviewRepoInMemImpl;

public class ReviewRepoFactory {

    private static ReviewRepo instance = null;

    public static ReviewRepo instance() {
        if (instance == null) {
            // Reviews start empty.
            // Users will add reviews while the app is running.
            instance = new ReviewRepoInMemImpl();
        }

        return instance;
    }
}