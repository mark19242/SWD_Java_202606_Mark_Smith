public class ExerciseDataTypes {

    static void main() {

        // =========================
        // Part 1 - Sports Statistics
        // =========================

        String playerName = "Lionel Messi";
        int jerseyNumber = 10;
        String position = "Forward";
        boolean isStarter = true;
        String teamName = "Inter Miami";

        // =========================
        // Movie Information
        // =========================

        String movieTitle = "Top Gun Maverick";
        int releaseYear = 2022;
        String rating = "PG-13";
        boolean isSequel = true;
        String leadActor = "Tom Cruise";

        // =========================
        // Weather Report
        // =========================

        String cityName = "San Antonio";
        int temperature = 95;
        boolean isRaining = false;
        int humidity = 60;
        String weatherCondition = "Sunny";

        // =========================
        // Flight Information
        // =========================

        String flightNumber = "AA256";
        String departureCity = "New York";
        String arrivalCity = "Los Angeles";
        int gateNumber = 12;
        int terminal = 3;
        boolean isDelayed = false;

        // =========================
        // Part 2 - Print Variables
        // =========================

        System.out.println("Soccer Player: " + playerName +
                " wears jersey number " + jerseyNumber +
                " and plays as a " + position +
                " for " + teamName + ".");

        System.out.println("The movie " + movieTitle +
                " was released in " + releaseYear +
                " and stars " + leadActor + ".");

        System.out.println("Weather Report: " + cityName +
                " has a temperature of " + temperature +
                "°F with " + weatherCondition + ".");

        System.out.println("Flight " + flightNumber +
                " departs from " + departureCity +
                " and arrives in " + arrivalCity +
                " from Gate " + gateNumber +
                " at Terminal " + terminal + ".");

        // =========================
        // Part 3 - Update Variables
        // =========================

        jerseyNumber = 30;
        isStarter = false;

        rating = "PG";
        isSequel = false;

        temperature = 82;
        weatherCondition = "Cloudy";

        gateNumber = 25;
        isDelayed = true;

        System.out.println("\n--- Updated Values ---");

        System.out.println("Updated Jersey Number: " + jerseyNumber);
        System.out.println("Starter Status: " + isStarter);

        System.out.println("Updated Rating: " + rating);
        System.out.println("Sequel Status: " + isSequel);

        System.out.println("Updated Temperature: " + temperature);
        System.out.println("Weather Condition: " + weatherCondition);

        System.out.println("Updated Gate Number: " + gateNumber);
        System.out.println("Flight Delayed: " + isDelayed);

        // =========================
        // Part 4 - Boolean Variables
        // =========================

        boolean hasHomework = true;
        boolean isWeekend = false;
        boolean attendedClass = true;

        System.out.println("\n--- School Status ---");

        System.out.println("Do I have homework? " + hasHomework);
        System.out.println("Is it the weekend? " + isWeekend);
        System.out.println("Did I attend class today? " + attendedClass);

        // =========================
        // Part 5 - Char Variables
        // =========================

        char firstInitial = 'M';
        char lastInitial = 'S';
        char favoriteGrade = 'A';

        System.out.println("\n--- Character Data ---");

        System.out.println("My initials are " +
                firstInitial + "." + lastInitial);

        System.out.println("My favorite grade is " +
                favoriteGrade);
    }
}
