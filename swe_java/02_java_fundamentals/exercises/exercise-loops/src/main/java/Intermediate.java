import java.util.Scanner;
public class Intermediate {
    /**
     *  Runs the multiplication table exercise.
     */
    static void main() {
        Scanner console = new Scanner(System.in);

        // =========================
        // Multiplication Table
        // =========================


        System.out.print("Enter a number: ");
        int number = Integer.parseInt(console.nextLine());

        System.out.println("\nMultiplication Table for " + number);

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " x " + i + " = " + (number * i));
        }
    }
}
