package learn.inventory.service;

import learn.inventory.data.InventoryRepository;
import learn.inventory.model.Product;
import learn.inventory.model.StandardProduct;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InventoryServiceTest {

    private FakeInventoryRepository repository;
    private InventoryService service;
    private Product laptop;
    private Product mouse;

    @BeforeEach
    void setUp() {
        repository = new FakeInventoryRepository();
        service = new InventoryService(repository);

        laptop = new StandardProduct(
                "A101",
                "Laptop",
                10,
                999.99
        );

        mouse = new StandardProduct(
                "B202",
                "Wireless Mouse",
                25,
                29.99
        );
    }

    @Test
    void addProductAddsProductToInventory() {
        boolean result = service.addProduct(laptop);

        assertTrue(result);
        assertEquals(1, service.getAllProducts().size());
        assertSame(laptop, service.findProductById("A101"));
    }

    @Test
    void addProductRejectsDuplicateProductID() {
        service.addProduct(laptop);

        Product duplicate = new StandardProduct(
                "A101",
                "Another Laptop",
                5,
                499.99
        );

        boolean result = service.addProduct(duplicate);

        assertFalse(result);
        assertEquals(1, service.getAllProducts().size());
    }

    @Test
    void addProductRejectsNullProduct() {
        boolean result = service.addProduct(null);

        assertFalse(result);
        assertTrue(service.getAllProducts().isEmpty());
    }

    @Test
    void getAllProductsReturnsCopyOfInventoryList() {
        service.addProduct(laptop);

        List<Product> returnedProducts = service.getAllProducts();
        returnedProducts.clear();

        assertEquals(1, service.getAllProducts().size());
    }

    @Test
    void findProductByIdIgnoresCaseAndWhitespace() {
        service.addProduct(laptop);

        Product result = service.findProductById("  a101  ");

        assertSame(laptop, result);
    }

    @Test
    void findProductByIdReturnsNullWhenProductDoesNotExist() {
        assertNull(service.findProductById("missing"));
        assertNull(service.findProductById(null));
    }

    @Test
    void findProductsByNameReturnsPartialCaseInsensitiveMatches() {
        service.addProduct(laptop);
        service.addProduct(mouse);

        List<Product> results =
                service.findProductsByName("WIRELESS");

        assertEquals(1, results.size());
        assertSame(mouse, results.get(0));
    }

    @Test
    void updateProductChangesQuantityAndPrice() {
        service.addProduct(laptop);

        boolean result = service.updateProduct(
                "A101",
                15,
                899.99
        );

        assertTrue(result);
        assertEquals(15, laptop.getQuantity());
        assertEquals(899.99, laptop.getPrice());
    }

    @Test
    void updateProductRejectsMissingProductOrNegativeValues() {
        service.addProduct(laptop);

        assertFalse(service.updateProduct("missing", 10, 20.00));
        assertFalse(service.updateProduct("A101", -1, 20.00));
        assertFalse(service.updateProduct("A101", 10, -1.00));

        assertEquals(10, laptop.getQuantity());
        assertEquals(999.99, laptop.getPrice());
    }

    @Test
    void deleteProductRemovesMatchingProduct() {
        service.addProduct(laptop);

        boolean result = service.deleteProduct("A101");

        assertTrue(result);
        assertTrue(service.getAllProducts().isEmpty());
        assertNull(service.findProductById("A101"));
    }

    @Test
    void deleteProductReturnsFalseWhenProductDoesNotExist() {
        boolean result = service.deleteProduct("missing");

        assertFalse(result);
    }

    @Test
    void saveInventoryPassesProductsToRepository() throws IOException {
        service.addProduct(laptop);
        service.addProduct(mouse);

        service.saveInventory();

        assertEquals(2, repository.savedProducts.size());
        assertEquals("A101", repository.savedProducts.get(0).getProductID());
        assertEquals("B202", repository.savedProducts.get(1).getProductID());
    }

    @Test
    void loadInventoryReplacesCurrentInventory() throws IOException {
        service.addProduct(laptop);
        repository.productsToLoad = List.of(mouse);

        service.loadInventory();

        assertEquals(1, service.getAllProducts().size());
        assertNull(service.findProductById("A101"));
        assertSame(mouse, service.findProductById("B202"));
    }

    /**
     * Simple repository implementation used to test InventoryService
     * without reading or writing an actual file.
     */
    private static class FakeInventoryRepository
            implements InventoryRepository {

        private List<Product> savedProducts = new ArrayList<>();
        private List<Product> productsToLoad = new ArrayList<>();

        @Override
        public void save(List<Product> products) {
            savedProducts = new ArrayList<>(products);
        }

        @Override
        public List<Product> load() {
            return new ArrayList<>(productsToLoad);
        }
    }
}