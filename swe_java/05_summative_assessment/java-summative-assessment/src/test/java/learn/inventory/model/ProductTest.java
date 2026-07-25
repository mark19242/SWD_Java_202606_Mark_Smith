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