package org.example;

/**
 * Stores information about a homeroom teacher.
 */
public class Teacher {

    private String firstName;
    private String lastName;

    // Returns the teacher's first name.
    public String getFirstName() {
        return firstName;
    }

    // Sets the teacher's first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the teacher's last name.
    public String getLastName() {
        return lastName;
    }

    // Sets the teacher's last name.
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}