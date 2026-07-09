package org.example.service;

import org.example.model.CartItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartServiceTest {

    private ShoppingCartService cartService;

    @BeforeEach
    void setUp() {
        // Each test starts with a fresh cart service and empty cart.
        cartService = new ShoppingCartService();
    }

    @Test
    void addItemAddsProductToCart() {
        boolean result = cartService.addItem("1", 2);

        assertTrue(result);
        assertEquals(1, cartService.getCartItems().size());
        assertEquals("Apple", cartService.getCartItems().get(0).getProduct().getName());
        assertEquals(2, cartService.getCartItems().get(0).getQuantity());
    }

    @Test
    void addItemIncreasesQuantityWhenProductAlreadyExistsInCart() {
        cartService.addItem("1", 2);
        cartService.addItem("1", 3);

        assertEquals(1, cartService.getCartItems().size());
        assertEquals(5, cartService.getCartItems().get(0).getQuantity());
    }

    @Test
    void addItemReturnsFalseForInvalidProductId() {
        boolean result = cartService.addItem("99", 1);

        assertFalse(result);
        assertTrue(cartService.isCartEmpty());
    }

    @Test
    void addItemReturnsFalseForZeroOrNegativeQuantity() {
        boolean zeroResult = cartService.addItem("1", 0);
        boolean negativeResult = cartService.addItem("1", -2);

        assertFalse(zeroResult);
        assertFalse(negativeResult);
        assertTrue(cartService.isCartEmpty());
    }

    @Test
    void removeItemDecreasesQuantityWhenQuantityIsLessThanCartQuantity() {
        cartService.addItem("1", 5);

        boolean result = cartService.removeItem("1", 2);

        assertTrue(result);
        assertEquals(1, cartService.getCartItems().size());
        assertEquals(3, cartService.getCartItems().get(0).getQuantity());
    }

    @Test
    void removeItemRemovesProductWhenQuantityReachesZero() {
        cartService.addItem("1", 2);

        boolean result = cartService.removeItem("1", 2);

        assertTrue(result);
        assertTrue(cartService.isCartEmpty());
    }

    @Test
    void removeItemReturnsFalseWhenRemovingMoreThanCartQuantity() {
        cartService.addItem("1", 2);

        boolean result = cartService.removeItem("1", 5);

        assertFalse(result);
        assertEquals(2, cartService.getCartItems().get(0).getQuantity());
    }

    @Test
    void removeItemReturnsFalseForProductNotInCart() {
        boolean result = cartService.removeItem("1", 1);

        assertFalse(result);
    }

    @Test
    void getCartTotalCalculatesTotalPrice() {
        cartService.addItem("1", 2); // Apple = 0.99 * 2 = 1.98
        cartService.addItem("2", 1); // Bread = 2.49 * 1 = 2.49

        double total = cartService.getCartTotal();

        assertEquals(4.47, total, 0.001);
    }

    @Test
    void checkoutReturnsTotalAndEmptiesCart() {
        cartService.addItem("1", 2);
        cartService.addItem("2", 1);

        double total = cartService.checkout();

        assertEquals(4.47, total, 0.001);
        assertTrue(cartService.isCartEmpty());
    }

    @Test
    void getInventoryProductsReturnsAvailableProducts() {
        assertFalse(cartService.getInventoryProducts().isEmpty());
        assertEquals(6, cartService.getInventoryProducts().size());
    }
}