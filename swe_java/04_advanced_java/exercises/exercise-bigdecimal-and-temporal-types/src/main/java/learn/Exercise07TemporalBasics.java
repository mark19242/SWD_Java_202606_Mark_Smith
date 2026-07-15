package learn;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * Exercise 07 — Temporal basics.
 *
 * <p>The {@code java.time} package models date/time as immutable value types:
 *
 * <ul>
 *   <li>{@link LocalDate} — a date, no time (2020-10-12)</li>
 *   <li>{@link LocalTime} — a time, no date (14:42:40)</li>
 *   <li>{@link LocalDateTime} — both (2020-10-12T14:42:40)</li>
 * </ul>
 *
 * <p>Build them from parts with {@code of(...)} and pull pieces back out with
 * {@code getYear()}, {@code getMonthValue()}, {@code getDayOfMonth()}, and
 * {@code getDayOfWeek()}. (This exercise avoids {@code now()} so results are
 * predictable.)
 */
public class Exercise07TemporalBasics {

    public static void main(String[] args) {
        System.out.println("=== Exercise 07: Temporal basics ===");

        LocalDate date = makeDate(2020, 10, 12);
        System.out.println("date               = " + date);            // 2020-10-12
        System.out.println("year               = " + yearOf(date));    // 2020
        System.out.println("month              = " + monthOf(date));   // 10
        System.out.println("day                = " + dayOf(date));     // 12
        System.out.println("dayOfWeek          = " + weekdayOf(date)); // MONDAY

        System.out.println("firstOfYear(2025)  = " + firstOfYear(2025)); // 2025-01-01
        System.out.println("makeTime(14,42,40) = " + makeTime(14, 42, 40));       // 14:42:40
        System.out.println("makeDateTime(...)  = " + makeDateTime(2020, 10, 12, 14, 42, 40)); // 2020-10-12T14:42:40
    }

    /** 1. Build a LocalDate from year, month, day. */
    static LocalDate makeDate(int year, int month, int day) {
        // TODO: return LocalDate.of(year, month, day).
        return LocalDate.EPOCH; // placeholder so the project compiles
    }

    /** 2. Return the year component of date. */
    static int yearOf(LocalDate date) {
        // TODO: return date.getYear().
        return 0; // placeholder so the project compiles
    }

    /** 3. Return the month component (1-12) of date. */
    static int monthOf(LocalDate date) {
        // TODO: return date.getMonthValue().
        return 0; // placeholder so the project compiles
    }

    /** 4. Return the day-of-month component of date. */
    static int dayOf(LocalDate date) {
        // TODO: return date.getDayOfMonth().
        return 0; // placeholder so the project compiles
    }

    /** 5. Return the day of the week (e.g. MONDAY) of date. */
    static DayOfWeek weekdayOf(LocalDate date) {
        // TODO: return date.getDayOfWeek().
        return DayOfWeek.MONDAY; // placeholder so the project compiles
    }

    /** 6. Return January 1 of the given year. */
    static LocalDate firstOfYear(int year) {
        // TODO: return LocalDate.of(year, 1, 1).
        return LocalDate.EPOCH; // placeholder so the project compiles
    }

    /** 7. Build a LocalTime from hour, minute, second. */
    static LocalTime makeTime(int hour, int minute, int second) {
        // TODO: return LocalTime.of(hour, minute, second).
        return LocalTime.MIDNIGHT; // placeholder so the project compiles
    }

    /** 8. Build a LocalDateTime from the given date and time parts. */
    static LocalDateTime makeDateTime(int year, int month, int day, int hour, int minute, int second) {
        // TODO: return LocalDateTime.of(year, month, day, hour, minute, second).
        return LocalDateTime.of(makeDate(year, month, day), makeTime(hour, minute, second)); // placeholder
    }
}
