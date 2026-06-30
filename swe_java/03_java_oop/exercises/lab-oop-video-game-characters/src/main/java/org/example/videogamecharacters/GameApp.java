package org.example.videogamecharacters;

import java.util.ArrayList;
import java.util.List;

/**
 * Runs the video game character application.
 *
 * This class creates different types of characters and stores them
 * in a list using the Character superclass type.
 */
public class GameApp {

    /**
     * Starts the program and demonstrates polymorphism by calling
     * each character's attack method.
     *
     * @param args command-line arguments not used in this program
     */
    public static void main(String[] args) {

        List<Character> characters = new ArrayList<>();

        characters.add(new Warrior("Thorn", 100, 25, "battle axe"));
        characters.add(new Mage("Elara", 80, 30, "fireball"));
        characters.add(new Archer("Raven", 90, 20, "poison"));

        System.out.println("Video Game Character Attacks");
        System.out.println("============================");

        for (Character character : characters) {
            character.attack();
        }
    }
}