package learn.inventory.model;

import java.time.LocalDate;

/**
 * Represents an inventory product with an expiration date.
 *
 * <p>This class inherits the shared product information and stock behavior
 * from {@link Product}. It also implements {@link Expirable} so it can
 * report its expiration date and whether it has expired.</p>
 */

public class PerishableProduct extends Product implements Expirable {

    private LocalDate expirationDate;

    /**
     * Creates a perishable product with inventory and expiration information.
     *
     * @param productID unique identifier for the product
     * @param productName name of the product
     * @param quantity starting quantity in inventory
     * @param price starting price of the product
     * @param expirationDate date on which the product expires
     */

    public PerishableProduct(
            String productID,
            String productName,
            int quantity,
            double price,
            LocalDate expirationDate
    ) {
        super(productID, productName, quantity, price);
        this.expirationDate = expirationDate;
    }

    /**
     * Identifies this product as a perishable product.
     *
     * @return the product type {@code "Perishable"}
     */

    @Override
    public String getProductType() {
        return "Perishable";
    }

    /**
     * Returns the product's expiration date.
     *
     * @return the expiration date
     */

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    /**
     * Determines whether the expiration date occurred before the current date.
     *
     * @return {@code true} when the product is expired; otherwise {@code false}
     */

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    /**
     * Creates a formatted description containing the inherited product
     * information and the product's expiration details.
     *
     * @return formatted product and expiration information
     */

    @Override
    public String displayProductInfo() {
        return super.displayProductInfo()
                + String.format(
                "%nExpiration Date: %s%nExpired: %s",
                expirationDate,
                isExpired() ? "Yes" : "No"
        );
    }
}