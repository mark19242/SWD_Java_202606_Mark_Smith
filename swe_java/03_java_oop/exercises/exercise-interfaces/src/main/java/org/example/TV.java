package org.example;

public class TV implements Connectable {

    private boolean isOn;
    private String name;

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