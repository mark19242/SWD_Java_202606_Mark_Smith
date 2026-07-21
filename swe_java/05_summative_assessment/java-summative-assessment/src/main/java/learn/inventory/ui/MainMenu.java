package learn.inventory.ui;
import learn.inventory.service.InventoryService;

import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final InventoryService inventoryService;

    public MainMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public void run() {

        boolean running = true;

        while (running) {
            displayMenu();

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> System.out.println("\nAdd Product selected.");
                case "2" -> System.out.println("\nView Products selected.");
                case "3" -> System.out.println("\nSearch Product selected.");
                case "4" -> System.out.println("\nUpdate Product selected.");
                case "5" -> System.out.println("\nDelete Product selected.");
                case "6" -> System.out.println("\nSave Inventory selected.");
                case "7" -> System.out.println("\nLoad Inventory selected.");
                case "8" -> running = confirmExit();
                default -> System.out.println("\nPlease enter a number from 1 through 8.");
            }

            if (running) {
                pause();
            }
        }

        System.out.println("\nThank you for using Inventory Manager!");
    }

    private void displayMenu() {
        System.out.println("\n===== Inventory Manager =====");
        System.out.println("1. Add Product");
        System.out.println("2. View Products");
        System.out.println("3. Search Product");
        System.out.println("4. Update Product");
        System.out.println("5. Delete Product");
        System.out.println("6. Save Inventory to File");
        System.out.println("7. Load Inventory from File");
        System.out.println("8. Exit");
        System.out.print("Enter your choice: ");
    }

    private boolean confirmExit() {
        System.out.print("\nAre you sure you want to exit? (Y/N): ");
        String confirmation = scanner.nextLine().trim();

        return !confirmation.equalsIgnoreCase("Y");
    }

    private void pause() {
        System.out.print("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }
}