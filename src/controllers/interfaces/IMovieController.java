package controllers.interfaces;

import models.Movie;

public interface IMovieController {
    String createMovie(Movie movie);
    String getMovieById(int genre_id);
    String getMovieByGenre(int movie_genre);
    String getAllMovies();
    String deleteMovie(int movieId);
    String updateMovie(int movieId, Movie updatedMovie);
}

