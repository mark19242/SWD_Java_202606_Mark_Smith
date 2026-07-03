package org.example.basketballgame;

import java.util.Scanner;

/**
 * Main starts the basketball game and connects the main classes together.
 * This version tests one tournament before I build the full tournament menu.
 */
public class Main {

    public static void main(String[] args) {

        Scanner inputScanner = new Scanner(System.in);

        System.out.println("Welcome to 1-on-1 Basketball Hustle!");
        System.out.println("====================================");

        // Get the player's name before creating the Player object.
        System.out.print("\nEnter your player's name: ");
        String playerName = inputScanner.nextLine();

        if (playerName.trim().isEmpty()) {
            playerName = "Rookie";
        }

        Player player = new Player(playerName);
        Locker locker = new Locker();
        Game game = new Game(inputScanner);

        // Create a starter prize item that can be won from the first tournament.
        Item prizeItem = new Item(
                "Defensive Headband",
                "Defense Gear",
                "Improves focus and gives a small defense boost.",
                0,
                0,
                0,
                5,
                0,
                50
        );

        // Create the first opponent for the player to face.
        Opponent opponent = new Opponent(
                "Quick Jay",
                "Fast driver",
                1,
                75
        );

        // Create the first tournament using the opponent and prize item.
        Tournament streetTournament = new Tournament(
                "Street Tournament",
                25,
                75,
                1,
                opponent,
                prizeItem
        );

        System.out.println("\nYour player has been created.");
        player.displayPlayerInfo();

        streetTournament.displayTournamentInfo();

        System.out.print("\nDo you want to enter this tournament? (yes/no): ");
        String choice = inputScanner.nextLine();

        if (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y")) {

            if (streetTournament.canEnter(player)) {

                // Player pays the entry fee before the game starts.
                player.spendMoney(streetTournament.getEntryFee());
                System.out.println("\nEntry fee paid. Good luck!");

                boolean playerWon = game.playOneOnOne(player, streetTournament.getOpponent());

                if (playerWon) {
                    System.out.println("\nYou won the tournament!");

                    player.earnMoney(streetTournament.getRewardMoney());

                    Item wonItem = streetTournament.getPrizeItem();
                    locker.addItem(wonItem);

                    System.out.println("You earned $" + streetTournament.getRewardMoney() + ".");
                    System.out.println("Prize item added to your locker.");

                    // Let the player decide if they want to use the prize item right away.
                    System.out.print("\nDo you want to equip " + wonItem.getName() + "? (yes/no): ");
                    String equipChoice = inputScanner.nextLine();

                    if (equipChoice.equalsIgnoreCase("yes") || equipChoice.equalsIgnoreCase("y")) {

                        player.equipItem(wonItem);

                        // Remove the item from the locker because it is now being used by the player.
                        locker.removeItem(locker.getItems().size() - 1);

                    } else {
                        System.out.println(wonItem.getName() + " will stay in your locker.");
                    }

                } else {
                    System.out.println("\nYou lost the tournament.");
                    System.out.println("You lost your entry fee, but you can try again later.");
                }

            } else {
                System.out.println("\nYou do not have enough money to enter this tournament.");
            }

        } else {
            System.out.println("\nYou skipped the tournament.");
        }

        // Show the player's updated money, stamina, boosts, and locker items.
        player.displayPlayerInfo();
        locker.displayLockerItems();

        System.out.println("\nThanks for playing!");

        inputScanner.close();
    }
}