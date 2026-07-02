import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class App {

    public static void main(String[] args) {

        // Part 1:
        // Create a HashMap where the key is the month number
        // and the value is the month name.
        Map<Integer, String> months = new HashMap<>();

        months.put(1, "January");
        months.put(2, "February");
        months.put(3, "March");
        months.put(4, "April");
        months.put(5, "May");
        months.put(6, "June");
        months.put(7, "July");
        months.put(8, "August");
        months.put(9, "September");
        months.put(10, "October");
        months.put(11, "November");
        months.put(12, "December");

        System.out.println("Months:");
        System.out.println("=======");

        // keySet() gives us all the keys in the map.
        // We use each key to get the value connected to it.
        for (Integer monthNumber : months.keySet()) {
            System.out.println(monthNumber + " - " + months.get(monthNumber));
        }

        System.out.println();

        // Part 2:
        // Create a HashMap where the key is the suit
        // and the value is a list of card names.
        Map<String, List<String>> deck = new HashMap<>();

        // This list holds the card values that each suit will use.
        List<String> cardValues = Arrays.asList(
                "Ace", "2", "3", "4", "5", "6", "7",
                "8", "9", "10", "Jack", "Queen", "King"
        );

        deck.put("Hearts", cardValues);
        deck.put("Diamonds", cardValues);
        deck.put("Clubs", cardValues);
        deck.put("Spades", cardValues);

        System.out.println("Deck of Cards:");
        System.out.println("==============");

        // Loop through each suit in the map.
        for (String suit : deck.keySet()) {

            // Get the list of card values for the current suit.
            List<String> values = deck.get(suit);

            // Loop through the card values and print each card.
            for (String value : values) {
                System.out.println(value + " of " + suit);
            }
        }
    }
}