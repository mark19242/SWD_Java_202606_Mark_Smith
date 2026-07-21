package learn.inventory.ui;
import learn.inventory.service.InventoryService;
import learn.inventory.model.Product;

import java.util.List;
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
                case "2" -> viewProducts();
                case "3" -> searchProduct();
                case "4" -> updateProduct();
                case "5" -> deleteProduct();
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

    private void viewProducts() {

        System.out.println("\n===== Inventory List =====");

        List<Product> products = inventoryService.getAllProducts();

        if (products.isEmpty()) {
            System.out.println("No products are currently in the inventory.");
            return;
        }

        System.out.printf(
                "%-12s %-25s %-12s %-12s%n",
                "Product ID",
                "Product Name",
                "Quantity",
                "Price"
        );

        System.out.println("---------------------------------------------------------------");

        for (Product product : products) {
            System.out.printf(
                    "%-12s %-25s %-12d $%-11.2f%n",
                    product.getProductID(),
                    product.getProductName(),
                    product.getQuantity(),
                    product.getPrice()
            );
        }
    }

    private void searchProduct() {

        System.out.println("\n===== Search Product =====");

        String searchTerm =
                promptForRequiredText("Enter Product ID or Name: ");

        // Search by product ID first.
        Product productByID =
                inventoryService.findProductById(searchTerm);

        if (productByID != null) {
            System.out.println("\nProduct Found:");
            System.out.println(productByID.displayProductInfo());
            return;
        }

        // If no ID matched, search by product name.
        List<Product> matchingProducts =
                inventoryService.findProductsByName(searchTerm);

        if (matchingProducts.isEmpty()) {
            System.out.println("\nProduct not found!");
            return;
        }

        System.out.println("\nMatching Products:");

        for (Product product : matchingProducts) {
            System.out.println("-------------------------");
            System.out.println(product.displayProductInfo());
        }
    }

    private void updateProduct() {

        System.out.println("\n===== Update Product =====");

        String productID =
                promptForRequiredText("Enter Product ID: ");

        Product product =
                inventoryService.findProductById(productID);

        if (product == null) {
            System.out.println("\nProduct not found!");
            return;
        }

        System.out.println("\nCurrent Details:");
        System.out.println(product.displayProductInfo());

        Integer newQuantity = promptForOptionalNonNegativeInt(
                "\nEnter New Quantity (or press Enter to skip): "
        );

        Double newPrice = promptForOptionalNonNegativeDouble(
                "Enter New Price (or press Enter to skip): "
        );

        if (newQuantity == null && newPrice == null) {
            System.out.println("\nNo changes were made.");
            return;
        }

        int quantityToUse = newQuantity == null
                ? product.getQuantity()
                : newQuantity;

        double priceToUse = newPrice == null
                ? product.getPrice()
                : newPrice;

        boolean wasUpdated = inventoryService.updateProduct(
                productID,
                quantityToUse,
                priceToUse
        );

        if (wasUpdated) {
            System.out.println("\nProduct updated successfully!");
            System.out.println("\nUpdated Details:");
            System.out.println(product.displayProductInfo());
        } else {
            System.out.println("\nUnable to update product.");
        }
    }

    private void deleteProduct() {

        System.out.println("\n===== Delete Product =====");

        String productID =
                promptForRequiredText("Enter Product ID: ");

        Product product =
                inventoryService.findProductById(productID);

        if (product == null) {
            System.out.println("\nProduct not found!");
            return;
        }

        System.out.println("\nProduct to Delete:");
        System.out.println(product.displayProductInfo());

        boolean confirmed = promptForYesNo(
                "\nAre you sure you want to delete this product? (Y/N): "
        );

        if (!confirmed) {
            System.out.println("\nDeletion canceled.");
            return;
        }

        boolean wasDeleted =
                inventoryService.deleteProduct(productID);

        if (wasDeleted) {
            System.out.println("\nProduct deleted successfully!");
        } else {
            System.out.println("\nUnable to delete product.");
        }
    }

    private boolean promptForYesNo(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("Y")) {
                return true;
            }

            if (input.equalsIgnoreCase("N")) {
                return false;
            }

            System.out.println("Please enter Y or N.");
        }
    }

    private Double promptForOptionalNonNegativeDouble(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return null;
            }

            try {
                double number = Double.parseDouble(input);

                if (number < 0) {
                    System.out.println(
                            "Please enter zero or a positive number."
                    );
                    continue;
                }

                return number;

            } catch (NumberFormatException ex) {
                System.out.println(
                        "Please enter a valid price or press Enter to skip."
                );
            }
        }
    }

    private Integer promptForOptionalNonNegativeInt(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.isBlank()) {
                return null;
            }

            try {
                int number = Integer.parseInt(input);

                if (number < 0) {
                    System.out.println(
                            "Please enter zero or a positive number."
                    );
                    continue;
                }

                return number;

            } catch (NumberFormatException ex) {
                System.out.println(
                        "Please enter a valid whole number or press Enter to skip."
                );
            }
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

        boolean wantsToExit = promptForYesNo(
                "\nAre you sure you want to exit? (Y/N): "
        );

        return !wantsToExit;
    }

    private void pause() {
        System.out.print("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }
}