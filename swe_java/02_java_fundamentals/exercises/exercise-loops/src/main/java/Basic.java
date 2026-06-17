import java.util.Random;
import java.util.Scanner;

public class Basic {

    static void main() {

        Scanner console = new Scanner(System.in);

        // =========================
        // Count Up! (For Loop)
        // =========================

        System.out.println("Numbers 1 - 100");

        for (int i = 1; i <= 100; i++) {
            System.out.println(i);
        }

        System.out.println("\nEven Numbers 1 - 100");

        for (int i = 2; i <= 100; i += 2) {
            System.out.println(i);
        }

        // =========================
        // Countdown Timer (While Loop)
        // =========================

        System.out.print("\nEnter a starting number: ");
        int countdown = Integer.parseInt(console.nextLine());

        while (countdown >= 0) {
            System.out.println(countdown);
            countdown--;
        }

        System.out.println("Blast off!");

        // =========================
        // Guess the Number (Do-While Loop)
        // =========================

        Random random = new Random();

        int secretNumber = random.nextInt(50) + 1;
        int guess;

        do {

            System.out.print("\nGuess a number between 1 and 50: ");
            guess = Integer.parseInt(console.nextLine());

            if (guess < secretNumber) {
                System.out.println("Too low!");
            } else if (guess > secretNumber) {
                System.out.println("Too high!");
            }

        } while (guess != secretNumber);

        System.out.println("Correct! You guessed the number!");
    }
}