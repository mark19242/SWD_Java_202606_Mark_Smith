package org.example.basketballgame;

/**
 * ConsoleEffects handles small visual effects for the console game.
 * It keeps pause and slow text logic separate from the main game logic.
 */
public class ConsoleEffects {

    /**
     * Pauses the game for a short amount of time.
     *
     * @param milliseconds how long the game should pause
     */
    public static void pause(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * Prints a message one character at a time.
     *
     * @param message the message being printed slowly
     */
    public static void slowPrint(String message) {
        for (int i = 0; i < message.length(); i++) {
            System.out.print(message.charAt(i));
            pause(25);
        }

        System.out.println();
    }
}