package repositories.interfaces;

import models.Review;

import java.util.List;

public interface IReviewRepository {
    boolean createReview(Review review);
    List<Review> getReviewsByMovieId(int movieId);
    List<Review> getReviewsByUserId(int userId);
}