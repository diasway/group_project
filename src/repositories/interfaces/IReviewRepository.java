package repositories.interfaces;

import models.Review;

import java.util.List;

public interface IReviewRepository {
    boolean createReview(Review review);
    Review getReviewById(int id);
    List<Review> getAllReviews();
    List<Review> getReviewsByMovieId(int movieId);
    List<Review> getReviewsByUserId(int userId);
}