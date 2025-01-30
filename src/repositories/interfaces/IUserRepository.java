package repositories.interfaces;

import models.Movie;
import models.User;

import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserById(String name);
    List<User> getAllUsers();
    boolean getUserPassword(String name, String password);
}
