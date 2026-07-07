package org.example;

import org.example.model.Printer;
import org.example.service.PrinterManager;
import org.example.view.ConsoleIO;

/**
 * App class for the 3D Printer Manager Application.
 */
public class App {

    public static void main(String[] args) {

        ConsoleIO io = ConsoleIO.getInstance();

        // The manager will now handle printers and their monitors.
        PrinterManager printerManager = new PrinterManager();

        // Add the first starter printer to the manager.
        printerManager.addPrinter("P1", new Printer("My Cool 3D Printer"));

        // For now, we are still using one printer while we refactor in small steps.
        Printer myPrinter = printerManager.getPrinter("P1");

        boolean running = true;

        io.displayMessage("Printer monitor online");

        while (running) {
            io.displayMessage("[D]isplay printer status");
            io.displayMessage("[P]rint an object");
            io.displayMessage("[C]lear the print bed");
            io.displayMessage("e[X]it");

            String choice = io.getStringRequired("");

            switch (choice.toUpperCase()) {
                case "D":
                    io.displayMessage(myPrinter.toString());
                    break;

                case "P":
                    if (myPrinter.getStatus().equals(Printer.PrinterStatus.READY)) {
                        String file = io.getStringRequired("Object to print");
                        myPrinter.print(file);
                    } else {
                        io.displayMessage("Printer not ready to accept a new print.");
                    }
                    break;

                case "C":
                    if (myPrinter.getStatus().equals(Printer.PrinterStatus.COMPLETE)) {
                        io.displayMessage("Retrieving " + myPrinter.getPrintModelName());
                        myPrinter.clearBed();
                        io.displayMessage(myPrinter.toString());
                    } else {
                        io.displayMessage("Print incomplete or not started.");
                    }
                    break;

                case "X":
                    running = false;
                    break;

                default:
                    io.displayMessage("Invalid choice. Please try again.");
                    break;
            }
        }

        io.displayMessage("Halting printer monitors");

        // The manager stops all monitors before the program exits.
        printerManager.haltMonitors();

        io.displayMessage("Goodbye!");
        System.exit(0);
    }
}