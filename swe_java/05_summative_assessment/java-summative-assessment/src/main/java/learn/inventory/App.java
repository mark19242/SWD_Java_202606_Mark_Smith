package learn.inventory;

import learn.inventory.service.InventoryService;
import learn.inventory.ui.MainMenu;

public class App {

    public static void main(String[] args) {

        InventoryService inventoryService = new InventoryService();
        MainMenu mainMenu = new MainMenu(inventoryService);

        mainMenu.run();
    }
}