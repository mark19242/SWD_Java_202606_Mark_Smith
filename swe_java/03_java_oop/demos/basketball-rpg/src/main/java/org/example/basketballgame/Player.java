package org.example.basketballgame;

/**
 * The Player class represents the user-controlled basketball player.
 * It keeps track of the player's money, stamina, and power-up boosts.
 */
public class Player {

    private String name;
    private int money;
    private int stamina;

    private int shootingBoost;
    private int speedBoost;
    private int jumpBoost;
    private int defenseBoost;

    /**
     * Creates a new player with starting money and default stamina.
     *
     * @param name the player's name
     */
    public Player(String name) {
        this.name = name;
        this.money = 100;
        this.stamina = 100;

        this.shootingBoost = 0;
        this.speedBoost = 0;
        this.jumpBoost = 0;
        this.defenseBoost = 0;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    public int getStamina() {
        return stamina;
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

    /**
     * Adds prize money to the player's current money.
     *
     * @param amount the amount of money earned
     */
    public void earnMoney(int amount) {
        money += amount;
    }

    /**
     * Removes money from the player when entering tournaments.
     *
     * @param amount the amount of money being spent
     * @return true if the player had enough money, false if not
     */
    public boolean spendMoney(int amount) {
        if (money >= amount) {
            money -= amount;
            return true;
        }

        return false;
    }

    /**
     * Lowers stamina after the player uses energy during a game.
     *
     * @param amount the amount of stamina lost
     */
    public void reduceStamina(int amount) {
        stamina -= amount;

        if (stamina < 0) {
            stamina = 0;
        }
    }

    /**
     * Restores stamina without allowing it to go over 100.
     *
     * @param amount the amount of stamina restored
     */
    public void restoreStamina(int amount) {
        stamina += amount;

        if (stamina > 100) {
            stamina = 100;
        }
    }

    public void addShootingBoost(int amount) {
        shootingBoost += amount;
    }

    public void addSpeedBoost(int amount) {
        speedBoost += amount;
    }

    public void addJumpBoost(int amount) {
        jumpBoost += amount;
    }

    public void addDefenseBoost(int amount) {
        defenseBoost += amount;
    }

    /**
     * Applies an item's boosts to the player.
     * This is used when the player equips power-up merchandise from the locker.
     *
     * @param item the item being equipped
     */
    public void equipItem(Item item) {
        if (item == null) {
            System.out.println("No item was equipped.");
            return;
        }

        shootingBoost += item.getShootingBoost();
        speedBoost += item.getSpeedBoost();
        jumpBoost += item.getJumpBoost();
        defenseBoost += item.getDefenseBoost();

        restoreStamina(item.getStaminaBoost());

        System.out.println(item.getName() + " has been equipped.");
    }

    /**
     * Displays the player's current stats.
     */
    public void displayPlayerInfo() {
        System.out.println("\n--- Player Info ---");
        System.out.println("Name: " + name);
        System.out.println("Money: $" + money);
        System.out.println("Stamina: " + stamina);
        System.out.println("Shooting Boost: " + shootingBoost);
        System.out.println("Speed Boost: " + speedBoost);
        System.out.println("Jump Boost: " + jumpBoost);
        System.out.println("Defense Boost: " + defenseBoost);
    }
}