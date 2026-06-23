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
                rentLocker(lockers);
            } else if (menuChoice.equals("2")) {
                accessLocker(inputScanner, lockers);
            } else if (menuChoice.equals("3")) {
                System.out.println("Release a Locker selected.");
            } else {
                keepRunning = false;
                System.out.println("Program ended.");
            }
        }

    }
    private static void rentLocker(String[] lockers) {

        int lockerIndex = findNextAvailableLocker(lockers);

        if (lockerIndex == -1) {
            System.out.println("Sorry, there are no lockers available.");
        } else {
            String pin = generatePin();
            lockers[lockerIndex] = pin;

            System.out.println("Locker rented successfully.");
            System.out.println("Locker Number: " + (lockerIndex + 1));
            System.out.println("PIN: " + pin);
        }
    }

    private static void accessLocker(Scanner inputScanner, String[] lockers) {

        System.out.print("Enter locker number: ");
        int lockerNumber = Integer.parseInt(inputScanner.nextLine());

        System.out.print("Enter PIN: ");
        String pin = inputScanner.nextLine();

        int lockerIndex = lockerNumber - 1;

        if (lockerIndex < 0 || lockerIndex >= lockers.length) {
            System.out.println("Invalid locker number.");
        } else if (lockers[lockerIndex] == null) {
            System.out.println("That locker is currently available.");
        } else if (!lockers[lockerIndex].equals(pin)) {
            System.out.println("Incorrect PIN.");
        } else {
            System.out.println("Access granted. Locker " + lockerNumber + " opened.");
        }
    }

    private static int findNextAvailableLocker(String[] lockers) {

        for (int i = 0; i < lockers.length; i++) {
            if (lockers[i] == null) {
                return i;
            }
        }

        return -1;
    }

    private static String generatePin() {

        int randomNumber = (int) (Math.random() * 10000);

        return String.format("%04d", randomNumber);
    }
}