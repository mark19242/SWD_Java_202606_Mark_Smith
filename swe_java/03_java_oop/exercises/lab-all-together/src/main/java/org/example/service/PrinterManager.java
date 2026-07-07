package org.example.service;

import org.example.model.Printer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Manages all printers and their monitor objects.
 */
public class PrinterManager {

    // Map lets us find a printer by its unique ID.
    private Map<String, Printer> printers = new HashMap<>();

    // List keeps track of every monitor so we can stop them before exiting.
    private List<PrinterMonitor> monitors = new ArrayList<>();

    /**
     * Adds a printer and creates a monitor for it.
     */
    public void addPrinter(String key, Printer printer) {
        printers.put(key, printer);

        PrinterMonitor monitor = new PrinterMonitor(printer);
        monitors.add(monitor);
    }

    /**
     * Gets one printer by ID.
     */
    public Printer getPrinter(String key) {
        return printers.get(key);
    }

    /**
     * Gets all printer IDs.
     */
    public List<String> getAllPrinterIDs() {
        return new ArrayList<>(printers.keySet());
    }

    /**
     * Stops all printer monitors.
     */
    public void haltMonitors() {
        for (PrinterMonitor monitor : monitors) {
            monitor.cancel();
        }
    }
}