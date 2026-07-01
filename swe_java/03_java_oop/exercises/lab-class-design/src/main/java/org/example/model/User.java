package org.example.model;

import java.util.Objects;

public class User {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        // Store the user's id so we can identify them from the menu.
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        // Store the user's display name.
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getId() == user.getId() && getName().equals(user.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }
}