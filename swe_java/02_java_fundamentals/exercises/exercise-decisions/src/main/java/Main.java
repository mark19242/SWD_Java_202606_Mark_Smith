/*
 * Notes:
 *
 * This exercise helped me practice decision making
 * using if, else-if, nested if statements, and switch statements.
 *
 * One thing I learned was the equalsIgnoreCase() method.
 * I had not used it before, but it makes user input
 * easier to handle because capitalization does not matter.
 *
 * Example:
 * "yes"
 * "YES"
 * "Yes"
 *
 * are all treated the same.
 */
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        // Create Scanner object for user input
        Scanner console = new Scanner(System.in);

        // =========================
        // Game Introduction
        // =========================
        // Introduce the player to the adventure and ask
        // whether they want to enter the cave.

        System.out.println("Welcome to the Mysterious Cave Adventure!");
        System.out.println("You stand at the entrance of a dark cave.");
        System.out.println("Do you want to enter the cave? (yes/no)");

        String enterChoice = console.nextLine();

        // Learned about equalsIgnoreCase().
        // This allows the player to type:
        // yes, Yes, YES, yEs, etc...
        // without causing the program to fail.

        if (enterChoice.equalsIgnoreCase("yes")) {

            // =========================
            // Cave Entrance
            // =========================
            // Player decided to enter the cave.
            // Present the first major decision.

            System.out.println("\nYou step inside the cave.");
            System.out.println("The path splits in two directions.");
            System.out.println("Do you want to go left or right?");

            String pathChoice = console.nextLine();

            // =========================
            // Left Path
            // =========================
            // Use an if statement to determine which path
            // the player chooses.

            if (pathChoice.equalsIgnoreCase("left")) {

                System.out.println("\nYou walk down the left path.");
                System.out.println("A mysterious figure appears in front of you!");
                System.out.println("Do you want to fight or flee?");

                String actionChoice = console.nextLine();

                // =========================
                // Nested Decision
                // =========================
                // This decision only happens because the player
                // selected the left path.
                // Demonstrates a nested if statement.

                if (actionChoice.equalsIgnoreCase("fight")) {

                    System.out.println("\nYou bravely fight the mysterious figure.");
                    System.out.println("You win and escape the cave as a hero!");

                } else if (actionChoice.equalsIgnoreCase("flee")) {

                    System.out.println("\nYou run as fast as you can.");
                    System.out.println("You barely escape the cave safely.");

                } else {

                    // Handle invalid user input.
                    System.out.println("\nInvalid choice. The figure disappears, and you leave confused.");
                }

            }

            // =========================
            // Right Path
            // =========================
            // Player discovers a treasure room.
            // This path demonstrates a switch statement.

            else if (pathChoice.equalsIgnoreCase("right")) {

                System.out.println("\nYou walk down the right path.");
                System.out.println("You discover a treasure room!");
                System.out.println("Choose an artifact:");
                System.out.println("1. Gem");
                System.out.println("2. Key");
                System.out.println("3. Book");

                int artifactChoice = Integer.parseInt(console.nextLine());

                // =========================
                // Switch Statement
                // =========================
                // Switch works well here because the user is
                // selecting one exact value from a list.

                switch (artifactChoice) {

                    case 1:

                        System.out.println("\nYou chose the glowing gem.");
                        System.out.println("The gem lights your way out of the cave. You survive!");
                        break;

                    case 2:

                        System.out.println("\nYou chose the ancient key.");
                        System.out.println("The key unlocks a secret door filled with treasure!");
                        break;

                    case 3:

                        System.out.println("\nYou chose the dusty book.");
                        System.out.println("The book reveals the cave's secrets and you become a legend!");
                        break;

                    default:

                        // Handles invalid artifact selections.
                        System.out.println("\nInvalid artifact choice. The treasure room fades away.");
                        break;
                }

            } else {

                // Handles invalid path selections.
                System.out.println("\nInvalid path choice. You get lost and return to the entrance.");
            }

        }

        // =========================
        // Player Leaves
        // =========================
        // Alternative ending if the player decides not
        // to enter the cave.

        else if (enterChoice.equalsIgnoreCase("no")) {

            System.out.println("\nYou decide not to enter the cave.");
            System.out.println("Sometimes the safest adventure is walking away.");

        }

        // =========================
        // Invalid Starting Choice
        // =========================
        // Handles anything other than yes or no.

        else {

            System.out.println("\nInvalid choice. The cave entrance closes before you can decide.");
        }

        // =========================
        // End Game
        // =========================

        System.out.println("\nThank you for playing!");

        // Close Scanner to prevent resource leaks.
        console.close();
    }
}