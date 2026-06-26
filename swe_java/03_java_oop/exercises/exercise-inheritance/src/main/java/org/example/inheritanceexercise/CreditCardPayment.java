package org.example.inheritanceexercise;

public class CreditCardPayment extends Payment {

    private long accountNumber;
    private String cardVendor;

    public CreditCardPayment(int id, double amount,
                             long accountNumber,
                             String cardVendor) {

        super(id, amount);
        this.accountNumber = accountNumber;
        this.cardVendor = cardVendor;
    }

    @Override
    public boolean processPayment() {

        System.out.printf(
                "Processing via: %s for $%.2f%n",
                this.cardVendor,
                super.getAmount()
        );

        return true;
    }

    @Override
    public String toString() {

        return String.format(
                "Payment: %d Amount: $%.2f Type: Credit Card Vendor: %s",
                super.getId(),
                super.getAmount(),
                this.cardVendor
        );
    }
}

