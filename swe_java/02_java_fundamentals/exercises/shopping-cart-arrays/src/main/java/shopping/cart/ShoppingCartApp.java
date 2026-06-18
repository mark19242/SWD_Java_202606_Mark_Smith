package shopping.cart;

import java.util.Scanner;

public class ShoppingCartApp {

    public static void main() {

        Scanner console = new Scanner(System.in);

        // =========================
        // Product Information
        // =========================
        String businessName = "Remarkable Sports";
        String contactInfo = "support@remarkablesports.com";
        String itemDescription = "Premium training basketball for indoor and outdoor use";

        int productID = 1;
        int productCategory = 2;

        double productCost = 2.50;
        double productPrice = 4.99;
        int productQuantity = 8;

        // =========================
        // Arrays
        // =========================
        String[] shippingAddresses = {
                "123 Main St",
                "456 Main St",
                "789 Main St"
        };

        String[] productSizes = {
                "small",
                "medium",
                "large"
        };

        double taxRate = 0.07;
        double standardShipping = 2.00;
        double twoDayShipping = 5.00;
        double overnightShipping = 10.00;

        System.out.println("Business: " + businessName);
        System.out.println("Description: " + itemDescription);
        System.out.println("Contact: " + contactInfo);
        System.out.printf(
                "Category: %d ID: %d Desc: %s%n",
                productCategory,
                productID,
                "Widget"
        );

        // =========================
        // Customer Input Loop
        // =========================

        String taxExempt = "";
        String shippingChoice = "";
        int orderQuantity = 0;
        String promoCode = "";
        boolean confirm = false;

        String selectedAddress = "";
        String selectedSize = "";

        while (!confirm) {

            System.out.println("\nShopping Cart Questions");

            System.out.println("Are you tax-exempt? (y/n): ");
            taxExempt = console.nextLine();

            // =========================
            // Shipping Address Array Menu
            // =========================
            for (int i = 0; i < shippingAddresses.length; i++) {
                System.out.println((i + 1) + ". " + shippingAddresses[i]);
            }

            System.out.println("Shipping address? ");
            int addressChoice = Integer.parseInt(console.nextLine());

            selectedAddress = shippingAddresses[addressChoice - 1];

            System.out.println("Choose shipping method: STANDARD, TWO_DAY, or OVERNIGHT");
            shippingChoice = console.nextLine();

            System.out.println("Order quantity? ");
            orderQuantity = Integer.parseInt(console.nextLine());

            // =========================
            // Product Size Array Menu
            // =========================
            for (int i = 0; i < productSizes.length; i++) {
                System.out.println((i + 1) + ". " + productSizes[i]);
            }

            System.out.println("Size? ");
            int sizeChoice = Integer.parseInt(console.nextLine());

            selectedSize = productSizes[sizeChoice - 1];

            System.out.println("Promo code for free shipping? ");
            promoCode = console.nextLine();

            System.out.println("\nConfirm Order Details");
            System.out.println("--------------------");
            System.out.println("Tax-exempt: " + taxExempt);
            System.out.println("Shipping address: " + selectedAddress);
            System.out.println("Shipping choice: " + shippingChoice);
            System.out.println("Order quantity: " + orderQuantity);
            System.out.println("Product size: " + selectedSize);
            System.out.println("Promo code: " + promoCode);

            System.out.println("\nConfirm order? (y/n)");
            String answer = console.nextLine();

            if (answer.equalsIgnoreCase("y")) {
                confirm = true;
            }
        }

        // =========================
        // Order Calculations
        // =========================

        double orderSubtotal = productPrice * orderQuantity;
        double discount = 0.00;
        double taxAmount = 0.00;
        double shippingCost = 0.00;

        if (orderSubtotal > 500) {
            discount = orderSubtotal * 0.10;
        } else if (orderSubtotal > 100) {
            discount = orderSubtotal * 0.05;
        } else {
            discount = 0.00;
        }

        double discountedSubtotal = orderSubtotal - discount;

        if (taxExempt.equalsIgnoreCase("y")) {
            taxAmount = 0.00;
        } else {
            taxAmount = discountedSubtotal * taxRate;
        }

        ShippingMethod shippingMethod = ShippingMethod.STANDARD;

        if (shippingChoice.equalsIgnoreCase("TWO_DAY")) {
            shippingMethod = ShippingMethod.TWO_DAY;
        } else if (shippingChoice.equalsIgnoreCase("OVERNIGHT")) {
            shippingMethod = ShippingMethod.OVERNIGHT;
        } else {
            shippingMethod = ShippingMethod.STANDARD;
        }

        switch (shippingMethod) {

            case STANDARD:
                shippingCost = standardShipping;

                if (promoCode.equalsIgnoreCase("FREE")) {
                    shippingCost = 0.00;
                }
                break;

            case TWO_DAY:
                shippingCost = twoDayShipping;
                break;

            case OVERNIGHT:
                shippingCost = overnightShipping;
                break;
        }

        double finalTotal = discountedSubtotal + taxAmount + shippingCost;

        // =========================
        // Inventory Calculations
        // =========================

        double totalCost = productCost * productQuantity;
        double profitMargin = productPrice - productCost;
        double totalPotentialProfit = profitMargin * productQuantity;

        // =========================
        // Receipt
        // =========================

        System.out.println("\nOrder Summary");
        System.out.println("--------------------");
        System.out.println("Tax-exempt: " + taxExempt);
        System.out.println("Shipping Address: " + selectedAddress);
        System.out.println("Shipping Method: " + shippingMethod);
        System.out.println("Order quantity: " + orderQuantity);
        System.out.println("Product size: " + selectedSize);
        System.out.println("Promo code: " + promoCode);

        System.out.printf("Order Subtotal: $%.2f%n", orderSubtotal);
        System.out.printf("Discount: $%.2f%n", discount);
        System.out.printf("Tax Amount: $%.2f%n", taxAmount);
        System.out.printf("Shipping Cost: $%.2f%n", shippingCost);
        System.out.printf("Final Total: $%.2f%n", finalTotal);

        System.out.println("\nInventory Details");
        System.out.println("--------------------");
        System.out.printf("Total Inventory Cost: $%.2f%n", totalCost);
        System.out.printf("Profit Margin Per Product: $%.2f%n", profitMargin);
        System.out.printf("Total Potential Profit: $%.2f%n", totalPotentialProfit);

        OrderStatus orderStatus = OrderStatus.PENDING;

        System.out.println("\nCurrent Order Status: " + orderStatus);
        System.out.println("Current Shipping Method: " + shippingMethod);
    }

    enum OrderStatus {
        PENDING,
        PROCESSING,
        SHIPPED,
        DELIVERED
    }

    enum ShippingMethod {
        STANDARD,
        TWO_DAY,
        OVERNIGHT
    }
}