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

        // Extract individual pieces of information from today's date.
        int currentYear = today.getYear();
        int currentMonth = today.getMonthValue();
        int currentDay = today.getDayOfMonth();
        DayOfWeek currentDayOfWeek = today.getDayOfWeek();

        System.out.println("Part 3: Date Parts");
        System.out.println("--------------------------------");
        System.out.println("Year: " + currentYear);
        System.out.println("Month: " + currentMonth);
        System.out.println("Day: " + currentDay);
        System.out.println("Day of the week: " + currentDayOfWeek);
        System.out.println();


        // ---------------------------------------------------------------
        // Part 4: Date Calculations
        // ---------------------------------------------------------------
// Create the project start and end dates.
        LocalDate projectStart = LocalDate.of(2025, 9, 1);
        LocalDate projectEnd = LocalDate.of(2025, 12, 15);

// Calculate the total number of days and complete weeks in the project.
        long totalDays = ChronoUnit.DAYS.between(projectStart, projectEnd);
        long totalWeeks = totalDays / 7;

// Calculate the number of days from today until the project starts.
        long daysUntilProjectStart = ChronoUnit.DAYS.between(today, projectStart);

        System.out.println("Part 4: Date Calculations");
        System.out.println("--------------------------------");
        System.out.println("Project start: " + projectStart);
        System.out.println("Project end: " + projectEnd);
        System.out.println("Total days between project dates: " + totalDays);
        System.out.println("Total weeks between project dates: " + totalWeeks);
        System.out.println("Days from today until project start: " + daysUntilProjectStart);
        System.out.println();


// ---------------------------------------------------------------
// Part 5: Build a Date from Parts
// ---------------------------------------------------------------

// Build important dates using the current year.
        LocalDate newYearsDay = LocalDate.of(currentYear, Month.JANUARY, 1);
        LocalDate independenceDay = LocalDate.of(currentYear, Month.JULY, 4);
        LocalDate birthday = LocalDate.of(currentYear, Month.JANUARY, 5);

        System.out.println("Part 5: Dates Built from Parts");
        System.out.println("--------------------------------");
        System.out.println("New Year's Day: " + newYearsDay);
        System.out.println("Independence Day: " + independenceDay);
        System.out.println("Birthday: " + birthday);
        System.out.println();
    }
}
