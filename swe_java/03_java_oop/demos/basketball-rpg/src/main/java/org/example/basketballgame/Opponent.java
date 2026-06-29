package org.example.basketballgame;

/**
 * The Opponent class represents a basketball player the user competes against.
 * Each opponent has a difficulty level, playing style, and reward amount.
 */
public class Opponent {

    private String name;
    private String style;
    private int difficultyLevel;
    private int rewardMoney;

    /**
     * Creates a new opponent for the player to face.
     *
     * @param name the opponent's name
     * @param style the opponent's basketball style
     * @param difficultyLevel how difficult the opponent is
     * @param rewardMoney the money earned if the opponent is defeated
     */
    public Opponent(String name, String style, int difficultyLevel, int rewardMoney) {
        this.name = name;
        this.style = style;
        this.difficultyLevel = difficultyLevel;
        this.rewardMoney = rewardMoney;
    }

    public String getName() {
        return name;
    }

    public String getStyle() {
        return style;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    /**
     * Calculates how much the opponent lowers the player's scoring chance.
     *
     * @return the opponent's defensive penalty
     */
    public int getDifficultyPenalty() {
        return difficultyLevel * 3;
    }

    /**
     * Displays the opponent's basic information.
     */
    public void displayOpponentInfo() {
        System.out.println("\n--- Opponent Info ---");
        System.out.println("Name: " + name);
        System.out.println("Style: " + style);
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("Reward Money: $" + rewardMoney);
    }
}