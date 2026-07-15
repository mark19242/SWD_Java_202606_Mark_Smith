package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Exercise 03 — Scale and rounding.
 *
 * <p>The <b>scale</b> of a BigDecimal is the number of digits after the decimal
 * point: {@code 123} has scale 0, {@code 12.99} has scale 2, {@code -23.546}
 * has scale 3.
 *
 * <p>{@code setScale(n)} works only when the discarded digits are all zero;
 * otherwise it throws {@link ArithmeticException} ("Rounding necessary"). To
 * shrink the scale and drop non-zero digits you must supply a
 * {@link RoundingMode}, which forces you to decide how to round <em>on purpose</em>.
 */
public class Exercise03ScaleAndRounding {

    public static void main(String[] args) {
        System.out.println("=== Exercise 03: Scale and rounding ===");

        System.out.println("scaleOf(12.99)          = " + scaleOf(new BigDecimal("12.99"))); // 2
        System.out.println("trimTrailingZeros(...)  = " + trimTrailingZerosToScale(new BigDecimal("99.990000"), 2)); // 99.99
        System.out.println("roundToScale(2)         = " + roundToScale(new BigDecimal("99.990001"), 2)); // 99.99
        System.out.println("roundToScale(1)         = " + roundToScale(new BigDecimal("99.990001"), 1)); // 100.0
        System.out.println("roundToScale(0)         = " + roundToScale(new BigDecimal("99.990001"), 0)); // 100
    }

    /** 1. Return the scale (number of digits after the decimal point) of value. */
    static int scaleOf(BigDecimal value) {
        // TODO: return value's scale.
        return -1; // placeholder so the project compiles
    }

    /**
     * 2. Reduce value to the given scale WITHOUT rounding. This only succeeds if
     *    the digits being dropped are all zeros; otherwise it throws
     *    ArithmeticException — which is the point of this method.
     */
    static BigDecimal trimTrailingZerosToScale(BigDecimal value, int scale) {
        // TODO: return value.setScale(scale) — the single-argument form.
        return value; // placeholder so the project compiles
    }

    /**
     * 3. Reduce value to the given scale, rounding HALF_UP when digits must be
     *    discarded. HALF_UP is the "rounding taught in school" (0.5 rounds away
     *    from zero).
     */
    static BigDecimal roundToScale(BigDecimal value, int scale) {
        // TODO: return value.setScale(scale, RoundingMode.HALF_UP).
        return value; // placeholder so the project compiles
    }
}
