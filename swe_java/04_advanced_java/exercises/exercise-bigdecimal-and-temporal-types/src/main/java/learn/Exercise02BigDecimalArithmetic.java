package learn;

import java.math.BigDecimal;

/**
 * Exercise 02 — BigDecimal arithmetic.
 *
 * <p>BigDecimal does not support {@code + - * /}. Every arithmetic operation is
 * an instance method that returns a <em>new</em> BigDecimal — the original is
 * never modified, because BigDecimal is immutable.
 *
 * <ul>
 *   <li>{@code a.add(b)}      — a + b</li>
 *   <li>{@code a.subtract(b)} — a - b</li>
 *   <li>{@code a.multiply(b)} — a * b</li>
 *   <li>{@code a.divide(b)}   — a / b (throws if the result does not terminate)</li>
 * </ul>
 */
public class Exercise02BigDecimalArithmetic {

    public static void main(String[] args) {
        System.out.println("=== Exercise 02: BigDecimal arithmetic ===");

        BigDecimal rent = new BigDecimal("1200.00");
        BigDecimal utilities = new BigDecimal("225.00");
        BigDecimal discount = new BigDecimal("55.25");

        BigDecimal fixedCosts = add(rent, utilities);          // 1425.00
        System.out.println("rent + utilities        = " + fixedCosts);

        fixedCosts = subtract(fixedCosts, discount);           // 1369.75
        System.out.println("... - discount          = " + fixedCosts);

        BigDecimal annual = multiply(fixedCosts, new BigDecimal("12")); // 16437.00
        System.out.println("... * 12 (annual)       = " + annual);

        BigDecimal quarterly = divide(annual, new BigDecimal("4"));     // 4109.25
        System.out.println("annual / 4 (quarterly)  = " + quarterly);
    }

    /** 1. Return the sum of a and b. */
    static BigDecimal add(BigDecimal a, BigDecimal b) {
        return a.add(b);
    }

    /** 2. Return a minus b. */
    static BigDecimal subtract(BigDecimal a, BigDecimal b) {
        return a.subtract(b);
    }

    /** 3. Return a times b. */
    static BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b);
    }

    /**
     * 4. Return a divided by b. These inputs divide evenly, so the plain
     *    two-argument divide is safe here. (Exercise 05 handles the case where
     *    division does not terminate.)
     */
    static BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b);
    }
}
