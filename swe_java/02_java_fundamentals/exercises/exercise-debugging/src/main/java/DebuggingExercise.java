public class DebuggingExercise {

    public static void main(String[] args) {

        // Display the start of the program
        System.out.println("Starting Debugging Exercise...");

        // Create an array of numbers to calculate the total
        int[] numbers = {5, 10, 15, 20, 25};

        // Call the method to calculate the sum of the array
        System.out.println("Calculating Sum...");
        int total = calculateSum(numbers);

        // Display the total sum
        System.out.println("Sum: " + total);

        // Call the recursive factorial method
        System.out.println("Calculating Factorial...");
        int factorialResult = factorial(5);

        // Display the factorial result
        System.out.println("Factorial of 5: " + factorialResult);

        // Display that the program has completed
        System.out.println("Debugging Exercise Complete!");
    }

    /**
     * Calculates the sum of all numbers in an array.
     *
     * @param nums The array of integers to add together.
     * @return The total sum of the array.
     */
    public static int calculateSum(int[] nums) {

        // Variable to keep track of the running total
        int sum = 0;

        // Loop through every element in the array
        for (int i = 0; i < nums.length; i++) {

            // Add the current element to the total
            sum += nums[i];
        }

        // Return the completed sum
        return sum;
    }

    /**
     * Recursively calculates the factorial of a number.
     *
     * @param num The number to calculate the factorial for.
     * @return The factorial of the number.
     */
    public static int factorial(int num) {

        // Base case:
        // When num reaches 0, stop the recursion.
        if (num == 0) {
            return 1;
        }

        // Recursive case:
        // Multiply the current number by the factorial
        // of the next smaller number.
        return num * factorial(num - 1);
    }
}