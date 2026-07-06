package org.example.basketballgame;

import java.util.Random;
import java.util.Scanner;

/**
 * The Game class handles the 1-on-1 basketball gameplay.
 * It controls scoring, player choices, opponent choices, and shot chances.
 */
public class Game {

    private Scanner inputScanner;
    private Random random;

    /**
     * Creates a new Game object using the same Scanner from Main.
     *
     * @param inputScanner the Scanner used to collect user input
     */
    public Game(Scanner inputScanner) {
        this.inputScanner = inputScanner;
        this.random = new Random();
    }

    /**
     * Plays a 1-on-1 game between the player and an opponent.
     *
     * @param player the user-controlled player
     * @param opponent the opponent being played against
     * @return true if the player wins, false if the opponent wins
     */
    public boolean playOneOnOne(Player player, Opponent opponent) {

        int playerScore = 0;
        int opponentScore = 0;
        boolean playerHasBall = true;

        System.out.println("\n" + player.getName() + " vs " + opponent.getName());
        System.out.println("First to 15 wins!\n");

        while (playerScore < 15 && opponentScore < 15) {

            System.out.println("\nScore");
            System.out.println(player.getName() + ": " + playerScore);
            System.out.println(opponent.getName() + ": " + opponentScore);

            if (playerHasBall) {
                int moveChoice = getOffensiveChoice();
                int points = getPointsForMove(moveChoice);
                int scoringChance = calculatePlayerScoringChance(player, opponent, moveChoice);

                System.out.println("Scoring Chance: " + scoringChance + "%");

                if (actionSucceeded(scoringChance)) {
                    playerScore += points;
                    player.reduceStamina(5);
                    System.out.println("Bucket! You scored " + points + " points.");
                } else {
                    player.reduceStamina(3);
                    playerHasBall = false;
                    System.out.println("Missed shot. " + opponent.getName() + " gets the ball.");
                }

            } else {
                int defenseChoice = getDefensiveChoice();
                int opponentMove = chooseOpponentMove(opponent);
                int points = getPointsForMove(opponentMove);
                int opponentChance = calculateOpponentScoringChance(player, opponent, opponentMove, defenseChoice);

                System.out.println(opponent.getName() + " attacks with: " + getMoveName(opponentMove));
                System.out.println("Opponent Scoring Chance: " + opponentChance + "%");

                if (actionSucceeded(opponentChance)) {
                    opponentScore += points;
                    System.out.println(opponent.getName() + " scores " + points + " points.");
                } else {
                    playerHasBall = true;
                    System.out.println("Great defense! You get the ball back.");
                }
            }
        }

        System.out.println("\nFinal Score");
        System.out.println(player.getName() + ": " + playerScore);
        System.out.println(opponent.getName() + ": " + opponentScore);

        return playerScore >= 15;
    }

    /**
     * Gets the player's offensive move choice.
     *
     * @return the player's move choice
     */
    private int getOffensiveChoice() {
        System.out.println("\nChoose your offensive move:");
        System.out.println("1. Drive to the basket");
        System.out.println("2. Pull-up jumper");
        System.out.println("3. Step-back three");
        System.out.print("Enter choice: ");

        return getValidChoice();
    }

    /**
     * Gets the player's defensive choice.
     *
     * @return the player's defensive choice
     */
    private int getDefensiveChoice() {
        System.out.println("\nChoose your defense:");
        System.out.println("1. Play tight defense");
        System.out.println("2. Sag off");
        System.out.println("3. Go for steal");
        System.out.print("Enter choice: ");

        return getValidChoice();
    }

    /**
     * Keeps asking the user until they enter 1, 2, or 3.
     *
     * @return a valid menu choice
     */
    private int getValidChoice() {
        int choice = 0;

        while (choice < 1 || choice > 3) {
            try {
                choice = Integer.parseInt(inputScanner.nextLine());

                if (choice < 1 || choice > 3) {
                    System.out.print("Please enter 1, 2, or 3: ");
                }

            } catch (NumberFormatException e) {
                System.out.print("Please enter a number: ");
            }
        }

        return choice;
    }

    /**
     * Returns how many points a move is worth.
     *
     * @param moveChoice the selected move
     * @return 2 or 3 points
     */
    private int getPointsForMove(int moveChoice) {
        if (moveChoice == 3) {
            return 3;
        }

        return 2;
    }

