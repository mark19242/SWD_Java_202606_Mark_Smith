package learn.inventory.data;

import learn.inventory.model.Product;

import java.io.IOException;
import java.util.List;

/**
 * Defines the persistence operations required by the inventory service.
 *
 * <p>Implementations of this interface decide how product data is stored
 * and retrieved. For example, one implementation may use a text file,
 * while another could use a database.</p>
 */

public interface InventoryRepository {

    /**
     * Saves the provided products to persistent storage.
     *
     * @param products products to save
     * @throws IOException if the product data cannot be written
     */

    void save(List<Product> products) throws IOException;

    /**
     * Loads products from persistent storage.
     *
     * @return the products restored from storage
     * @throws IOException if the product data cannot be read or is invalid
     */

    List<Product> load() throws IOException;
}