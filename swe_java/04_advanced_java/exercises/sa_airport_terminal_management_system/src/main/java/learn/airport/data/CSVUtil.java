package learn.airport.data;

import learn.airport.model.*;
import learn.airport.reservation.ReservationSystem;
import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public class CSVUtil {

    public static void saveReservationsToCSV(String filename,
                                             ReservationSystem reservationSystem) {

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {

            for (Map.Entry<String, List<Passenger>> entry :
                    reservationSystem.getReservations().entrySet()) {

                String flightNumber = entry.getKey();

                Flight flight = reservationSystem.getFlight(flightNumber);

                List<Passenger> passengers = entry.getValue();

                for (Passenger passenger : passengers) {

                    writer.printf("%s,%s,%s,%s,%s,%s,%s%n",
                            flight.getFlightNumber(),
                            flight.getDepartureDate(),
                            flight.getTicketPrice(),
                            passenger.getName(),
                            passenger.getPassportNumber(),
                            flight.getAircraft().getModel(),
                            flight.getAircraft().getClass().getSimpleName());
                }
            }

        } catch (IOException e) {
            System.out.println("Error writing CSV file.");
        }
    }

    public static ReservationSystem loadReservationsFromCSV(String filename) {

        ReservationSystem reservationSystem = new ReservationSystem();

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = reader.readLine()) != null) {

                // Ignore empty lines in the CSV file.
                if (line.isBlank()) {
                    continue;
                }

                String[] parts = line.split(",");

                // Each reservation record must contain seven values.
                if (parts.length != 7) {
                    continue;
                }

                String flightNumber = parts[0];
                LocalDate departureDate = LocalDate.parse(parts[1]);
                BigDecimal ticketPrice = new BigDecimal(parts[2]);

                String passengerName = parts[3];
                String passportNumber = parts[4];

                String aircraftModel = parts[5];
                String aircraftType = parts[6];

                Aircraft aircraft;

                if (aircraftType.equals("CommercialAircraft")
                        || aircraftType.equalsIgnoreCase("Commercial")) {

                    /*
                     * The CSV does not store capacity, fuel capacity, or airline name,
                     * so default values are used while reconstructing the aircraft.
                     */
                    aircraft = new CommercialAircraft(
                            aircraftModel,
                            0,
                            0.0,
                            "Unknown Airline"
                    );

                } else {

                    /*
                     * The CSV also does not store the private jet's luxury service
                     * or maximum speed, so default values are used.
                     */
                    aircraft = new PrivateJet(
                            aircraftModel,
                            0,
                            0.0,
                            false,
                            0
                    );
                }

                Flight flight = new Flight(
                        flightNumber,
                        departureDate,
                        ticketPrice,
                        aircraft
                );

                Passenger passenger = new Passenger(
                        passengerName,
                        passportNumber
                );

                // Store the flight and reconnect the passenger reservation.
                reservationSystem.addFlight(flight);
                reservationSystem.addReservation(flightNumber, passenger);
            }

        } catch (IOException e) {
            System.out.println("Error reading CSV file.");
        }

        return reservationSystem;
    }
}