    /**
     * Calculates the player's chance to score.
     *
     * @param player the user-controlled player
     * @param opponent the opponent defending
     * @param moveChoice the player's offensive move
     * @return the final scoring chance
     */
    private int calculatePlayerScoringChance(Player player, Opponent opponent, int moveChoice) {
        int baseChance;
        int powerUpBonus = 0;

        switch (moveChoice) {
            case 1:
                baseChance = 60;
                powerUpBonus = player.getSpeedBoost() + player.getJumpBoost();
                break;
            case 2:
                baseChance = 50;
                powerUpBonus = player.getShootingBoost();
                break;
            case 3:
                baseChance = 40;
                powerUpBonus = player.getShootingBoost();
                break;
            default:
                baseChance = 50;
        }

        int finalChance = baseChance + powerUpBonus - opponent.getDifficultyPenalty();

        if (player.getStamina() < 30) {
            finalChance -= 10;
        }

        return keepChanceInRange(finalChance);
    }

    /**
     * Calculates the opponent's chance to score based on difficulty and player defense.
     *
     * @param player the user-controlled player
     * @param opponent the opponent with the ball
     * @param opponentMove the opponent's offensive move
     * @param defenseChoice the player's defensive choice
     * @return the opponent's final scoring chance
     */
    private int calculateOpponentScoringChance(Player player, Opponent opponent,
                                               int opponentMove, int defenseChoice) {

        int baseChance = 45 + opponent.getDifficultyLevel() * 3;
        int defenseBonus = player.getDefenseBoost();

        if (defenseChoice == 1) {
            if (opponentMove == 2 || opponentMove == 3) {
                baseChance -= 10;
            } else {
                baseChance += 5;
            }
        } else if (defenseChoice == 2) {
            if (opponentMove == 1) {
                baseChance -= 10;
            } else {
                baseChance += 5;
            }
        } else if (defenseChoice == 3) {
            baseChance -= 5;
        }

        int finalChance = baseChance - defenseBonus;

        return keepChanceInRange(finalChance);
    }

    /**
     * Chooses the opponent's move based on their playing style.
     * This makes scouting reports more useful during games.
     *
     * @param opponent the opponent with the ball
     * @return the opponent's move choice
     */
    private int chooseOpponentMove(Opponent opponent) {
        String style = opponent.getStyle().toLowerCase();
        int roll = random.nextInt(100) + 1;

        if (style.contains("driver") || style.contains("inside")
                || style.contains("athletic") || style.contains("finisher")) {

            if (roll <= 65) {
                return 1; // Drive
            } else if (roll <= 85) {
                return 2; // Pull-up jumper
            } else {
                return 3; // Step-back three
            }

        } else if (style.contains("three") || style.contains("shooter")) {

            if (roll <= 60) {
                return 3; // Step-back three
            } else if (roll <= 85) {
                return 2; // Pull-up jumper
            } else {
                return 1; // Drive
            }

        } else if (style.contains("left") || style.contains("tricky")) {

            if (roll <= 60) {
                return 2; // Pull-up jumper
            } else if (roll <= 80) {
                return 1; // Drive
            } else {
                return 3; // Step-back three
            }

        } else if (style.contains("ball handler")) {

            if (roll <= 45) {
                return 1; // Drive
            } else if (roll <= 75) {
                return 2; // Pull-up jumper
            } else {
                return 3; // Step-back three
            }

        } else if (style.contains("all-around") || style.contains("best")) {

            if (roll <= 34) {
                return 1; // Drive
            } else if (roll <= 67) {
                return 2; // Pull-up jumper
            } else {
                return 3; // Step-back three
            }
        }

        return random.nextInt(3) + 1;
    }

    /**
     * Converts a move number into a readable move name.
     *
     * @param moveChoice the move number
     * @return the move name
     */
    private String getMoveName(int moveChoice) {
        switch (moveChoice) {
            case 1:
                return "Drive to the basket";
            case 2:
                return "Pull-up jumper";
            case 3:
                return "Step-back three";
            default:
                return "Unknown move";
        }
    }

    /**
     * Uses Random to decide if the action works.
     *
     * @param chance the chance of success
     * @return true if the roll succeeds, false if it fails
     */
    private boolean actionSucceeded(int chance) {
        int roll = random.nextInt(100) + 1;
        return roll <= chance;
    }

    /**
     * Keeps scoring chances from becoming too low or too high.
     *
     * @param chance the chance before limits are applied
     * @return the adjusted chance
     */
    private int keepChanceInRange(int chance) {
        if (chance < 15) {
            return 15;
        }

        if (chance > 90) {
            return 90;
        }

        return chance;
    }
}