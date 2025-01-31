package controllers;

import controllers.interfaces.IRatingController;
import models.Rating;
import repositories.interfaces.IRatingRepository;

import java.util.List;

public class RatingController implements IRatingController {
    private final IRatingRepository repo;

    public RatingController(IRatingRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createRating(int userId, int movieId, double score) {
        if (score < 0 || score > 5) {
            return "Score must be between 0 and 5";
        }
        Rating rating = new Rating(userId, movieId, score);
        boolean created = repo.createRating(rating);
        return (created) ? "Rating was created" : "Rating creation failed";
    }

    @Override
    public String getRatingById(int id) {
        Rating rating = repo.getRatingById(id);
        return (rating == null) ? "Rating was not found" : rating.toString();
    }

    @Override
    public String getAllRatings() {
        List<Rating> ratings = repo.getAllRatings();
        StringBuilder response = new StringBuilder();
        for (Rating rating : ratings) {
            response.append(rating.toString()).append("\n");
        }
        return response.toString();
    }
}
