package models;

public class Review {
    private int id;
    private int movieId;
    private int userId;
    private double rating;
    private String reviewText;
    private String userName;
    public Review() {
    }

    public Review(int movieId, int userId, String reviewText, double rating) {
        setMovieId(movieId);
        setUserId(userId);
        setReviewText(reviewText);
        setRating(rating);
    }

    public Review(int id, int movieId, int userId, String reviewText, double rating) {
        this(movieId, userId, reviewText, rating);
        setId(id);
    }
    public Review(int id, int movieId, int userId, String reviewText, double rating, String userName) {
        this(movieId, userId, reviewText, rating);
        this.userName = userName;
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "User: " + userName + "\n" + "Review: " + reviewText + " Rate: " + rating + "\n----------------------------------------";
    }
}