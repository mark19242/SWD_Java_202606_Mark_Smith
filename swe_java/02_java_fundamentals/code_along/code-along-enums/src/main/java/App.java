public class App {

    enum CompassPoint {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

    public static void main(String[] args) {

        System.out.println(banner("Welcome to Enums"));

        CompassPoint currentDirection = CompassPoint.EAST;

        System.out.println(
                "I am heading " +
                        currentDirection.toString().toLowerCase()
        );

        int currentDirectionOrdinal = currentDirection.ordinal();

        System.out.println(
                "This is option: " +
                        currentDirectionOrdinal
        );
    }

    public static String banner(String message) {

        // declare method return value, the result
        String result = "";

        int bannerWidth = message.length() + 2;

        result += "#".repeat(bannerWidth) + "\n";
        result += " " + message + "\n";
        result += "#".repeat(bannerWidth) + "\n";

        // return the result to satisfy the method signature
        return result;
    }
}