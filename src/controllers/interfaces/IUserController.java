package controllers.interfaces;

public interface IUserController {
    String createUser(String name, int age, String gender, int genre_id, String password, String role);
    String getUserByName(String name);
    String getUserById(int id);
    String getAllUsers();
    boolean getUserPassword(String name, String password);
    String updateUser(int id, String name, int age, String gender, int preferred_genre, String password, String role);
    String deleteUser(int id);
    String getUsersOlderThan18();
}
