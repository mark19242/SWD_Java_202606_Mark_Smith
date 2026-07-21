package learn.inventory.ui;
import learn.inventory.service.InventoryService;
import learn.inventory.model.Product;

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
                case "1" -> addProduct();
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

    private void addProduct() {

        System.out.println("\n===== Add Product =====");

        String productID = promptForUniqueProductID();
        String productName = promptForRequiredText("Enter Product Name: ");
        int quantity = promptForNonNegativeInt("Enter Quantity: ");
        double price = promptForNonNegativeDouble("Enter Price: ");

        Product product = new Product(
                productID,
                productName,
                quantity,
                price
        );

        boolean wasAdded = inventoryService.addProduct(product);

        if (wasAdded) {
            System.out.println("\nProduct added successfully!");
        } else {
            System.out.println("\nUnable to add product.");
        }
    }

    private String promptForRequiredText(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isBlank()) {
                return input;
            }

            System.out.println("This field cannot be empty.");
        }
    }

    private String promptForUniqueProductID() {

        while (true) {
            System.out.print("Enter Product ID: ");
            String productID = scanner.nextLine().trim();

            if (productID.isBlank()) {
                System.out.println("Product ID cannot be empty.");
                continue;
            }

            if (inventoryService.findProductById(productID) != null) {
                System.out.println("That Product ID is already in use.");
                continue;
            }

            return productID;
        }
    }

    private int promptForNonNegativeInt(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                int number = Integer.parseInt(input);

                if (number < 0) {
                    System.out.println("Please enter zero or a positive number.");
                    continue;
                }

                return number;

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid whole number.");
            }
        }
    }

    private double promptForNonNegativeDouble(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                double number = Double.parseDouble(input);

                if (number < 0) {
                    System.out.println("Please enter zero or a positive number.");
                    continue;
                }

                return number;

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid price.");
            }
        }
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