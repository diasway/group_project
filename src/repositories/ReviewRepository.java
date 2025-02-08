package repositories;

import data.interfaces.IDB;
import models.Review;
import repositories.interfaces.IReviewRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewRepository implements IReviewRepository {
    private final IDB db;
    private final MovieRepository movieRepository;

    public ReviewRepository(IDB db) {
        this.db = db;
        this.movieRepository = new MovieRepository(db);
    }

    @Override
    public boolean createReview(Review review) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "INSERT INTO movie_reviews(movie_id, user_id, review_text, rating) VALUES (?, ?, ?, ?)";
            PreparedStatement st = connection.prepareStatement(sql);

            st.setInt(1, review.getMovieId());
            st.setInt(2, review.getUserId());
            st.setString(3, review.getReviewText());
            st.setDouble(4, review.getRating());

            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                movieRepository.updateMovieRating(review.getMovieId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Review> getReviewsByMovieId(int movieId) {
        Connection connection = null;
        try {
            connection = db.getConnection();
            String sql = "SELECT r.review_id, r.movie_id, r.user_id, r.review_text, r.rating , u.user_name " +
                    "FROM movie_reviews r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.movie_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, movieId);

            ResultSet rs = st.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                String userName = rs.getString("user_name");
                Review review = new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"),
                        rs.getDouble("rating"),
                        userName);
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
            String sql = "SELECT r.review_id, r.movie_id, r.user_id, r.review_text, r.rating, u.user_name " +
                    "FROM movie_reviews r " +
                    "JOIN users u ON r.user_id = u.user_id " +
                    "WHERE r.user_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, userId);

            ResultSet rs = st.executeQuery();
            List<Review> reviews = new ArrayList<>();
            while (rs.next()) {
                Review review = new Review(rs.getInt("review_id"),
                        rs.getInt("movie_id"),
                        rs.getInt("user_id"),
                        rs.getString("review_text"),
                        rs.getDouble("rating"));
                reviews.add(review);
            }
            return reviews;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return null;
    }

    @Override
    public boolean updateReview(Review review) {
        try (Connection connection = db.getConnection()) {
            String sql = "UPDATE movie_reviews SET review_text = ?, rating = ? WHERE review_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, review.getReviewText());
            st.setDouble(2,review.getRating());
            st.setInt(3, review.getId());
            int affectedRows = st.executeUpdate();
            if (affectedRows > 0) {
                movieRepository.updateMovieRating(review.getMovieId());
                return true;
            }
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteReview(int reviewId) {
        try (Connection connection = db.getConnection()) {
            String sql = "DELETE FROM movie_reviews WHERE review_id = ?";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, reviewId);
            int affectedRows = st.executeUpdate();
            return affectedRows > 0;
        } catch (SQLException e) {
            System.out.println("SQL error: " + e.getMessage());
        }
        return false;
    }
}