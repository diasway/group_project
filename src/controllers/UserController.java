package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;
import java.util.List;
import java.util.stream.Collectors;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, int age, String gender, int genre_id, String password, String role) {
        try {
            boolean male = gender.equalsIgnoreCase("male");
            User user = new User(name, age, male, genre_id, password, role);
            boolean created = repo.createUser(user);
            return created ? "User was created" : "User creation failed";
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();  // Сatch the validation error and show it to the user
        }
    }

    @Override
    public String getUserByName(String name) {
        User user = repo.getUserByName(name);
        return (user == null) ? "User was not found" : user.toString();
    }

    @Override
    public String getUserById(int id) {
        User user = repo.getUserById(id);
        return (user == null) ? "User was not found" : user.toString();
    }

    public boolean getUserPassword(String name, String password){
        boolean password_check = repo.getUserPassword(name, password);
        return password_check;
    }

    @Override
    public String updateUser(int id, String name, int age, String gender, int preferred_genre, String password, String role) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = repo.getUserById(id);
        if (user == null) {
            return "User not found";
        }
        boolean updated = repo.updateUser(new User(id, name, age, male, preferred_genre, password, role));
        return (updated) ? "User was updated" : "User update failed";
    }

    @Override
    public String deleteUser(int id) {
        boolean deleted = repo.deleteUser(id);
        return (deleted) ? "User was deleted" : "User deletion failed";
    }

    @Override
    public String getUsersOlderThan18() {
        List<User> users = repo.getUsersOlderThan18();
        if (users.isEmpty()) {
            return "No users older than 18 found.";
        }
        return users.stream()
                .map(user -> user.getName() + " - " + user.getUser_age() + " years old")
                .collect(Collectors.joining("\n"));
    }


    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        return users.stream()
                .map(User::toString)
                .reduce((a, b) -> a + "\n" + b)
                .orElse("No users found");
    }
}