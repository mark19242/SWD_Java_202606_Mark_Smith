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

public class AirportTerminalApp {

    public static void main(String[] args) {

        String filename = "data/reservations.csv";

        // Create two different aircraft types.
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

        // Associate each aircraft with a flight.
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

        Passenger alice = new Passenger(
                "Alice Smith",
                "P12345"
        );

        Passenger john = new Passenger(
                "John Doe",
                "P67890"
        );

        ReservationSystem reservationSystem = new ReservationSystem();

        reservationSystem.addFlight(commercialFlight);
        reservationSystem.addFlight(privateFlight);

        reservationSystem.addReservation(
                commercialFlight.getFlightNumber(),
                alice
        );

        reservationSystem.addReservation(
                privateFlight.getFlightNumber(),
                john
        );

        // Save all current reservations.
        CSVUtil.saveReservationsToCSV(
                filename,
                reservationSystem
        );

        System.out.println("Reservations saved successfully.");

        // Load the reservations into a new system.
        ReservationSystem loadedReservationSystem =
                CSVUtil.loadReservationsFromCSV(filename);

        System.out.println("\nPassengers on AA101:");
        System.out.println(
                loadedReservationSystem.getPassengersForFlight("AA101")
        );

        System.out.println("\nPassengers on PJ001:");
        System.out.println(
                loadedReservationSystem.getPassengersForFlight("PJ001")
        );
    }
}