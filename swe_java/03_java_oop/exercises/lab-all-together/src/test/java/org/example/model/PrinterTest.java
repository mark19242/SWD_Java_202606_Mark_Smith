package org.example.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PrinterTest {

    @Test
    void printerShouldStartWithReadyStatus() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        assertEquals("P1", printer.getId());
        assertEquals("My Cool 3D Printer", printer.getName());
        assertEquals(Printer.PrinterStatus.READY, printer.getStatus());
    }

    @Test
    void printShouldSetModelNameAndChangeStatusToWarmingUp() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        printer.print("test cube");

        assertEquals("test cube", printer.getPrintModelName());
        assertEquals(Printer.PrinterStatus.WARMING_UP, printer.getStatus());
    }

    @Test
    void clearBedShouldResetPrinterToReadyAndRemoveModelName() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        printer.print("test cube");
        printer.clearBed();

        assertNull(printer.getPrintModelName());
        assertEquals(Printer.PrinterStatus.READY, printer.getStatus());
    }
}