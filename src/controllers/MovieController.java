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
    private final IReviewRepository reviewRepo;


    public MovieController(IMovieRepository repo, IReviewRepository reviewRepo) {
        this.repo = repo;
        this.reviewRepo = reviewRepo;
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
    public String addReviewForMovie(int movieId, int userId, String reviewText) {
        Review review = new Review(movieId, CurrentUser.getCurrentUser().getUser_id(), reviewText);
        boolean created = reviewRepo.createReview(review);
        return created ? "Review has been added." : "Error adding review.";
    }

    @Override
    public String getReviewsByMovieId(int movieId) {
        List<Review> reviews = reviewRepo.getReviewsByMovieId(movieId);
        StringBuilder response = new StringBuilder();
        for (Review review : reviews) {
            response.append(review.toString()).append("\n");
        }
        return response.toString();
    }
}
