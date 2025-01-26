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
            String sql = "INSERT INTO movies (movie_name, movie_genre, age_restriction, rating, reviews) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, movie.getMovie_name());
            st.setString(2, movie.getMovie_genre());
            st.setInt(3, movie.getAge_restriction());
            st.setDouble(4, movie.getRating());
            st.setString(5, movie.getReviews());

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
            String sql = "SELECT * FROM movies WHERE movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movie_id);

            ResultSet rs = st.executeQuery();
            if(rs.next()){
                return new Movie(rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getString("movie_genre"),
                        rs.getInt("age_restriction"),
                        rs.getDouble("rating"),
                        rs.getString("reviews"));
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
            String sql = "SELECT * FROM movies";
            Statement st = connection.createStatement();
            ResultSet rs = st.executeQuery(sql);
            List<Movie> movies = new ArrayList<>();
            while (rs.next()){
                Movie movie = new Movie(rs.getInt("movie_id"),
                        rs.getString("movie_name"),
                        rs.getString("movie_genre"),
                        rs.getInt("age_restriction"),
                        rs.getDouble("rating"),
                        rs.getString("reviews"));
                movies.add(movie);
            }
            return movies;
        }catch (SQLException e){
            System.out.println("sql error: " + e.getMessage());
        }
        return null;
    }
}
