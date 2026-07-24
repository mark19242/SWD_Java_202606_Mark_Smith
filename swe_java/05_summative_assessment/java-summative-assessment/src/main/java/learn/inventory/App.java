package learn.inventory;

import learn.inventory.service.InventoryService;
import learn.inventory.ui.MainMenu;

/**
 * Starts the Inventory Manager console application.
 *
 * <p>This class creates the main application dependencies and begins
 * the user interaction through the {@link MainMenu}.</p>
 */

public class App {

    /**
     * Creates the inventory service and main menu, then starts the application.
     *
     * @param args command-line arguments supplied when the application starts;
     *             they are not currently used
     */

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();
        MainMenu mainMenu = new MainMenu(inventoryService);

        mainMenu.run();
    }
}