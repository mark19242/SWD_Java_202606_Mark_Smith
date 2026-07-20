package learn.airport.reservation;

import learn.airport.model.Flight;
import learn.airport.model.Passenger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ReservationSystem {

    private Map<String, List<Passenger>> reservations;
    private Map<String, Flight> flights;

    public ReservationSystem() {
        reservations = new HashMap<>();
        flights = new HashMap<>();
    }

    public void addFlight(Flight flight) {
        flights.put(flight.getFlightNumber(), flight);
    }

    public Flight getFlight(String flightNumber) {
        return flights.get(flightNumber);
    }

    public void addReservation(String flightNumber, Passenger passenger) {

        // Create a passenger list when this flight has no reservations yet.
        reservations.putIfAbsent(flightNumber, new ArrayList<>());

        reservations.get(flightNumber).add(passenger);
    }

    public List<Passenger> getPassengersForFlight(String flightNumber) {

        // Return an empty list when the flight number is not found.
        return reservations.getOrDefault(flightNumber, new ArrayList<>());
    }

    public Map<String, List<Passenger>> getReservations() {
        return reservations;
    }

    public Map<String, Flight> getFlights() {
        return flights;
    }
}