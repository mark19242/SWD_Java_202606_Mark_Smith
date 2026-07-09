package org.example.service;

import org.example.model.CartItem;
import org.example.model.Product;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Handles the business logic for the shopping cart.
public class ShoppingCartService implements CartService {

    private Map<String, Product> inventory;
    private List<CartItem> cartItems;

    public ShoppingCartService() {
        inventory = new HashMap<>();
        cartItems = new ArrayList<>();
        loadInventory();
    }

    // Loads a small starter inventory for the cashier to choose from.
    private void loadInventory() {
        inventory.put("1", new Product("1", "Apple", 0.99));
        inventory.put("2", new Product("2", "Bread", 2.49));
        inventory.put("3", new Product("3", "Milk", 3.79));
        inventory.put("4", new Product("4", "Eggs", 4.29));
        inventory.put("5", new Product("5", "Chicken", 8.99));
        inventory.put("6", new Product("6", "Rice", 5.49));
    }

    @Override
    public List<Product> getInventoryProducts() {
        List<Product> products = new ArrayList<>(inventory.values());

        // Sorts the products by id so they display in a predictable order.
        products.sort((p1, p2) -> p1.getId().compareTo(p2.getId()));

        return products;
    }

    @Override
    public List<CartItem> getCartItems() {
        return new ArrayList<>(cartItems);
    }

    @Override
    public boolean addItem(String productId, int quantity) {
        if (quantity <= 0 || !inventory.containsKey(productId)) {
            return false;
        }

        Product product = inventory.get(productId);
        CartItem existingItem = findCartItemByProductId(productId);

        if (existingItem == null) {
            cartItems.add(new CartItem(product, quantity));
        } else {
            existingItem.increaseQuantity(quantity);
        }

        return true;
    }

    @Override
    public boolean removeItem(String productId, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        CartItem item = findCartItemByProductId(productId);

        if (item == null || quantity > item.getQuantity()) {
            return false;
        }

        item.decreaseQuantity(quantity);

        // Only remove the item from the cart when its quantity reaches zero.
        if (item.getQuantity() == 0) {
            cartItems.remove(item);
        }

        return true;
    }

    @Override
    public double getCartTotal() {
        double total = 0;

        for (CartItem item : cartItems) {
            total += item.getLineTotal();
        }

        return total;
    }

    @Override
    public double checkout() {
        double total = getCartTotal();
        cartItems.clear();
        return total;
    }

    @Override
    public boolean isCartEmpty() {
        return cartItems.isEmpty();
    }

    private CartItem findCartItemByProductId(String productId) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getId().equals(productId)) {
                return item;
            }
        }

        return null;
    }
}