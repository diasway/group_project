package controllers.interfaces;

public interface IReviewController {
    String createReview(int movieId, int userId, String reviewText);
    String getReviewById(int id);
    String getAllReviews();
    String getReviewsByMovieId(int movieId);
    String getReviewsByUserId(int userId);
}
