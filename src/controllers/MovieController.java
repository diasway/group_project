package controllers;

import controllers.interfaces.IMovieController;
import models.Movie;
import repositories.interfaces.IMovieRepository;

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
    public String getAllMovies() {
        List<Movie> movies = repo.getAllMovies();
        StringBuilder response = new StringBuilder();
        for(Movie movie: movies){
            response.append(movie.toString()).append("\n");
        }
        return response.toString();
    }
}
