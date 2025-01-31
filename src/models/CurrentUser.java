package models;

public class CurrentUser {
    private static User currentUser;

    private CurrentUser() {

    }

    public static void setCurrentUser(User user) {
        if (user != null) {
            System.out.println("User saved: " + user.getUser_name());
        } else {
            System.out.println("Error: user was not saved");
        }
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static boolean isLogged() {
        return currentUser != null;
    }
}