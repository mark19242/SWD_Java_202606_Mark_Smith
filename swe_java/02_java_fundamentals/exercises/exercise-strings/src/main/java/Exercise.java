public class Exercise {
    public static void main(String[] args) {
        // Part 1: Basic String Operations

        String firstName = "Harry";
        String lastName = "Potter";
        String fullName = firstName + " " + lastName;

        System.out.println("Full name: " + fullName);
        System.out.println("Length: " + fullName.length());
        System.out.println("First character: " + fullName.charAt(0));
        System.out.println("Index of 'r': " + fullName.indexOf('r'));

        System.out.println();

// Part 2: Extracting Parts of a String

        String sentence = "Learning Java is fun!";

        System.out.println("First word: " + sentence.substring(0, 8));
        System.out.println("Second word: " + sentence.substring(9, 13));
        System.out.println("Last word: " + sentence.substring(17));

        System.out.println();

// Part 3: Splitting Strings

        String csvData = "apple,banana,grape,orange";

        String[] fruits = csvData.split(",");

        for (int i = 0; i < fruits.length; i++) {
            System.out.println("Fruit " + (i + 1) + ": " + fruits[i]);
        }

        System.out.println();

// Part 4: Replacing Characters

        String sentence2 = "The quick brown fox.";

        String modifiedSentence =
                sentence2.replace("quick", "slow")
                        .replace(" ", "_");

        System.out.println("Modified sentence: " + modifiedSentence);

        System.out.println();

// Part 5: Handling Null and Empty Strings

        String nullString = null;

        if (nullString != null) {
            System.out.println(nullString.length());
        } else {
            System.out.println("The string is null, cannot compute length.");
        }

        String emptyString = "";

        System.out.println("Empty string length: " + emptyString.length());

    }
}
