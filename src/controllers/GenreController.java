package controllers;



import controllers.interfaces.IGenreController;
import models.Genre;
import repositories.interfaces.IGenreRepository;

import java.util.List;

public class GenreController implements IGenreController {
    private final IGenreRepository repo;

    public GenreController(IGenreRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createGenre(String genreName) {
        Genre genre = new Genre(genreName);
        boolean created = repo.createGenre(genre);
        return created ? "Genre was successfully created." : "Genre creation failed.";
    }

    @Override
    public String getGenreById(int id) {
        Genre genre = repo.getGenreById(id);
        return (genre == null) ? "Genre not found." : genre.toString();
    }

    @Override
    public String getAllGenres() {
        List<Genre> genres = repo.getAllGenres();
        StringBuilder response = new StringBuilder();
        for (Genre genre : genres) {
            response.append(genre.toString()).append("\n");
        }
        return response.toString();
    }
}