package learn.inventory.data;

import learn.inventory.model.Expirable;
import learn.inventory.model.Product;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;

public class InventoryRepository {

    private final Path filePath;

    public InventoryRepository(Path filePath) {
        this.filePath = filePath;
    }

    // Writes every product in the inventory to the data file.
    public void save(List<Product> products) throws IOException {

        Path parentDirectory = filePath.getParent();

        if (parentDirectory != null) {
            Files.createDirectories(parentDirectory);
        }

        try (BufferedWriter writer = Files.newBufferedWriter(
                filePath,
                StandardCharsets.UTF_8
        )) {

            for (Product product : products) {
                writer.write(formatProduct(product));
                writer.newLine();
            }
        }
    }

    private String formatProduct(Product product) {

        String expirationDate = "";

        if (product instanceof Expirable expirable) {
            expirationDate = expirable.getExpirationDate().toString();
        }

        return String.join(
                "\t",
                cleanField(product.getProductType()),
                cleanField(product.getProductID()),
                cleanField(product.getProductName()),
                Integer.toString(product.getQuantity()),
                String.format(Locale.US, "%.2f", product.getPrice()),
                expirationDate
        );
    }

    // Prevents tabs or line breaks from damaging the file structure.
    private String cleanField(String value) {
        return value
                .replace("\t", " ")
                .replace("\r", " ")
                .replace("\n", " ");
    }
}