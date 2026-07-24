package learn.inventory.model;

/**
 * Represents a regular inventory product that does not have
 * expiration-related information.
 *
 * <p>This class inherits the shared product data and inventory behavior
 * defined by {@link Product}.</p>
 */

public class StandardProduct extends Product {

    /**
     * Creates a standard product with its identifying and inventory information.
     *
     * @param productID unique identifier for the product
     * @param productName name of the product
     * @param quantity starting quantity in inventory
     * @param price starting price of the product
     */

    public StandardProduct(
            String productID,
            String productName,
            int quantity,
            double price
    ) {
        super(productID, productName, quantity, price);
    }

    /**
     * Identifies this product as a standard product.
     *
     * @return the product type {@code "Standard"}
     */

    @Override
    public String getProductType() {
        return "Standard";
    }
}