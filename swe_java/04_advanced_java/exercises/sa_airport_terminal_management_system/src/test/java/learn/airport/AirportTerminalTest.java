package learn.airport;

import learn.airport.model.Passenger;
import learn.airport.reservation.ReservationSystem;
import org.junit.jupiter.api.Test;
import java.util.List;
import learn.airport.model.CommercialAircraft;
import learn.airport.model.Flight;
import learn.airport.model.PrivateJet;
import java.math.BigDecimal;
import java.time.LocalDate;
import learn.airport.data.CSVUtil;
import org.junit.jupiter.api.io.TempDir;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class AirportTerminalTest {

    @TempDir
    Path tempDirectory;

    @Test
    void addReservationAddsPassengerToFlight() {

        // Arrange
        ReservationSystem reservationSystem = new ReservationSystem();
        Passenger passenger = new Passenger("Alice Smith", "P12345");

        // Act
        reservationSystem.addReservation("AA101", passenger);

        // Assert
        assertEquals(
                1,
                reservationSystem.getPassengersForFlight("AA101").size()
        );

        assertSame(
                passenger,
                reservationSystem.getPassengersForFlight("AA101").get(0)
        );
    }

    @Test
    void getPassengersForFlightReturnsCorrectPassengers() {

        // Arrange
        ReservationSystem reservationSystem = new ReservationSystem();

        Passenger alice = new Passenger("Alice Smith", "P12345");
        Passenger john = new Passenger("John Doe", "P67890");
        Passenger sarah = new Passenger("Sarah Jones", "P24680");

        reservationSystem.addReservation("AA101", alice);
        reservationSystem.addReservation("AA101", john);

        // This passenger belongs to a different flight.
        reservationSystem.addReservation("BB202", sarah);

        // Act
        List<Passenger> passengers =
                reservationSystem.getPassengersForFlight("AA101");

        // Assert
        assertEquals(2, passengers.size());
        assertSame(alice, passengers.get(0));
        assertSame(john, passengers.get(1));
    }

    @Test
    void aircraftSubclassesCanBeAssociatedWithFlights() {

        // Arrange
        CommercialAircraft commercialAircraft = new CommercialAircraft(
                "Boeing 737",
                180,
                26000.0,
                "American Airlines"
        );

        PrivateJet privateJet = new PrivateJet(
                "Gulfstream G650",
                18,
                18000.0,
                true,
                956
        );

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

        // Assert
        assertSame(commercialAircraft, commercialFlight.getAircraft());
        assertSame(privateJet, privateFlight.getAircraft());

        assertEquals("American Airlines", commercialAircraft.getAirlineName());
        assertTrue(privateJet.hasLuxuryService());
        assertEquals(956, privateJet.getMaxSpeed());

        assertTrue(commercialAircraft.toString().contains("American Airlines"));
        assertTrue(privateJet.toString().contains("maxSpeed=956"));
    }

    @Test
    void saveReservationsToCSVWritesReservationData() throws IOException {

        // Arrange
        Path testFile = tempDirectory.resolve("reservations.csv");

        CommercialAircraft aircraft = new CommercialAircraft(
                "Boeing 737",
                180,
                26000.0,
                "American Airlines"
        );

        Flight flight = new Flight(
                "AA101",
                LocalDate.of(2026, 7, 20),
                new BigDecimal("299.99"),
                aircraft
        );

        Passenger passenger = new Passenger(
                "Alice Smith",
                "P12345"
        );

        ReservationSystem reservationSystem = new ReservationSystem();

        reservationSystem.addFlight(flight);
        reservationSystem.addReservation(
                flight.getFlightNumber(),
                passenger
        );

        // Act
        CSVUtil.saveReservationsToCSV(
                testFile.toString(),
                reservationSystem
        );

        // Assert
        assertTrue(Files.exists(testFile));

        List<String> lines = Files.readAllLines(testFile);

        assertEquals(1, lines.size());

        assertEquals(
                "AA101,2026-07-20,299.99,Alice Smith,P12345,Boeing 737,CommercialAircraft",
                lines.get(0)
        );
    }

    @Test
    void loadReservationsFromCSVReconstructsReservationData() throws IOException {

        // Arrange
        Path testFile = tempDirectory.resolve("loaded-reservations.csv");

        String csvRecord =
                "AA101,2026-07-20,299.99,Alice Smith,P12345,Boeing 737,CommercialAircraft";

        Files.writeString(
                testFile,
                csvRecord + System.lineSeparator()
        );

        // Act
        ReservationSystem loadedReservationSystem =
                CSVUtil.loadReservationsFromCSV(testFile.toString());

        List<Passenger> loadedPassengers =
                loadedReservationSystem.getPassengersForFlight("AA101");

        Flight loadedFlight =
                loadedReservationSystem.getFlight("AA101");

        // Assert passenger information.
        assertEquals(1, loadedPassengers.size());
        assertEquals("Alice Smith", loadedPassengers.get(0).getName());
        assertEquals("P12345", loadedPassengers.get(0).getPassportNumber());

        // Assert flight information.
        assertNotNull(loadedFlight);
        assertEquals("AA101", loadedFlight.getFlightNumber());
        assertEquals(
                LocalDate.of(2026, 7, 20),
                loadedFlight.getDepartureDate()
        );
        assertEquals(
                new BigDecimal("299.99"),
                loadedFlight.getTicketPrice()
        );

        // Assert that the correct aircraft subclass was reconstructed.
        assertInstanceOf(
                CommercialAircraft.class,
                loadedFlight.getAircraft()
        );

        assertEquals(
                "Boeing 737",
                loadedFlight.getAircraft().getModel()
        );
    }
}