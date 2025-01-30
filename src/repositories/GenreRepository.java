package repositories;

import data.interfaces.IDB;
import models.Genre;
import repositories.interfaces.IGenreRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepository implements IGenreRepository {
    private final IDB db;

    public GenreRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createGenre(Genre genre) {
        try (Connection connection = db.getConnection()) {
            String sql = "INSERT INTO genres(genre_name) VALUES (?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, genre.getGenreName());
            st.execute();
            return true;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Genre getGenreById(int id) {
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM genres WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Genre(rs.getInt("id"), rs.getString("genre_name"));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Genre> getAllGenres() {
        List<Genre> genres = new ArrayList<>();
        try (Connection connection = db.getConnection()) {
            String sql = "SELECT * FROM genres";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                genres.add(new Genre(rs.getInt("id"), rs.getString("genre_name")));
            }
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return genres;
    }
}
