package controllers.interfaces;

public interface IReviewController {
    String createReview(int movieId, int userId, String reviewText);
    String getReviewsByMovieId(int movieId);
    String getReviewsByUserId(int userId);
    String updateReview(int reviewId, String newReviewText);
    String deleteReview(int reviewId);
}
