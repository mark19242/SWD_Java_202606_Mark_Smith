import java.util.Scanner;
public class App {
    static void main() {
        Scanner console = new Scanner(System.in);

        // Welcome Message
        System.out.println("Welcome to the order form!");

        // Customer Name
        System.out.print("What is your name? ");
        String customerName = console.nextLine();

        System.out.printf(
                "Hello, %s! Let's get started with your order.%n%n",
                customerName
        );

        // Product Name
        System.out.print("What product would you like to purchase? ");
        String productName = console.nextLine();

        // Quantity
        System.out.print("How many would you like? ");
        int quantity = Integer.parseInt(console.nextLine());

        // Unit Price
        System.out.print("What is the unit price? ");
        double unitPrice = Double.parseDouble(console.nextLine());

        // Calculations
        double subtotal = quantity * unitPrice;
        double tax = subtotal * 0.07;
        double grandTotal = subtotal + tax;

        // Receipt
        System.out.println();
        System.out.println("Order Summary");
        System.out.println("-------------------------------");

        System.out.printf("Item: %s%n", productName);
        System.out.printf("Quantity: %d%n", quantity);
        System.out.printf("Unit Price: $%.2f%n", unitPrice);

        System.out.println("-------------------------------");

        System.out.printf("Subtotal: $%.2f%n", subtotal);
        System.out.printf("Tax (7%%): $%.2f%n", tax);
        System.out.printf("Grand Total: $%.2f%n", grandTotal);

        System.out.println("-------------------------------");

        System.out.printf(
                "Thank you for your order, %s!%n",
                customerName
        );

        console.close();
    }
}
