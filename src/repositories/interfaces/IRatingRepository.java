package repositories.interfaces;

import models.Rating;
import java.util.List;

public interface IRatingRepository {
    boolean createRating(Rating rating);
    Rating getRatingById(int id);
    List<Rating> getAllRatings();
}
