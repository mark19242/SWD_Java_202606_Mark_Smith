package org.example.videogamecharacters;

/**
 * Represents a general video game character.
 *
 * This class is abstract because each specific character type
 * should define its own attack behavior.
 */
public abstract class Character {

    private String name;
    private int health;
    private int attackPower;

    /**
     * Creates a new character with a name, health amount, and attack power.
     *
     * @param name the character's name
     * @param health the character's health points
     * @param attackPower the character's attack strength
     */
    public Character(String name, int health, int attackPower) {
        this.name = name;
        this.health = health;
        this.attackPower = attackPower;
    }

    /**
     * Gets the character's name.
     *
     * @return the character's name
     */
    public String getName() {
        return name;
    }

    /**
     * Updates the character's name.
     *
     * @param name the new character name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the character's health points.
     *
     * @return the character's health
     */
    public int getHealth() {
        return health;
    }

    /**
     * Updates the character's health points.
     *
     * @param health the new health value
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Gets the character's attack power.
     *
     * @return the character's attack power
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * Updates the character's attack power.
     *
     * @param attackPower the new attack power value
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * Performs the character's attack.
     *
     * Each subclass must provide its own version of this method.
     */
    public abstract void attack();
}