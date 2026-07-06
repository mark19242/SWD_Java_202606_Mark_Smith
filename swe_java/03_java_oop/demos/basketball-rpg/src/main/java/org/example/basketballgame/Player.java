package org.example.basketballgame;
import java.util.HashSet;

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
    private int wins;
    private int losses;
    private String equippedSneakers;
    private String equippedSleeve;
    private String equippedDefenseItem;
    private String equippedTrainingItem;
    private final int maxBoost = 30;
    private HashSet<String> completedTournaments;

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
        this.wins = 0;
        this.losses = 0;
        this.equippedSneakers = "None";
        this.equippedSleeve = "None";
        this.equippedDefenseItem = "None";
        this.equippedTrainingItem = "None";
        this.completedTournaments = new HashSet<>();
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

    /**
     * Adds to the player's shooting boost without going over the max boost.
     *
     * @param amount the boost amount being added
     */
    public void addShootingBoost(int amount) {
        shootingBoost += amount;

        if (shootingBoost > maxBoost) {
            shootingBoost = maxBoost;
        }
    }

    /**
     * Adds to the player's speed boost without going over the max boost.
     *
     * @param amount the boost amount being added
     */
    public void addSpeedBoost(int amount) {
        speedBoost += amount;

        if (speedBoost > maxBoost) {
            speedBoost = maxBoost;
        }
    }

    /**
     * Adds to the player's jump boost without going over the max boost.
     *
     * @param amount the boost amount being added
     */
    public void addJumpBoost(int amount) {
        jumpBoost += amount;

        if (jumpBoost > maxBoost) {
            jumpBoost = maxBoost;
        }
    }

    /**
     * Adds to the player's defense boost without going over the max boost.
     *
     * @param amount the boost amount being added
     */
    public void addDefenseBoost(int amount) {
        defenseBoost += amount;

        if (defenseBoost > maxBoost) {
            defenseBoost = maxBoost;
        }
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return losses;
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
     * Equips or uses an item from the player's locker.
     * Gear items are equipped into slots, while drinks are consumed right away.
     *
     * @param item the item being equipped or used
     * @return true if the item was equipped or used, false if it could not be used
     */
    public boolean equipItem(Item item) {
        if (item == null) {
            System.out.println("No item was selected.");
            return false;
        }

        String itemType = item.getItemType();

        if (itemType.equalsIgnoreCase("Drink")) {
            restoreStamina(item.getStaminaBoost());
            System.out.println(item.getName() + " was used.");
            return true;
        }

        if (itemType.contains("Sneakers")) {
            if (!equippedSneakers.equals("None")) {
                System.out.println("You already have sneakers equipped.");
                return false;
            }

            equippedSneakers = item.getName();

        } else if (itemType.equalsIgnoreCase("Sleeve")) {
            if (!equippedSleeve.equals("None")) {
                System.out.println("You already have a sleeve equipped.");
                return false;
            }

            equippedSleeve = item.getName();

        } else if (itemType.contains("Defense Gear")) {
            if (!equippedDefenseItem.equals("None")) {
                System.out.println("You already have a defensive item equipped.");
                return false;
            }

            equippedDefenseItem = item.getName();

        } else if (itemType.equalsIgnoreCase("Training Item")) {
            if (!equippedTrainingItem.equals("None")) {
                System.out.println("You already have a training item equipped.");
                return false;
            }

            equippedTrainingItem = item.getName();

        } else {
            System.out.println("This item cannot be equipped right now.");
            return false;
        }

        addShootingBoost(item.getShootingBoost());
        addSpeedBoost(item.getSpeedBoost());
        addJumpBoost(item.getJumpBoost());
        addDefenseBoost(item.getDefenseBoost());
        restoreStamina(item.getStaminaBoost());

        System.out.println(item.getName() + " has been equipped.");
        return true;
    }

    /**
     * Adds one win to the player's record after winning a tournament.
     */
    public void addWin() {
        wins++;
    }

    /**
     * Adds one loss to the player's record after losing a tournament.
     */
    public void addLoss() {
        losses++;
    }

    /**
     * Adds a tournament win to the player's trophy case.
     * A HashSet keeps the same tournament from being listed more than once.
     *
     * @param tournamentName the name of the tournament the player won
     */
    public void addCompletedTournament(String tournamentName) {
        if (completedTournaments.add(tournamentName)) {
            System.out.println(tournamentName + " was added to your trophy case.");
        } else {
            System.out.println("You have already won " + tournamentName + " before.");
        }
    }

    /**
     * Displays the tournaments the player has won.
     */
    public void displayTrophyCase() {
        System.out.println("\n--- Trophy Case ---");

        if (completedTournaments.isEmpty()) {
            System.out.println("You have not won any tournaments yet.");
            return;
        }

        for (String tournamentName : completedTournaments) {
            System.out.println("- " + tournamentName);
        }
    }

    /**
     * Checks if the player has already won a specific tournament.
     *
     * @param tournamentName the name of the tournament being checked
     * @return true if the tournament is in the trophy case, false otherwise
     */
    public boolean hasCompletedTournament(String tournamentName) {
        return completedTournaments.contains(tournamentName);
    }

    /**
     * Displays the player's current stats.
     */
    public void displayPlayerInfo() {
        System.out.println("\n--- Player Info ---");
        System.out.println("Name: " + name);
        System.out.println("Money: $" + money);
        System.out.println("Stamina: " + stamina);
        System.out.println("Wins: " + wins);
        System.out.println("Losses: " + losses);
        System.out.println("Shooting Boost: " + shootingBoost);
        System.out.println("Speed Boost: " + speedBoost);
        System.out.println("Jump Boost: " + jumpBoost);
        System.out.println("Defense Boost: " + defenseBoost);
        System.out.println("Max Boost Per Skill: " + maxBoost);
        System.out.println("Equipped Sneakers: " + equippedSneakers);
        System.out.println("Equipped Sleeve: " + equippedSleeve);
        System.out.println("Equipped Defense Item: " + equippedDefenseItem);
        System.out.println("Equipped Training Item: " + equippedTrainingItem);
    }
}