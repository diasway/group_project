package repositories;

import data.interfaces.IDB;
import models.Movie;
import models.User;
import repositories.interfaces.IUserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository implements IUserRepository {
    private final IDB db;

    public UserRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createUser(User user) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO users(user_name, user_age, user_gender, preferred_genre) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setString(1, user.getUser_name());
            st.setInt(2, user.getUser_age());
            st.setBoolean(3, user.getUser_gender());
            st.setString(4, user.getPreferred_genre());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public User getUserById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM users WHERE user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new User(rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getString("preferred_genre"));
            }
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<User> getAllUsers() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT user_id, user_name, user_age, user_gender, preferred_genre FROM users";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<User> users = new ArrayList<>();
            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("user_name"),
                        rs.getInt("user_age"),
                        rs.getBoolean("user_gender"),
                        rs.getString("preferred_genre")
                );
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            System.out.println("sql error:" + e.getMessage());
        }
        return null;
    }

    //movies methods
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
