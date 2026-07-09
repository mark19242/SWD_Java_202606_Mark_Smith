package org.example.controller;

import org.example.model.CartItem;
import org.example.model.Product;
import org.example.service.CartService;
import org.example.service.ShoppingCartService;
import org.example.ui.ConsoleIO;

import java.util.List;

// Controls the menu flow for the shopping cart app.
public class ShoppingCartController {

    private CartService cartService;
    private ConsoleIO io;

    public ShoppingCartController() {
        cartService = new ShoppingCartService();
        io = new ConsoleIO();
    }

    public void run() {
        boolean running = true;

        io.printMessage("Welcome to the Shopping Cart App!");

        while (running) {
            displayMenu();

            int choice = io.promptInt("Choose an option: ", 1, 5);

            switch (choice) {
                case 1:
                    displayCart();
                    break;
                case 2:
                    removeItem();
                    break;
                case 3:
                    addItem();
                    break;
                case 4:
                    checkout();
                    break;
                case 5:
                    io.printMessage("Goodbye!");
                    running = false;
                    break;
            }
        }
    }

    private void displayMenu() {
        io.printBlankLine();
        io.printMessage("Main Menu");
        io.printMessage("1. Display Cart");
        io.printMessage("2. Remove an Item");
        io.printMessage("3. Add an Item");
        io.printMessage("4. Checkout");
        io.printMessage("5. Exit");
    }

    private void displayCart() {
        io.printBlankLine();
        io.printMessage("Current Cart");

        if (cartService.isCartEmpty()) {
            io.printMessage("Your cart is empty.");
            return;
        }

        List<CartItem> cartItems = cartService.getCartItems();

        for (int i = 0; i < cartItems.size(); i++) {
            io.printMessage((i + 1) + ". " + cartItems.get(i));
        }

        io.printMessage("Cart Total: $" + formatCurrency(cartService.getCartTotal()));
    }

    private void addItem() {
        io.printBlankLine();
        io.printMessage("Available Products");

        List<Product> products = cartService.getInventoryProducts();

        for (Product product : products) {
            io.printMessage(product.toString());
        }

        String productId = io.promptString("Enter the product id to add: ");
        int quantity = io.promptPositiveInt("Enter quantity: ");

        boolean added = cartService.addItem(productId, quantity);

        if (added) {
            io.printMessage("Item added to cart.");
        } else {
            io.printMessage("Item could not be added. Please check the product id.");
        }
    }

    private void removeItem() {
        if (cartService.isCartEmpty()) {
            io.printMessage("Your cart is empty. There is nothing to remove.");
            return;
        }

        displayCart();

        List<CartItem> cartItems = cartService.getCartItems();

        int itemNumber = io.promptInt("Choose the item number to remove: ", 1, cartItems.size());
        CartItem selectedItem = cartItems.get(itemNumber - 1);

        int quantity = io.promptInt("Enter quantity to remove: ", 1, selectedItem.getQuantity());

        boolean removed = cartService.removeItem(selectedItem.getProduct().getId(), quantity);

        if (removed) {
            io.printMessage("Item quantity updated.");
        } else {
            io.printMessage("Item could not be removed.");
        }
    }

    private void checkout() {
        if (cartService.isCartEmpty()) {
            io.printMessage("Your cart is empty. There is nothing to checkout.");
            return;
        }

        io.printBlankLine();
        io.printMessage("Checkout Summary");
        displayCart();

        double total = cartService.checkout();

        io.printMessage("Total amount due: $" + formatCurrency(total));
        io.printMessage("Cart has been emptied.");
    }

    private String formatCurrency(double amount) {
        return String.format("%.2f", amount);
    }
}