package org.example.service;

import org.example.model.Payload;
import org.example.model.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class VendingMachineImplTest {

    VendingMachine vm;

    static final Product APPLE = new Product("Apple", .25);
    static final Product CANDY_BAR = new Product("Candy Bar", 1.00);
    static final Product SODA = new Product("Soda", .50);

    @BeforeEach
    void setUp() {
        // Each test gets a fresh vending machine with no leftover state.
        vm = new VendingMachineImpl();
    }

    @Test
    void loadProductLoadsCorrectInventoryCount() {
        // Arrange
        String targetBin = "A1";
        int expectedCount = 10;

        // Act
        vm.loadProduct(targetBin, APPLE, expectedCount);

        // Assert
        assertEquals(expectedCount, vm.getBinQuantity(targetBin),
                "Load count does not match count param.");
    }

    @Test
    void vendLastItemDoesNotCrashAndEmptiesBin() {
        // Arrange
        String targetBin = "A1";
        int startingCount = 3;

        vm.loadProduct(targetBin, APPLE, startingCount);
        vm.addMoney(APPLE.getPrice() * startingCount);

        // Vend until only one item is left.
        while (vm.getBinQuantity(targetBin) > 1) {
            Payload<Product> setupVend = vm.vend(targetBin);
            assertTrue(setupVend.isSuccess(),
                    "Setup vend should succeed before testing the last item.");
        }

        // Act
        Payload<Product> result = assertDoesNotThrow(
                () -> vm.vend(targetBin),
                "Vending the last item should not crash."
        );

        // Assert
        assertTrue(result.isSuccess(), "Vending the last item should succeed.");
        assertEquals(0, vm.getBinQuantity(targetBin),
                "Bin should be empty after vending the last item.");
    }

    @Test
    void vendAddsPurchaseMoneyToMoneyBin() {
        // Arrange
        String targetBin = "A1";

        vm.loadProduct(targetBin, APPLE, 3);
        vm.addMoney(APPLE.getPrice());

        // Act
        Payload<Product> result = vm.vend(targetBin);

        // Assert
        assertTrue(result.isSuccess(), "Vend should be successful.");
        assertEquals(APPLE.getPrice(), vm.getMoneyBinContents(), 0.001,
                "Money bin should collect the purchase price.");
        assertEquals(0.00, vm.getCustomerMoney(), 0.001,
                "Customer money should be reduced after purchase.");
    }
}