package org.example.repo;

import org.example.model.User;

public interface UserRepo {

    User[] getAllUsers();

    User getUser(int id);

    void addUser(User user);

    void updateUser(User user);
}