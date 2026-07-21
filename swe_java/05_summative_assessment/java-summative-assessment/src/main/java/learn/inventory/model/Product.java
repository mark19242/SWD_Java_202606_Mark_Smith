package learn.inventory.model;

public class Product {

    private String productID;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    public String getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }

    // Adds stock only when the amount is positive.
    public boolean addStock(int amount) {
        if (amount <= 0) {
            return false;
        }

        quantity += amount;
        return true;
    }

    // Prevents the quantity from dropping below zero.
    public boolean removeStock(int amount) {
        if (amount <= 0 || amount > quantity) {
            return false;
        }

        quantity -= amount;
        return true;
    }

    // Prevents a product from receiving a negative price.
    public boolean updatePrice(double newPrice) {
        if (newPrice < 0) {
            return false;
        }

        price = newPrice;
        return true;
    }

    public String displayProductInfo() {
        return String.format(
                "Product ID: %s%nProduct Name: %s%nQuantity: %d%nPrice: $%.2f",
                productID,
                productName,
                quantity,
                price
        );
    }
}