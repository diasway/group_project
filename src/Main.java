import controllers.CurrentUserController;
import controllers.GenreController;
import controllers.MovieController;
import controllers.UserController;
import controllers.interfaces.ICurrentUserController;
import controllers.interfaces.IGenreController;
import controllers.interfaces.IMovieController;
import controllers.interfaces.IUserController;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.CurrentUserRepository;
import repositories.GenreRepository;
import repositories.MovieRepository;
import repositories.UserRepository;
import repositories.interfaces.ICurrentUserRepository;
import repositories.interfaces.IGenreRepository;
import repositories.interfaces.IMovieRepository;
import repositories.interfaces.IUserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "LEMONBRO", "legion2");
        IUserRepository user_repo = new UserRepository(db);
        IUserController user_controller = new UserController(user_repo);
        IMovieRepository movie_repo = new MovieRepository(db);
        IMovieController movie_controller = new MovieController(movie_repo);
        IGenreRepository genre_repo = new GenreRepository(db);
        IGenreController genre_controller = new GenreController(genre_repo);
        ICurrentUserRepository current_user_repository = new CurrentUserRepository(db);
        ICurrentUserController currentUser_controller = new CurrentUserController(current_user_repository);


        MyApplication app = new MyApplication(user_controller,movie_controller,genre_controller,currentUser_controller);
        app.start();

        db.close();
    }
}