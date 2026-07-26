package learn.inventory.data;

import learn.inventory.model.PerishableProduct;
import learn.inventory.model.Product;
import learn.inventory.model.StandardProduct;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileInventoryRepositoryTest {

    @TempDir
    Path tempDirectory;

    @Test
    void saveAndLoadRestoresStandardAndPerishableProducts()
            throws IOException {

        Path filePath = tempDirectory.resolve("inventory.txt");
        FileInventoryRepository repository =
                new FileInventoryRepository(filePath);

        Product laptop = new StandardProduct(
                "A101",
                "Laptop",
                10,
                999.99
        );

        Product milk = new PerishableProduct(
                "B202",
                "Milk",
                12,
                4.99,
                LocalDate.of(2026, 8, 1)
        );

        repository.save(List.of(laptop, milk));

        List<Product> loadedProducts = repository.load();

        assertEquals(2, loadedProducts.size());

        Product loadedLaptop = loadedProducts.get(0);
        assertTrue(loadedLaptop instanceof StandardProduct);
        assertEquals("A101", loadedLaptop.getProductID());
        assertEquals("Laptop", loadedLaptop.getProductName());
        assertEquals(10, loadedLaptop.getQuantity());
        assertEquals(999.99, loadedLaptop.getPrice(), 0.001);

        Product loadedMilk = loadedProducts.get(1);
        assertTrue(loadedMilk instanceof PerishableProduct);
        assertEquals("B202", loadedMilk.getProductID());
        assertEquals("Milk", loadedMilk.getProductName());
        assertEquals(12, loadedMilk.getQuantity());
        assertEquals(4.99, loadedMilk.getPrice(), 0.001);

        PerishableProduct perishableProduct =
                (PerishableProduct) loadedMilk;

        assertEquals(
                LocalDate.of(2026, 8, 1),
                perishableProduct.getExpirationDate()
        );
    }

    @Test
    void saveCreatesMissingParentDirectory() throws IOException {

        Path filePath = tempDirectory
                .resolve("data")
                .resolve("inventory.txt");

        FileInventoryRepository repository =
                new FileInventoryRepository(filePath);

        repository.save(
                List.of(
                        new StandardProduct(
                                "A101",
                                "Laptop",
                                10,
                                999.99
                        )
                )
        );

        assertTrue(Files.exists(filePath));
    }

    @Test
    void loadThrowsExceptionWhenFileDoesNotExist() {

        Path missingFile =
                tempDirectory.resolve("missing-inventory.txt");

        FileInventoryRepository repository =
                new FileInventoryRepository(missingFile);

        assertThrows(
                NoSuchFileException.class,
                repository::load
        );
    }

    @Test
    void loadThrowsExceptionForMalformedRecord()
            throws IOException {

        Path filePath = tempDirectory.resolve("inventory.txt");

        Files.writeString(
                filePath,
                "Standard\tA101\tLaptop"
        );

        FileInventoryRepository repository =
                new FileInventoryRepository(filePath);

        assertThrows(
                IOException.class,
                repository::load
        );
    }

    @Test
    void loadThrowsExceptionForUnknownProductType()
            throws IOException {

        Path filePath = tempDirectory.resolve("inventory.txt");

        Files.writeString(
                filePath,
                "Mystery\tA101\tLaptop\t10\t999.99\t"
        );

        FileInventoryRepository repository =
                new FileInventoryRepository(filePath);

        assertThrows(
                IOException.class,
                repository::load
        );
    }
}