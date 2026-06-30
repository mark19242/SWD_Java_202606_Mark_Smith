package org.example.videogamecharacters;

/**
 * Represents a mage character in the game.
 *
 * A Mage is a specific type of Character that uses spells to attack.
 */
public class Mage extends Character {

    private String spell;

    /**
     * Creates a new Mage with a name, health, attack power, and spell.
     *
     * @param name the mage's name
     * @param health the mage's health points
     * @param attackPower the mage's attack strength
     * @param spell the spell the mage uses
     */
    public Mage(String name, int health, int attackPower, String spell) {
        super(name, health, attackPower);
        this.spell = spell;
    }

    /**
     * Gets the mage's spell.
     *
     * @return the mage's spell
     */
    public String getSpell() {
        return spell;
    }

    /**
     * Updates the mage's spell.
     *
     * @param spell the new spell
     */
    public void setSpell(String spell) {
        this.spell = spell;
    }

    /**
     * Performs the mage's attack using their spell.
     */
    @Override
    public void attack() {
        System.out.println(getName() + " casts " + spell
                + " and attacks for " + getAttackPower() + " magic damage!");
    }
}