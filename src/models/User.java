package models;

public class User {
    private int user_id;
    private String user_name;
    private int user_age;
    private boolean user_gender;
    private String preferred_genre;

    public User() {
    }

    public User(String user_name, int user_age, boolean user_gender, String preferred_genre) {
        setUser_name(user_name);
        setUser_age(user_age);
        setUser_gender(user_gender);
        setPreferred_genre(preferred_genre);
    }

    public User(int user_id, String user_name, int user_age, boolean user_gender, String preferred_genre) {
        this(user_name, user_age, user_gender, preferred_genre);
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

    public String getPreferred_genre() {
        return preferred_genre;
    }

    public void setPreferred_genre(String preferred_genre) {
        this.preferred_genre = preferred_genre;
    }
    private String gender_casting(){
        if(user_gender) return "Male";
        else return "Female";
    }

    @Override
    public String toString() {
        return "id. " + user_id +  " Username: " + user_name + " age: " + user_age + " gender: " + gender_casting() + " preferred genre: " + preferred_genre;
    }
}
