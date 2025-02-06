package repositories.interfaces;

import models.Genre;
import java.util.List;

public interface IGenreRepository {
    boolean createGenre(Genre genre);
    Genre getGenreById(int id);
    List<Genre> getAllGenres();
    boolean updateGenre(Genre genre);
    boolean deleteGenre(int id);
}