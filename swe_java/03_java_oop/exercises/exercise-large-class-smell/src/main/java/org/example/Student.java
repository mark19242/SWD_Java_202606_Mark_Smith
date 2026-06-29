package org.example;

/**
 * Stores information about a student.
 * Uses composition by storing a Teacher object
 * instead of storing teacher information directly.
 */
public class Student {

    private String firstName;
    private String lastName;
    private double GPA;
    private Teacher homeroomTeacher;

    // Returns the student's first name.
    public String getFirstName() {
        return firstName;
    }

    // Sets the student's first name.
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    // Returns the student's last name.
    public String getLastName() {
        return lastName;
    }

    // Sets the student's last name.
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    // Returns the student's GPA.
    public double getGPA() {
        return GPA;
    }

    // Sets the student's GPA.
    public void setGPA(double GPA) {
        this.GPA = GPA;
    }

    // Returns the student's homeroom teacher.
    public Teacher getHomeroomTeacher() {
        return homeroomTeacher;
    }

    // Assigns a homeroom teacher to the student.
    public void setHomeroomTeacher(Teacher homeroomTeacher) {
        this.homeroomTeacher = homeroomTeacher;
    }
}