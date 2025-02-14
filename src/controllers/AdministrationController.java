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

        Administration admin = new Administration(0, name, password, "admin");
        return repo.createAdministration(admin) ? "Administrator was created successfully." : "Administrator creation failed.";
    }

    @Override
    public String getAllAdministrators() {
        List<Administration> admins = repo.getAllAdministrators();
        return admins.isEmpty() ? "No administrators found." :
                admins.stream().map(Administration::toString).collect(Collectors.joining("\n"));
    }

    @Override
    public String getUserRoleByName(String name) {
        return repo.getUserRoleByName(name);
    }
}
