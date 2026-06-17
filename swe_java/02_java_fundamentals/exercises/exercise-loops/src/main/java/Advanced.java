/**
 * FizzBuzz problem using loops and conditional statements.
 *
 * Prints:
 * - Fizz for multiples of 3
 * - Buzz for multiples of 5
 * - FizzBuzz for multiples of both 3 and 5
 *
 * @author Mark Smith
 */
public class Advanced {
    static void main() {

        for (int i = 1; i <= 100; i++) {

            if (i % 3 == 0 && i % 5 == 0) {
                System.out.println("FizzBuzz");
            } else if (i % 3 == 0) {
                System.out.println("Fizz");
            } else if (i % 5 == 0) {
                System.out.println("Buzz");
            } else {
                System.out.println(i);
            }
        }
    }
}