package repositories.interfaces;

import models.Administration;
import java.util.List;

public interface IAdministrationRepository {
    boolean createAdministration(Administration admin);
    List<Administration> getAllAdministrators();

    String getUserRoleByName(String name);
}
