public class App {
    public static void main(String[] args) {
        RateEngine myEngine = new RateEngine();
        Client myClient = new Client();

        myClient.setFirstName("Clint");
        myClient.setLastName("McClient");
        myClient.setAddress("121 Broadway New York, New York");
        myClient.setGender("Male");

        myEngine.calculateRates(myClient);
    }
}
