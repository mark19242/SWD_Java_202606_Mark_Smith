# Airport Terminal Management System

This is a Java program that helps manage flights and passenger reservations at an airport terminal.

The program uses a console menu so the user can choose what they would like to do.

## What the Program Can Do

- Show available flights
- Add a passenger to a flight
- Show the passengers booked on a flight
- Work with commercial airplanes and private jets
- Save reservations to a CSV file
- Load saved reservations from a CSV file
- Exit the program

## Menu Options

1. View available flights
2. Add passenger reservation
3. View passengers for a flight
4. Save reservations
5. Load reservations
6. Exit

## How the Project Is Organized

- `model` contains the passenger, flight, and aircraft classes.
- `reservation` handles passenger reservations.
- `data` handles saving and loading the CSV file.
- `view` contains the main program and console menu.
- `src/test` contains the JUnit tests.
- `data/reservations.csv` stores saved reservations.

## How to Run the Program

1. Open the project in IntelliJ IDEA.
2. Allow Maven to load the project.
3. Open `AirportTerminalApp.java`.
4. Run the `main()` method.
5. Choose an option from the menu.

## How to Run the Tests

Open `AirportTerminalTest.java` and run the test class.

## CSV File

Reservations are saved in the following order:

```text
flight number, departure date, ticket price, passenger name,
passport number, aircraft model, aircraft type
```


## Tools Used

- Java
- Maven
- JUnit 5
- Git
- GitHub