import controllers.interfaces.IMovieController;
import controllers.interfaces.IUserController;
import models.Movie;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MyApplication {
    private final IUserController user_controller;
    private final IMovieController movie_controller;
    private final Scanner scanner = new Scanner(System.in);

    public MyApplication(IUserController user_controller,IMovieController movie_controller) {
        this.user_controller = user_controller;
        this.movie_controller = movie_controller;
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
                    default:
                        return;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid option!" + e);
                scanner.nextLine(); // to ignore incorrect input
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            System.out.println("----------------------------------------");
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
                case 1: getUserByIdMenu(); break;
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
        System.out.println("2. Get movie by name");
        System.out.println("3. Get all movies");
        System.out.println("0. Exit");
        System.out.println();
        System.out.print("Select an option (1-3): ");
        try {
            int option = scanner.nextInt();
            switch (option){
                case 1: getAllUsersMenu(); break;
                case 2: getMovieByIdMenu(); break;
                case 3: getAllMoviesMenu(); break;
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
        System.out.println("2. Get user by id");
        System.out.println("3. Get all movies");
        System.out.println("4. Upload a new movie");
        System.out.println("0. Exit");
        System.out.println("Enter option: (1-3): ");
        try {
            int option = scanner.nextInt();
            switch (option){
                case 1 : getAllUsersMenu(); break;
                case 2 : getUserByIdMenu(); break;
                case 3 : getAllMoviesMenu(); break;
                case 4 : createMovieMenu(); break;
                default: return;
            }
        }
        catch (InputMismatchException e){
            System.out.println("Input must be a number");
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
        System.out.println("Please enter preferred genre: ");
        String preferred_genre = scanner.next();
        System.out.println("Please password: ");
        String password = scanner.next();

        String response = user_controller.createUser(name, age, gender, preferred_genre, password);
        System.out.println(response);
        System.out.println("----------------------------------------");
    }

    private void getUserByIdMenu() {
        System.out.println("Please enter a user name: ");
        String name = scanner.next();
        System.out.println("Please enter a password");
        String password = scanner.next();
        String response = user_controller.getUserById(name);
        if(user_controller.getUserPassword(name,password)) System.out.println(response);
        else System.out.println("Incorrect password");
        System.out.println("----------------------------------------");
    }

    private void getAllUsersMenu() {
        String response = user_controller.getAllUsers();
        System.out.println(response);
        System.out.println("----------------------------------------");
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
    private void createMovieMenu(){
        System.out.println("Please enter a movie name: ");
        String name = scanner.next();
        System.out.println("Please enter a movie genre: ");
        String genre = scanner.next();
        System.out.println("Please enter a movie age restriction: ");
        int age_res = scanner.nextInt();
        System.out.println("Please enter a movie rating: ");
        int rating = scanner.nextInt();
        System.out.println("Please enter a movie review: ");
        String review = scanner.next();

        Movie movie = new Movie(name, genre, age_res, rating, review);
        String response = movie_controller.createMovie(movie);
        System.out.println(response);
        System.out.println("----------------------------------------");
    }

}
