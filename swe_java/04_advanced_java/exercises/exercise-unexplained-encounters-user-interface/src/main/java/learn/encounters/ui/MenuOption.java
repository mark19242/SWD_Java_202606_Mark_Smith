package learn.encounters.ui;

public enum MenuOption {

    EXIT(0, "Exit"),
    VIEW_BY_TYPE(1, "View Encounters By Type"),
    ADD(2, "Add an Encounter"),
    UPDATE(3, "Update an Encounter"),
    DELETE(4, "Delete an Encounter");

    private final int value;
    private final String message;

    MenuOption(int value, String message) {
        this.value = value;
        this.message = message;
    }

    public int getValue() {
        return value;
    }

    public String getMessage() {
        return message;
    }

    public static MenuOption fromValue(int value) {
        for (MenuOption option : values()) {
            if (option.getValue() == value) {
                return option;
            }
        }
        return EXIT;
    }
}
