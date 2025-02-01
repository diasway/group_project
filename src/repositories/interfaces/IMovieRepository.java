package repositories.interfaces;

import models.Movie;

import java.util.List;

public interface IMovieRepository {
    boolean createMovie(Movie movie);
    Movie getMovieById(int movie_id);
    Movie getMovieByGenre(int movie_genre);
    List<Movie> getAllMovies();
    boolean deleteMovie(int movieId);
}
