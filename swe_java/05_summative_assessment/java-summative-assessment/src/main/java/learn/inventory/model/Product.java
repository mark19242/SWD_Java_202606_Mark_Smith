package learn.inventory.model;

/**
 * Represents the shared data and behavior of all inventory product types.
 *
 * <p>This abstract class stores common product information and provides
 * controlled methods for changing stock and price. Concrete subclasses
 * identify the specific type of product.</p>
 */

public abstract class Product {

    private String productID;
    private String productName;
    private int quantity;
    private double price;

    /**
     * Creates a product with its identifying and inventory information.
     *
     * @param productID unique identifier for the product
     * @param productName name of the product
     * @param quantity starting quantity in inventory
     * @param price starting price of the product
     */

    public Product(String productID, String productName, int quantity, double price) {
        this.productID = productID;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    /**
     * Returns the product's unique identifier.
     *
     * @return the product ID
     */

    public String getProductID() {
        return productID;
    }

    /**
     * Returns the product's name.
     *
     * @return the product name
     */

    public String getProductName() {
        return productName;
    }

    /**
     * Returns the current inventory quantity.
     *
     * @return the quantity currently in stock
     */

    public int getQuantity() {
        return quantity;
    }
    /**
     * Returns the current product price.
     *
     * @return the product price
     */
    public double getPrice() {
        return price;
    }

    /**
     * Adds a positive amount to the current inventory quantity.
     *
     * @param amount amount of stock to add
     * @return {@code true} when stock is added; {@code false} when the
     *         amount is zero or negative
     */

    public boolean addStock(int amount) {
        if (amount <= 0) {
            return false;
        }

        quantity += amount;
        return true;
    }

    /**
     * Removes stock without allowing the quantity to fall below zero.
     *
     * @param amount amount of stock to remove
     * @return {@code true} when stock is removed; {@code false} when the
     *         amount is invalid or greater than the available quantity
     */

    public boolean removeStock(int amount) {
        if (amount <= 0 || amount > quantity) {
            return false;
        }

        quantity -= amount;
        return true;
    }

    /**
     * Changes the product price when the new price is not negative.
     *
     * @param newPrice new price to assign to the product
     * @return {@code true} when the price is updated; {@code false} when
     *         the new price is negative
     */


    public boolean updatePrice(double newPrice) {
        if (newPrice < 0) {
            return false;
        }

        price = newPrice;
        return true;
    }

    /**
     * Creates a formatted description of the product.
     *
     * @return product type, ID, name, quantity, and price as formatted text
     */

    public String displayProductInfo() {
        return String.format(
                "Product Type: %s%n" +
                        "Product ID: %s%n" +
                        "Product Name: %s%n" +
                        "Quantity: %d%n" +
                        "Price: $%.2f",
                getProductType(),
                productID,
                productName,
                quantity,
                price
        );
    }
    /**
     * Returns the specific product type supplied by the concrete subclass.
     *
     * @return the product type
     */
    public abstract String getProductType();
}