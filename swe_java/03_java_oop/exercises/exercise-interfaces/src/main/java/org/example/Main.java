package org.example;

import java.util.Scanner;

/**
 * Runs the Device Manager App and allows the user to create
 * and interact with connectable devices.
 */
public class Main {

    private static Scanner inputScanner = new Scanner(System.in);

    /**
     * Lists the supported device types that the user can create.
     */
    enum DEVICE {
        FRIDGE,
        TV,
        TOASTER
    }

    public static void main(String[] args) {
        // Your existing main code stays the same here.
    }

    /**
     * Prints the current power status of a connectable device.
     *
     * @param device the device whose status will be displayed
     */
    public static void printDeviceStatus(Connectable device) {

        String status;

        if (device.getState()) {
            status = "ON";
        } else {
            status = "OFF";
        }

        System.out.println(device.getName() + " is " + status + ".");
    }

    /**
     * Prompts the user for an integer until valid numeric input is entered.
     *
     * @param message the message shown to the user
     * @return the valid integer entered by the user
     */
    public static int promptInt(String message) {

        int result = 0;

        while (true) {

            try {
                System.out.print(message);
                String input = inputScanner.nextLine();
                result = Integer.parseInt(input);
                break;

            } catch (NumberFormatException ex) {
                System.out.println("Invalid input. Try again.");
            }
        }

        return result;
    }

    /**
     * Prompts the user for an integer within a specific range.
     *
     * @param message the message shown to the user
     * @param min the lowest allowed value
     * @param max the highest allowed value
     * @return the valid integer entered by the user
     */
    public static int promptInt(String message, int min, int max) {

        int result = 0;
        boolean isValid = false;

        while (!isValid) {

            result = promptInt(message);

            if (result > max || result < min) {
                System.out.println("Entry out of range: " + min + " - " + max + ". Try again.");
            } else {
                isValid = true;
            }
        }

        return result;
    }
}