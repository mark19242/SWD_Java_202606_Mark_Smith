package org.example.controller;

import org.example.model.Printer;
import org.example.service.PrinterManager;
import org.example.view.ConsoleIO;

import java.util.List;

/**
 * Handles the menu logic for the 3D Printer Manager application.
 */
public class MenuController {

    private PrinterManager printerManager;
    private ConsoleIO io;
    private boolean running = true;

    public MenuController(PrinterManager printerManager, ConsoleIO io) {
        this.printerManager = printerManager;
        this.io = io;
    }

    /**
     * Runs the main menu loop.
     */
    public void run() {
        while (running) {
            showMenu();

            String choice = io.getStringRequired("").trim().toUpperCase();

            switch (choice) {
                case "D":
                    display();
                    break;

                case "A":
                    add();
                    break;

                case "P":
                    print();
                    break;

                case "C":
                    clear();
                    break;

                case "X":
                    running = false;
                    break;

                default:
                    io.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }
    }

    private void showMenu() {
        io.displayMessage("");
        io.displayMessage("[D]isplay printer status");
        io.displayMessage("[A]dd a printer");
        io.displayMessage("[P]rint an object");
        io.displayMessage("[C]lear the print bed");
        io.displayMessage("e[X]it");
    }

    /**
     * Shows the status of all printers.
     */
    public void display() {
        List<String> printerIDs = printerManager.getAllPrinterIDs();

        if (printerIDs.isEmpty()) {
            io.displayMessage("No printers have been added yet.");
            return;
        }

        for (String id : printerIDs) {
            Printer printer = printerManager.getPrinter(id);
            io.displayMessage(id + " - " + printer.toString());
        }
    }

    /**
     * Adds a new printer to the application.
     */
    public void add() {
        String id = io.getStringRequired("New printer ID").trim().toUpperCase();

        if (printerManager.getPrinter(id) != null) {
            io.displayMessage("A printer with that ID already exists.");
            return;
        }

        String name = io.getStringRequired("Printer name");

        Printer printer = new Printer(id, name);
        printerManager.addPrinter(id, printer);

        io.displayMessage("Printer added: " + id + " - " + name);
    }

    /**
     * Starts a print job on a selected printer.
     */
    public void print() {
        Printer printer = selectPrinter();

        if (printer == null) {
            return;
        }

        if (printer.getStatus().equals(Printer.PrinterStatus.READY)) {
            String file = io.getStringRequired("Object to print");
            printer.print(file);
        } else {
            io.displayMessage("Printer not ready to accept a new print.");
        }
    }

    /**
     * Clears the print bed for a selected printer.
     */
    public void clear() {
        Printer printer = selectPrinter();

        if (printer == null) {
            return;
        }

        if (printer.getStatus().equals(Printer.PrinterStatus.COMPLETE)) {
            io.displayMessage("Retrieving " + printer.getPrintModelName());
            printer.clearBed();
            io.displayMessage(printer.toString());
        } else {
            io.displayMessage("Print incomplete or not started.");
        }
    }

    /**
     * Lets the user pick a printer by ID.
     */
    private Printer selectPrinter() {
        display();

        String id = io.getStringRequired("Printer ID").trim().toUpperCase();
        Printer printer = printerManager.getPrinter(id);

        if (printer == null) {
            io.displayMessage("No printer found with ID: " + id);
        }

        return printer;
    }
}