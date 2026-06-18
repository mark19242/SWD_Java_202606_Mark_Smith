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
        int[] testScores = {92, 85, 77, 98, 88};

        int sum = 0;

        for (int i = 0; i < testScores.length; i++) {
            sum += testScores[i];
        }

        System.out.println("\nTotal test score sum: " + sum);

        int highestScore = testScores[0];
        int lowestScore = testScores[0];

        for (int i = 0; i < testScores.length; i++) {
            if (testScores[i] > highestScore) {
                highestScore = testScores[i];
            }

            if (testScores[i] < lowestScore) {
                lowestScore = testScores[i];
            }
        }

        System.out.println("Highest score: " + highestScore);
        System.out.println("Lowest score: " + lowestScore);

        double average = (double) sum / testScores.length;

        System.out.println("Average score: " + average);


        // TODO Part 4: Advanced Challenges

        // Problem: Shift Elements in an Array

        int[] shiftNumbers = {1, 2, 3, 4, 5};

        // I know I need to move the first number to the end,
        // so I tried saving the first value first.
        int firstNumber = shiftNumbers[0];

        // I was trying to shift each number one spot to the left,
        // but I got confused with the indexes here.
        for (int i = 0; i < shiftNumbers.length; i++) {

            // This was my attempt to move the next value into the current spot.
            // The problem is when i gets to the last index,
            // i + 1 goes outside the array.
            shiftNumbers[i] = shiftNumbers[i + 1];
        }

        // I know the first number is supposed to go at the end,
        // but the loop above crashes before this line can really work.
        shiftNumbers[shiftNumbers.length - 1] = firstNumber;

        System.out.println("\nShifted array:");

        for (int i = 0; i < shiftNumbers.length; i++) {
            System.out.println(shiftNumbers[i]);
        }
    }
}
