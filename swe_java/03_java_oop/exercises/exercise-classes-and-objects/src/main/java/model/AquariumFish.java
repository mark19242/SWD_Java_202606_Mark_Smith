package model;

public class AquariumFish {

    // CONSTANTS
    public static final double MIN_TEMP = 10.0;
    public static final double MAX_TEMP = 100.0;

    // CLASS VARIABLES & PROPERTIES
    private String species;
    private String commonName;
    private double maxTemp = MAX_TEMP;
    private double minTemp = MIN_TEMP;
    private String diet;

    // DERIVED PROPERTIES
    public double averageTemp() {
        return (this.minTemp + this.maxTemp) / 2.0;
    }

    // ACCESSORS (GETTERS & SETTERS)
    public String getSpecies() {
        return this.species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getCommonName() {
        return this.commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public void setMaxTemp(double maxTemp) throws IllegalArgumentException {
        if (isValidTemp(maxTemp) && maxTemp >= this.minTemp) {
            this.maxTemp = maxTemp;
        } else {
            throw new IllegalArgumentException("Max Temp is out of range");
        }
    }

    public double getMinTemp() {
        return minTemp;
    }

    public void setMinTemp(double minTemp) throws IllegalArgumentException {
        if (isValidTemp(minTemp) && minTemp <= this.maxTemp) {
            this.minTemp = minTemp;
        } else {
            throw new IllegalArgumentException("Min Temp is out of range");
        }
    }

    public String getDiet() {
        return this.diet;
    }

    public void setDiet(String diet) {
        this.diet = diet;
    }

    // CTORS (Constructors)

    // Default CTOR
    // TODO: CONSIDER REMOVAL - NO SUCH THING AS A FISH WITH NO NAME ETC.
    public AquariumFish() {
    }

    // Overload CTOR
    public AquariumFish(String species, String commonName, String diet) {
        this.species = species;
        this.commonName = commonName;
        this.diet = diet;
    }

    // METHODS
    private boolean isValidTemp(double temp) {
        return (temp <= MAX_TEMP && temp >= MIN_TEMP);
    }

    // OBJECT OVERRIDES - .equals(), .toString(), .hashCode()
}