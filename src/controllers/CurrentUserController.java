package controllers;

import controllers.interfaces.ICurrentUserController;
import models.CurrentUser;
import models.User;
import repositories.interfaces.ICurrentUserRepository;

public class CurrentUserController implements ICurrentUserController {
    private final ICurrentUserRepository currentUserRepo;
    private static User currentUser;

    public CurrentUserController(ICurrentUserRepository currentUserRepo) {
        this.currentUserRepo = currentUserRepo;
    }

    @Override
    public User getUserInfo(String name) {
        User user = currentUserRepo.getUserInfo(name);
        CurrentUser.setCurrentUser(user);
        return CurrentUser.getCurrentUser();
    }
}
