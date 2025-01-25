package repositories.interfaces;

import models.Movie;
import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserById(int id);
    List<User> getAllUsers();

    boolean createMovie(Movie movie);
    Movie getMovieById(int movie_id);
    List<Movie> getAllMovies();
}
