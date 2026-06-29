public class RateEngine {

    public void calculateRates(Client client) {
        System.out.println("Calculating rates for: "
                + client.getLastName()
                + ", "
                + client.getFirstName());

        locationAdjustment(client.getAddress());

        genderAdjustment(client.getGender());
    }

    public void locationAdjustment(String location) {
        System.out.println("Adjusting rate for location: ");
        System.out.println(location);
    }

    public void genderAdjustment(String gender) {
        System.out.println("Adjusting rate for client gender: " + gender);
    }
}