import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Welcome to the Text Adventure Game!\n");
        Scanner console = new Scanner(System.in);
        boolean keepRunning = true;

        // TODO 1: Create a boolean array.
        boolean key1 = false;
        boolean key2 = false;

        // TODO 2: Update the menu so it has 5 doors instead of 3.
        String mainMenu = "\nYou find yourself in a room with 3 doors. Which one do you enter?" +
                "\n1. The unpainted wooden door" +
                "\n2. The black metal door" +
                "\n3. The blue painted wooden door" +
                "\nDoor Selection: ";

        // TODO 3: Put the room Strings into an array.
        String room1 = "You've entered a room lit with candles. You see a desk with find half of a key!";
        String room1_visited = "You've entered a room lit with candles. You've been here before. There is an empty desk.";

        String room2 = "You've entered a room cold and wet. Upon further inspection you see a hole in the far wall. Something sparkles and catches your attention. It is half of a key!";
        String room2_visited = "You've entered a room cold and wet. You've been here before.";

        String room3_locked = "You've found a room with a red door... it's locked... do you have a key?";
        String room3_unlocked = "You've found a room with a red door... you've used the key you have found and open the door!";

        // TODO 4: Add two new locations so there are 5 total doors.

        // TODO 5: Move the input logic into a promptString method.
        System.out.print("Hello, Please enter your name: ");
        String name = console.nextLine();
        System.out.println("Your name is: " + name);

        while (keepRunning) {
            System.out.print(mainMenu);

            // TODO 6: Convert this String input into an int door number.
            String direction = console.nextLine();

            // TODO 7: Update the switch to use int cases instead of String cases.
            switch (direction) {
                case "1":

                    // TODO 8: Move the room/key logic into a method.
                    if (key1) {
                        System.out.println(room1_visited);
                    } else {
                        key1 = true;
                        System.out.println(room1);
                    }
                    break;

                case "2":

                    // TODO 9: Use the same method for this room instead of repeating the logic.
                    if (key2) {
                        System.out.println(room2_visited);
                    } else {
                        key2 = true;
                        System.out.println(room2);
                    }
                    break;

                case "3":

                    // TODO 10: Update this locked door logic so it checks for all 4 keys.
                    if (key1 && key2) {
                        System.out.println(room3_unlocked);
                        keepRunning = false;
                    } else {
                        System.out.println(room3_locked);
                    }
                    break;

                default:
                    System.out.println("Unable to find the door you are looking for");
            }

            // TODO 11: At the very end, add try/catch for invalid number input.
        }

        System.out.println("\nBye, " + name + "!");
    }

    // TODO 12: Create a print method.

    // TODO 13: Create a promptString method.

    // TODO 14: Create a method that handles finding a key and showing room text.
}