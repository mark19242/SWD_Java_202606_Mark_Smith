package org.example.service;

import org.example.model.Printer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PrinterManagerTest {

    private PrinterManager printerManager;

    @BeforeEach
    void setUp() {
        printerManager = new PrinterManager();
    }

    @AfterEach
    void tearDown() {
        // Each added printer creates a monitor, so I stop them after each test.
        printerManager.haltMonitors();
    }

    @Test
    void addPrinterShouldStorePrinterById() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        printerManager.addPrinter("P1", printer);

        Printer result = printerManager.getPrinter("P1");

        assertNotNull(result);
        assertEquals("P1", result.getId());
        assertEquals("My Cool 3D Printer", result.getName());
    }

    @Test
    void getAllPrinterIDsShouldReturnAddedPrinterIDs() {
        Printer printerOne = new Printer("P1", "Printer One");
        Printer printerTwo = new Printer("P2", "Printer Two");

        printerManager.addPrinter("P1", printerOne);
        printerManager.addPrinter("P2", printerTwo);

        List<String> printerIDs = printerManager.getAllPrinterIDs();

        assertEquals(2, printerIDs.size());
        assertTrue(printerIDs.contains("P1"));
        assertTrue(printerIDs.contains("P2"));
    }
}