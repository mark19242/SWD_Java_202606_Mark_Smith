package learn.inventory.data;

import learn.inventory.model.Expirable;
import learn.inventory.model.Product;
import learn.inventory.model.PerishableProduct;
import learn.inventory.model.StandardProduct;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Locale;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * Saves inventory products to a text file and loads them back into the
 * application.
 *
 * <p>Each product is stored as a tab-separated record. The saved product
 * type allows the repository to recreate either a {@link StandardProduct}
 * or a {@link PerishableProduct} when the file is loaded.</p>
 */

public class FileInventoryRepository implements InventoryRepository {

    private final Path filePath;

    /**
     * Creates a file-based repository using the provided file location.
     *
     * @param filePath path of the inventory file used for saving and loading
     */

    public FileInventoryRepository(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves every product to the configured inventory file.
     *
     * <p>The parent directory is created when it does not already exist.
     * Existing file contents are replaced with the current inventory.</p>
     *
     * @param products products to save
     * @throws IOException if the directory or file cannot be created or written
     */

    @Override
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

    /**
     * Loads all valid product records from the configured inventory file.
     *
     * <p>Blank lines are ignored. Each nonblank line is converted into the
     * correct concrete product type.</p>
     *
     * @return products reconstructed from the inventory file
     * @throws IOException if the file cannot be read or contains invalid data
     */

    @Override
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

    /**
     * Converts a product into one tab-separated file record.
     *
     * <p>Products that implement {@link Expirable} include an expiration
     * date. Standard products use an empty expiration-date field.</p>
     *
     * @param product product to convert
     * @return formatted product record ready to be written to the file
     */

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

    /**
     * Replaces tab and line-break characters that could damage the file
     * record structure.
     *
     * @param value text value to clean
     * @return cleaned text safe for the tab-separated file format
     */

    private String cleanField(String value) {
        return value
                .replace("\t", " ")
                .replace("\r", " ")
                .replace("\n", " ");
    }

    /**
     * Converts one saved file record into the correct product object.
     *
     * @param line saved product record
     * @param lineNumber line number used when reporting invalid data
     * @return a standard or perishable product created from the record
     * @throws IOException if the record has missing, invalid, or unknown data
     */


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