import java.util.Scanner;

public class App {

    public static void main() {

        Scanner console = new Scanner(System.in);

        // TODO Part 1: Basic Array Operations
        String[] cities = {"San Antonio", "Austin", "Dallas", "Houston", "El Paso"};

        System.out.println("Original cities:");
        System.out.println(cities[0]);
        System.out.println(cities[1]);
        System.out.println(cities[2]);
        System.out.println(cities[3]);
        System.out.println(cities[4]);

        cities[2] = "Fort Worth";

        System.out.println("\nUpdated cities:");
        System.out.println(cities[0]);
        System.out.println(cities[1]);
        System.out.println(cities[2]);
        System.out.println(cities[3]);
        System.out.println(cities[4]);

        System.out.println("\nTotal number of cities: " + cities.length);



        // TODO Part 2: Iterating Over Arrays
        System.out.println("\nCities using a loop:");

        for (int i = 0; i < cities.length; i++) {
            System.out.println(cities[i]);
        }

        System.out.println("\nCities in reverse order:");

        for (int i = cities.length - 1; i >= 0; i--) {
            System.out.println(cities[i]);
        }

        System.out.print("\nEnter a city name to search for: ");
        String citySearch = console.nextLine();

        boolean cityFound = false;

        for (int i = 0; i < cities.length; i++) {
            if (cities[i].equalsIgnoreCase(citySearch)) {
                cityFound = true;
            }
        }

        if (cityFound) {
            System.out.println("City found!");
        } else {
            System.out.println("City not found!");
        }





        // TODO Part 3: Numeric Arrays & Calculations



        // TODO Part 4: Advanced Challenges

    }
}
