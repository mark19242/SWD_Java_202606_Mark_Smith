import java.util.Scanner;

public class StorageLockerApp {

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        String[] lockers = new String[10];

        displayMainMenu(inputScanner, lockers);
    }

    private static void displayMainMenu(Scanner inputScanner, String[] lockers) {

        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("\nWhat would you like to do next?");
            System.out.println("1. Rent a Locker");
            System.out.println("2. Access a Locker");
            System.out.println("3. Release a Locker");
            System.out.println("---");
            System.out.println("Any other key to exit.");
            System.out.print("Enter choice: ");

            String menuChoice = inputScanner.nextLine();

            if (menuChoice.equals("1")) {
                System.out.println("Rent a Locker selected.");
            } else if (menuChoice.equals("2")) {
                System.out.println("Access a Locker selected.");
            } else if (menuChoice.equals("3")) {
                System.out.println("Release a Locker selected.");
            } else {
                keepRunning = false;
                System.out.println("Program ended.");
            }
        }

    }
}