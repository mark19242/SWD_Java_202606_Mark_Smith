import java.util.ArrayList;
import java.util.Collections;

public class App {

    public static void main(String[] args) {

        // Create an ArrayList that will store student names.
        ArrayList<String> students = new ArrayList<>();

        // Add at least five student names to the list.
        students.add("Ava");
        students.add("Olivia");
        students.add("Emma");
        students.add("John");
        students.add("Liam");

        // Get the third student.
        // Indexes start at 0, so index 2 is the third item.
        String thirdStudent = students.get(2);
        System.out.println("Third student: " + thirdStudent);

        // Remove the second student.
        // Index 1 is the second item in the list.
        String removedStudent = students.remove(1);
        System.out.println("Removed student: " + removedStudent);

        // Print how many students are left after removing one.
        System.out.println("Total students remaining: " + students.size());

        // Check if the list is empty.
        // This will print false because students are still in the list.
        System.out.println("List is empty: " + students.isEmpty());

        // Sort the list alphabetically.
        Collections.sort(students);

        // Print the sorted list.
        System.out.println("Sorted list: " + students);
    }
}