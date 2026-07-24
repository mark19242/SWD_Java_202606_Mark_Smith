package learn.inventory.data;

import learn.inventory.model.Product;

import java.io.IOException;
import java.util.List;

public interface InventoryRepository {

    void save(List<Product> products) throws IOException;

    List<Product> load() throws IOException;
}