package repositories.interfaces;
import models.User;
import java.util.List;

public interface IUserRepository {
    boolean createUser(User user);
    User getUserByName(String name);
    User getUserById(int id);
    List<User> getAllUsers();
    boolean getUserPassword(String name, String password);
    boolean updateUser(User user);
    boolean deleteUser(int id);

    List<User> getUsersOlderThan18();
}
