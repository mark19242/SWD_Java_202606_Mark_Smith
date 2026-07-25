package learn.inventory.service;

import learn.inventory.model.Product;
import learn.inventory.data.FileInventoryRepository;
import learn.inventory.data.InventoryRepository;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Manages the application's in-memory inventory and its core product
 * operations.
 *
 * <p>This service handles adding, finding, updating, and deleting products.
 * It also coordinates saving and loading through an
 * {@link InventoryRepository}.</p>
 *
 * <p>The service depends on the repository interface rather than a specific
 * storage implementation. This allows different repository implementations
 * to be used without changing the inventory business logic.</p>
 */


public class InventoryService {

    private final List<Product> products = new ArrayList<>();
    private final InventoryRepository repository;

    /**
     * Creates an inventory service using the application's default
     * file-based repository.
     *
     * <p>Inventory data is saved to and loaded from
     * {@code data/inventory.txt}.</p>
     */

    public InventoryService() {
        this(
                new FileInventoryRepository(
                        Path.of("data", "inventory.txt")
                )
        );
    }

    /**
     * Creates an inventory service using the supplied repository.
     *
     * <p>This constructor allows the service to work with different storage
     * implementations and makes the service easier to test.</p>
     *
     * @param repository repository used to save and load inventory products
     */

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void saveInventory() throws IOException {
        repository.save(products);
    }

    /**
     * Loads products through the configured repository and replaces the
     * current in-memory inventory.
     *
     * <p>The file is loaded into a temporary list before the current
     * inventory is cleared. This protects the existing inventory if loading
     * fails.</p>
     *
     * @throws IOException if the inventory cannot be read or contains
     *                     invalid data
     */

    public void loadInventory() throws IOException {

        List<Product> loadedProducts = repository.load();

        products.clear();
        products.addAll(loadedProducts);
    }


    /**
     * Adds a product when the product is not null and its ID is not already
     * in the inventory.
     *
     * @param product product to add
     * @return {@code true} when the product is added; {@code false} when the
     *         product is null or its ID already exists
     */


    public boolean addProduct(Product product) {

        if (product == null || findProductById(product.getProductID()) != null) {
            return false;
        }

        products.add(product);
        return true;
    }

    /**
     * Returns a copy of the current product list.
     *
     * <p>Returning a copy prevents outside classes from directly modifying
     * the service's internal inventory collection.</p>
     *
     * @return a new list containing all current products
     */


    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

    /**
     * Searches for a product using its unique product ID.
     *
     * <p>The search ignores capitalization and surrounding whitespace.</p>
     *
     * @param productID product ID to search for
     * @return the matching product, or {@code null} when no match is found
     */

    public Product findProductById(String productID) {

        if (productID == null) {
            return null;
        }

        for (Product product : products) {
            if (product.getProductID().equalsIgnoreCase(productID.trim())) {
                return product;
            }
        }

        return null;
    }

    /**
     * Searches for products whose names contain the supplied search text.
     *
     * <p>The search ignores capitalization and supports partial-name
     * matches.</p>
     *
     * @param productName full or partial product name to search for
     * @return a list of matching products; the list is empty when no matches
     *         are found or the search value is blank
     */

    public List<Product> findProductsByName(String productName) {

        List<Product> matchingProducts = new ArrayList<>();

        if (productName == null || productName.isBlank()) {
            return matchingProducts;
        }

        String searchTerm = productName.trim().toLowerCase();

        for (Product product : products) {
            if (product.getProductName().toLowerCase().contains(searchTerm)) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    /**
     * Updates the quantity and price of an existing product.
     *
     * <p>The quantity is adjusted through the product's stock methods instead
     * of changing its private quantity field directly.</p>
     *
     * @param productID ID of the product to update
     * @param newQuantity new inventory quantity
     * @param newPrice new product price
     * @return {@code true} when the product is updated; {@code false} when
     *         the product does not exist or either new value is negative
     */

    public boolean updateProduct(
            String productID,
            int newQuantity,
            double newPrice
    ) {
        Product product = findProductById(productID);

        if (product == null || newQuantity < 0 || newPrice < 0) {
            return false;
        }

        int quantityDifference = newQuantity - product.getQuantity();

        if (quantityDifference > 0) {
            product.addStock(quantityDifference);
        } else if (quantityDifference < 0) {
            product.removeStock(Math.abs(quantityDifference));
        }

        return product.updatePrice(newPrice);
    }

    /**
     * Removes the product with the matching product ID.
     *
     * @param productID ID of the product to remove
     * @return {@code true} when the product is removed; {@code false} when
     *         no matching product exists
     */

    public boolean deleteProduct(String productID) {

        Product product = findProductById(productID);

        if (product == null) {
            return false;
        }

        return products.remove(product);
    }


}