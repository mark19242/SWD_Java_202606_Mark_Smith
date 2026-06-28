package org.example;

public class Toaster implements Connectable {

    // =========================
    // Instance Variables
    // =========================
    private boolean isOn;
    private String name;

    // =========================
    // Constructor
    // =========================
    public Toaster(String name) {
        this.name = name;
        this.setOn(false);
    }

    // =========================
    // Getter & Setter
    // =========================
    public boolean isOn() {
        return isOn;
    }

    public void setOn(boolean on) {
        isOn = on;
    }

    // =========================
    // Interface Methods
    // =========================
    @Override
    public void turnOn() {
        this.setOn(true);
    }

    @Override
    public void turnOff() {
        this.setOn(false);
    }

    @Override
    public boolean getState() {
        return isOn();
    }

    @Override
    public String getName() {
        return name;
    }
}