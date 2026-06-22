public class DebuggingExercise {

            static void main() {


        System.out.println("Starting Debugging Exercise...");

        int[] numbers = {5, 10, 15, 20, 25};

        // Calculate the sum of the array
        System.out.println("Calculating Sum...");
        int total = calculateSum(numbers);

        System.out.println("Sum: " + total);

        System.out.println("Calculating Factorial...");
        int factorialResult = factorial(5);

        System.out.println("Factorial of 5: " + factorialResult);

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

        for (int i = 0; i < nums.length; i++) {

            sum += nums[i];
        }

        return sum;
    }

    /**
     * Recursively calculates the factorial of a number.
     *
     * @param num The number to calculate the factorial for.
     * @return The factorial of the number.
     */
    public static int factorial(int num) {

        // When num reaches 0, stop the recursion.
        if (num == 0) {
            return 1;
        }

        // Multiply the current number by the factorial
        // of the next smaller number.
        return num * factorial(num - 1);
    }
}