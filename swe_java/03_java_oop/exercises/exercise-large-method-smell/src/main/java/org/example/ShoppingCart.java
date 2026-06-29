package org.example;

public class ShoppingCart {

    // TODO: FIX discountCode naming
    // TODO: Break Totals, Discount, and Tax into separate methods

    /**
     * Calculates the final total for a shopping cart.
     */
    public double checkoutShoppingCart(Item[] items, double taxRate, double discountCode) {

        // Calculate the total cost of all items.
        double subtotal = calcItemTotal(items);

        // Apply the discount to the subtotal.
        subtotal = calcDiscountedTotal(subtotal, discountCode);

        // Calculate tax using the discounted subtotal.
        double tax = calcTax(subtotal, taxRate);

        // Return the final total.
        return subtotal + tax;
    }

    /**
     * Calculates the total price of all items in the cart.
     */
    private double calcItemTotal(Item[] items) {

        double result = 0.0;

        // Add the price of each item to the running total.
        for (Item item : items) {
            result += item.getPrice();
        }

        return result;
    }

    /**
     * Applies the discount to the subtotal.
     */
    private double calcDiscountedTotal(double subtotal, double discountCode) {

        double discount = subtotal * discountCode;

        return subtotal - discount;
    }

    /**
     * Calculates the tax based on the current subtotal.
     */
    private double calcTax(double subtotal, double taxRate) {

        return subtotal * taxRate;
    }
}