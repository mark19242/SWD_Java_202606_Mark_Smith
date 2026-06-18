public class App {

    public static void main(String[] args) {

        // Task 1
        printWelcomeMessage();

        // Task 2
        System.out.println("Sum of 5 and 10: " + sum(5, 10));
        System.out.println("Sum of 20 and 30: " + sum(20, 30));

        // Task 3
        System.out.println("0°C = " + convertToFahrenheit(0) + "°F");
        System.out.println("100°C = " + convertToFahrenheit(100) + "°F");

        // Task 4
        System.out.println("Is 8 even? " + isEven(8));
        System.out.println("Is 7 even? " + isEven(7));

        // Task 5
        printMultipleTimes("Java Methods!", 3);

        // Task 6
        System.out.println("Largest number: " + findMax(10, 25, 15));
        System.out.println("Largest number: " + findMax(100, 50, 75));

        // Task 7
        System.out.println("5! = " + factorial(5));
        System.out.println("7! = " + factorial(7));
        System.out.println("10! = " + factorial(10));

        // Task 8
        greet("Mark");
        greet("Mark", 35);

        // Task 9
        System.out.println("Vowels in \"hello world\": " + countVowels("hello world"));
        System.out.println("Vowels in \"java programming\": " + countVowels("java programming"));

        // Task 10
        System.out.println("10 + 5 = " + calculator(10, 5, '+'));
        System.out.println("10 - 5 = " + calculator(10, 5, '-'));
        System.out.println("10 * 5 = " + calculator(10, 5, '*'));
        System.out.println("10 / 5 = " + calculator(10, 5, '/'));
    }

    /**
     * Prints the welcome message for the exercise.
     */
    public static void printWelcomeMessage() {
        System.out.println("Welcome to the Java Methods Exercise!");
    }

    /**
     * Adds two integers together.
     *
     * @param a The first number.
     * @param b The second number.
     * @return The sum of the two numbers.
     */
    public static int sum(int a, int b) {
        return a + b;
    }

    /**
     * Converts a Celsius temperature to Fahrenheit.
     *
     * @param celsius Temperature in Celsius.
     * @return The converted Fahrenheit temperature.
     */
    public static double convertToFahrenheit(double celsius) {
        return (celsius * 9 / 5) + 32;
    }

    /**
     * Determines whether a number is even.
     *
     * @param number The number to check.
     * @return True if the number is even, otherwise false.
     */
    public static boolean isEven(int number) {
        return number % 2 == 0;
    }

    /**
     * Prints the provided text a specified number of times.
     *
     * @param text The text to print.
     * @param times The number of times to print the text.
     */
    public static void printMultipleTimes(String text, int times) {
        for (int i = 0; i < times; i++) {
            System.out.println(text);
        }
    }

    /**
     * Finds the largest of three integers.
     *
     * @param a The first number.
     * @param b The second number.
     * @param c The third number.
     * @return The largest number.
     */
    public static int findMax(int a, int b, int c) {

        int max = a;

        if (b > max) {
            max = b;
        }

        if (c > max) {
            max = c;
        }

        return max;
    }

    /**
     * Calculates the factorial of a number using recursion.
     *
     * @param n The number to calculate the factorial of.
     * @return The factorial of the number.
     */
    public static int factorial(int n) {

        if (n == 0 || n == 1) {
            return 1;
        }

        return n * factorial(n - 1);
    }

    /**
     * Greets a user by name.
     *
     * @param name The person's name.
     */
    public static void greet(String name) {
        System.out.println("Hello, " + name + "!");
    }

    /**
     * Greets a user by name and age.
     *
     * @param name The person's name.
     * @param age The person's age.
     */
    public static void greet(String name, int age) {
        System.out.println("Hello, " + name + "!");
        System.out.println("You are " + age + " years old.");
    }

    /**
     * Counts the number of vowels in a string.
     *
     * @param text The text to search.
     * @return The total number of vowels.
     */
    public static int countVowels(String text) {

        int count = 0;

        for (int i = 0; i < text.length(); i++) {

            char letter = Character.toLowerCase(text.charAt(i));

            if (letter == 'a'
                    || letter == 'e'
                    || letter == 'i'
                    || letter == 'o'
                    || letter == 'u') {

                count++;
            }
        }

        return count;
    }

    /**
     * Performs a basic arithmetic calculation.
     *
     * @param num1 The first number.
     * @param num2 The second number.
     * @param operator The arithmetic operator (+, -, *, /).
     * @return The result of the calculation.
     */
    public static double calculator(int num1, int num2, char operator) {

        switch (operator) {

            case '+':
                return num1 + num2;

            case '-':
                return num1 - num2;

            case '*':
                return num1 * num2;

            case '/':
                if (num2 == 0) {
                    System.out.println("Error: Cannot divide by zero.");
                    return 0;
                }

                return (double) num1 / num2;

            default:
                System.out.println("Invalid operator.");
                return 0;
        }
    }
}
