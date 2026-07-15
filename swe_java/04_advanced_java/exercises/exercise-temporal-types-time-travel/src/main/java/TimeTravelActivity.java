import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Locale;
import java.time.format.FormatStyle;

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
        // Format today's date using three custom display patterns.
        DateTimeFormatter dashFormatter =
                DateTimeFormatter.ofPattern("MM-dd-yyyy");

        DateTimeFormatter slashFormatter =
                DateTimeFormatter.ofPattern("MM/dd/yy");

        DateTimeFormatter fullMonthFormatter =
                DateTimeFormatter.ofPattern("MMMM d, yyyy");

        // Format today's date using French and Japanese cultural conventions.
        DateTimeFormatter frenchFormatter =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.FRANCE);

        DateTimeFormatter japaneseFormatter =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)
                        .withLocale(Locale.JAPAN);

        System.out.println("Part 2: Formatted Dates");
        System.out.println("--------------------------------");
        System.out.println("Dash format: " + today.format(dashFormatter));
        System.out.println("Slash format: " + today.format(slashFormatter));
        System.out.println("Full month format: " + today.format(fullMonthFormatter));
        System.out.println("French format: " + today.format(frenchFormatter));
        System.out.println("Japanese format: " + today.format(japaneseFormatter));
        System.out.println();


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
