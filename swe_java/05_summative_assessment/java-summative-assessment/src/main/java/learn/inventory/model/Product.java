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
}