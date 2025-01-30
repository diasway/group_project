package controllers.interfaces;

import models.Movie;

public interface IMovieController {
    String createMovie(Movie movie);
    String getMovieById(int movie_id);
    String getAllMovies();
}
