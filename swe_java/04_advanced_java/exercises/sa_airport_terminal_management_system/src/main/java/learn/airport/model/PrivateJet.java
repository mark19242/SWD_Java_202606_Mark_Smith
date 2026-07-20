package learn.airport.model;

public class PrivateJet extends Aircraft {

    private boolean hasLuxuryService;
    private int maxSpeed;

    public PrivateJet(String model,
                      int capacity,
                      double fuelCapacity,
                      boolean hasLuxuryService,
                      int maxSpeed) {

        // Initialize the shared Aircraft fields.
        super(model, capacity, fuelCapacity);

        this.hasLuxuryService = hasLuxuryService;
        this.maxSpeed = maxSpeed;
    }

    public boolean hasLuxuryService() {
        return hasLuxuryService;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String toString() {
        return "PrivateJet{" +
                "hasLuxuryService=" + hasLuxuryService +
                ", maxSpeed=" + maxSpeed +
                ", " + super.toString() +
                '}';
    }
}