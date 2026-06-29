package org.example;

public class Main {

    public static void main(String[] args) {

        // Create a Student and a Teacher object.
        Student student = new Student();
        Teacher teacher = new Teacher();

        ConsoleIO.display("Welcome to Better School Tracker!");

        // Collect student information.
        student.setFirstName(ConsoleIO.promptString("Enter Student First Name"));
        student.setLastName(ConsoleIO.promptString("Enter Student Last Name"));
        student.setGPA(ConsoleIO.promptDouble("Enter Student's GPA"));

        // Collect teacher information.
        teacher.setFirstName(ConsoleIO.promptString("Enter Homeroom Teacher's First Name"));
        teacher.setLastName(ConsoleIO.promptString("Enter Homeroom Teacher's Last Name"));

        // Associate the teacher with the student.
        student.setHomeroomTeacher(teacher);

        // Display the completed student information.
        System.out.println("\nStudent: "
                + student.getFirstName() + " "
                + student.getLastName());

        System.out.println("GPA: "
                + student.getGPA());

        System.out.println("Homeroom Teacher: "
                + student.getHomeroomTeacher().getFirstName() + " "
                + student.getHomeroomTeacher().getLastName());
    }
}