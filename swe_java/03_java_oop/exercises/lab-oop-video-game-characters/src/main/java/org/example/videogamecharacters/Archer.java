package org.example.videogamecharacters;

public class Archer extends Character {

    private String arrowType;

    public Archer(String name, int health, int attackPower, String arrowType) {
        super(name, health, attackPower);
        this.arrowType = arrowType;
    }

    public String getArrowType() {
        return arrowType;
    }

    public void setArrowType(String arrowType) {
        this.arrowType = arrowType;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " shoots a " + arrowType
                + " arrow and attacks for " + getAttackPower() + " damage!");
    }
}