package controllers.interfaces;


import models.Genre;
import java.util.List;

public interface IGenreController {
    String createGenre(String genreName);
    String getGenreById(int id);
    String getAllGenres();
    String updateGenre(int id, String newGenreName);
    String deleteGenre(int id);
}