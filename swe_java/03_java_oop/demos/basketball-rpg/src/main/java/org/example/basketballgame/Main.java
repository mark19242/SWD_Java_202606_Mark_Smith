package org.example.basketballgame;

import java.util.ArrayList;
import java.util.HashMap;
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
        HashMap<String, String> scoutingReports = createScoutingReports();
        ArrayList<Item> shopItems = createShopItems();

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
            System.out.println("9. View Scouting Reports");
            System.out.println("10. Visit Item Shop");
            System.out.println("11. Practice Facility");
            System.out.println("12. Exit Game");
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
                    boolean beatFinalBoss = chooseTournament(inputScanner, player, locker, game, tournaments, scoutingReports);
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
                    displayScoutingReports(scoutingReports);
                    break;

                case "10":
                    visitItemShop(inputScanner, player, locker, shopItems);
                    break;

                case "11":
                    visitPracticeFacility(inputScanner, player);
                    break;

                case "12":
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
                                            Game game, ArrayList<Tournament> tournaments,
                                            HashMap<String, String> scoutingReports) {

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

        if (player.hasCompletedTournament(selectedTournament.getName())) {
            System.out.println("\nYou have already won this tournament.");
            System.out.println("Repeat wins give half money and no extra prize item.");
        }

        String opponentName = selectedTournament.getOpponent().getName();

        if (scoutingReports.containsKey(opponentName)) {
            System.out.println("\n--- Scouting Report ---");
            System.out.println(scoutingReports.get(opponentName));
        }

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

        // The final boss tournament is locked until the player beats the sub-boss tournament.
        if (tournament.getName().equals("National 1-on-1 Tournament")
                && !player.hasCompletedTournament("Pro-Am Invitational")) {

            System.out.println("\nThe National 1-on-1 Tournament is still locked.");
            System.out.println("You must win the Pro-Am Invitational before challenging King Supreme.");
            return false;
        }

        // Check this before the game so we know if this is a repeat tournament win.
        boolean alreadyWonTournament = player.hasCompletedTournament(tournament.getName());

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

            if (!alreadyWonTournament) {

                // First-time tournament wins give full rewards and a possible prize item.
                player.addCompletedTournament(tournament.getName());
                player.earnMoney(tournament.getRewardMoney());

                if (tournament.getRewardMoney() > 0) {
                    System.out.println("You earned $" + tournament.getRewardMoney() + ".");
                }

                if (tournament.getPrizeItem() != null) {
                    locker.addItem(tournament.getPrizeItem());
                    System.out.println("Prize item added to your locker.");
                }

            } else {

                // Repeat wins still pay money, but only half the original reward.
                int repeatReward = tournament.getRewardMoney() / 2;
                player.earnMoney(repeatReward);

                System.out.println("You already had this tournament in your trophy case.");

                if (repeatReward > 0) {
                    System.out.println("Repeat win reward: $" + repeatReward + ".");
                }

                System.out.println("No duplicate prize item was awarded.");
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

        if (player.hasCompletedTournament("Pro-Am Invitational")) {
            System.out.println("Sub-Boss Requirement: Complete");
        } else {
            System.out.println("Sub-Boss Requirement: Win the Pro-Am Invitational");
        }
    }

    /**
     * Lets the player pay for a workout to improve one skill area.
     * This gives the player another way to prepare for harder tournaments.
     *
     * @param inputScanner the Scanner used for user input
     * @param player the user-controlled player
     */
    private static void visitPracticeFacility(Scanner inputScanner, Player player) {

        int workoutCost = 50;
        int boostAmount = 3;

        System.out.println("\n--- Practice Facility ---");
        System.out.println("Current Money: $" + player.getMoney());
        System.out.println("Each workout costs $" + workoutCost + ".");
        System.out.println("1. Shooting Workout");
        System.out.println("2. Speed Workout");
        System.out.println("3. Jump Training");
        System.out.println("4. Defensive Slides");
        System.out.println("0. Cancel");
        System.out.print("Choose a workout: ");

        int workoutChoice = getNumberInput(inputScanner);

        if (workoutChoice == 0) {
            System.out.println("Practice canceled.");
            return;
        }

        if (workoutChoice < 1 || workoutChoice > 4) {
            System.out.println("Invalid workout choice.");
            return;
        }

        // Do not charge the player if the selected skill is already maxed out.
        if (isSelectedWorkoutMaxed(player, workoutChoice)) {
            System.out.println("That skill is already maxed out. Choose another workout.");
            return;
        }

        if (!player.spendMoney(workoutCost)) {
            System.out.println("You do not have enough money for this workout.");
            return;
        }

        switch (workoutChoice) {
            case 1:
                player.addShootingBoost(boostAmount);
                System.out.println("You completed a shooting workout.");
                System.out.println("Shooting Boost increased by " + boostAmount + ".");
                break;

            case 2:
                player.addSpeedBoost(boostAmount);
                System.out.println("You completed a speed workout.");
                System.out.println("Speed Boost increased by " + boostAmount + ".");
                break;

            case 3:
                player.addJumpBoost(boostAmount);
                System.out.println("You completed jump training.");
                System.out.println("Jump Boost increased by " + boostAmount + ".");
                break;

            case 4:
                player.addDefenseBoost(boostAmount);
                System.out.println("You completed defensive slides.");
                System.out.println("Defense Boost increased by " + boostAmount + ".");
                break;
        }

        System.out.println("Current Money: $" + player.getMoney());
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
     * Creates scouting reports for the opponents.
     * A HashMap connects each opponent's name to a helpful gameplay tip.
     *
     * @return a HashMap of opponent names and scouting tips
     */
    private static HashMap<String, String> createScoutingReports() {

        HashMap<String, String> scoutingReports = new HashMap<>();

        scoutingReports.put("Quick Jay", "He likes to drive. Sag off to protect the basket.");
        scoutingReports.put("Shooter Sam", "He looks for threes. Play tight defense.");
        scoutingReports.put("Big Mike", "He attacks inside. Sag off and make him shoot.");
        scoutingReports.put("Lefty Lou", "He is tricky with pull-up jumpers. Stay balanced on defense.");
        scoutingReports.put("Handles Dre", "He has strong handles. Going for steals is risky.");
        scoutingReports.put("Lockdown Leo", "He is tough on defense. Use your best power-ups before playing him.");
        scoutingReports.put("Bounce King", "He is athletic at the rim. Defensive gear helps against him.");
        scoutingReports.put("Clutch Carter", "He is the sub-boss. He does a little bit of everything well.");
        scoutingReports.put("King Supreme", "Final boss. Rest, equip your best gear, and bring enough money.");

        return scoutingReports;
    }

    /**
     * Creates the items that are available in the shop.
     *
     * @return a list of items the player can buy
     */
    private static ArrayList<Item> createShopItems() {

        ArrayList<Item> shopItems = new ArrayList<>();

        shopItems.add(new Item(
                "Gatorade",
                "Drink",
                "Restores stamina after a tough matchup.",
                0, 0, 0, 0, 20, 25
        ));

        shopItems.add(new Item(
                "Defensive Headband",
                "Defense Gear",
                "Improves focus and gives a small defense boost.",
                0, 0, 0, 5, 0, 50
        ));

        shopItems.add(new Item(
                "Shooting Sleeve",
                "Sleeve",
                "Improves shooting accuracy on jumpers and threes.",
                8, 0, 0, 0, 0, 75
        ));

        shopItems.add(new Item(
                "Speed Sneakers",
                "Sneakers",
                "Helps the player attack the basket faster.",
                0, 8, 0, 0, 0, 100
        ));

        shopItems.add(new Item(
                "Ankle Braces",
                "Defense Gear",
                "Helps the player stay in front on defense.",
                0, 0, 0, 8, 0, 125
        ));

        return shopItems;
    }

    /**
     * Displays all scouting reports to help the player prepare for matchups.
     *
     * @param scoutingReports the HashMap containing opponent names and tips
     */
    private static void displayScoutingReports(HashMap<String, String> scoutingReports) {

        System.out.println("\n--- Scouting Reports ---");

        for (String opponentName : scoutingReports.keySet()) {
            System.out.println(opponentName + ": " + scoutingReports.get(opponentName));
        }
    }

    /**
     * Lets the player buy an item from the shop.
     * Bought items are stored in the player's locker.
     *
     * @param inputScanner the Scanner used for user input
     * @param player the user-controlled player
     * @param locker the player's locker
     * @param shopItems the list of items available to buy
     */
    private static void visitItemShop(Scanner inputScanner, Player player, Locker locker,
                                      ArrayList<Item> shopItems) {

        System.out.println("\n--- Item Shop ---");
        System.out.println("Current Money: $" + player.getMoney());

        for (int i = 0; i < shopItems.size(); i++) {
            Item item = shopItems.get(i);

            System.out.println((i + 1) + ". " + item.getName()
                    + " | Price: $" + item.getValue()
                    + " | " + item.getDescription());
        }

        System.out.print("Choose an item number to buy, or 0 to cancel: ");
        int itemChoice = getNumberInput(inputScanner);

        if (itemChoice == 0) {
            System.out.println("Shop menu canceled.");
            return;
        }

        if (itemChoice < 1 || itemChoice > shopItems.size()) {
            System.out.println("Invalid shop item choice.");
            return;
        }

        Item selectedItem = shopItems.get(itemChoice - 1);

        selectedItem.displayItemInfo();

        System.out.print("\nDo you want to buy this item for $" + selectedItem.getValue() + "? (yes/no): ");
        String buyChoice = inputScanner.nextLine();

        if (buyChoice.equalsIgnoreCase("yes") || buyChoice.equalsIgnoreCase("y")) {

            if (player.spendMoney(selectedItem.getValue())) {
                locker.addItem(selectedItem);

                System.out.println("You bought " + selectedItem.getName() + ".");
                System.out.println("It was added to your locker.");
                System.out.println("Current money: $" + player.getMoney());

            } else {
                System.out.println("You do not have enough money to buy this item.");
            }

        } else {
            System.out.println("You did not buy anything.");
        }
    }

    /**
     * Checks if the selected workout skill is already at the max boost.
     *
     * @param player the user-controlled player
     * @param workoutChoice the workout option selected by the player
     * @return true if the selected skill is maxed out, false otherwise
     */
    private static boolean isSelectedWorkoutMaxed(Player player, int workoutChoice) {

        switch (workoutChoice) {
            case 1:
                return player.getShootingBoost() >= player.getMaxBoost();

            case 2:
                return player.getSpeedBoost() >= player.getMaxBoost();

            case 3:
                return player.getJumpBoost() >= player.getMaxBoost();

            case 4:
                return player.getDefenseBoost() >= player.getMaxBoost();

            default:
                return false;
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