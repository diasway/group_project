package controllers.interfaces;

public interface IRatingController {
    String createRating(int userId, int movieId, double score);
    String getRatingById(int id);
    String getAllRatings();
}

