package learn.airport;

import learn.airport.model.Passenger;
import learn.airport.reservation.ReservationSystem;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirportTerminalTest {

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
}