package org.example;

import java.util.Scanner;

public class Main {

    private static Scanner inputScanner = new Scanner(System.in);

    enum DEVICE {
        FRIDGE,
        TV,
        TOASTER
    }

    public static void main(String[] args) {

        Connectable[] devices = new Connectable[5];

        System.out.println("Welcome to the Device Manager App!!");
        System.out.println("===================================\n");

        System.out.println("Setup the devices");
        System.out.println("=================\n");

        for (int i = 0; i < devices.length; i++) {

            System.out.println("Select device type for device #" + (i + 1));

            for (DEVICE d : DEVICE.values()) {
                System.out.printf("%d) %s%n", d.ordinal() + 1, d.name());
            }

            int selection = promptInt("Enter your choice: ", 1, DEVICE.values().length);

            System.out.print("What is the name of this new device?: ");
            String dName = inputScanner.nextLine();

            Connectable newDevice = switch (DEVICE.values()[selection - 1]) {
                case FRIDGE -> new Fridge(dName);
                case TV -> new TV(dName);
                case TOASTER -> new Toaster(dName);
            };

            devices[i] = newDevice;
            System.out.println();
        }

        System.out.println("Current Device States");
        System.out.println("=====================");

        for (Connectable device : devices) {
            printDeviceStatus(device);
        }

        System.out.println("\nInteract with the devices");
        System.out.println("=========================");

        int menuChoice = 0;

        while (menuChoice != 5) {

            System.out.println("\nDevice Menu Options:");
            System.out.println("1. Get Device Name");
            System.out.println("2. Turn On Device");
            System.out.println("3. Turn Off Device");
            System.out.println("4. Get Device Status");
            System.out.println("5. Quit");

            menuChoice = promptInt("Enter menu choice: ", 1, 5);

            if (menuChoice == 5) {
                break;
            }

            System.out.println("\nSelect a device:");

            for (int i = 0; i < devices.length; i++) {
                System.out.println((i + 1) + ". " + devices[i].getName());
            }

            int deviceChoice = promptInt("Choose device 1-5: ", 1, devices.length);
            Connectable selectedDevice = devices[deviceChoice - 1];

            if (menuChoice == 1) {

                System.out.println("Device Name: " + selectedDevice.getName());

            } else if (menuChoice == 2) {

                selectedDevice.turnOn();
                System.out.println(selectedDevice.getName() + " is now ON.");

            } else if (menuChoice == 3) {

                selectedDevice.turnOff();
                System.out.println(selectedDevice.getName() + " is now OFF.");

            } else if (menuChoice == 4) {

                printDeviceStatus(selectedDevice);
            }
        }

        System.out.println("\nThanks for using the Device Manager App. Bye!");
    }

    public static void printDeviceStatus(Connectable device) {

        String status;

        if (device.getState()) {
            status = "ON";
        } else {
            status = "OFF";
        }

        System.out.println(device.getName() + " is " + status + ".");
    }

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