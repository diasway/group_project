package controllers.interfaces;


import models.Genre;
import java.util.List;

public interface IGenreController {
    String createGenre(String genreName);
    String getGenreById(int id);
    String getAllGenres();
}