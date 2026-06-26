package org.example.inheritanceexercise;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args) {

        List<Payment> payments = new ArrayList<>();

        // Sample Payments
        payments.add(new CreditCardPayment(
                1001,
                149.99,
                1234567890123456L,
                "Visa"
        ));

        payments.add(new DirectDebitPayment(
                1002,
                275.50,
                111000025L,
                987654321L,
                "Chase",
                1.50
        ));

        payments.add(new GiftCardPayment(
                1003,
                50.00,
                "GC-2026-001",
                100.00,
                500
        ));

        System.out.println("=== Payment Report ===");

        for (Payment payment : payments) {
            System.out.println(payment);
        }

        System.out.println("\n=== Processing Payments ===");

        for (Payment payment : payments) {
            boolean success = payment.processPayment();

            System.out.println("Payment Successful: " + success);
            System.out.println();
        }
    }
}