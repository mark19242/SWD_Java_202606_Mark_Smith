package exercises;

public class WeatherForecast {
    static void main() {

        double temperatureCelsius = 25.0;
        boolean isRaining = false;
        int windSpeedKmh = 10;

        double temperatureFahrenheit;

        temperatureFahrenheit =
                (temperatureCelsius * 9 / 5) + 32;

        temperatureCelsius += 5;
        windSpeedKmh += 5;

        temperatureFahrenheit =
                (temperatureCelsius * 9 / 5) + 32;

        boolean tempAbove85 =
                temperatureFahrenheit > 85;

        boolean windAbove20 =
                windSpeedKmh > 20;

        boolean goodDayOutside =
                !isRaining && temperatureFahrenheit >= 60 && temperatureFahrenheit <= 85;

        boolean weatherWarning =
                windSpeedKmh > 30 || temperatureCelsius < 0;

        System.out.println("Temperature (C): " + temperatureCelsius);
        System.out.println("Temperature (F): " + temperatureFahrenheit);
        System.out.println("Wind Speed: " + windSpeedKmh + " km/h");

        System.out.println("Above 85°F: " + tempAbove85);
        System.out.println("Wind Above 20 km/h: " + windAbove20);

        System.out.println("Good Day Outside: " + goodDayOutside);
        System.out.println("Weather Warning: " + weatherWarning);
    }
}
