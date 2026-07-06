package org.converters;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TemperatureConverterTest {

    private TemperatureConverter testConverter;

    @BeforeEach
    public void setup() {
        testConverter = new TemperatureConverter();
    }

    @AfterEach
    public void teardown() {
        testConverter = null;
    }

    @Test
    public void celsiusToFahrenheit0ShouldReturn32() {

        // Arrange
        double celsius = 0.0;
        double expected = 32.0;

        // Act
        double actual = testConverter.celsiusToFahrenheit(celsius);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void fahrenheitToCelsius32ShouldReturn0() {

        // Arrange
        double fahrenheit = 32.0;
        double expected = 0.0;

        // Act
        double actual = testConverter.fahrenheitToCelsius(fahrenheit);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void celsiusToKelvin100ShouldReturn373Point15() {

        // Arrange
        double celsius = 100.0;
        double expected = 373.15;

        // Act
        double actual = testConverter.celsiusToKelvin(celsius);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void kelvinToCelsius0ShouldReturnNegative273Point15() {

        // Arrange
        double kelvin = 0.0;
        double expected = -273.15;

        // Act
        double actual = testConverter.kelvinToCelsius(kelvin);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void fahrenheitToKelvin212ShouldReturn373Point15() {

        // Arrange
        double fahrenheit = 212.0;
        double expected = 373.15;

        // Act
        double actual = testConverter.fahrenheitToKelvin(fahrenheit);

        // Assert
        Assertions.assertEquals(expected, actual);
    }

    @Test
    public void kelvinToFahrenheit273Point15ShouldReturn32() {

        // Arrange
        double kelvin = 273.15;
        double expected = 32.0;

        // Act
        double actual = testConverter.kelvinToFahrenheit(kelvin);

        // Assert
        Assertions.assertEquals(expected, actual);
    }
}