package repositories;

import data.interfaces.IDB;
import models.Movie;
import repositories.interfaces.IMovieRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements IMovieRepository {
    private final IDB db;

    public MovieRepository(IDB db){
        this.db = db;
    }
    @Override
    public boolean createMovie(Movie movie) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "INSERT INTO movies (movie_name, genre_id, age_restriction, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, movie.getMovie_name());
            st.setInt(2, movie.getGenre_id());
            st.setInt(3, movie.getAge_restriction());
            st.setDouble(4, movie.getRating());

            st.execute();
            return true;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Movie getMovieById(int movie_id) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "SELECT m.movie_id, m.movie_name, m.genre_id, m.age_restriction, m.rating, g.genre_name " +
                    "FROM movies m " +
                    "JOIN genres g ON m.genre_id = g.genre_id " +
                    "WHERE m.movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movie_id);
            ResultSet rs = st.executeQuery();
            if(rs.next()){
                Movie movie = new Movie(rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("genre_id"),
                        rs.getInt("age_restriction"),
                        rs.getDouble("rating"));
                movie.setGenre_name(rs.getString("genre_name"));
                return movie;
            }
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Movie getMovieByGenre(int movie_genre) {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql = "SELECT * FROM movies WHERE genre_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movie_genre);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Movie(rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("genre_id"),
                        rs.getInt("age_restriction"),
                        rs.getDouble("rating"));
            }
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Movie> getAllMovies() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT m.movie_id, m.movie_name, m.genre_id, m.age_restriction, m.rating, g.genre_name " +
                    "FROM movies m " +
                    "JOIN genres g ON m.genre_id = g.genre_id ORDER BY movie_id ASC ";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Movie> movies = new ArrayList<>();
            while (rs.next()){
                Movie movie = new Movie(rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getInt("genre_id"),
                        rs.getInt("age_restriction"),
                        rs.getDouble("rating"));
                movie.setGenre_name(rs.getString("genre_name"));
                movies.add(movie);
            }
            return movies;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean deleteMovie(int movieId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "DELETE FROM movies WHERE movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movieId);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean updateMovie(Movie movie) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE movies SET movie_name = ?, genre_id = ?, age_restriction = ?, rating = ? WHERE movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, movie.getMovie_name());
            st.setInt(2, movie.getGenre_id());
            st.setInt(3, movie.getAge_restriction());
            st.setDouble(4, movie.getRating());
            st.setInt(5, movie.getMovieId());
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
        return false;
    }
    @Override
    public void updateMovieRating(int movieId) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE movies SET rating = (SELECT AVG(rating) FROM movie_reviews WHERE movie_id = ?) WHERE movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movieId);
            st.setInt(2, movieId);
            st.executeUpdate();
        } catch (SQLException e) {
            System.out.println("SQL Error: " + e.getMessage());
        }
    }
}
