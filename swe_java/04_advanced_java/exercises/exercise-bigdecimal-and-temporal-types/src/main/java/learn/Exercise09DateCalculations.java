package learn;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * Exercise 09 — Date calculations.
 *
 * <p>Three tools for measuring the gap between temporal values:
 *
 * <ul>
 *   <li>{@link ChronoUnit} — a single unit as a {@code long}
 *       ({@code ChronoUnit.DAYS.between(a, b)} = 20).</li>
 *   <li>{@link Period} — a calendar gap in years/months/days between dates.</li>
 *   <li>{@link Duration} — a time gap in hours/minutes/seconds between
 *       date-times.</li>
 * </ul>
 *
 * <p>Compare dates with {@code isBefore(...)} / {@code isAfter(...)} — never
 * with {@code < >}.
 */
public class Exercise09DateCalculations {

    public static void main(String[] args) {
        System.out.println("=== Exercise 09: Date calculations ===");

        LocalDate d1 = LocalDate.of(2024, 5, 12);
        LocalDate d2 = LocalDate.of(2025, 3, 20);
        System.out.println("earlier            = " + earlier(d1, d2)); // 2024-05-12
        System.out.println("later              = " + later(d1, d2));   // 2025-03-20

        LocalDate order = LocalDate.of(2025, 6, 1);
        LocalDate delivery = LocalDate.of(2025, 6, 21);
        System.out.println("daysBetween        = " + daysBetween(order, delivery)); // 20
        System.out.println("periodDays         = " + periodDays(order, delivery));  // 20

        LocalDateTime clockIn = LocalDateTime.of(2025, 6, 1, 9, 0);
        LocalDateTime clockOut = LocalDateTime.of(2025, 6, 1, 17, 30);
        System.out.println("shiftMinutes       = " + shiftMinutes(clockIn, clockOut)); // 510
    }

    /** 1. Return whichever date is earlier. Use isBefore(...). */
    static LocalDate earlier(LocalDate a, LocalDate b) {
        // TODO: return a.isBefore(b) ? a : b.
        return a; // placeholder so the project compiles
    }

    /** 2. Return whichever date is later. Use isAfter(...). */
    static LocalDate later(LocalDate a, LocalDate b) {
        // TODO: return a.isAfter(b) ? a : b.
        return b; // placeholder so the project compiles
    }

    /** 3. Return the whole number of days from start to end (ChronoUnit.DAYS). */
    static long daysBetween(LocalDate start, LocalDate end) {
        // TODO: return ChronoUnit.DAYS.between(start, end).
        return 0L; // placeholder so the project compiles
    }

    /** 4. Return the day component of the Period between start and end. */
    static int periodDays(LocalDate start, LocalDate end) {
        // TODO: return Period.between(start, end).getDays().
        return 0; // placeholder so the project compiles
    }

    /** 5. Return the number of whole minutes between two date-times (Duration). */
    static long shiftMinutes(LocalDateTime start, LocalDateTime end) {
        // TODO: return Duration.between(start, end).toMinutes().
        return 0L; // placeholder so the project compiles
    }
}
