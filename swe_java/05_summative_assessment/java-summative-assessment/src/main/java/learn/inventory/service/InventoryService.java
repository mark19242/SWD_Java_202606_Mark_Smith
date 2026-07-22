package learn.inventory.service;

import learn.inventory.model.Product;
import learn.inventory.data.InventoryRepository;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.nio.file.Path;

public class InventoryService {

    private final List<Product> products = new ArrayList<>();
    private final InventoryRepository repository;

    public InventoryService() {
        this(
                new InventoryRepository(
                        Path.of("data", "inventory.txt")
                )
        );
    }

    public InventoryService(InventoryRepository repository) {
        this.repository = repository;
    }

    public void saveInventory() throws IOException {
        repository.save(products);
    }

    // Adds a product only when its ID is not already in the inventory.
    public boolean addProduct(Product product) {

        if (product == null || findProductById(product.getProductID()) != null) {
            return false;
        }

        products.add(product);
        return true;
    }

    // Returns a copy so outside classes cannot directly change the inventory list.
    public List<Product> getAllProducts() {
        return new ArrayList<>(products);
    }

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

    // Removes a product when a matching product ID exists.
    public boolean deleteProduct(String productID) {

        Product product = findProductById(productID);

        if (product == null) {
            return false;
        }

        return products.remove(product);
    }


}