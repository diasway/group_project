package controllers;

import controllers.interfaces.IUserController;
import models.Movie;
import models.User;
import repositories.interfaces.IUserRepository;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, int age, String gender, String preffered_genre) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, age, male, preffered_genre);
        boolean created = repo.createUser(user);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User was not found" : user.toString();
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }
        return response.toString();
    }


    //movies methods
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
