package org.example.repo;

import org.example.model.User;

import java.util.ArrayList;
import java.util.List;

public class UserRepoInMemImpl implements UserRepo {

    // Using an ArrayList instead of a fixed-size array so the repo can grow safely.
    private List<User> users = new ArrayList<>();

    public UserRepoInMemImpl() {
        // No setup needed here because the ArrayList is already initialized above.
    }

    @Override
    public User[] getAllUsers() {
        // The interface returns an array, so we convert the ArrayList into a User[].
        return users.toArray(new User[0]);
    }

    @Override
    public User getUser(int id) {
        // Prevents the app from crashing if the user enters an invalid id.
        if (id < 0 || id >= users.size()) {
            return null;
        }

        return users.get(id);
    }

    @Override
    public void addUser(User user) {
        // Assign the next available id based on the current list size.
        user.setId(users.size());

        // Add the user to the in-memory list.
        users.add(user);
    }

    @Override
    public void updateUser(User user) {
        // Only update the user if the id exists in the list.
        if (user.getId() >= 0 && user.getId() < users.size()) {
            users.set(user.getId(), user);
        }
    }
}