package models;

import repositories.GenreRepository;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int movie_id;
    private String movie_name;
    private int genre_id;
    private int age_restriction;
    private double rating;
    private String genre_name;
    private List<Review> reviews = new ArrayList<>();


    public Movie(String movie_name, int genre_id, int age_restriction, double rating){
        setMovie_name(movie_name);
        setGenre_id(genre_id);
        setAge_restriction(age_restriction);
        setRating(rating);
    }

    public Movie(int movie_id, String movie_name, int genre_id, int age_restriction, double rating){
        this(movie_name, genre_id, age_restriction,rating);
        this.movie_id = movie_id;
    }

    public String getMovie_name() {
        return movie_name;
    }

    public void setMovie_name(String movie_name) {
        this.movie_name = movie_name;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
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

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    public void addReview(Review review) {
        reviews.add(review);
    }

    public List<Review> getReviews() {
        return reviews;
    }

    @Override
    public String toString() {
        return String.format("Movie ID: %-4d | Name: %-20s | Genre: %-15s | Age Restriction: %-3d | Rating: %-4.1f",
                movie_id, movie_name, genre_name, age_restriction, rating);
    }

}
