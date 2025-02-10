package repositories.interfaces;

import models.Administration;
import java.util.List;

public interface IAdministrationRepository {
    boolean createAdministration(Administration admin);
    Administration getAdministrationById(int id);
    List<Administration> getAllAdministrators();
    void viewUserById(int userId);
    boolean deleteUser(int userId);
    List<String> getAllUsers();
}
