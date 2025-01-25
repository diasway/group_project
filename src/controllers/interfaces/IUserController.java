package controllers.interfaces;
public interface IUserController {
    String createUser(String name, int age, String gender);
    String getUserById(int id);
    String getAllUsers();
}