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

    @Test
    void setStatusShouldUpdatePrinterStatus() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        printer.setStatus(Printer.PrinterStatus.COMPLETE);

        assertEquals(Printer.PrinterStatus.COMPLETE, printer.getStatus());
    }

    @Test
    void toStringShouldIncludeIdNameAndStatus() {
        Printer printer = new Printer("P1", "My Cool 3D Printer");

        String result = printer.toString();

        assertEquals("P1 - My Cool 3D Printer: READY", result);
    }
}