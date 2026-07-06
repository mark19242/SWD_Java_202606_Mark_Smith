package org.example.basketballgame;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Main starts the basketball game and controls the main menu.
 * The player can view stats, check the locker, and enter tournaments.
 */
public class Main {

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Welcome to 1-on-1 Basketball Hustle!");
        System.out.println("====================================");

        System.out.print("\nEnter your player's name: ");
        String playerName = inputScanner.nextLine();

        if (playerName.trim().isEmpty()) {
            playerName = "Rookie";
        }

        Player player = new Player(playerName);
        Locker locker = new Locker();
        Game game = new Game(inputScanner);

        ArrayList<Tournament> tournaments = createTournaments();

        boolean keepPlaying = true;

        while (keepPlaying) {

            System.out.println("\n--- Main Menu ---");
            System.out.println("1. View Player Info");
            System.out.println("2. View / Equip Locker Item");
            System.out.println("3. Enter Tournament");
            System.out.println("4. Sell Locker Item");
            System.out.println("5. Rest and Recover Stamina");
            System.out.println("6. View Final Boss Goal");
            System.out.println("7. Play Pickup Game");
            System.out.println("8. View Trophy Case");
            System.out.println("9. Exit Game");
            System.out.print("Choose an option: ");

            String choice = inputScanner.nextLine();

            switch (choice) {
                case "1":
                    player.displayPlayerInfo();
                    break;

                case "2":
                    manageLocker(inputScanner, player, locker);
                    break;

                case "3":
                    boolean beatFinalBoss = chooseTournament(inputScanner, player, locker, game, tournaments);

                    if (beatFinalBoss) {
                        System.out.println("\nYou defeated the best 1-on-1 player in the nation!");
                        System.out.println("You are now the king of 1-on-1 basketball!");
                        keepPlaying = false;
                    }
                    break;

                case "4":
                    sellLockerItem(inputScanner, player, locker);
                    break;

                case "5":
                    restPlayer(player);
                    break;

                case "6":
                    displayFinalBossGoal(player);
                    break;

                case "7":
                    playPickupGame(player, game);
                    break;

                case "8":
                    player.displayTrophyCase();
                    break;

                case "9":
                    keepPlaying = false;
                    System.out.println("\nThanks for playing!");
                    break;

                default:
                    System.out.println("Please choose a valid menu option.");
            }
        }

