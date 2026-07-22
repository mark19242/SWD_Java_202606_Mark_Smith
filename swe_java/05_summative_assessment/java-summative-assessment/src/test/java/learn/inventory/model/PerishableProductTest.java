package learn.inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PerishableProductTest {

    private PerishableProduct product;

    @BeforeEach
    void setUp() {
        product = new PerishableProduct(
                "201",
                "Milk",
                12,
                4.99,
                LocalDate.now().plusDays(7)
        );
    }

    @Test
    void constructorSetsPerishableProductValues() {
        assertEquals("201", product.getProductID());
        assertEquals("Milk", product.getProductName());
        assertEquals(12, product.getQuantity());
        assertEquals(4.99, product.getPrice());
        assertEquals("Perishable", product.getProductType());
        assertEquals(
                LocalDate.now().plusDays(7),
                product.getExpirationDate()
        );
    }

    @Test
    void isExpiredReturnsFalseForFutureDate() {
        assertFalse(product.isExpired());
    }

    @Test
    void isExpiredReturnsTrueForPastDate() {
        PerishableProduct expiredProduct =
                new PerishableProduct(
                        "202",
                        "Old Milk",
                        2,
                        2.99,
                        LocalDate.now().minusDays(1)
                );

        assertTrue(expiredProduct.isExpired());
    }

    @Test
    void displayProductInfoIncludesExpirationDetails() {
        String result = product.displayProductInfo();

        assertTrue(result.contains("Product Type: Perishable"));
        assertTrue(result.contains("Expiration Date:"));
        assertTrue(result.contains("Expired: No"));
    }
}