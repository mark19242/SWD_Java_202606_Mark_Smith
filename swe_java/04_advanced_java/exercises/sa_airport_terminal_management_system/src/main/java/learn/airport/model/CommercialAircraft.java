package learn.airport.model;

public class CommercialAircraft extends Aircraft {

    private String airlineName;

    public CommercialAircraft(String model, int capacity, double fuelCapacity, String airlineName) {

        // Call the parent constructor to initialize common aircraft fields.
        super(model, capacity, fuelCapacity);

        // Initialize the airline name.
        this.airlineName = airlineName;
    }

    public String getAirlineName() {
        return airlineName;
    }

    @Override
    public String toString() {
        return "CommercialAircraft{" +
                "airlineName='" + airlineName + '\'' +
                ", " + super.toString() +
                '}';
    }
}