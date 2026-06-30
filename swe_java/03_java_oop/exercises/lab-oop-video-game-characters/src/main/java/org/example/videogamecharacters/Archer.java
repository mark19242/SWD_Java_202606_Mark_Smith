package org.example.videogamecharacters;

/**
 * Represents an archer character in the game.
 *
 * An Archer is a specific type of Character that uses arrows to attack.
 */
public class Archer extends Character {

    private String arrowType;

    /**
     * Creates a new Archer with a name, health, attack power, and arrow type.
     *
     * @param name the archer's name
     * @param health the archer's health points
     * @param attackPower the archer's attack strength
     * @param arrowType the type of arrow the archer uses
     */
    public Archer(String name, int health, int attackPower, String arrowType) {
        super(name, health, attackPower);
        this.arrowType = arrowType;
    }

    /**
     * Gets the archer's arrow type.
     *
     * @return the archer's arrow type
     */
    public String getArrowType() {
        return arrowType;
    }

    /**
     * Updates the archer's arrow type.
     *
     * @param arrowType the new arrow type
     */
    public void setArrowType(String arrowType) {
        this.arrowType = arrowType;
    }

    /**
     * Performs the archer's attack using their arrow type.
     */
    @Override
    public void attack() {
        System.out.println(getName() + " shoots a " + arrowType
                + " arrow and attacks for " + getAttackPower() + " damage!");
    }
}