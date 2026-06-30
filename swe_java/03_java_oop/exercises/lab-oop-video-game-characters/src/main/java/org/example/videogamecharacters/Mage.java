package org.example.videogamecharacters;

public class Mage extends Character {

    private String spell;

    public Mage(String name, int health, int attackPower, String spell) {
        super(name, health, attackPower);
        this.spell = spell;
    }

    public String getSpell() {
        return spell;
    }

    public void setSpell(String spell) {
        this.spell = spell;
    }

    @Override
    public void attack() {
        System.out.println(getName() + " casts " + spell
                + " and attacks for " + getAttackPower() + " magic damage!");
    }
}

