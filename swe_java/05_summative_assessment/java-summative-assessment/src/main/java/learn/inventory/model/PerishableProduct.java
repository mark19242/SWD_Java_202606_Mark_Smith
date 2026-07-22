package learn.inventory.model;

import java.time.LocalDate;

public class PerishableProduct extends Product implements Expirable {

    private LocalDate expirationDate;

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

    @Override
    public String getProductType() {
        return "Perishable";
    }

    @Override
    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    @Override
    public boolean isExpired() {
        return expirationDate.isBefore(LocalDate.now());
    }

    // Adds expiration details to the information inherited from Product.
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