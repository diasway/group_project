package repositories.interfaces;

import models.Movie;

import java.util.List;

public interface IMovieRepository {
    boolean createMovie(Movie movie);
    Movie getMovieById(int movie_id);
    List<Movie> getAllMovies();
}
