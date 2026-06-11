public class BasketballGame {

    enum PlayerAction {
        SHOOT,
        PASS,
        DRIBBLE,
        DEFEND
    }

    enum ShotType {
        LAYUP,
        MID_RANGE,
        THREE_POINTER
    }

    public static void main(String[] args) {

        System.out.println(banner("Welcome To My Basketball Game"));

        // Create enum variables
        PlayerAction currentAction = PlayerAction.SHOOT;
        ShotType currentShot = ShotType.THREE_POINTER;

        // Display current selections
        System.out.println("Current Action: " + currentAction);
        System.out.println("Current Shot: " + currentShot);

        // Display ordinal positions
        System.out.println("Action Option: " + currentAction.ordinal());
        System.out.println("Shot Option: " + currentShot.ordinal());

        // Loop through all actions
        System.out.println(banner("Available Actions"));

        for (PlayerAction action : PlayerAction.values()) {
            System.out.println(action);
        }

        // Loop through all shot types
        System.out.println(banner("Available Shots"));

        for (ShotType shot : ShotType.values()) {
            System.out.println(shot);
        }

        // Simulate user input
        String userChoice = "pass";

        System.out.println("\nUser chose: " + userChoice);

        currentAction =
                PlayerAction.valueOf(
                        userChoice.toUpperCase()
                );

        System.out.println("Updated Action: " + currentAction);

        // Simulate another choice
        String shotChoice = "layup";

        System.out.println("\nUser selected shot: " + shotChoice);

        currentShot =
                ShotType.valueOf(
                        shotChoice.toUpperCase()
                );

        System.out.println("Updated Shot: " + currentShot);
    }

    public static String banner(String message) {

        String result = "";

        int bannerWidth = message.length() + 2;

        result += "\n";
        result += "#".repeat(bannerWidth) + "\n";
        result += " " + message + "\n";
        result += "#".repeat(bannerWidth) + "\n";

        return result;
    }
}