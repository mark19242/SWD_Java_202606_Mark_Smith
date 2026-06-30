package org.example;

/**
 * Represents a refrigerator that can be turned on and off.
 * Implements the Connectable interface.
 */
public class Fridge implements Connectable {

    private boolean isOn;
    private String name;

    /**
     * Creates a new fridge with the specified name.
     *
     * @param name the name of the fridge
     */
    public Fridge(String name) {
        this.name = name;
        this.setOn(false);
    }

    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    /**
     * Turns the fridge on.
     */
    @Override
    public void turnOn() {
        this.setOn(true);
    }

    /**
     * Turns the fridge off.
     */
    @Override
    public void turnOff() {
        this.setOn(false);
    }

    /**
     * Returns whether the fridge is currently powered on.
     *
     * @return true if the fridge is on; otherwise false
     */
    @Override
    public boolean getState() {
        return isOn();
    }

    /**
     * Returns the name of the fridge.
     *
     * @return the fridge name
     */
    @Override
    public String getName() {
        return name;
    }
}