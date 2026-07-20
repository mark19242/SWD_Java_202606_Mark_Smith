package learn.airport.view;

import learn.airport.data.CSVUtil;
import learn.airport.model.Aircraft;
import learn.airport.model.CommercialAircraft;
import learn.airport.model.Flight;
import learn.airport.model.Passenger;
import learn.airport.reservation.ReservationSystem;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AirportTerminalApp {

    public static void main(String[] args) {

        String filename = "data/reservations.csv";

        // Create a commercial aircraft.
        Aircraft aircraft = new CommercialAircraft(
                "Boeing 737",
                180,
                26000.0,
                "American Airlines"
        );

        // Create a flight associated with the aircraft.
        Flight flight = new Flight(
                "AA101",
                LocalDate.of(2026, 7, 20),
                new BigDecimal("299.99"),
                aircraft
        );

        // Create a passenger.
        Passenger passenger = new Passenger(
                "Alice Smith",
                "P12345"
        );

        ReservationSystem reservationSystem = new ReservationSystem();

        // The flight must be added before its reservations are saved.
        reservationSystem.addFlight(flight);
        reservationSystem.addReservation(
                flight.getFlightNumber(),
                passenger
        );

        // Save the current reservation data.
        CSVUtil.saveReservationsToCSV(
                filename,
                reservationSystem
        );

        System.out.println("Reservations saved to CSV.");

        // Reconstruct a new reservation system from the CSV file.
        ReservationSystem loadedReservationSystem =
                CSVUtil.loadReservationsFromCSV(filename);

        System.out.println("Loaded passengers for "
                + flight.getFlightNumber() + ":");

        System.out.println(
                loadedReservationSystem.getPassengersForFlight(
                        flight.getFlightNumber()
                )
        );
    }
}