package learn.airport.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Flight {

    private String flightNumber;
    private LocalDate departureDate;
    private BigDecimal ticketPrice;
    private Aircraft aircraft;

    public Flight(String flightNumber,
                  LocalDate departureDate,
                  BigDecimal ticketPrice,
                  Aircraft aircraft) {

        this.flightNumber = flightNumber;
        this.departureDate = departureDate;
        this.ticketPrice = ticketPrice;
        this.aircraft = aircraft;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public Aircraft getAircraft() {
        return aircraft;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", departureDate=" + departureDate +
                ", ticketPrice=" + ticketPrice +
                ", aircraft=" + aircraft +
                '}';
    }
}