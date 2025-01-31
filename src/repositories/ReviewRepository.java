package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;

    public ReviewRepository(IDB db) {
        this.db = db;
    }

    @Override
    public boolean createReview(Review review) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO reviews(movie_id, user_id, review_text) VALUES (?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, review.getMovieId());
            st.setInt(2, review.getUserId());
            st.setString(3, review.getReviewText());

            st.execute();

            return true;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public Review getReviewById(int id) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT * FROM reviews WHERE review_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);

            ResultSet rs = st.executeQuery();
            if (rs.next()) {
                return new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"));
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Review> getAllReviews() {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT review_id, movie_id, user_id, review_text FROM movie_reviews";
            Statement st = connection.createStatement();

            ResultSet rs = st.executeQuery(sql);
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Review> getReviewsByMovieId(int movieId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT review_id, movie_id, user_id, review_text FROM reviews WHERE movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movieId);

            ResultSet rs = st.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Review> getReviewsByUserId(int userId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT review_id, movie_id, user_id, review_text FROM movie_reviews WHERE user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }
}