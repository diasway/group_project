package repositories;

import data.interfaces.IDB;
import models.Rating;
import repositories.interfaces.IRatingRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RatingRepository implements IRatingRepository {
    private final IDB db;

    public RatingRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createRating(Rating rating) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="INSERT INTO ratings(user_id, movie_id, score) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, rating.getUserId());
            st.setInt(2, rating.getMovieId());
            st.setDouble(3, rating.getScore());

            st.execute();

            return true;
        } catch (SQLException e){
            System.out.println("SQL error:" + e.getMessage());
        }
        return false;
    }

    @Override
    public Rating getRatingById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql ="SELECT * FROM ratings WHERE id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()){
                return new Rating(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getDouble("score"));
            }
        }catch (SQLException e){
            System.out.println("SQL error:" + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Rating> getAllRatings() {
        Connection connection = null;
        try{
            connection = db.getConnection();
            String sql ="SELECT id, user_id, movie_id, score FROM ratings";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Rating> ratings = new ArrayList<>();
            while(rs.next()){
                Rating rating = new Rating(rs.getInt("id"),
                        rs.getInt("user_id"),
                        rs.getInt("movie_id"),
                        rs.getDouble("score"));
                ratings.add(rating);
            }
            return ratings;
        }catch (SQLException e){
            System.out.println("SQL error:" + e.getMessage());
        }
        return null;
    }
}