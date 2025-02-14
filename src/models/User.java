package models;

public class User extends Administration{
    private int user_age;
    private boolean user_gender;
    private int genre_id;
    private String genre_name;


    public User(String user_name, int user_age, boolean user_gender, int genre_id, String password, String role) {
        super(0, user_name, password, role);
        this.user_age = user_age;
        this.user_gender = user_gender;
        this.genre_id = genre_id;
    }

    public User(int user_id, String user_name, int user_age, boolean user_gender, int genre_id, String password, String role) {
        super(user_id, user_name, password, role);
        this.user_age = user_age;
        this.user_gender = user_gender;
        this.genre_id = genre_id;
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


    public String getGenre_name() {
        return genre_name;
    }

    public void setGenre_name(String genre_name) {
        this.genre_name = genre_name;
    }

    private String gender_casting(){
        if(user_gender) return "Male";
        else return "Female";
    }

    @Override
    public String toString() {
        return String.format("ID: %-4d | Username: %-15s | Age: %-3d | Gender: %-6s | Preferred Genre: %-10s",
                getId(), getName(), user_age, gender_casting(), genre_name);
    }
}