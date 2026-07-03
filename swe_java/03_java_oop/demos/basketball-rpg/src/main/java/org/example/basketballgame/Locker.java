package org.example.basketballgame;

import java.util.ArrayList;

/**
 * The Locker class stores the player's extra merchandise and power-up items.
 */
public class Locker {

    private ArrayList<Item> items;

    /**
     * Creates an empty locker for the player.
     */
    public Locker() {
        this.items = new ArrayList<>();
    }

    /**
     * Adds an item to the locker.
     *
     * @param item the item being stored
     */
    public void addItem(Item item) {
        items.add(item);
        System.out.println(item.getName() + " was added to your locker.");
    }

    /**
     * Removes an item from the locker by its position in the list.
     *
     * @param index the position of the item being removed
     * @return the item that was removed, or null if the position is invalid
     */
    public Item removeItem(int index) {
        if (index >= 0 && index < items.size()) {
            return items.remove(index);
        }

        System.out.println("Invalid item selection.");
        return null;
    }

    /**
     * Displays all items currently stored in the locker.
     */
    public void displayLockerItems() {
        System.out.println("\n--- Locker Items ---");

        if (items.isEmpty()) {
            System.out.println("Your locker is empty.");
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            System.out.println((i + 1) + ". " + items.get(i).getName()
                    + " - " + items.get(i).getDescription());
        }
    }

    public ArrayList<Item> getItems() {
        return items;
    }
}