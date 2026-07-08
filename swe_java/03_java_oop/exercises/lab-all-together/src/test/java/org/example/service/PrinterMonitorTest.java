package org.example.service;

import org.example.model.Printer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrinterMonitorTest {

    private PrinterMonitor monitor;

    @AfterEach
    void tearDown() {
        if (monitor != null) {
            monitor.halt();
        }
    }

    @Test
    void monitorShouldMovePrinterFromWarmingUpToPrinting() {
        Printer printer = new Printer("P1", "Printer One");
        monitor = new PrinterMonitor(printer);

        printer.print("test cube");

        monitor.run();
        monitor.run();

        assertEquals(Printer.PrinterStatus.PRINTING, printer.getStatus());
    }

    @Test
    void monitorShouldMovePrinterFromPrintingToComplete() {
        Printer printer = new Printer("P1", "Printer One");
        monitor = new PrinterMonitor(printer);

        printer.print("test cube");

        // Move from WARMING_UP to PRINTING.
        monitor.run();
        monitor.run();

        // Move from PRINTING to COMPLETE.
        monitor.run();
        monitor.run();
        monitor.run();

        assertEquals(Printer.PrinterStatus.COMPLETE, printer.getStatus());
    }

    @Test
    void monitorShouldLeaveReadyPrinterReady() {
        Printer printer = new Printer("P1", "Printer One");
        monitor = new PrinterMonitor(printer);

        monitor.run();

        assertEquals(Printer.PrinterStatus.READY, printer.getStatus());
    }
}