package org.example.inheritanceexercise;

public class DirectDebitPayment extends Payment {

    private long routingNumber;
    private long accountNumber;
    private String bankName;
    private double processingFee;

    public DirectDebitPayment(int id, double amount, long routingNumber,
                              long accountNumber, String bankName, double processingFee) {
        super(id, amount);
        this.routingNumber = routingNumber;
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.processingFee = processingFee;
    }

    @Override
    public boolean processPayment() {
        System.out.printf("Direct debit processing fee: $%.2f%n", this.processingFee);
        System.out.println("Sending transaction to bank: " + this.bankName + "...");
        return true;
    }

    @Override
    public String toString() {
        return String.format("Payment: %d Amount: $%.2f Type: Direct Debit Bank: %s",
                super.getId(),
                super.getAmount(),
                this.bankName
        );
    }
}