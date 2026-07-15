package learn;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Exercise 04 — Rounding modes.
 *
 * <p>{@link RoundingMode} decides what happens to the digit being dropped. The
 * two you will use most are {@code HALF_UP} (school rounding — ties go away from
 * zero) and {@code HALF_EVEN} ("banker's rounding" — ties go to the nearest even
 * digit, which removes the upward bias when you round many numbers).
 *
 * <p>Watch the ties: rounding {@code 1.25} to one decimal gives {@code 1.3}
 * under HALF_UP but {@code 1.2} under HALF_EVEN.
 */
public class Exercise04RoundingModes {

    public static void main(String[] args) {
        System.out.println("=== Exercise 04: Rounding modes ===");
        System.out.println("VALUE |  UP  | DOWN | CEILING | FLOOR | HALF_UP | HALF_DOWN | HALF_EVEN");
        printRoundingTable(new BigDecimal("1.13"), new BigDecimal("1.99"), new BigDecimal("0.02"));
    }

    /**
     * 1. Round value to one decimal place using the given rounding mode.
     */
    static BigDecimal roundOneDecimal(BigDecimal value, RoundingMode mode) {
        return value.setScale(1, mode);
    }

    /**
     * 2. Walk value from `start` up to (and including) `end`, stepping by
     * `step`, and print each value rounded to one decimal under all seven
     * rounding modes. Use compareTo(...) to test the loop bound (never == on
     * BigDecimal). Use roundOneDecimal(...) for each column.
     */
    static void printRoundingTable(
            BigDecimal start,
            BigDecimal end,
            BigDecimal step) {

        for (BigDecimal value = start;
             value.compareTo(end) <= 0;
             value = value.add(step)) {

            System.out.printf(
                    "%s | %s | %s | %s | %s | %s | %s | %s%n",
                    value,
                    roundOneDecimal(value, RoundingMode.UP),
                    roundOneDecimal(value, RoundingMode.DOWN),
                    roundOneDecimal(value, RoundingMode.CEILING),
                    roundOneDecimal(value, RoundingMode.FLOOR),
                    roundOneDecimal(value, RoundingMode.HALF_UP),
                    roundOneDecimal(value, RoundingMode.HALF_DOWN),
                    roundOneDecimal(value, RoundingMode.HALF_EVEN)
            );
        }
    }

}

