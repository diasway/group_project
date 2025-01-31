package models;

public class Rating {
    private int id;
    private int userId;
    private int movieId;
    private double score;

    public Rating() {
    }

    public Rating(int userId, int movieId, double score) {
        setUserId(userId);
        setMovieId(movieId);
        setScore(score);
    }

    public Rating(int id, int userId, int movieId, double score) {
        this(userId, movieId, score);
        setId(id);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        if (score < 0 || score > 5) {
            throw new IllegalArgumentException("Score must be between 0 and 5");
        }
        this.score = score;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "id=" + id +
                ", userId=" + userId +
                ", movieId=" + movieId +
                ", score=" + score +
                '}';
    }
}

