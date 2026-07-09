package org.example.ui;

import java.util.Scanner;

// Handles all console input and output.
public class ConsoleIO {

    private Scanner scanner = new Scanner(System.in);

    public void printMessage(String message) {
        System.out.println(message);
    }

    public void printBlankLine() {
        System.out.println();
    }

    public String promptString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public int promptInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);

            try {
                int number = Integer.parseInt(scanner.nextLine());

                if (number >= min && number <= max) {
                    return number;
                }

                System.out.println("Please enter a number from " + min + " to " + max + ".");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public int promptPositiveInt(String prompt) {
        while (true) {
            System.out.print(prompt);

            try {
                int number = Integer.parseInt(scanner.nextLine());

                if (number > 0) {
                    return number;
                }

                System.out.println("Please enter a number greater than 0.");
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }
}