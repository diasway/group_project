package models;

public class Review {
    private int id;
    private int movieId;
    private int userId;
    private String reviewText;

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

    @Override
    public String toString() {
        return "Review{" +
                "id=" + id +
                ", movieId=" + movieId +
                ", userId=" + userId +
                ", reviewText='" + reviewText + '\'' +
                '}';
    }
}