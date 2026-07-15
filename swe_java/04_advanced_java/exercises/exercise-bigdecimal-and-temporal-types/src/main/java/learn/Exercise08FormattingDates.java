package learn;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Exercise 08 — Formatting dates.
 *
 * <p>A {@link DateTimeFormatter} turns a temporal value into a string in the
 * shape you want. Build one with {@code DateTimeFormatter.ofPattern(pattern)}
 * and apply it with {@code value.format(formatter)}.
 *
 * <p>Pattern letters: {@code MM} = 2-digit month, {@code dd} = 2-digit day,
 * {@code yyyy} = 4-digit year, {@code yy} = 2-digit year, {@code HH} = 24-hour.
 * Any other characters (dashes, slashes) pass through literally.
 */
public class Exercise08FormattingDates {

    public static void main(String[] args) {
        System.out.println("=== Exercise 08: Formatting dates ===");

        LocalDateTime moment = LocalDateTime.of(2025, 6, 27, 14, 42, 40);
        System.out.println("MM-dd-yyyy     = " + format(moment, "MM-dd-yyyy")); // 06-27-2025
        System.out.println("MM/dd/yy       = " + format(moment, "MM/dd/yy"));   // 06/27/25
        System.out.println("yyyy.MM.dd HH  = " + format(moment, "yyyy.MM.dd HH")); // 2025.06.27 14

        LocalDate date = LocalDate.of(2025, 1, 5);
        System.out.println("iso           = " + toIso(date)); // 2025-01-05
    }

    /**
     * 1. Format a LocalDateTime using the given pattern string.
     */
    static String format(LocalDateTime value, String pattern) {
        // TODO: build a DateTimeFormatter from `pattern` and return
        //       value.format(formatter).
        return ""; // placeholder so the project compiles
    }

    /**
     * 2. Return the ISO-8601 text of a date (yyyy-MM-dd). LocalDate.toString()
     *    already produces ISO-8601 — return it.
     */
    static String toIso(LocalDate date) {
        // TODO: return date.toString() (ISO-8601), or format with "yyyy-MM-dd".
        return ""; // placeholder so the project compiles
    }
}
