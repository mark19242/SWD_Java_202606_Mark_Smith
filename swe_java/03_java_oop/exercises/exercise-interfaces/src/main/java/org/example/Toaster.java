package org.example;

/**
 * Represents a toaster that can be turned on and off.
 */
public class Toaster implements Connectable {

    private boolean isOn;
    private String name;

    /**
     * Creates a new toaster with the specified name.
     *
     * @param name the name of the toaster
     */
    public Toaster(String name) {
        this.name = name;
        this.setOn(false);
    }

    /**
     * Returns whether the toaster is currently on.
     *
     * @return true if the toaster is on; otherwise false
     */
    public boolean isOn() {
        return isOn;
    }

    /**
     * Updates the power state of the toaster.
     *
     * @param on the new power state
     */
    public void setOn(boolean on) {
        isOn = on;
    }

    /**
     * Turns the toaster on.
     */
    @Override
    public void turnOn() {
        this.setOn(true);
    }

    /**
     * Turns the toaster off.
     */
    @Override
    public void turnOff() {
        this.setOn(false);
    }

    /**
     * Returns whether the toaster is currently powered on.
     *
     * @return true if the toaster is on; otherwise false
     */
    @Override
    public boolean getState() {
        return isOn();
    }

    /**
     * Returns the name of the toaster.
     *
     * @return the toaster name
     */
    @Override
    public String getName() {
        return name;
    }
}