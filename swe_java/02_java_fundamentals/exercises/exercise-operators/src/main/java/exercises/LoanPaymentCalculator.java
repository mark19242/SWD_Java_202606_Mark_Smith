package exercises;

public class LoanPaymentCalculator {

    static void main() {
        double loanAmount = 25000.00;
        double annualInterestRate = 5.5;
        int loanTermYears = 5;

        double monthlyPayment;

        monthlyPayment =
                (loanAmount * (annualInterestRate / 100) / 12);

        loanAmount += 5000;
        annualInterestRate -= 1;
        loanTermYears++;

        monthlyPayment =
                (loanAmount * (annualInterestRate / 100) / 12);

        boolean exceedsThirtyThousand =
                loanAmount > 30000;

        boolean paymentOverFiveHundred =
                monthlyPayment > 500;

        boolean affordable =
                monthlyPayment < 500 && loanTermYears > 3;

        boolean expensive =
                monthlyPayment > 700 || annualInterestRate > 6;

        System.out.println("Loan Amount: $" + loanAmount);
        System.out.println("Interest Rate: " + annualInterestRate + "%");
        System.out.println("Loan Term: " + loanTermYears + " years");
        System.out.println("Monthly Payment: $" + monthlyPayment);

        System.out.println("Exceeds $30,000: " + exceedsThirtyThousand);
        System.out.println("Payment Over $500: " + paymentOverFiveHundred);

        System.out.println("Affordable Loan: " + affordable);
        System.out.println("Expensive Loan: " + expensive);

    }
}
