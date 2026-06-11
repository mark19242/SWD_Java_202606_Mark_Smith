public class App {

    // Reusable format string for Part 2
    // %s = String placeholder
    // %d = integer placeholder
    // \n = new line
    static final String SEAT_FORMAT = "%s is assigned value: %d\n";

    public static void main(String[] args) {

        // =====================================================
        // PART 1: DEFINE AND USE AN ENUM
        // =====================================================
        // Enums allow us to create a fixed set of constants.
        // In this example, a coffee order can only be SMALL,
        // MEDIUM, or LARGE.
        // =====================================================

        enum CoffeeSize {
            SMALL,
            MEDIUM,
            LARGE
        }

        // Create a variable of type CoffeeSize
        // and assign a predefined coffee order.
        CoffeeSize joesOrder = CoffeeSize.LARGE;

        // Print the selected coffee size.
        System.out.println("Selected coffee size: " + joesOrder);


        // =====================================================
        // PART 2: CONVERT ENUMS TO INTEGER VALUES
        // =====================================================
        // Every enum constant has an index number.
        // The ordinal() method returns that index.
        //
        // GENERAL = 0
        // PREMIUM = 1
        // VIP = 2
        // =====================================================

        enum SeatSection {
            GENERAL,
            PREMIUM,
            VIP
        }

        // Loop through every value inside the enum.
        for (SeatSection seat : SeatSection.values()) {

            // Print the enum name and its ordinal value.
            System.out.printf(
                    App.SEAT_FORMAT,
                    seat,
                    seat.ordinal()
            );
        }


        // =====================================================
        // PART 3: CONVERT AN INTEGER TO AN ENUM
        // =====================================================
        // The values() method returns an array containing all
        // enum constants in the order they were declared.
        //
        // Index:
        // 0 = RED
        // 1 = YELLOW
        // 2 = GREEN
        // =====================================================

        enum TrafficLight {
            RED,
            YELLOW,
            GREEN
        }

        // Store all enum values into an array.
        TrafficLight[] signals = TrafficLight.values();

        // Choose the value at index 1.
        int signalIndex = 1;

        // Retrieve the enum constant from the array.
        TrafficLight currentSignal = signals[signalIndex];

        // Display the selected traffic signal.
        System.out.println("Traffic light signal: " + currentSignal);


        // =====================================================
        // PART 4: WEEKDAY SCHEDULE
        // =====================================================
        // Enums can be used to represent categories such as
        // days of the week.
        //
        // We will determine whether a selected day falls on
        // a weekend.
        // =====================================================

        enum Weekday {
            MONDAY,
            TUESDAY,
            WEDNESDAY,
            THURSDAY,
            FRIDAY,
            SATURDAY,
            SUNDAY
        }

        // Select a predefined workday.
        Weekday workday = Weekday.WEDNESDAY;

        // Display the selected day.
        System.out.println("Workday selected: " + workday);

        // Check if the day is Saturday or Sunday.
        boolean isWeekend =
                workday == Weekday.SATURDAY || workday == Weekday.SUNDAY;

        // Display the result.
        System.out.println("Is it a weekend? " + isWeekend);


        // =====================================================
        // PART 5: EMERGENCY ALERT SYSTEM
        // =====================================================
        // Enums work very well with switch statements.
        //
        // Each alert level has a predefined response.
        // =====================================================

        enum AlertLevel {
            LOW,
            MODERATE,
            HIGH,
            SEVERE
        }

        // Select the current alert level.
        AlertLevel currentAlert = AlertLevel.HIGH;

        // Display the selected alert level.
        System.out.println("Current alert level: " + currentAlert);

        // Perform different actions based on the alert level.
        switch (currentAlert) {

            case LOW:
                System.out.println("No immediate danger.");
                break;

            case MODERATE:
                System.out.println("Stay alert and aware.");
                break;

            case HIGH:
                System.out.println("Take precautions and stay informed.");
                break;

            case SEVERE:
                System.out.println("Immediate action required!");
                break;
        }
    }
}