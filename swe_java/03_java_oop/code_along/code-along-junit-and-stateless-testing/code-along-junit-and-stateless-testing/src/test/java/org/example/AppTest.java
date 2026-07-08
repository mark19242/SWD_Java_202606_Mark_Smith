package org.example;

import org.junit.jupiter.api.*;

@TestMethodOrder(MethodOrderer.MethodName.class)
public class AppTest {

    private Thermostat testThermostat;

    @BeforeEach
    void setup() {
        testThermostat = new Thermostat();

        System.out.println("Configuring Test Thermostat: " + testThermostat.toString());

        testThermostat.setTargetTemperature(74);
        testThermostat.setTolerance(2);
    }

    @AfterEach
    void tearDown() {
        testThermostat = null;
        System.out.println("Tearing down Test Rig");
    }

    @Test
    public void setThermostatTemperature76GetReturns76() {

        // Arrange
        int setTemp = 76;

        // Act
        testThermostat.setTargetTemperature(setTemp);

        // Assert
        Assertions.assertEquals(setTemp, testThermostat.getTargetTemperature(),
                "The Target getter did not return the set value");
    }

    @Test
    public void getUnsetToleranceShouldBeDefault() {

        // Arrange
        Thermostat sut = new Thermostat();
        int expected = Thermostat.DEFAULT_TOLERANCE;

        // Act + Assert
        Assertions.assertEquals(expected, sut.getTolerance(),
                "A newly instantiated Thermostat failed to report the correct default tolerance");
    }

    @Test
    public void setTolerance3GetReturns3() {

        // Arrange
        int setTolerance = 3;

        // Act
        testThermostat.setTolerance(setTolerance);

        // Assert
        Assertions.assertEquals(setTolerance, testThermostat.getTolerance(),
                "The Tolerance getter did not return the set value");
    }

    // SENSOR DATA TESTS

    // 1. OFF
    @Test
    public void setTestThermostatShouldBeOffWhenTempInTolerance() {

        // Arrange
        int[] temps = {73, 74, 75};
        Thermostat.ThermostatBehavior expectedBehavior =
                Thermostat.ThermostatBehavior.OFF;

        // Act
        Thermostat.ThermostatBehavior actualBehavior =
                testThermostat.readSensorData(temps);

        // Assert
        Assertions.assertEquals(expectedBehavior, actualBehavior);
    }

    // 2. ON_AC
    @Test
    public void setTestThermostatShouldBeACWhenTempAboveTolerance() {

        // Arrange
        int[] temps = {100, 80, 60};
        Thermostat.ThermostatBehavior expectedBehavior =
                Thermostat.ThermostatBehavior.ON_AC;

        // Act
        Thermostat.ThermostatBehavior actualBehavior =
                testThermostat.readSensorData(temps);

        // Assert
        Assertions.assertEquals(expectedBehavior, actualBehavior);
    }

    // 3. ON_HEAT
    @Test
    public void setTestThermostatShouldBeHeatWhenTempBelowTolerance() {

        // Arrange
        int[] temps = {70, 70, 70};
        Thermostat.ThermostatBehavior expectedBehavior =
                Thermostat.ThermostatBehavior.ON_HEAT;

        // Act
        Thermostat.ThermostatBehavior actualBehavior =
                testThermostat.readSensorData(temps);

        // Assert
        Assertions.assertEquals(expectedBehavior, actualBehavior);
    }

    // 3.5 EDGE CASE FOR OFF - LOW SIDE
    @Test
    public void setTestThermostatShouldBeOffWhenTempInLowTolerance() {

        // Arrange
        int[] temps = {72};
        Thermostat.ThermostatBehavior expectedBehavior =
                Thermostat.ThermostatBehavior.OFF;

        // Act
        Thermostat.ThermostatBehavior actualBehavior =
                testThermostat.readSensorData(temps);

        // Assert
        Assertions.assertEquals(expectedBehavior, actualBehavior);
    }

    // 3.5 EDGE CASE FOR OFF - HIGH SIDE
    @Test
    public void setTestThermostatShouldBeOffWhenTempInHighTolerance() {

        // Arrange
        int[] temps = {76};
        Thermostat.ThermostatBehavior expectedBehavior =
                Thermostat.ThermostatBehavior.OFF;

        // Act
        Thermostat.ThermostatBehavior actualBehavior =
                testThermostat.readSensorData(temps);

        // Assert
        Assertions.assertEquals(expectedBehavior, actualBehavior);
    }
}