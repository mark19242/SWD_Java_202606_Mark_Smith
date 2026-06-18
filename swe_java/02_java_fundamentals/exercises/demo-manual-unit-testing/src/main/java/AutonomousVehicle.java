public class AutonomousVehicle {

    public String reactToTrafficLight(String color) {

        String result = "ERROR";

        color = color.toLowerCase();

        if (color.equals("red")) {
            result = "stop";
        } else if (color.equals("yellow")) {
            result = "slow";
        } else if (color.equals("green")) {
            result = "continue";
        } else {
            result = "ERROR";
        }

        return result;
    }
}