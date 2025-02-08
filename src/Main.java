import controllers.*;
import controllers.interfaces.*;
import data.PostgresDB;
import data.interfaces.IDB;
import repositories.*;
import repositories.interfaces.*;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "LEMONBRO", "legion2");
        IUserRepository user_repo = new UserRepository(db);
        IUserController user_controller = new UserController(user_repo);
        IGenreRepository genre_repo = new GenreRepository(db);
        IGenreController genre_controller = new GenreController(genre_repo);
        ICurrentUserRepository current_user_repository = new CurrentUserRepository(db);
        ICurrentUserController currentUser_controller = new CurrentUserController(current_user_repository);
        IReviewRepository review_repo = new ReviewRepository(db);
        IReviewController review_controller = new ReviewController(review_repo);
        IMovieRepository movie_repo = new MovieRepository(db);
        IMovieController movie_controller = new MovieController(movie_repo);

        MyApplication app = new MyApplication(user_controller,movie_controller,genre_controller,review_controller, currentUser_controller);
        app.start();
        db.close();
    }
}