package org.example.basketballgame;

/**
 * The Item class represents merchandise or gear that can give the player boosts.
 * Items can improve shooting, speed, jumping, defense, or stamina.
 */
public class Item {

    private String name;
    private String itemType;
    private String description;

    private int shootingBoost;
    private int speedBoost;
    private int jumpBoost;
    private int defenseBoost;
    private int staminaBoost;
    private int value;

    /**
     * Creates a new item with possible power-up effects.
     *
     * @param name the name of the item
     * @param itemType the type of item, such as sneakers, sleeve, drink, or defense gear
     * @param description a short explanation of what the item does
     * @param shootingBoost how much the item improves shooting
     * @param speedBoost how much the item improves speed
     * @param jumpBoost how much the item improves jumping
     * @param defenseBoost how much the item improves defense
     * @param staminaBoost how much the item restores or improves stamina
     * @param value how much the item is worth
     */
    public Item(String name, String itemType, String description,
                int shootingBoost, int speedBoost, int jumpBoost,
                int defenseBoost, int staminaBoost, int value) {

        this.name = name;
        this.itemType = itemType;
        this.description = description;
        this.shootingBoost = shootingBoost;
        this.speedBoost = speedBoost;
        this.jumpBoost = jumpBoost;
        this.defenseBoost = defenseBoost;
        this.staminaBoost = staminaBoost;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getItemType() {
        return itemType;
    }

    public String getDescription() {
        return description;
    }

    public int getShootingBoost() {
        return shootingBoost;
    }

    public int getSpeedBoost() {
        return speedBoost;
    }

    public int getJumpBoost() {
        return jumpBoost;
    }

    public int getDefenseBoost() {
        return defenseBoost;
    }

    public int getStaminaBoost() {
        return staminaBoost;
    }

    public int getValue() {
        return value;
    }

    /**
     * Displays the item's information and boost values.
     */
    public void displayItemInfo() {
        System.out.println("\n--- Item Info ---");
        System.out.println("Name: " + name);
        System.out.println("Type: " + itemType);
        System.out.println("Description: " + description);
        System.out.println("Shooting Boost: " + shootingBoost);
        System.out.println("Speed Boost: " + speedBoost);
        System.out.println("Jump Boost: " + jumpBoost);
        System.out.println("Defense Boost: " + defenseBoost);
        System.out.println("Stamina Boost: " + staminaBoost);
        System.out.println("Value: $" + value);
    }
}