package org.example.basketballgame;

/**
 * The Tournament class represents a 1-on-1 tournament the player can enter.
 * Each tournament has an entry fee, reward money, opponent, and possible prize item.
 */
public class Tournament {

    private String name;
    private int entryFee;
    private int rewardMoney;
    private int difficultyLevel;
    private Opponent opponent;
    private Item prizeItem;

    /**
     * Creates a new tournament.
     *
     * @param name the name of the tournament
     * @param entryFee the money needed to enter the tournament
     * @param rewardMoney the money earned if the player wins
     * @param difficultyLevel how difficult the tournament is
     * @param opponent the opponent the player will face
     * @param prizeItem the item the player can win
     */
    public Tournament(String name, int entryFee, int rewardMoney, int difficultyLevel,
                      Opponent opponent, Item prizeItem) {

        this.name = name;
        this.entryFee = entryFee;
        this.rewardMoney = rewardMoney;
        this.difficultyLevel = difficultyLevel;
        this.opponent = opponent;
        this.prizeItem = prizeItem;
    }

    public String getName() {
        return name;
    }

    public int getEntryFee() {
        return entryFee;
    }

    public int getRewardMoney() {
        return rewardMoney;
    }

    public int getDifficultyLevel() {
        return difficultyLevel;
    }

    public Opponent getOpponent() {
        return opponent;
    }

    public Item getPrizeItem() {
        return prizeItem;
    }

    /**
     * Checks if the player has enough money to enter this tournament.
     *
     * @param player the player trying to enter
     * @return true if the player has enough money, false if not
     */
    public boolean canEnter(Player player) {
        return player.getMoney() >= entryFee;
    }

    /**
     * Displays the tournament information before the player enters.
     */
    public void displayTournamentInfo() {
        System.out.println("\n--- Tournament Info ---");
        System.out.println("Tournament: " + name);
        System.out.println("Entry Fee: $" + entryFee);
        System.out.println("Reward Money: $" + rewardMoney);
        System.out.println("Difficulty Level: " + difficultyLevel);
        System.out.println("Opponent: " + opponent.getName());

        if (prizeItem != null) {
            System.out.println("Prize Item: " + prizeItem.getName());
        } else {
            System.out.println("Prize Item: None");
        }
    }
}