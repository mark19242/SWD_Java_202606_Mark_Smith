package org.example.model;

// Represents one product that the store can sell.
public class Product {

    private String id;
    private String name;
    private double price;

    public Product(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    // Makes the product display nicely when printed.
    @Override
    public String toString() {
        return id + " - " + name + " - $" + String.format("%.2f", price);
    }
}