import java.util.Scanner;

public class Main {

    public static Scanner console = new Scanner(System.in);

    public static void main(String[] args) {

        print("Welcome to the Text Adventure Game!\n");

        boolean keepRunning = true;

        boolean[] keys = {false, false, false, false};

        String mainMenu = "\nYou find yourself in a room with 5 doors. Which one do you enter?" +
                "\n1. The unpainted wooden door" +
                "\n2. The black metal door" +
                "\n3. The yellow painted wooden door" +
                "\n4. The green painted wooden door" +
                "\n5. The blue painted wooden door" +
                "\nDoor Selection: ";

        String[] rooms = {
                "You've entered a room lit with candles. You see a desk and find part of a key!",
                "You've entered a room cold and wet. Upon further inspection you see a hole in the far wall. Something sparkles and catches your attention. It is part of a key!",
                "You've entered a yellow room filled with old books. Inside one book, you find part of a key!",
                "You've entered a green room covered in vines. Behind the vines, you find the final part of the key!"
        };

        String[] unlockedRooms = {
                "You've entered a room lit with candles. You've been here before. There is an empty desk.",
                "You've entered a room cold and wet. You've been here before.",
                "You've entered the yellow room again. The old books are still there, but the key piece is gone.",
                "You've entered the green room again. The vines are still on the wall, but there is nothing new here."
        };

        String room5_locked = "You've found a room with a blue door... it's locked... do you have all 4 key pieces?";
        String room5_unlocked = "You've found a room with a blue door... you've used all 4 key pieces and open the door!";

        String name = promptString("Hello, Please enter your name: ");
        print("Your name is: " + name);

        while (keepRunning) {

            try {

                String direction = promptString(mainMenu);

                int door = Integer.parseInt(direction);

                switch (door) {
                    case 1:
                        findKeyAndDisplayRoomText(rooms, unlockedRooms, keys, door - 1);
                        break;

                    case 2:
                        findKeyAndDisplayRoomText(rooms, unlockedRooms, keys, door - 1);
                        break;

                    case 3:
                        findKeyAndDisplayRoomText(rooms, unlockedRooms, keys, door - 1);
                        break;

                    case 4:
                        findKeyAndDisplayRoomText(rooms, unlockedRooms, keys, door - 1);
                        break;

                    case 5:
                        if (keys[0] && keys[1] && keys[2] && keys[3]) {
                            print(room5_unlocked);
                            keepRunning = false;
                        } else {
                            print(room5_locked);
                        }
                        break;

                    default:
                        print("Unable to find the door you are looking for");
                }
            } catch (NumberFormatException e) {
                print("Invalid menu option. Please try again.");
            }
        }
        print("\nBye, " + name + "!");
    }

    public static void print(String message) {
        System.out.println(message);
    }

    public static String promptString(String message) {
        System.out.print(message);
        return console.nextLine();
    }

    public static void findKeyAndDisplayRoomText(String[] rooms, String[] unlockedRooms, boolean[] keys, int door) {
        if (keys[door]) {
            print(unlockedRooms[door]);
        } else {
            keys[door] = true;
            print(rooms[door]);
        }
    }
}

