package org.example.model;

// Represents a product that has been added to the cart.
public class CartItem {

    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    // Adds more of the same product to the cart.
    public void increaseQuantity(int amount) {
        quantity += amount;
    }

    // Removes some quantity from this cart item.
    public void decreaseQuantity(int amount) {
        quantity -= amount;
    }

    // Calculates price * quantity for this one cart item.
    public double getLineTotal() {
        return product.getPrice() * quantity;
    }

    @Override
    public String toString() {
        return product.getName()
                + " | Qty: " + quantity
                + " | Total: $" + String.format("%.2f", getLineTotal());
    }
}