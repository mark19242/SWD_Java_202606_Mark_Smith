package org.example;

import org.example.controller.MenuController;
import org.example.model.Printer;
import org.example.service.PrinterManager;
import org.example.view.ConsoleIO;

/**
 * App class for the 3D Printer Manager Application.
 */
public class App {

    public static void main(String[] args) {

        ConsoleIO io = ConsoleIO.getInstance();

        PrinterManager printerManager = new PrinterManager();

        // Starter printer so the app has one printer when it begins.
        printerManager.addPrinter("P1", new Printer("P1", "My Cool 3D Printer"));

        MenuController menuController = new MenuController(printerManager, io);

        io.displayMessage("Printer monitor online");

        menuController.run();

        io.displayMessage("Halting printer monitors");

        // Stop every monitor before the program exits.
        printerManager.haltMonitors();

        io.displayMessage("Goodbye!");
        System.exit(0);
    }
}