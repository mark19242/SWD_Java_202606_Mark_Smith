package learn.inventory.model;

public class StandardProduct extends Product {

    public StandardProduct(
            String productID,
            String productName,
            int quantity,
            double price
    ) {
        super(productID, productName, quantity, price);
    }

    @Override
    public String getProductType() {
        return "Standard";
    }
}