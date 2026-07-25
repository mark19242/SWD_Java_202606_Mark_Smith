package learn.inventory.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    private Product product;

    @BeforeEach
    void setUp() {
        product = new StandardProduct(
                "101",
                "Laptop",
                10,
                999.99
        );
    }

    @Test
    void constructorSetsProductValues() {
        assertEquals("101", product.getProductID());
        assertEquals("Laptop", product.getProductName());
        assertEquals(10, product.getQuantity());
        assertEquals(999.99, product.getPrice());
        assertEquals("Standard", product.getProductType());
    }

    @Test
    void addStockIncreasesQuantity() {
        boolean result = product.addStock(5);

        assertTrue(result);
        assertEquals(15, product.getQuantity());
    }

    @Test
    void addStockRejectsZeroOrNegativeAmount() {
        assertFalse(product.addStock(0));
        assertFalse(product.addStock(-5));
        assertEquals(10, product.getQuantity());
    }

    @Test
    void removeStockDecreasesQuantity() {
        boolean result = product.removeStock(4);

        assertTrue(result);
        assertEquals(6, product.getQuantity());
    }

    @Test
    void removeStockRejectsMoreThanAvailableQuantity() {
        boolean result = product.removeStock(11);

        assertFalse(result);
        assertEquals(10, product.getQuantity());
    }

    @Test
    void removeStockRejectsZeroOrNegativeAmount() {
        assertFalse(product.removeStock(0));
        assertFalse(product.removeStock(-2));
        assertEquals(10, product.getQuantity());
    }

    @Test
    void updatePriceChangesPrice() {
        boolean result = product.updatePrice(899.99);

        assertTrue(result);
        assertEquals(899.99, product.getPrice());
    }

    @Test
    void updatePriceRejectsNegativePrice() {
        boolean result = product.updatePrice(-1.00);

        assertFalse(result);
        assertEquals(999.99, product.getPrice());
    }

    @Test
    void displayProductInfoContainsExpectedDetails() {
        String result = product.displayProductInfo();

        assertTrue(result.contains("Product Type: Standard"));
        assertTrue(result.contains("Product ID: 101"));
        assertTrue(result.contains("Product Name: Laptop"));
        assertTrue(result.contains("Quantity: 10"));
        assertTrue(result.contains("Price: $999.99"));
    }
}