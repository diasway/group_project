package controllers;

import controllers.interfaces.IAdministrationController;
import models.Administration;
import repositories.interfaces.IAdministrationRepository;

import java.util.List;
import java.util.stream.Collectors;

public class AdministrationController implements IAdministrationController {
    private final IAdministrationRepository repo;

    public AdministrationController(IAdministrationRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createAdministration(String name, String password) {
        if (name == null || name.trim().isEmpty() || password == null || password.trim().isEmpty()) {
            return "Invalid input: Name and password cannot be empty.";
        }

        Administration admin = new Administration(0, name, password);
        return repo.createAdministration(admin) ? "Administrator was created successfully." : "Administrator creation failed.";
    }

    @Override
    public String getAdministrationById(int id) {
        Administration admin = repo.getAdministrationById(id);
        return (admin == null) ? "Administrator not found." : admin.toString();
    }

    @Override
    public String getAllAdministrators() {
        List<Administration> admins = repo.getAllAdministrators();
        return admins.isEmpty() ? "No administrators found." :
                admins.stream().map(Administration::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String viewUserById(int userId) {
        if (userId <= 0) return "Invalid ID.";
        repo.viewUserById(userId);
        return "User details printed.";
    }

    @Override
    public String deleteUser(int userId) {
        return repo.deleteUser(userId) ? "User deleted successfully." : "User deletion failed.";
    }

    @Override
    public String getAllUsers() {
        List<String> users = repo.getAllUsers();
        return users.isEmpty() ? "No users found." : String.join("\n", users);
    }
}
