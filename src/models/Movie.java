package models;

public class Movie {
    private int movie_id;
    private String movie_name;
    private String movie_genre;
    private int age_restriction;
    private double rating;
    private String reviews;


    public Movie(String movie_name, String movie_genre, int age_restriction, double rating, String reviews){
        setMovie_name(movie_name);
        setMovie_genre(movie_genre);
        setAge_restriction(age_restriction);
        setRating(rating);
        setReviews(reviews);
    }

    public Movie(int movie_id, String movie_name, String movie_genre, int age_restriction, double rating, String reviews){
        this(movie_name, movie_genre, age_restriction,rating,reviews);
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public String getMovie_genre() {
        return movie_genre;
    }

    public void setMovie_genre(String movie_genre) {
        this.movie_genre = movie_genre;
    }

    public int getAge_restriction() {
        return age_restriction;
    }

    public void setAge_restriction(int age_restriction) {
        this.age_restriction = age_restriction;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "id. " + movie_id +  " Movie name: " + movie_name + " movie genre: " + movie_genre + " age restriction: " + age_restriction + " rating: " + rating;
    }
}
