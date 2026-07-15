package learn;

import java.math.BigDecimal;

/**
 * Exercise 01 — Creating a BigDecimal.
 *
 * <p>{@code BigDecimal} lives in {@code java.math} and is a reference type, so
 * it can be {@code null}. It has constructors that take a {@code String}, an
 * {@code int}, or a {@code double}. Passing a non-numeric string throws a
 * {@link NumberFormatException}.
 *
 * <p>Prefer the {@code String} constructor for money: {@code new BigDecimal("0.1")}
 * is exactly 0.1, but {@code new BigDecimal(0.1)} captures the messy binary
 * approximation of the double.
 *
 * <p>Fill in each stubbed method so the numbered comment comes true, then run
 * {@code main} to see your results.
 */
public class Exercise01CreatingBigDecimal {

    public static void main(String[] args) {
        System.out.println("=== Exercise 01: Creating a BigDecimal ===");
        System.out.println("fromString(\"299.99\") = " + fromString("299.99")); // 299.99
        System.out.println("fromInt(12)          = " + fromInt(12));           // 12
        System.out.println("fromDoubleString     = " + fromDoubleString(3500000000000.0)); // 3500000000000
        System.out.println("safeParse(\"299.99\")  = " + safeParse("299.99"));  // 299.99
        System.out.println("safeParse(\"nefarious\") = " + safeParse("nefarious")); // null
    }

    /**
     * 1. Create a BigDecimal from a numeric string. The string constructor is
     * exact — the digits you pass are the digits you get.
     */
    static BigDecimal fromString(String value) {
        return new BigDecimal(value);
    }

    /**
     * 2. Create a BigDecimal from an int. An int has no fractional part, so the
     * result has a scale of 0.
     */
    static BigDecimal fromInt(int value) {
        return new BigDecimal(value);
    }

    /**
     * 3. Create a BigDecimal that exactly represents a whole-dollar amount that
     * arrived as a double. Convert the double to a String first with
     * String.valueOf(value) so you avoid the double's binary noise.
     */
    static BigDecimal fromDoubleString(double value) {
        return new BigDecimal(String.valueOf(value));
    }

    /**
     * 4. Parse a string safely: return a BigDecimal if the string is numeric,
     * or null if it is not. Catch NumberFormatException and return null.
     */
    static BigDecimal safeParse(String value) {
        try {
            return new BigDecimal(value);
        } catch (NumberFormatException ex) {
            return null;
        }
    }

}
