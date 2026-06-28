package org.example;

public class Fridge implements Connectable {

    private boolean isOn;
    private String name;

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