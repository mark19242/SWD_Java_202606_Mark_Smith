package org.example.repo.factory;

import org.example.model.User;
import org.example.repo.UserRepo;
import org.example.repo.UserRepoInMemImpl;

public class UserRepoFactory {

    private static UserRepo instance = null;

    public static UserRepo instance() {
        if (instance == null) {
            instance = new UserRepoInMemImpl();

            // Hard-coded users for the lab.
            // This matches how the starter app already hard-codes artists and albums.
            User user = new User();
            user.setName("Mark");
            instance.addUser(user);

            user = new User();
            user.setName("Rich");
            instance.addUser(user);

            user = new User();
            user.setName("Guest");
            instance.addUser(user);
        }

        return instance;
    }
}