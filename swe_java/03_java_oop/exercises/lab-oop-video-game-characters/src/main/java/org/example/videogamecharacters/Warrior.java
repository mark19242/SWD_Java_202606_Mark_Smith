package org.example.videogamecharacters;

/**
 * Represents a warrior character in the game.
 *
 * A Warrior is a specific type of Character that uses a weapon to attack.
 */
public class Warrior extends Character {

    private String weaponType;

    /**
     * Creates a new Warrior with a name, health, attack power, and weapon type.
     *
     * @param name the warrior's name
     * @param health the warrior's health points
     * @param attackPower the warrior's attack strength
     * @param weaponType the type of weapon the warrior uses
     */
    public Warrior(String name, int health, int attackPower, String weaponType) {
        super(name, health, attackPower);
        this.weaponType = weaponType;
    }

    /**
     * Gets the warrior's weapon type.
     *
     * @return the warrior's weapon type
     */
    public String getWeaponType() {
        return weaponType;
    }

    /**
     * Updates the warrior's weapon type.
     *
     * @param weaponType the new weapon type
     */
    public void setWeaponType(String weaponType) {
        this.weaponType = weaponType;
    }

    /**
     * Performs the warrior's attack using their weapon.
     */
    @Override
    public void attack() {
        System.out.println(getName() + " swings a " + weaponType
                + " and attacks for " + getAttackPower() + " damage!");
    }
}