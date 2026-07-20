package learn.airport.reservation;

import learn.airport.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationSystem {

    private Map<String, List<Passenger>> reservations;

    public ReservationSystem() {
        reservations = new HashMap<>();
    }

    public void addReservation(String flightNumber, Passenger passenger) {

        // Create a new passenger list only when the flight number is not already stored.
        reservations.putIfAbsent(flightNumber, new ArrayList<>());

        // Add the passenger to the correct flight.
        reservations.get(flightNumber).add(passenger);
    }

    public List<Passenger> getPassengersForFlight(String flightNumber) {

        // Return an empty list when the flight number has no reservations.
        return reservations.getOrDefault(flightNumber, new ArrayList<>());
    }

    public Map<String, List<Passenger>> getReservations() {
        return reservations;
    }
}