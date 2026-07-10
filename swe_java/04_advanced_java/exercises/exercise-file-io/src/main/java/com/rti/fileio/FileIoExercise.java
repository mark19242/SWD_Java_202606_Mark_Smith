package com.rti.fileio;

import java.io.File;
import java.io.IOException;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.util.Scanner;

/**
 * File I/O exercise.
 *
 * <p>Work through the six methods below in order. Each one is stubbed with a
 * {@code // TODO:} comment and a placeholder {@code System.out} line so the
 * project compiles and runs before you start. Replace each stub with a real
 * implementation using classes from {@code java.io}.
 *
 * <p>The goal: create a file named {@code student_data.txt}, write and append
 * some student records to it, read them back, explore absolute vs. relative
 * paths, and finally delete the file.
 */
public class FileIoExercise {

    /** Name of the file every method in this exercise operates on. */
    private static final String FILE_NAME = "student_data.txt";

    public static void main(String[] args) {
        System.out.println("=== File I/O Exercise ===");

        createFile();
        writeData();
        appendData();
        readData();
        comparePaths();
        deleteFile();

        System.out.println("=== Done ===");
    }

    /**
     * Part 1 — Create a new file named {@code student_data.txt}.
     * Report whether the file was created or already existed.
     */
    static void createFile() {
        File file = new File(FILE_NAME);

        try {
            if (file.createNewFile()) {
                System.out.println("Created: " + FILE_NAME);
            } else {
                System.out.println(FILE_NAME + " already exists.");
            }
        } catch (IOException e) {
            System.out.println("Unable to create file.");
        }

    }

    /**
     * Part 2 — Write the initial student records to the file (overwrite mode).
     * Lines: "Alice, A", "Bob, B", "Charlie, A+".
     */
    static void writeData() {

        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {

            writer.println("Alice, A");
            writer.println("Bob, B");
            writer.println("Charlie, A+");

            System.out.println("Initial student records written.");

        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file.");
        }
    }

    /**
     * Part 3 — Append additional records without overwriting existing content.
     * Lines: "David, B+", "Eva, A".
     */
    static void appendData() {

        try (PrintWriter writer =
                     new PrintWriter(new FileOutputStream(FILE_NAME, true))) {

            writer.println("David, B+");
            writer.println("Eva, A");

            System.out.println("Additional student records appended.");

        } catch (FileNotFoundException e) {
            System.out.println("Unable to append to file.");
        }
    }

    /**
     * Part 4 — Read the file line by line and print each line to the console.
     */
    static void readData() {

        File file = new File(FILE_NAME);

        try (Scanner fileReader = new Scanner(file)) {

            System.out.println("Student records:");

            while (fileReader.hasNextLine()) {
                String line = fileReader.nextLine();
                System.out.println(line);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file.");
        }
    }

    /**
     * Part 5 — Print the absolute and relative paths of the file and compare them.
     */
    static void comparePaths() {

        File file = new File(FILE_NAME);

        System.out.println("Relative path: " + file.getPath());
        System.out.println("Absolute path: " + file.getAbsolutePath());
    }

    /**
     * Part 6 — Delete the file and report whether the deletion succeeded.
     */
    static void deleteFile() {

        File file = new File(FILE_NAME);

        if (file.delete()) {
            System.out.println(FILE_NAME + " was deleted successfully.");
        } else {
            System.out.println("Unable to delete " + FILE_NAME + ".");
        }
    }
}
