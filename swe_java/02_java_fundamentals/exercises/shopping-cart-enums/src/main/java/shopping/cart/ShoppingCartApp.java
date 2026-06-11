package shopping.cart;

public class ShoppingCartApp {

    public static void main() {

        // =========================
        // Product Information
        // =========================

        // Unique ID assigned to the product
        int productID = 1;

        // Category ID assigned to the product
        int productCategory = 2;

        // Cost to purchase/manufacture one item
        double productCost = 2.50;

        // Selling price of one item
        double productPrice = 4.99;

        // Number of items currently in inventory
        int productQuantity = 8;

        // Display basic product information
        System.out.printf(
                "Category: %d ID: %d Desc: %s%n",
                productCategory,
                productID,
                "Widget"
        );

        // =========================
        // Inventory Calculations
        // =========================

        // Calculate the total cost of all inventory
        // Formula: Cost × Quantity
        double totalCost = productCost * productQuantity;

        System.out.println("Total Inventory Cost: $" + totalCost);

        // Calculate profit made on each individual item
        // Formula: Selling Price - Product Cost
        double profitMargin = productPrice - productCost;

        System.out.println("Profit Margin Per Product: $" + profitMargin);

        // Calculate total potential profit if all items are sold
        // Formula: Profit Margin × Quantity
        double totalPotentialProfit = profitMargin * productQuantity;

        System.out.println("Total Potential Profit: $" + totalPotentialProfit);

        // =========================
        // Enum Variables
        // =========================

        // Assign an order status to this order
        OrderStatus orderStatus = OrderStatus.PENDING;

        // Assign a shipping method to this order
        ShippingMethod shippingMethod = ShippingMethod.STANDARD;

        // Display the selected enum values
        System.out.println("\nCurrent Order Status: " + orderStatus);
        System.out.println("Current Shipping Method: " + shippingMethod);

        // =========================
        // Print All Order Statuses
        // =========================

        System.out.println("\nAvailable Order Statuses:");

        // Loop through every value in the OrderStatus enum
        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status);
        }

        // =========================
        // Print All Shipping Methods
        // =========================

        System.out.println("\nAvailable Shipping Methods:");

        // Loop through every value in the ShippingMethod enum
        for (ShippingMethod method : ShippingMethod.values()) {
            System.out.println(method);
        }
    }

    // =========================
    // Order Status Enum
    // =========================
    // Represents the current state of an order
    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    // =========================
    // Shipping Method Enum
    // =========================
    // Represents available shipping options
    enum ShippingMethod {
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }
}