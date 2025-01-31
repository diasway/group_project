package models;

public class Genre {
    private int genre_id;
    private String genre_name;

    public Genre() {
    }

    public Genre(String genreName) {
        this.genre_name = genreName;
    }
    public Genre(int genre_id, String genre_name){
        this(genre_name);
        setGenre_id(genre_id);
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    @Override
    public String toString() {
        return String.format("Genre ID: %-4d | Name: %-15s", genre_id, genre_name);
    }
}

