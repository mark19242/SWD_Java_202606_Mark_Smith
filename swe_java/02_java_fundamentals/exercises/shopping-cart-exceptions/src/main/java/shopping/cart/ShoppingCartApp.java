package shopping.cart;

import java.util.Scanner;

/**
 * A simple shopping cart exception handling exercise.
 */
public class ShoppingCartApp {

    /**
     * Runs the shopping cart program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        String[] menuOptions = {
                "Basketball",
                "Shoes",
                "Training Shirt"
        };

        int choice = 0;
        int quantity = 0;

        while (choice == 0) {
            try {
                System.out.println("Shopping Cart Menu");
                displayMenu(menuOptions);

                System.out.print("Choose an item: ");
                choice = Integer.parseInt(console.nextLine());

                if (choice < 1 || choice > menuOptions.length) {
                    throw new ArrayIndexOutOfBoundsException();
                }

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number");
                choice = 0;
            } catch (ArrayIndexOutOfBoundsException ex) {
                System.out.println("Please enter a valid menu option");
                choice = 0;
            }
        }

        while (quantity == 0) {
            try {
                System.out.print("Enter quantity: ");
                quantity = Integer.parseInt(console.nextLine());

            } catch (NumberFormatException ex) {
                System.out.println("Please enter a valid number");
                quantity = 0;
            }
        }

        System.out.println("\nItem selected: " + menuOptions[choice - 1]);
        System.out.println("Quantity: " + quantity);
        System.out.println("Shopping cart updated.");

        console.close();
    }

    /**
     * Displays the available menu options.
     *
     * @param menuOptions the list of items available to choose from
     */
    private static void displayMenu(String[] menuOptions) {

        for (int i = 0; i < menuOptions.length; i++) {
            System.out.println((i + 1) + ". " + menuOptions[i]);
        }
    }
}