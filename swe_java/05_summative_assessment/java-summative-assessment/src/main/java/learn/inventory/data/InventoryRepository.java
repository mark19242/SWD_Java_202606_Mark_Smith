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
import learn.inventory.model.PerishableProduct;
import learn.inventory.model.StandardProduct;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.io.BufferedReader;

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

    // Reads all saved products from the inventory file.
    public List<Product> load() throws IOException {

        List<Product> loadedProducts = new ArrayList<>();

        try (BufferedReader reader = Files.newBufferedReader(
                filePath,
                StandardCharsets.UTF_8
        )) {

            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;

                if (line.isBlank()) {
                    continue;
                }

                loadedProducts.add(
                        parseProduct(line, lineNumber)
                );
            }
        }

        return loadedProducts;
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

    // Converts one saved line back into the correct Product object.
    private Product parseProduct(
            String line,
            int lineNumber
    ) throws IOException {

        String[] fields = line.split("\t", -1);

        if (fields.length != 6) {
            throw new IOException(
                    "Invalid inventory data on line " + lineNumber + "."
            );
        }

        String productType = fields[0].trim();
        String productID = fields[1].trim();
        String productName = fields[2].trim();

        try {
            int quantity = Integer.parseInt(fields[3].trim());
            double price = Double.parseDouble(fields[4].trim());
            String expirationDate = fields[5].trim();

            if (quantity < 0 || price < 0) {
                throw new IOException(
                        "Negative quantity or price on line " + lineNumber + "."
                );
            }

            if (productType.equalsIgnoreCase("Standard")) {
                return new StandardProduct(
                        productID,
                        productName,
                        quantity,
                        price
                );
            }

            if (productType.equalsIgnoreCase("Perishable")) {

                if (expirationDate.isBlank()) {
                    throw new IOException(
                            "Missing expiration date on line "
                                    + lineNumber + "."
                    );
                }

                return new PerishableProduct(
                        productID,
                        productName,
                        quantity,
                        price,
                        LocalDate.parse(expirationDate)
                );
            }

            throw new IOException(
                    "Unknown product type on line " + lineNumber + "."
            );

        } catch (
                NumberFormatException |
                DateTimeParseException ex
        ) {
            throw new IOException(
                    "Invalid inventory data on line " + lineNumber + ".",
                    ex
            );
        }
    }
}