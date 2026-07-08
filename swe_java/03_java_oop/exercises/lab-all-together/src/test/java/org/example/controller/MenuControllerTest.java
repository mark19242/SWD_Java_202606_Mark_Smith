package org.example.controller;

import org.example.model.Printer;
import org.example.service.PrinterManager;
import org.example.view.ConsoleIO;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Field;

import static org.junit.jupiter.api.Assertions.*;

class MenuControllerTest {

    private PrinterManager printerManager;

    @AfterEach
    void tearDown() {
        if (printerManager != null) {
            printerManager.haltMonitors();
        }
    }

    @Test
    void addShouldCreateNewPrinterFromUserInput() throws Exception {
        printerManager = new PrinterManager();

        // Simulates the user typing:
        // P2
        // Second Printer
        String input = "P2\nSecond Printer\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        resetConsoleIO();

        ConsoleIO io = ConsoleIO.getInstance();
        MenuController controller = new MenuController(printerManager, io);

        controller.add();

        Printer result = printerManager.getPrinter("P2");

        assertNotNull(result);
        assertEquals("P2", result.getId());
        assertEquals("Second Printer", result.getName());
        assertEquals(Printer.PrinterStatus.READY, result.getStatus());
    }

    private void resetConsoleIO() throws Exception {
        Field instanceField = ConsoleIO.class.getDeclaredField("instance");
        instanceField.setAccessible(true);
        instanceField.set(null, null);
    }
}