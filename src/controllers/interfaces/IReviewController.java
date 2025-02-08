package controllers.interfaces;

public interface IReviewController {
    String addReviewForMovie(int movieId, int userId, String reviewText, double rating);
    String getReviewsByMovieId(int movieId);
    String getReviewsByUserId(int userId);
    String updateReview(int reviewId, String newReviewText, double rating);
    String deleteReview(int reviewId);
}
