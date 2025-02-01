import controllers.interfaces.ICurrentUserController;
import controllers.interfaces.IGenreController;
import controllers.interfaces.IMovieController;
import controllers.interfaces.IUserController;
import models.*;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController user_controller;
    private final IMovieController movie_controller;
    private final IGenreController genre_controller;
    private final ICurrentUserController currentUser_controller;
    private final Scanner scanner = new Scanner(System.in);
    private User user;

    public MyApplication(IUserController user_controller, IMovieController movie_controller, IGenreController genre_controller, ICurrentUserController currentUser_controller) {
        this.user_controller = user_controller;
        this.movie_controller = movie_controller;
        this.genre_controller = genre_controller;
        this.currentUser_controller = currentUser_controller;
    }

    private void mainMenu() {
        System.out.println();
        System.out.println("Welcome to My Application");
        System.out.println("Select one of the following options:");
        System.out.println("1. Enter as administrator");
        System.out.println("2. Enter as user");
        System.out.println("0. Exit");
    }

    public void start() {

        while (true) {
            if (CurrentUser.isLogged()) {
                UserMenu();
            } else {
                mainMenu();
                try {                                                   //user or admin login
                    int option = scanner.nextInt();
                    switch (option) {
                        case 1:
                            MovieMenu();
                            break;
                        case 2:
                            UserPasswordCheckMenu();
                            break;
                        case 3:
                            System.out.println(genre_controller.getAllGenres());
                            break;
                        default:
                            return;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Please enter a valid option!" + e);
                    scanner.nextLine();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("----------------------------------------");
            }
            }

    }
    private void UserPasswordCheckMenu(){
        System.out.println("Do you have an account?");
        System.out.println("1. Yes, log in to existing account");
        System.out.println("2. No, create account");
        System.out.println("0. Exit");

        try {
            int option = scanner.nextInt();
            switch (option){
                case 1: getUserByName(); break;
                case 2: createUserMenu(); break;
                default: return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option!" + e);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }
    private void UserMenu(){
        System.out.println("1. Profile");
        System.out.println("2. Get movie by ID");
        System.out.println(("3. Get movie by genre"));
        System.out.println("4. Get all movies");
        System.out.println("5. Add movie review");
        System.out.println("6. Get movie review");
        System.out.println("7. Logout");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (1-7): ");
        try {
            int option = scanner.nextInt();
            switch (option){
                case 1: getCurrentUserInfo(); break;
                case 2: getMovieByIdMenu(); break;
                case 3: getMovieByGenreMenu(); break;
                case 4: getAllMoviesMenu(); break;
                case 5: addMovieReviewMenu(); break;
                case 6: getMovieReviewsMenu(); break;
                case 7: logout(); break;
                default: return;
            }
        } catch (InputMismatchException e) {
            System.out.println("Please enter a valid option!" + e);
            scanner.nextLine();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }
    private void MovieMenu(){
        System.out.println("Welcome to movie centre");
        System.out.println("Select the option:");
        System.out.println("1. Get all users");
        System.out.println("2. Get user by ID");
        System.out.println("3. Get all movies");
        System.out.println("4. Get movie by ID");
        System.out.println("5. Upload a new movie");
        System.out.println("6. Upload a new genre");
        System.out.println("7. Delete movie by ID");
        System.out.println("0. Exit");
        System.out.println("Enter option: (1-6): ");
        try {
            int option = scanner.nextInt();
            switch (option){
                case 1 : getAllUsersMenu(); break;
                case 2 : getUserById(); break;
                case 3 : getAllMoviesMenu(); break;
                case 4 : getMovieByIdMenu(); break;
                case 5: createMovieMenu(); break;
                case 6: createGenreMenu(); break;
                case 7: deleteMovieMenu(); break;
                default: return;
            }
        }
        catch (InputMismatchException e){
            System.out.println("Input must be a number");
            scanner.nextLine();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        System.out.println("----------------------------------------");
    }

    //user menus
    private void createUserMenu() {
        System.out.println("Please enter name: ");
        String name = scanner.next();
        System.out.println("Please enter age: ");
        int age = scanner.nextInt();
        System.out.println("Please enter gender (male/female): ");
        String gender = scanner.next();
        String genres = genre_controller.getAllGenres();
        System.out.println("Available genres:\n" + genres);
        System.out.println("Please enter preferred genre: ");
        int genre_id = scanner.nextInt();
        System.out.println("Please enter password: ");
        String password = scanner.next();

        String response = user_controller.createUser(name, age, gender, genre_id, password);
        currentUser_controller.getUserInfo(name);
        System.out.println(response);
        if (response.equals("User was created") && CurrentUser.isLogged()) {
            UserMenu();
        } else {
            start();
        }
        System.out.println("----------------------------------------");
    }

    private void getUserByName() {
        System.out.println("Please enter your name: ");
        String name = scanner.next();
        System.out.println("Please enter your password: ");
        String password = scanner.next();

        if (user_controller.getUserPassword(name, password)) {
            currentUser_controller.getUserInfo(name);
            System.out.println("Welcome, " + CurrentUser.getCurrentUser().getUser_name());
            UserMenu();
        } else {
            System.out.println("Incorrect password");
            start();
        }
    }

    private void getAllUsersMenu() {
        String response = user_controller.getAllUsers();
        System.out.println(response);
        System.out.println("----------------------------------------");
    }

    private void getUserById(){
        System.out.println("Please enter user id: ");
        int id = scanner.nextInt();
        String response = user_controller.getUserById(id);
        System.out.println(response);
    }

    //movies menu
    private void getMovieByIdMenu() {
        System.out.println("Enter movie id: ");
        int id = scanner.nextInt();
        String response = movie_controller.getMovieById(id);
        System.out.println(response);
        System.out.println("----------------------------------------");
    }

    private void getAllMoviesMenu() {
        String response = movie_controller.getAllMovies();
        System.out.println(response);
        System.out.println("----------------------------------------");
    }
    private void getMovieByGenreMenu(){
        String genres = genre_controller.getAllGenres();
        System.out.println("Available genres:\n" + genres);
        System.out.println("Enter genre ID:");
        int id = scanner.nextInt();
        String response = movie_controller.getMovieByGenre(id);
        System.out.println(response);
    }
    private void createMovieMenu(){
        System.out.println("Please enter a movie name: ");
        String name = scanner.next();
        String genres = genre_controller.getAllGenres();
        System.out.println("Available genres:\n" + genres);
        System.out.println("Please enter a movie ID of  genre: ");
        int genre = scanner.nextInt();
        System.out.println("Please enter a movie age restriction: ");
        int age_res = scanner.nextInt();
        System.out.println("Please enter a movie rating: ");
        int rating = scanner.nextInt();

        Movie movie = new Movie(name, genre, age_res, rating);
        String response = movie_controller.createMovie(movie);
        System.out.println(response);
        System.out.println("----------------------------------------");
    }
    private void getCurrentUserInfo(){
        User currentUser = CurrentUser.getCurrentUser();
        if (currentUser != null) {
            System.out.println("Logged in as: " + currentUser.getUser_name());
        } else {
            System.out.println("No user is logged in.");
        }
    }
    private void createGenreMenu(){
        System.out.println("Please enter a genre name: ");
        String genre_name = scanner.next();
        Genre genre = new Genre(genre_name);
        String response = genre_controller.createGenre(genre_name);
        System.out.println(response);
        System.out.println("----------------------------------------");
    }

    private void deleteMovieMenu(){
        System.out.println("Enter the ID of movie you want delete: ");
        int id = scanner.nextInt();
        String response = movie_controller.deleteMovie(id);
        System.out.println("----------------------------------------");
    }
    private void addMovieReviewMenu() {
        getAllMoviesMenu();
        System.out.println("Enter movie ID: ");
        int movieId = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Enter your review: ");
        String reviewText = scanner.nextLine();
        String response = movie_controller.addReviewForMovie(movieId,CurrentUser.getCurrentUser().getUser_id(), reviewText);
        System.out.println(response);
    }

    private void getMovieReviewsMenu() {
        getAllMoviesMenu();
        System.out.println("Enter movie ID to see reviews: ");
        int id = scanner.nextInt();
        String reviews = movie_controller.getReviewsByMovieId(id);
        System.out.println("ID: " + id + " Reviews: ");
        System.out.println(reviews);
    }

    private void logout() {
        CurrentUser.setCurrentUser(null);
        System.out.println("logged out.");
        start();
    }
}
