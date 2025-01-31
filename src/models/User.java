package models;

public class User {
    private int user_id;
    private String user_name;
    private int user_age;
    private boolean user_gender;
    private int genre_id;
    private String password;

    public User() {
    }

    public User(String user_name, int user_age, boolean user_gender, int genre_id, String password) {
        setUser_name(user_name);
        setUser_age(user_age);
        setUser_gender(user_gender);
        setGenre_id(genre_id);
        setPassword(password);
    }

    public User(int user_id, String user_name, int user_age, boolean user_gender, int genre_id, String password) {
        this(user_name, user_age, user_gender, genre_id, password);
        setUser_id(user_id);
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getUser_age() {
        return user_age;
    }

    public void setUser_age(int user_age) {
        this.user_age = user_age;
    }

    public boolean getUser_gender() {
        return user_gender;
    }

    public void setUser_gender(boolean user_gender) {
        this.user_gender = user_gender;
    }

    public int getGenre_id() {
        return genre_id;
    }

    public void setGenre_id(int genre_id) {
        this.genre_id = genre_id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    private String gender_casting(){
        if(user_gender) return "Male";
        else return "Female";
    }

    @Override
    public String toString() {
        return "id. " + user_id +  " Username: " + user_name + " age: " + user_age + " gender: " + gender_casting() + " preferred genre: " + genre_id;
    }
}
