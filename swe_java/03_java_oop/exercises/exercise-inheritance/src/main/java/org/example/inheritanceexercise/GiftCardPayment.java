package org.example.inheritanceexercise;

public class GiftCardPayment extends Payment {

    private String accountNumber;
    private double balance;
    private int loyaltyPoints;

    public GiftCardPayment(int id, double amount, String accountNumber,
                           double balance, int loyaltyPoints) {
        super(id, amount);
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public boolean processPayment() {

        System.out.println("Processing gift card payment...");

        if (balance >= super.getAmount()) {
            balance -= super.getAmount();
            loyaltyPoints += (int) (super.getAmount() * 100);

            System.out.println("Payment approved.");
            return true;
        }

        System.out.println("Insufficient gift card balance.");
        return false;
    }

    @Override
    public String toString() {
        return String.format("Payment: %d Amount: $%.2f Type: Gift Card Balance: $%.2f Loyalty Points: %d",
                super.getId(),
                super.getAmount(),
                this.balance,
                this.loyaltyPoints
        );
    }
}