package learn.inventory.ui;

import learn.inventory.service.InventoryService;
import learn.inventory.model.Product;
import learn.inventory.model.StandardProduct;
import learn.inventory.model.PerishableProduct;

import java.io.IOException;
import java.nio.file.NoSuchFileException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;


/**
 * Provides the console-based user interface for the Inventory Manager.
 *
 * <p>This class displays menu options, collects and validates user input,
 * delegates inventory operations to {@link InventoryService}, and displays
 * the results to the user.</p>
 */
public class MainMenu {



    private final Scanner scanner = new Scanner(System.in);
    private final InventoryService inventoryService;
    private static final long DISPLAY_DELAY_MS = 400;


    /**
     * Creates the main menu using the supplied inventory service.
     *
     * @param inventoryService service used to manage inventory operations
     */

    public MainMenu(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    /**
     * Runs the main application loop until the user confirms that they want
     * to exit.
     */

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
                case "6" -> saveInventory();
                case "7" -> loadInventory();
                case "8" -> running = confirmExit();
                default -> System.out.println("\nPlease enter a number from 1 through 8.");
            }

            if (running) {
                pause();
            }
        }

        System.out.println("\nThank you for using Inventory Manager!");
    }

    /**
     * Displays the available Inventory Manager menu options.
     */
    private void displayMenu() {

        displayDivider();
        System.out.println("                    INVENTORY MANAGER");
        displayDivider();

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

    /**
     * Displays the decorative divider used around the main menu heading.
     */
    private void displayDivider() {
        System.out.println(".-.-.  .-.-.  .-.-.  .-.-.  .-.-.  .-.-.  .-.-.");
        delayDisplay(DISPLAY_DELAY_MS);

        System.out.println("/ / \\\\ \\\\/ / \\\\ \\\\/ / \\\\ \\\\/ / \\\\ \\\\/ / \\\\ \\\\/ / \\\\ \\\\/ / \\\\ \\\\");
        delayDisplay(DISPLAY_DELAY_MS);

        System.out.println("`-'   `-'   `-'   `-'   `-'   `-'   `-'");
        delayDisplay(DISPLAY_DELAY_MS);
    }

    /**
     * Briefly pauses the display to make console output easier to follow.
     *
     * @param milliseconds length of the pause in milliseconds
     */
    private void delayDisplay(long milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException ex) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Collects product information, creates either a standard or perishable
     * product, and requests that the service add it to the inventory.
     */
    private void addProduct() {

        System.out.println("\n===== Add Product =====");

        int productType = promptForProductType();

        String productID = promptForUniqueProductID();
        String productName = promptForRequiredText("Enter Product Name: ");
        int quantity = promptForNonNegativeInt("Enter Quantity: ");
        double price = promptForNonNegativeDouble("Enter Price: ");

        Product product;

        if (productType == 1) {
            product = new StandardProduct(
                    productID,
                    productName,
                    quantity,
                    price
            );
        } else {
            LocalDate expirationDate = promptForExpirationDate(
                    "Enter Expiration Date (YYYY-MM-DD): "
            );

            product = new PerishableProduct(
                    productID,
                    productName,
                    quantity,
                    price,
                    expirationDate
            );
        }

        boolean wasAdded = inventoryService.addProduct(product);

        if (wasAdded) {
            System.out.println("\nProduct added successfully!");
        } else {
            System.out.println("\nUnable to add product.");
        }
    }

    /**
     * Displays all current inventory products in a formatted table.
     *
     * <p>If the inventory is empty, an informational message is displayed
     * instead.</p>
     */

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

    /**
     * Searches for a product by ID first and then by full or partial name.
     *
     * <p>Matching products are displayed when found. Otherwise, the user is
     * informed that no product matched the search.</p>
     */

    private void searchProduct() {

        System.out.println("\n===== Search Product =====");

        String searchTerm =
                promptForRequiredText("Enter Product ID or Name: ");

        Product productByID =
                inventoryService.findProductById(searchTerm);

        if (productByID != null) {
            System.out.println("\nProduct Found:");
            System.out.println(productByID.displayProductInfo());
            return;
        }

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

    /**
     * Allows the user to update the quantity, price, or both values for an
     * existing product.
     *
     * <p>Pressing Enter without entering a value leaves that product value
     * unchanged.</p>
     */

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

    /**
     * Locates a product by ID and requests confirmation before deleting it.
     */

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

    /**
     * Requests that the inventory service save the current inventory.
     *
     * <p>Any file-writing error is handled and reported to the user without
     * terminating the application.</p>
     */

    private void saveInventory() {

        System.out.println("\n===== Save Inventory =====");
        System.out.println("Saving inventory data...");

        try {
            inventoryService.saveInventory();

            System.out.println(
                    "Inventory successfully saved to data/inventory.txt!"
            );

        } catch (IOException ex) {
            System.out.println(
                    "Error saving inventory data!"
            );
        }
    }

    /**
     * Requests that the inventory service load products from persistent storage.
     *
     * <p>This method separately handles a missing file and other file-reading
     * errors so the application can continue running.</p>
     */

    private void loadInventory() {

        System.out.println("\n===== Load Inventory =====");
        System.out.println("Loading inventory data...");

        try {
            inventoryService.loadInventory();

            int productCount =
                    inventoryService.getAllProducts().size();

            System.out.printf(
                    "Inventory successfully loaded from " +
                            "data/inventory.txt!%n" +
                            "%d product(s) loaded.%n",
                    productCount
            );

        } catch (NoSuchFileException ex) {
            System.out.println(
                    "No saved inventory file was found."
            );

        } catch (IOException ex) {
            System.out.println(
                    "Error loading inventory data!"
            );
        }
    }

    /**
     * Prompts the user to select either a standard or perishable product.
     *
     * @return {@code 1} for a standard product or {@code 2} for a perishable
     *         product
     */

    private int promptForProductType() {

        while (true) {
            System.out.println("1. Standard Product");
            System.out.println("2. Perishable Product");
            System.out.print("Select Product Type: ");

            String input = scanner.nextLine().trim();

            if (input.equals("1")) {
                return 1;
            }

            if (input.equals("2")) {
                return 2;
            }

            System.out.println(
                    "Please enter 1 for Standard or 2 for Perishable."
            );
        }
    }

    /**
     * Repeatedly prompts for an expiration date until the user enters a valid
     * ISO date in {@code YYYY-MM-DD} format.
     *
     * @param message prompt displayed to the user
     * @return the validated expiration date
     */

    private LocalDate promptForExpirationDate(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            try {
                return LocalDate.parse(input);

            } catch (DateTimeParseException ex) {
                System.out.println(
                        "Please enter the date using the YYYY-MM-DD format."
                );
            }
        }
    }

    /**
     * Repeatedly prompts the user until either Y or N is entered.
     *
     * @param message confirmation prompt displayed to the user
     * @return {@code true} for Y or {@code false} for N
     */

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

    /**
     * Prompts for an optional nonnegative decimal value.
     *
     * @param message prompt displayed to the user
     * @return the entered value, or {@code null} when the user presses Enter
     *         without entering a value
     */

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


    /**
     * Prompts for an optional nonnegative whole number.
     *
     * @param message prompt displayed to the user
     * @return the entered value, or {@code null} when the user presses Enter
     *         without entering a value
     */

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

    /**
     * Repeatedly prompts until the user enters nonblank text.
     *
     * @param message prompt displayed to the user
     * @return the validated text with surrounding whitespace removed
     */

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

    /**
     * Repeatedly prompts for a nonblank product ID that is not already used
     * by another inventory product.
     *
     * @return a unique product ID
     */


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

    /**
     * Repeatedly prompts for a valid nonnegative whole number.
     *
     * @param message prompt displayed to the user
     * @return the validated whole number
     */

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

    /**
     * Repeatedly prompts for a valid nonnegative decimal number.
     *
     * @param message prompt displayed to the user
     * @return the validated decimal number
     */

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

    /**
     * Asks the user whether the application should exit.
     *
     * <p>The returned value represents whether the main application loop should
     * continue running.</p>
     *
     * @return {@code false} when the user confirms exiting; otherwise
     *         {@code true}
     */

    private boolean confirmExit() {

        boolean wantsToExit = promptForYesNo(
                "\nAre you sure you want to exit? (Y/N): "
        );

        return !wantsToExit;
    }

    /**
     * Pauses the application until the user presses Enter.
     */

    private void pause() {
        System.out.print("\nPress Enter to return to the main menu...");
        scanner.nextLine();
    }
}