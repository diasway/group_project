package controllers.interfaces;

public interface IAdministrationController {

    String createAdministration(String name, String password);
    String getAllAdministrators();

    String getUserRoleByName(String name);
}
