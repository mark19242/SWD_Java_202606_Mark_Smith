import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;

/**
 * TimeTravelActivity (STARTER)
 *
 * Practice working with Java's date and time classes. Fill in the code under
 * each TODO so the program formats dates, calculates deadlines, and builds
 * dates from parts.
 *
 * Keep the class runnable as you go: this starter compiles and runs as-is, so
 * you can fill in one section at a time and re-run to check your work.
 *
 * Compile and run:
 *   javac TimeTravelActivity.java && java TimeTravelActivity
 */
public class TimeTravelActivity {

    public static void main(String[] args) {

// ---------------------------------------------------------------
// Part 1: Get the Current Date and Time
// ---------------------------------------------------------------

// Capture the current date and time using the appropriate temporal types.
        LocalDate today = LocalDate.now();
        LocalTime currentTime = LocalTime.now();
        LocalDateTime currentDateTime = LocalDateTime.now();
        OffsetDateTime currentOffsetDateTime = OffsetDateTime.now();

        System.out.println("Part 1: Current Date and Time");
        System.out.println("--------------------------------");
        System.out.println("Current date: " + today);
        System.out.println("Current time: " + currentTime);
        System.out.println("Current date and time: " + currentDateTime);
        System.out.println("Current date and time with offset: " + currentOffsetDateTime);
        System.out.println();


        // ---------------------------------------------------------------
        // Part 2: Format the Date
        // ---------------------------------------------------------------
        // TODO: Format the current date three ways:
        //         - MM-dd-yyyy      (e.g., 06-27-2025)
        //         - MM/dd/yy        (e.g., 06/27/25)
        //         - Full month name with day and year (e.g., June 27, 2025)
        //       Then format the current date for two other cultures:
        //         - French  (fr-FR)
        //         - Japanese (ja-JP)
        //       Print each formatted value.


        // ---------------------------------------------------------------
        // Part 3: Extract Date Parts
        // ---------------------------------------------------------------
        // TODO: From today's date, extract and print the year, month, day,
        //       and day of the week (e.g., Monday).


        // ---------------------------------------------------------------
        // Part 4: Date Calculations
        // ---------------------------------------------------------------
        // TODO: Create the two project dates below, then calculate and print:
        //         - total days between projectStart and projectEnd
        //         - weeks between them (hint: days / 7)
        //         - days remaining from TODAY until projectStart
        //
        //   LocalDate projectStart = LocalDate.of(2025, 9, 1);
        //   LocalDate projectEnd   = LocalDate.of(2025, 12, 15);


        // ---------------------------------------------------------------
        // Part 5: Build a Date from Parts
        // ---------------------------------------------------------------
        // TODO: Using the current year, build and print dates for:
        //         - New Year's Day (January 1)
        //         - Independence Day (July 4)
        //         - Your birthday
    }
}
