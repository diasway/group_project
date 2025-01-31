package controllers.interfaces;
public interface IUserController {
    String createUser(String name, int age, String gender, int preferred_genre, String password);
    String getUserById(String name);
    String getAllUsers();
    boolean getUserPassword(String name, String password);
}