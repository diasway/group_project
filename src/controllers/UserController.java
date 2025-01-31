package controllers;

import controllers.interfaces.IUserController;
import models.User;
import repositories.interfaces.IUserRepository;
import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository repo;

    public UserController(IUserRepository repo) {
        this.repo = repo;
    }

    @Override
    public String createUser(String name, int age, String gender, int genre_id, String password) {
        boolean male = gender.equalsIgnoreCase("male");
        User user = new User(name, age, male, genre_id, password);
        boolean created = repo.createUser(user);
        return (created) ? "User was created" : "User creation was failed";
    }

    @Override
    public String getUserById(String name) {
        User user = repo.getUserById(name);
        return (user == null) ? "User was not found" : user.toString();
    }

    public boolean getUserPassword(String name, String password){
        boolean password_check = repo.getUserPassword(name, password);
        return password_check;
    }

    @Override
    public String getAllUsers() {
        List<User> users = repo.getAllUsers();
        StringBuilder response = new StringBuilder();
        for (User user : users) {
            response.append(user.toString()).append("\n");
        }
        return response.toString();
    }
}
