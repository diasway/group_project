package controllers.interfaces;

import models.User;

public interface ICurrentUserController {
    User getUserInfo(String name);
    String updateUser(String name, User updatedUser);
    String deleteUser(String name);
}

