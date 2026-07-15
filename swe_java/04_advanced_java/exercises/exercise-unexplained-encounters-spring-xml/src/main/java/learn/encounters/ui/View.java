package learn.encounters.ui;

import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

public class View {

    private final Scanner console = new Scanner(System.in);

    public MenuOption chooseMenuOption() {
        displayHeader("Main Menu");
        for (MenuOption option : MenuOption.values()) {
            System.out.printf("%s. %s%n", option.getValue(), option.getMessage());
        }
        int value = readInt("Select [0-4]: ", 0, 4);
        return MenuOption.fromValue(value);
    }

    public EncounterType chooseEncounterType() {
        displayHeader("Encounter Types");
        EncounterType[] types = EncounterType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%s. %s%n", i + 1, types[i]);
        }
        int index = readInt("Select a type: ", 1, types.length);
        return types[index - 1];
    }

    public Encounter chooseEncounter(List<Encounter> encounters) {
        displayEncounters(encounters);
        if (encounters == null || encounters.isEmpty()) {
            return null;
        }
        int id = readInt("Select an encounter id: ");
        for (Encounter encounter : encounters) {
            if (encounter.getEncounterId() == id) {
                return encounter;
            }
        }
        displayMessage("No encounter with id " + id + ".");
        return null;
    }

    public Encounter makeEncounter() {
        displayHeader("Add an Encounter");
        Encounter encounter = new Encounter();
        encounter.setType(chooseEncounterType());
        encounter.setWhen(readDate("When did it happen? [YYYY-MM-DD]: "));
        encounter.setDescription(readRequiredString("Description: "));
        encounter.setOccurrences(readInt("Occurrences: ", 1, Integer.MAX_VALUE));
        return encounter;
    }

    public Encounter editEncounter(Encounter encounter) {
        displayHeader("Update an Encounter");

        encounter.setType(chooseEncounterType());

        String when = readString(String.format("When (%s): ", encounter.getWhen()));
        if (!when.isBlank()) {
            encounter.setWhen(LocalDate.parse(when.trim()));
        }

        String description = readString(String.format("Description (%s): ", encounter.getDescription()));
        if (!description.isBlank()) {
            encounter.setDescription(description);
        }

        String occurrences = readString(String.format("Occurrences (%s): ", encounter.getOccurrences()));
        if (!occurrences.isBlank()) {
            encounter.setOccurrences(Integer.parseInt(occurrences.trim()));
        }

        return encounter;
    }

    public void displayEncounters(List<Encounter> encounters) {
        if (encounters == null || encounters.isEmpty()) {
            displayHeader("No Encounters Found");
            return;
        }
        displayHeader("Encounters");
        for (Encounter encounter : encounters) {
            System.out.printf("#%s [%s] %s (x%s)%n    %s%n",
                    encounter.getEncounterId(),
                    encounter.getType(),
                    encounter.getWhen(),
                    encounter.getOccurrences(),
                    encounter.getDescription());
        }
    }

    public void displayHeader(String message) {
        System.out.println();
        System.out.println(message);
        System.out.println("=".repeat(message.length()));
    }

    public void displayMessage(String message) {
        System.out.println();
        System.out.println(message);
    }

    public void displayErrors(List<String> errors) {
        displayHeader("Errors");
        for (String error : errors) {
            System.out.println("- " + error);
        }
    }

    private String readString(String prompt) {
        System.out.print(prompt);
        return console.nextLine();
    }

    private String readRequiredString(String prompt) {
        String result;
        do {
            result = readString(prompt);
            if (result.isBlank()) {
                System.out.println("Value is required.");
            }
        } while (result.isBlank());
        return result;
    }

    private int readInt(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return Integer.parseInt(value.trim());
            } catch (NumberFormatException ex) {
                System.out.printf("'%s' is not a valid number.%n", value);
            }
        }
    }

    private int readInt(String prompt, int min, int max) {
        while (true) {
            int value = readInt(prompt);
            if (value >= min && value <= max) {
                return value;
            }
            System.out.printf("Value must be between %s and %s.%n", min, max);
        }
    }

    private LocalDate readDate(String prompt) {
        while (true) {
            String value = readString(prompt);
            try {
                return LocalDate.parse(value.trim());
            } catch (DateTimeParseException ex) {
                System.out.printf("'%s' is not a valid date. Use YYYY-MM-DD.%n", value);
            }
        }
    }
}
