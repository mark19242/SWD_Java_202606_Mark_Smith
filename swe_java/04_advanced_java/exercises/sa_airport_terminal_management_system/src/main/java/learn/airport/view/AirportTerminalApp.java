package learn.airport.view;

import learn.airport.data.CSVUtil;
import learn.airport.model.Aircraft;
import learn.airport.model.CommercialAircraft;
import learn.airport.model.Flight;
import learn.airport.model.Passenger;
import learn.airport.model.PrivateJet;
import learn.airport.reservation.ReservationSystem;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Scanner;

public class AirportTerminalApp {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        // Create aircraft for the available flights.
        Aircraft commercialAircraft = new CommercialAircraft(
                "Boeing 737",
                180,
                26000.0,
                "American Airlines"
        );

        Aircraft privateJet = new PrivateJet(
                "Gulfstream G650",
                18,
                18000.0,
                true,
                956
        );

// Create flights associated with the aircraft.
        Flight commercialFlight = new Flight(
                "AA101",
                LocalDate.of(2026, 7, 20),
                new BigDecimal("299.99"),
                commercialAircraft
        );

        Flight privateFlight = new Flight(
                "PJ001",
                LocalDate.of(2026, 7, 21),
                new BigDecimal("5000.00"),
                privateJet
        );

        ReservationSystem reservationSystem = new ReservationSystem();

        reservationSystem.addFlight(commercialFlight);
        reservationSystem.addFlight(privateFlight);

        while (running) {

            displayMenu();

            System.out.print("Select an option: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {

                case "1":
                    displayAvailableFlights(reservationSystem);
                    break;

                case "2":
                    addPassengerReservation(scanner, reservationSystem);
                    break;

                case "3":
                    System.out.println("\nViewing passengers for a flight...");
                    break;

                case "4":
                    System.out.println("\nSaving reservations...");
                    break;

                case "5":
                    System.out.println("\nLoading reservations...");
                    break;

                case "6":
                    System.out.println("\nThank you for using the Airport Terminal Management System.");
                    running = false;
                    break;

                default:
                    System.out.println("\nInvalid selection. Please choose options 1 through 6.");
            }
        }



    }

    private static void displayMenu() {

        System.out.println();
        System.out.println("============================================");
        System.out.println("   AIRPORT TERMINAL MANAGEMENT SYSTEM");
        System.out.println("============================================");
        System.out.println("1. View available flights");
        System.out.println("2. Add passenger reservation");
        System.out.println("3. View passengers for a flight");
        System.out.println("4. Save reservations");
        System.out.println("5. Load reservations");
        System.out.println("6. Exit");
        System.out.println("============================================");
    }

    private static void displayAvailableFlights(
            ReservationSystem reservationSystem) {

        System.out.println();
        System.out.println("AVAILABLE FLIGHTS");
        System.out.println("=================");

        if (reservationSystem.getFlights().isEmpty()) {
            System.out.println("No flights are currently available.");
            return;
        }

        for (Flight flight : reservationSystem.getFlights().values()) {

            System.out.println();
            System.out.println("Flight Number: " + flight.getFlightNumber());
            System.out.println("Departure Date: " + flight.getDepartureDate());
            System.out.println("Ticket Price: $" + flight.getTicketPrice());
            System.out.println("Aircraft Model: "
                    + flight.getAircraft().getModel());
            System.out.println("Aircraft Type: "
                    + flight.getAircraft().getClass().getSimpleName());
            System.out.println("--------------------------------------------");
        }
    }

    private static void addPassengerReservation(
            Scanner scanner,
            ReservationSystem reservationSystem) {

        System.out.println();
        System.out.println("ADD PASSENGER RESERVATION");
        System.out.println("=========================");

        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine().trim().toUpperCase();

        Flight flight = reservationSystem.getFlight(flightNumber);

        // A reservation cannot be added if the flight does not exist.
        if (flight == null) {
            System.out.println("Flight " + flightNumber + " was not found.");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine().trim();

        System.out.print("Enter passport number: ");
        String passportNumber = scanner.nextLine().trim();

        if (passengerName.isBlank() || passportNumber.isBlank()) {
            System.out.println("Passenger name and passport number are required.");
            return;
        }

        Passenger passenger = new Passenger(
                passengerName,
                passportNumber
        );

        reservationSystem.addReservation(
                flightNumber,
                passenger
        );

        System.out.println();
        System.out.println("Reservation added successfully.");
        System.out.println(passengerName + " is booked on flight "
                + flightNumber + ".");
    }
}