package controllers.interfaces;

import models.User;

public interface ICurrentUserController {
    User getUserInfo(String name);
}

