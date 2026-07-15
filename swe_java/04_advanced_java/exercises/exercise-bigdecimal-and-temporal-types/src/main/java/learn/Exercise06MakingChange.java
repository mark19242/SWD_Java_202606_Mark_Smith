package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Exercise 06 — Money math.
 *
 * <p>Two classic money problems that only BigDecimal gets right:
 *
 * <ol>
 *   <li><b>Exact accumulation</b> — adding a penny many times lands on the
 *       exact total; a {@code double} would drift.</li>
 *   <li><b>Making change</b> — {@code divideAndRemainder(coin)} returns a
 *       two-element array: {@code [0]} is how many whole coins fit, {@code [1]}
 *       is the leftover. Feed the leftover into the next-smaller coin.</li>
 * </ol>
 */
public class Exercise06MakingChange {

    static final BigDecimal DOLLAR = BigDecimal.ONE;
    static final BigDecimal QUARTER = new BigDecimal("0.25");
    static final BigDecimal DIME = new BigDecimal("0.1");
    static final BigDecimal NICKEL = new BigDecimal("0.05");

    public static void main(String[] args) {
        System.out.println("=== Exercise 06: Making change ===");

        BigDecimal start = new BigDecimal("100.00");
        System.out.println("100.00 + 250 pennies = " + accumulatePennies(start, 250)); // 102.50

        int[] change = makeChange(new BigDecimal("13.93"));
        System.out.printf("Change for 13.93 -> dollars=%d quarters=%d dimes=%d nickels=%d pennies=%d%n",
                change[0], change[1], change[2], change[3], change[4]); // 13, 3, 1, 1, 3
    }

    /**
     * 1. Start from `start` and add exactly one penny (0.01) `count` times,
     *    returning the exact total. (A double loop here would accumulate error.)
     */
    static BigDecimal accumulatePennies(BigDecimal start, int count) {
        // TODO: loop `count` times, each time adding new BigDecimal("0.01"),
        //       and return the running total.
        return start; // placeholder so the project compiles
    }

    /**
     * 2. Break `amount` into whole coins. Return an int[5]:
     *    [dollars, quarters, dimes, nickels, pennies].
     *
     *    For each coin: call remaining.divideAndRemainder(coin). Element [0] is
     *    the coin count (convert with intValueExact() after setScale(0, ...)),
     *    element [1] is the remainder to carry to the next coin. After nickels,
     *    the leftover is pennies: multiply by 100 and round HALF_EVEN to a whole
     *    number.
     */
    static int[] makeChange(BigDecimal amount) {
        // TODO: use divideAndRemainder against DOLLAR, QUARTER, DIME, NICKEL,
        //       then convert the final remainder to whole pennies.
        return new int[]{0, 0, 0, 0, 0}; // placeholder so the project compiles
    }
}