        inputScanner.close();
    }

    /**
     * Creates the tournament list for the game.
     * The tournaments get harder as the rewards and opponents improve.
     *
     * @return a list of tournaments the player can enter
     */
    private static ArrayList<Tournament> createTournaments() {

        ArrayList<Tournament> tournaments = new ArrayList<>();

        // Starter and low-level prize items
        Item gatorade = new Item(
                "Gatorade",
                "Drink",
                "Restores stamina after a tough matchup.",
                0, 0, 0, 0, 20, 25
        );

        Item defensiveHeadband = new Item(
                "Defensive Headband",
                "Defense Gear",
                "Improves focus and gives a small defense boost.",
                0, 0, 0, 5, 0, 50
        );

        Item shootingSleeve = new Item(
                "Shooting Sleeve",
                "Sleeve",
                "Improves shooting accuracy on jumpers and threes.",
                8, 0, 0, 0, 0, 75
        );

        Item jumpSneakers = new Item(
                "Jump Sneakers",
                "Sneakers",
                "Helps the player finish stronger at the rim.",
                0, 0, 8, 0, 0, 100
        );

        Item speedSneakers = new Item(
                "Speed Sneakers",
                "Sneakers",
                "Helps the player attack the basket faster.",
                0, 8, 0, 0, 0, 100
        );

        Item ankleBraces = new Item(
                "Ankle Braces",
                "Defense Gear",
                "Helps the player stay in front on defense.",
                0, 0, 0, 8, 0, 125
        );

        Item filmStudyNotebook = new Item(
                "Film Study Notebook",
                "Training Item",
                "Helps the player read opponents better on defense.",
                0, 0, 0, 10, 0, 175
        );

        Item lockdownBadge = new Item(
                "Lockdown Badge",
                "Rare Defense Gear",
                "Gives a major defensive boost.",
                0, 0, 0, 15, 0, 300
        );

        Item proSneakers = new Item(
                "Pro Level Sneakers",
                "Rare Sneakers",
                "Improves speed and jumping for tougher tournaments.",
                0, 10, 10, 0, 0, 400
        );

        // Opponents get harder as the player moves up the tournament ladder.
        Opponent quickJay = new Opponent("Quick Jay", "Fast driver", 1, 75);
        Opponent shooterSam = new Opponent("Shooter Sam", "Three-point shooter", 2, 100);
        Opponent bigMike = new Opponent("Big Mike", "Strong inside scorer", 3, 150);
        Opponent leftyLou = new Opponent("Lefty Lou", "Tricky left-handed scorer", 4, 200);
        Opponent handlesDre = new Opponent("Handles Dre", "Elite ball handler", 5, 300);
        Opponent lockdownLeo = new Opponent("Lockdown Leo", "Defensive specialist", 6, 400);
        Opponent bounceKing = new Opponent("Bounce King", "Athletic finisher and shot blocker", 7, 600);
        Opponent clutchCarter = new Opponent("Clutch Carter", "Sub-boss all-around scorer", 8, 1000);
        Opponent kingSupreme = new Opponent("King Supreme", "Best 1-on-1 player in the nation", 10, 0);

        tournaments.add(new Tournament("Park Run Tournament", 25, 75, 1, quickJay, gatorade));
        tournaments.add(new Tournament("Street Tournament", 50, 100, 2, shooterSam, defensiveHeadband));
        tournaments.add(new Tournament("Local Gym Tournament", 75, 150, 3, bigMike, shootingSleeve));
        tournaments.add(new Tournament("Southside Classic", 125, 225, 4, leftyLou, jumpSneakers));
        tournaments.add(new Tournament("City Tournament", 200, 350, 5, handlesDre, speedSneakers));
        tournaments.add(new Tournament("Lockdown Challenge", 300, 500, 6, lockdownLeo, ankleBraces));
        tournaments.add(new Tournament("Above The Rim Tournament", 400, 700, 7, bounceKing, filmStudyNotebook));
        tournaments.add(new Tournament("State Tournament", 500, 1000, 8, clutchCarter, lockdownBadge));
        tournaments.add(new Tournament("Pro-Am Invitational", 750, 1400, 9, clutchCarter, proSneakers));
        tournaments.add(new Tournament("National 1-on-1 Tournament", 2000, 0, 10, kingSupreme, null));

        return tournaments;
    }

    /**
     * Lets the player choose a tournament from the list.
     *
     * @param inputScanner the Scanner used for user input
     * @param player the user-controlled player
     * @param locker the player's locker
     * @param game the game object that runs the 1-on-1 matchup
     * @param tournaments the list of available tournaments
     * @return true if the player beats the final boss, false otherwise
     */
    private static boolean chooseTournament(Scanner inputScanner, Player player, Locker locker,
                                            Game game, ArrayList<Tournament> tournaments) {

        System.out.println("\n--- Available Tournaments ---");

        for (int i = 0; i < tournaments.size(); i++) {
            Tournament tournament = tournaments.get(i);

            System.out.println((i + 1) + ". " + tournament.getName()
                    + " | Entry Fee: $" + tournament.getEntryFee()
                    + " | Reward: $" + tournament.getRewardMoney()
                    + " | Opponent: " + tournament.getOpponent().getName());
        }

        System.out.print("Choose a tournament number or 0 to cancel: ");

        int tournamentChoice = getNumberInput(inputScanner);

        if (tournamentChoice == 0) {
            System.out.println("Tournament selection canceled.");
            return false;
        }

        if (tournamentChoice < 1 || tournamentChoice > tournaments.size()) {
            System.out.println("Invalid tournament choice.");
            return false;
        }

        Tournament selectedTournament = tournaments.get(tournamentChoice - 1);

        selectedTournament.displayTournamentInfo();

        System.out.print("\nDo you want to enter this tournament? (yes/no): ");
        String confirmChoice = inputScanner.nextLine();

        if (!confirmChoice.equalsIgnoreCase("yes") && !confirmChoice.equalsIgnoreCase("y")) {
            System.out.println("You skipped the tournament.");
            return false;
        }

        return playTournament(player, locker, game, selectedTournament);
    }

    /**
     * Runs the selected tournament and handles entry fee, rewards, and prize items.
     *
     * @param player the user-controlled player
     * @param locker the player's locker
     * @param game the game object that runs the 1-on-1 matchup
     * @param tournament the tournament being played
     * @return true if the player wins the final tournament, false otherwise
     */
    private static boolean playTournament(Player player, Locker locker, Game game, Tournament tournament) {

        if (!tournament.canEnter(player)) {
            System.out.println("\nYou do not have enough money to enter this tournament.");
            return false;
        }

        player.spendMoney(tournament.getEntryFee());
        System.out.println("\nEntry fee paid. Good luck!");

        boolean playerWon = game.playOneOnOne(player, tournament.getOpponent());

        if (playerWon) {
            System.out.println("\nYou won the tournament!");

            player.addWin();
            player.addCompletedTournament(tournament.getName());

            player.earnMoney(tournament.getRewardMoney());

            if (tournament.getRewardMoney() > 0) {
                System.out.println("You earned $" + tournament.getRewardMoney() + ".");
            }

            if (tournament.getPrizeItem() != null) {
                locker.addItem(tournament.getPrizeItem());
                System.out.println("Prize item added to your locker.");
            }

            return tournament.getName().equals("National 1-on-1 Tournament");

        } else {
            player.addLoss();

            System.out.println("\nYou lost the tournament.");
            System.out.println("You lost your entry fee, but you can try again later.");
        }

        return false;
    }

    /**
     * Lets the player view locker items and equip one if they choose.
     *
     * @param inputScanner the Scanner used for user input
     * @param player the user-controlled player
     * @param locker the player's locker
     */
    private static void manageLocker(Scanner inputScanner, Player player, Locker locker) {

        locker.displayLockerItems();

        if (locker.getItems().isEmpty()) {
            return;
        }

        System.out.print("\nChoose an item number to view, or 0 to cancel: ");
        int itemChoice = getNumberInput(inputScanner);

        if (itemChoice == 0) {
            System.out.println("Locker menu canceled.");
            return;
        }

        if (itemChoice < 1 || itemChoice > locker.getItems().size()) {
            System.out.println("Invalid item choice.");
            return;
        }

        Item selectedItem = locker.getItems().get(itemChoice - 1);

        selectedItem.displayItemInfo();

        System.out.print("\nDo you want to equip this item? (yes/no): ");
        String equipChoice = inputScanner.nextLine();

        if (equipChoice.equalsIgnoreCase("yes") || equipChoice.equalsIgnoreCase("y")) {

            // Equipping the item applies its boosts to the player.
            boolean itemWasUsed = player.equipItem(selectedItem);

            if (itemWasUsed) {
                // Remove the item only if it was successfully equipped or consumed.
                locker.removeItem(itemChoice - 1);
            }

        } else {
            System.out.println(selectedItem.getName() + " will stay in your locker.");
        }
    }

    /**
     * Lets the player recover stamina between tournaments.
     * This gives the player a way to prepare before tougher matchups.
     *
     * @param player the user-controlled player
     */
    private static void restPlayer(Player player) {

        System.out.println("\nYou took time to rest and recover.");

        player.restoreStamina(25);

        System.out.println("Your stamina increased by 25.");
        System.out.println("Current stamina: " + player.getStamina());
    }

    /**
     * Lets the player sell an item from the locker for money.
     * This gives the player another way to earn money after winning merchandise.
     *
     * @param inputScanner the Scanner used for user input
     * @param player the user-controlled player
     * @param locker the player's locker
     */
    private static void sellLockerItem(Scanner inputScanner, Player player, Locker locker) {

        locker.displayLockerItems();

        if (locker.getItems().isEmpty()) {
            return;
        }

        System.out.print("\nChoose an item number to sell, or 0 to cancel: ");
        int itemChoice = getNumberInput(inputScanner);

        if (itemChoice == 0) {
            System.out.println("Sell item canceled.");
            return;
        }

        if (itemChoice < 1 || itemChoice > locker.getItems().size()) {
            System.out.println("Invalid item choice.");
            return;
        }

        Item selectedItem = locker.getItems().get(itemChoice - 1);

        selectedItem.displayItemInfo();

        System.out.print("\nDo you want to sell this item for $" + selectedItem.getValue() + "? (yes/no): ");
        String sellChoice = inputScanner.nextLine();

        if (sellChoice.equalsIgnoreCase("yes") || sellChoice.equalsIgnoreCase("y")) {

            Item soldItem = locker.removeItem(itemChoice - 1);

            if (soldItem != null) {
                player.earnMoney(soldItem.getValue());
                System.out.println("You sold " + soldItem.getName() + " for $" + soldItem.getValue() + ".");
                System.out.println("Current money: $" + player.getMoney());
            }

        } else {
            System.out.println(selectedItem.getName() + " will stay in your locker.");
        }
    }

    /**
     * Displays the player's progress toward entering the final boss tournament.
     *
     * @param player the user-controlled player
     */
    private static void displayFinalBossGoal(Player player) {

        int finalBossEntryFee = 2000;
        int moneyNeeded = finalBossEntryFee - player.getMoney();

        System.out.println("\n--- Final Boss Goal ---");
        System.out.println("Final Tournament: National 1-on-1 Tournament");
        System.out.println("Final Boss: King Supreme");
        System.out.println("Entry Fee Needed: $" + finalBossEntryFee);
        System.out.println("Current Money: $" + player.getMoney());

        if (moneyNeeded <= 0) {
            System.out.println("You have enough money to enter the final boss tournament!");
        } else {
            System.out.println("Money Still Needed: $" + moneyNeeded);
            System.out.println("Keep winning tournaments or selling locker items to reach the goal.");
        }
    }

    /**
     * Lets the player play a free pickup game for a small money reward.
     * This helps the player recover if they do not have enough money for tournaments.
     *
     * @param player the user-controlled player
     * @param game the game object that runs the 1-on-1 matchup
     */
    private static void playPickupGame(Player player, Game game) {

        Opponent pickupOpponent = new Opponent(
                "Neighborhood Nick",
                "Casual park player",
                1,
                25
        );

        System.out.println("\n--- Pickup Game ---");
        System.out.println("No entry fee.");
        System.out.println("Reward: $25");
        System.out.println("Opponent: " + pickupOpponent.getName());

        boolean playerWon = game.playOneOnOne(player, pickupOpponent);

        if (playerWon) {
            player.addWin();
            player.earnMoney(pickupOpponent.getRewardMoney());

            System.out.println("\nYou won the pickup game!");
            System.out.println("You earned $" + pickupOpponent.getRewardMoney() + ".");
            System.out.println("Current money: $" + player.getMoney());

        } else {
            player.addLoss();

            System.out.println("\nYou lost the pickup game.");
            System.out.println("No money lost. Run it back when you're ready.");
        }
    }

    /**
     * Gets a number from the user without crashing if they type letters.
     *
     * @param inputScanner the Scanner used for user input
     * @return the number the user entered
     */
    private static int getNumberInput(Scanner inputScanner) {
        try {
            return Integer.parseInt(inputScanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
}