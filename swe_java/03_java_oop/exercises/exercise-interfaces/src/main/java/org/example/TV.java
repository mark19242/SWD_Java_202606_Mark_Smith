package org.example;

/**
 * Represents a television that can be turned on and off.
 * Implements the Connectable interface.
 */
public class TV implements Connectable {

    private boolean isOn;
    private String name;

    /**
     * Creates a new TV with the specified name.
     *
     * @param name the name of the TV
     */
    public TV(String name) {
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
     * Turns the TV on.
     */
    @Override
    public void turnOn() {
        this.setOn(true);
    }

    /**
     * Turns the TV off.
     */
    @Override
    public void turnOff() {
        this.setOn(false);
    }

    /**
     * Returns whether the TV is currently powered on.
     *
     * @return true if the TV is on; otherwise false
     */
    @Override
    public boolean getState() {
        return isOn();
    }

    /**
     * Returns the name of the TV.
     *
     * @return the TV name
     */
    @Override
    public String getName() {
        return name;
    }
}