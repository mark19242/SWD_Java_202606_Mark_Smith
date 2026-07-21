package learn.inventory.service;

import learn.inventory.model.Product;

import java.util.ArrayList;
import java.util.List;

public class InventoryService {

    private final List<Product> products = new ArrayList<>();

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
}