package model;

public class Aquarium {

    // CONSTANTS

    // CLASS VARIABLES & PROPERTIES
    private String tankName;
    private String tankSize;
    private double tankTemp;
    private String tankStatus;
    private AquariumFish fish;

    // DERIVED PROPERTIES
    // (None for now)

    // ACCESSORS (GETTERS & SETTERS)

    public String getTankName() {
        return this.tankName;
    }

    public void setTankName(String tankName) {
        this.tankName = tankName;
    }

    public String getTankSize() {
        return this.tankSize;
    }

    public void setTankSize(String tankSize) {
        this.tankSize = tankSize;
    }

    public double getTankTemp() {
        return this.tankTemp;
    }

    public void setTankTemp(double tankTemp) {
        this.tankTemp = tankTemp;
    }

    public String getTankStatus() {
        return this.tankStatus;
    }

    public void setTankStatus(String tankStatus) {
        this.tankStatus = tankStatus;
    }

    public AquariumFish getFish() {
        return fish;
    }

    public void setFish(AquariumFish fish) {
        this.fish = fish;
    }


    // CONSTRUCTORS

    // Default CTOR
    public Aquarium() {
    }

    // Overload CTOR
    public Aquarium(String tankName, String tankSize, double tankTemp, String tankStatus) {
        this.tankName = tankName;
        this.tankSize = tankSize;
        this.tankTemp = tankTemp;
        this.tankStatus = tankStatus;
    }


    // METHODS
    public boolean isTankReady() {
        return tankStatus.equalsIgnoreCase("Ready");
    }



    // OBJECT OVERRIDES
}