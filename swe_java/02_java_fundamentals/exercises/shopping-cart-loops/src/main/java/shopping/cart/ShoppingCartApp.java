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

        // Decision exercise constants
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

        while (!confirm) {

            System.out.println("\nShopping Cart Questions");

            System.out.println("Are you tax-exempt? (y/n): ");
            taxExempt = console.nextLine();

            System.out.println("Choose shipping method: STANDARD, TWO_DAY, or OVERNIGHT");
            shippingChoice = console.nextLine();

            System.out.println("Order quantity? ");
            orderQuantity = Integer.parseInt(console.nextLine());

            System.out.println("Promo code for free shipping? ");
            promoCode = console.nextLine();

            System.out.println("\nConfirm Order Details");
            System.out.println("--------------------");
            System.out.println("Tax-exempt: " + taxExempt);
            System.out.println("Shipping choice: " + shippingChoice);
            System.out.println("Order quantity: " + orderQuantity);
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

        // =========================
        // Discount Decision
        // =========================
        // I checked $500 first because it is the larger discount.
        if (orderSubtotal > 500) {
            discount = orderSubtotal * 0.10;
        } else if (orderSubtotal > 100) {
            discount = orderSubtotal * 0.05;
        } else {
            discount = 0.00;
        }

        double discountedSubtotal = orderSubtotal - discount;

        // =========================
        // Tax Decision
        // =========================
        if (taxExempt.equalsIgnoreCase("y")) {
            taxAmount = 0.00;
        } else {
            taxAmount = discountedSubtotal * taxRate;
        }

        // =========================
        // Shipping Decision
        // =========================
        ShippingMethod shippingMethod = ShippingMethod.STANDARD;

        if (shippingChoice.equalsIgnoreCase("TWO_DAY")) {
            shippingMethod = ShippingMethod.TWO_DAY;
        } else if (shippingChoice.equalsIgnoreCase("OVERNIGHT")) {
            shippingMethod = ShippingMethod.OVERNIGHT;
        } else {
            shippingMethod = ShippingMethod.STANDARD;
        }
        //Decided To Go With A Switch
        switch (shippingMethod) {

            case STANDARD:
                shippingCost = standardShipping;

                // Promo code only applies to standard shipping.
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
        System.out.println("Shipping Method: " + shippingMethod);
        System.out.println("Order quantity: " + orderQuantity);
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

        // =========================
        // Enum Variables
        // =========================

        OrderStatus orderStatus = OrderStatus.PENDING;

        System.out.println("\nCurrent Order Status: " + orderStatus);
        System.out.println("Current Shipping Method: " + shippingMethod);

        System.out.println("\nAvailable Order Statuses:");

        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(status);
        }

        System.out.println("\nAvailable Shipping Methods:");

        for (ShippingMethod method : ShippingMethod.values()) {
            System.out.println(method);
        }

        console.close();
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