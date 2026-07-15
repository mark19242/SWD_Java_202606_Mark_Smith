package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Exercise 05 — Scaling during division.
 *
 * <p>Some divisions never terminate: {@code 1 / 3 = 0.3333...} forever. The
 * two-argument {@code divide(divisor)} throws
 * {@code ArithmeticException: Non-terminating decimal expansion} rather than
 * silently losing precision.
 *
 * <p>The three-argument {@code divide(divisor, scale, roundingMode)} fixes this
 * by telling BigDecimal exactly how many decimal places to keep and how to
 * round the last one.
 */
public class Exercise05ScalingDuringDivision {

    public static void main(String[] args) {
        System.out.println("=== Exercise 05: Scaling during division ===");

        BigDecimal three = new BigDecimal("3");
        System.out.println("1/3 @ scale 9  = " + divideWithScale(BigDecimal.ONE, three, 9, RoundingMode.HALF_UP)); // 0.333333333
        System.out.println("1/3 @ scale 3  = " + divideWithScale(BigDecimal.ONE, three, 3, RoundingMode.DOWN));    // 0.333
        System.out.println("safeDivide 1/3 = " + safeDivide(BigDecimal.ONE, three)); // "Non-terminating..." message
        System.out.println("safeDivide 1/4 = " + safeDivide(BigDecimal.ONE, new BigDecimal("4"))); // 0.25
    }

    /**
     * 1. Divide a by b, keeping `scale` digits after the decimal point and
     *    rounding the last kept digit with `mode`.
     */
    static BigDecimal divideWithScale(BigDecimal a, BigDecimal b, int scale, RoundingMode mode) {
        // TODO: return a.divide(b, scale, mode).
        return BigDecimal.ZERO; // placeholder so the project compiles
    }

    /**
     * 2. Try the exact (two-argument) division a.divide(b). If it succeeds,
     *    return the result's toString(). If it throws ArithmeticException
     *    because the expansion does not terminate, catch it and return the
     *    exception's message instead.
     */
    static String safeDivide(BigDecimal a, BigDecimal b) {
        // TODO: try { return a.divide(b).toString(); }
        //       catch (ArithmeticException ex) { return ex.getMessage(); }
        return ""; // placeholder so the project compiles
    }
}
