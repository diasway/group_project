package controllers;

import controllers.interfaces.IReviewController;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.util.List;

public class ReviewController implements IReviewController {
    private final IReviewRepository repo;

    public ReviewController(IReviewRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createReview(int movieId, int userId, String reviewText) {
        Review review = new Review(movieId, userId, reviewText);
        boolean created = repo.createReview(review);
        return (created) ? "Review was created" : "Review creation failed";
    }

    @Override
    public String getReviewsByMovieId(int movieId) {
        List<Review> reviews = repo.getReviewsByMovieId(movieId);
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String getReviewsByUserId(int userId) {
        List<Review> reviews = repo.getReviewsByUserId(userId);
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String updateReview(int reviewId, String newReviewText) {
        if (newReviewText == null || newReviewText.trim().isEmpty()) {
            return "Review text cannot be empty";
        }
        Review review = new Review(reviewId, 0, 0, newReviewText);
        boolean updated = repo.updateReview(review);
        return updated ? "Review was updated" : "Review update failed";
    }

    @Override
    public String deleteReview(int reviewId) {
        boolean deleted = repo.deleteReview(reviewId);
        return deleted ? "Review was deleted" : "Review deletion failed";
    }
}