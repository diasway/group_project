package controllers.interfaces;

import models.Movie;

public interface IUserController {
    String createUser(String name, int age, String gender, String preferred_genre);
    String getUserById(int id);
    String getAllUsers();

    //movies methods
    String createMovie(Movie movie);
    String getMovieById(int movie_id);
    String getAllMovies();
}