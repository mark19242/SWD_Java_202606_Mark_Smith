public class ManualTesting {

    static final String[] HAPPY_PATH_VALUES = {"red", "yellow", "green"};
    static final String[] EDGE_CASE_VALUES = {"Red", "YELLOW", "gReEn"};
    static final String[] INVALID_CASE_VALUES = {"blink", "flash", "out"};

    static final AutonomousVehicle av = new AutonomousVehicle();

    public static void main(String[] args) {

        System.out.println("TEST AV IMPLEMENTATION");
        System.out.println("---------------------------");
        System.out.println("ASSUMED HAPPY PATH BEHAVIOR");
        System.out.println("---------------------------");
        trafficLightTest(HAPPY_PATH_VALUES);

        System.out.println("---------------------------");
        System.out.println("EDGE CASES");
        System.out.println("---------------------------");
        trafficLightTest(EDGE_CASE_VALUES);

        System.out.println("---------------------------");
        System.out.println("INVALID CASES");
        System.out.println("---------------------------");
        trafficLightTest(INVALID_CASE_VALUES);
    }

    private static void trafficLightTest(String[] testValues) {

        for (int i = 0; i < testValues.length; i++) {
            String testValue = testValues[i];

            System.out.println(testValue + ": " + av.reactToTrafficLight(testValue));
        }
    }
}