package models;

public class CurrentUser extends User{
    private static CurrentUser instance;

    private CurrentUser(int user_id, String user_name, int user_age, boolean user_gender, int genre_id, String password) {
        super(user_id, user_name, user_age, user_gender, genre_id, password);
    }

    public static void setCurrentUser(User user) {
        if (user != null) {
            instance = new CurrentUser(
                    user.getUser_id(),
                    user.getUser_name(),
                    user.getUser_age(),
                    user.getUser_gender(),
                    user.getGenre_id(),
                    user.getPassword());
        } else {
            instance = null;
            System.out.println("Error: user was not saved");
        }
    }


    public static User getCurrentUser() {
        return instance;
    }

    @Override
    public int getGenre_id() {
        return super.getGenre_id();
    }

    public static boolean isLogged() {
        return instance != null;
    }
}