package learn.inventory;

import learn.inventory.model.Product;

public class App {

    public static void main(String[] args) {

        Product laptop = new Product(
                "101",
                "Laptop",
                10,
                999.99
        );

        System.out.println("Original Product");
        System.out.println("================");
        System.out.println(laptop.displayProductInfo());

        laptop.addStock(5);
        laptop.removeStock(3);
        laptop.updatePrice(899.99);

        System.out.println("\nUpdated Product");
        System.out.println("===============");
        System.out.println(laptop.displayProductInfo());
    }
}