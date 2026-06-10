package shopping.cart;

public class ShoppingCartApp {
    static void main() {

//  Product Information
//  Product ID is 1
//  Product Category is 2
//  Product Cost is 2.56
//  Product Price is 4.99
//  Product Quantity is 78
//  Requirements
//  1. Assign variables to each of the elements above.
//  2. Write code to calculate the total cost of the product based on the inventory. Print
//     the value.
//  3. Write code to calculate the profit margin of the product. Print the value.
//  4. Write code to calculate the total potential profit. Print the value.

        // Product Information

        int productID = 1;
        int productCategory = 2;
        double productCost = 2.56;
        double productPrice = 4.99;
        int productQuantity = 78;

        // Print Product Information

        System.out.println("Product ID: " + productID);
        System.out.println("Product Category: " + productCategory);
        System.out.println("Product Cost: $" + productCost);
        System.out.println("Product Price: $" + productPrice);
        System.out.println("Product Quantity: " + productQuantity);

        System.out.println();

        // Requirement 2
        // Calculate total inventory cost

        double totalCost = productCost * productQuantity;

        System.out.println("Total Inventory Cost: $" + totalCost);

        // Requirement 3
        // Calculate profit margin per item

        double profitMargin = productPrice - productCost;

        System.out.println("Profit Margin Per Product: $" + profitMargin);

        // Requirement 4
        // Calculate total potential profit

        double totalPotentialProfit = profitMargin * productQuantity;

        System.out.println("Total Potential Profit: $" + totalPotentialProfit);

        }
}
