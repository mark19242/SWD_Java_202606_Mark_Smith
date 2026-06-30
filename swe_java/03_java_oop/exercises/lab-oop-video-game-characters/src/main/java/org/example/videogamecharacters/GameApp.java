package org.example.videogamecharacters;

import java.util.ArrayList;
import java.util.List;

public class GameApp {

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