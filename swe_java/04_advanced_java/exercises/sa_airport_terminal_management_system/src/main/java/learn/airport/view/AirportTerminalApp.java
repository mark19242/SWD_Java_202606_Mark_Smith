package learn.airport.view;

import learn.airport.model.Passenger;
import learn.airport.reservation.ReservationSystem;

public class AirportTerminalApp {

    public static void main(String[] args) {

        ReservationSystem reservationSystem = new ReservationSystem();

        Passenger passenger = new Passenger("Alice Smith", "P12345");

        reservationSystem.addReservation("AA101", passenger);

        System.out.println(
                reservationSystem.getPassengersForFlight("AA101")
        );
    }
}