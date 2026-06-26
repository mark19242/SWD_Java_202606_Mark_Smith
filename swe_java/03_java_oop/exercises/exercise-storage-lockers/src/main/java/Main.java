import model.Locker;

import java.util.Scanner;

public class Main {

    private Scanner inputScanner;

    static void main() {

        Main app = new Main();
        app.run();

    }


    private void run() {

        inputScanner = new Scanner(System.in);

        LockerManager lockerManager = new LockerManager();

        String choice;

        System.out.println("Welcome to the Storage Locker Manager!");

        // =========================
        // Main Menu
        // =========================

        do {

            System.out.println("\n1. Add Locker");
            System.out.println("2. Remove Locker");
            System.out.println("3. Store Item");
            System.out.println("4. Retrieve Item");
            System.out.println("5. Display All Lockers");
            System.out.println("6. Exit");

            System.out.print("Choose an option: ");
            choice = inputScanner.nextLine();

            switch (choice) {

                // =========================
                // Add Locker
                // =========================

                case "1":

                    System.out.print("Enter locker ID: ");
                    String addId = inputScanner.nextLine();

                    lockerManager.addLocker(addId);

                    break;

                // =========================
                // Remove Locker
                // =========================

                case "2":

                    System.out.print("Enter locker ID: ");
                    String removeId = inputScanner.nextLine();

                    lockerManager.removeLocker(removeId);

                    break;

                // =========================
                // Store Item
                // =========================

                case "3":

                    System.out.print("Enter locker ID: ");
                    String storeId = inputScanner.nextLine();

                    Locker locker = lockerManager.getLocker(storeId);

                    // Validate locker exists
                    while (locker == null) {

                        System.out.println("Locker not found.");

                        System.out.print("Enter another locker ID: ");

                        storeId = inputScanner.nextLine();

                        locker = lockerManager.getLocker(storeId);

                    }

                    System.out.print("Enter item to store: ");

                    String item = inputScanner.nextLine();

                    locker.storeItem(item);

                    System.out.println("Item stored.");

                    break;

                // =========================
                // Retrieve Item
                // =========================

                case "4":

                    System.out.print("Enter locker ID: ");

                    String retrieveId = inputScanner.nextLine();

                    Locker retrieveLocker = lockerManager.getLocker(retrieveId);

                    // Validate locker exists
                    while (retrieveLocker == null) {

                        System.out.println("Locker not found.");

                        System.out.print("Enter another locker ID: ");

                        retrieveId = inputScanner.nextLine();

                        retrieveLocker = lockerManager.getLocker(retrieveId);

                    }

                    retrieveLocker.removeItem();

                    System.out.println("Locker emptied.");

                    break;

                // =========================
                // Display All Lockers
                // =========================

                case "5":

                    lockerManager.displayAllLockers();

                    break;

                // =========================
                // Exit Program
                // =========================

                case "6":

                    System.out.println("Exiting program.");

                    break;

                // =========================
                // Invalid Menu Option
                // =========================

                default:

                    System.out.println("Invalid option.");

            }

        } while (!choice.equals("6"));

    }

}

