import controllers.MovieController;
import controllers.UserController;
import controllers.interfaces.IMovieController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.MovieRepository;
import repositories.UserRepository;
import repositories.interfaces.IMovieRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "LEMONBRO", "legion2");
        IUserRepository user_repo = new UserRepository(db);
        IUserController user_controller = new UserController(user_repo);
        IMovieRepository movie_repo = new MovieRepository(db);
        IMovieController movie_controller = new MovieController(movie_repo);
        MyApplication app = new MyApplication(user_controller,movie_controller);
        app.start();

        db.close();
    }
}