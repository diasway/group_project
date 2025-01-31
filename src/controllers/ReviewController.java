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
    public String getReviewById(int id) {
        Review review = repo.getReviewById(id);
        return (review == null) ? "Review was not found" : review.toString();
    }

    @Override
    public String getAllReviews() {
        List<Review> reviews = repo.getAllReviews();
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
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
}