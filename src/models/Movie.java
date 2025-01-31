package models;

import repositories.GenreRepository;

public class Movie {
    private int movie_id;
    private String movie_name;
    private int genre_id;
    private int age_restriction;
    private double rating;
    private Genre genre;

    private GenreRepository genreRepo;

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
    public String getGenreName(GenreRepository genreRepo) {
        Genre genre = genreRepo.getGenreById(this.genre_id);
        return (genre != null) ? genre.getGenre_name() : "Unknown";
    }

    @Override
    public String toString() {
        return "id. " + movie_id +  " Movie name: " + movie_name + " movie genre: " + genre_id + " age restriction: " + age_restriction + " rating: " + rating;
    }

}
