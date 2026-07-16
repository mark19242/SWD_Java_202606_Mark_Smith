package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Exercise 10 — Invoice aging (stub for the markdown exercise).
 *
 * <p>See {@code Exercise10-InvoiceAging.md} for the full instructions. Finish
 * the three methods below so an invoice's overdue days, late fee, and balance
 * due are computed with exact BigDecimal money math and java.time date
 * arithmetic.
 */
public class Exercise10InvoiceAging {

    public static void main(String[] args) {
        System.out.println("=== Exercise 10: Invoice aging ===");

        BigDecimal amount = new BigDecimal("1000.00");
        BigDecimal dailyRate = new BigDecimal("0.001"); // 0.1% per day
        LocalDate dueDate = LocalDate.of(2025, 6, 1);
        LocalDate asOf = LocalDate.of(2025, 7, 1);

        long overdue = daysOverdue(dueDate, asOf);
        System.out.println("daysOverdue = " + overdue);                              // 30
        System.out.println("lateFee     = " + lateFee(amount, dailyRate, overdue));  // 30.00
        System.out.println("balanceDue  = " + balanceDue(amount, dailyRate, dueDate, asOf)); // 1030.00
    }

    /**
     * 1. Return the whole days from dueDate to asOf, never negative. If the
     *    invoice is not yet due, return 0.
     */
    static long daysOverdue(LocalDate dueDate, LocalDate asOf) {
        long days = ChronoUnit.DAYS.between(dueDate, asOf);

        return Math.max(0, days);
    }

    /**
     * 2. Return amount * dailyRate * daysOverdue, scaled to 2 decimals HALF_UP.
     */
    /**
     * 2. Return amount * dailyRate * daysOverdue,
     *    scaled to 2 decimal places using HALF_UP rounding.
     */
    static BigDecimal lateFee(
            BigDecimal amount,
            BigDecimal dailyRate,
            long daysOverdue) {

        BigDecimal overdueDays = BigDecimal.valueOf(daysOverdue);

        return amount
                .multiply(dailyRate)
                .multiply(overdueDays)
                .setScale(2, RoundingMode.HALF_UP);
    }

    /**
     * 3. Return amount + lateFee(...), scaled to 2 decimals HALF_UP.
     */
    /**
     * 3. Return the original amount plus the late fee,
     *    scaled to 2 decimal places.
     */
    static BigDecimal balanceDue(
            BigDecimal amount,
            BigDecimal dailyRate,
            LocalDate dueDate,
            LocalDate asOf) {

        long overdueDays = daysOverdue(dueDate, asOf);
        BigDecimal fee = lateFee(amount, dailyRate, overdueDays);

        return amount
                .add(fee)
                .setScale(2, RoundingMode.HALF_UP);
    }
}
