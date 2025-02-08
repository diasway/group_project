package controllers;

import controllers.interfaces.IMovieController;
import models.CurrentUser;
import models.Movie;
import models.Review;
import repositories.interfaces.IMovieRepository;
import repositories.interfaces.IReviewRepository;

import java.util.List;

public class MovieController implements IMovieController {
    private final IMovieRepository repo;


    public MovieController(IMovieRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createMovie(Movie movie) {
        boolean created = repo.createMovie(movie);
        return (created) ? "Movie has been created" : "Movie creation error";
    }

    @Override
    public String getMovieById(int id) {
        Movie movie = repo.getMovieById(id);
        return (movie == null) ? "Movie was not found" : movie.toString();
    }

    @Override
    public String getMovieByGenre(int movie_genre) {
        Movie movie = repo.getMovieByGenre(movie_genre);
        return (movie == null) ? "Movie was not found" : movie.toString();
    }


    @Override
    public String getAllMovies() {
        List<Movie> movies = repo.getAllMovies();
        StringBuilder response = new StringBuilder();
        for(Movie movie: movies){
            response.append(movie.toString()).append("\n");
        }
        return response.toString();
    }

    @Override
    public String deleteMovie(int movieId) {
        boolean deleted = repo.deleteMovie(movieId);
        return deleted ? "Movie has been deleted" : "Error deleting movie";
    }

    @Override
    public String updateMovie(int movieId, Movie updatedMovie) {
        boolean updated = repo.updateMovie(updatedMovie);
        return updated ? "Movie has been updated" : "Error updating movie";
    }
}
