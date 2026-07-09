package org.example.service;

import org.example.model.CartItem;
import org.example.model.Product;

import java.util.List;

public interface CartService {

    List<Product> getInventoryProducts();

    List<CartItem> getCartItems();

    boolean addItem(String productId, int quantity);

    boolean removeItem(String productId, int quantity);

    double getCartTotal();

    double checkout();

    boolean isCartEmpty();
}