public class RateEngine {
    public void calculateRates(Client client) {
        System.out.println("Calculating rates for: " + client.getLastName()
                + ", " + client.getFirstName());

        System.out.println("Adjusting rate for location: ");
        System.out.println(client.getAddress());

        System.out.println("Adjusting rate for client gender: " + client.getGender());
    }
}
