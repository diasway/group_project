package models;

public class Review {
    private int id;
    private int movieId;
    private int userId;
    private String reviewText;
    private String userName;
    public Review() {
    }

    public Review(int movieId, int userId, String reviewText) {
        setMovieId(movieId);
        setUserId(userId);
        setReviewText(reviewText);
    }

    public Review(int id, int movieId, int userId, String reviewText) {
        this(movieId, userId, reviewText);
        setId(id);
    }
    public Review(int id, int movieId, int userId, String reviewText, String userName) {
        this(movieId, userId, reviewText);
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

    @Override
    public String toString() {
        return "User: " + userName + "\n" + "Review: " + reviewText + "\n----------------------------------------";
    }
}