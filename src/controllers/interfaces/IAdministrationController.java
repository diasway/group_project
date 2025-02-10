package controllers.interfaces;

public interface IAdministrationController {

    String createAdministration(String name, String password);
    String getAdministrationById(int id);
    String getAllAdministrators();

    String viewUserById(int userId);
    String deleteUser(int userId);
    String getAllUsers();
}
