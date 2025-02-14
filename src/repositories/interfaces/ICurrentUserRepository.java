package repositories.interfaces;

import models.User;

public interface ICurrentUserRepository {
    User getUserInfo(String name);
}
