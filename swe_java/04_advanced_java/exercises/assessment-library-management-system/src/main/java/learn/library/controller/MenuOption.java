package learn.library.controller;

public enum MenuOption {
    EXIT(0, "Exit"),
    FIND_BY_CATEGORY(1, "Find Books by Category"),
    ADD_BOOK(2, "Add a Book"),
    UPDATE_BOOK(3, "Update a Book"),
    REMOVE_BOOK(4, "Remove a Book");

    private final int value;
    private final String label;

    MenuOption(int value, String label) {
        this.value = value;
        this.label = label;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    public static MenuOption fromValue(int value) {
        for (MenuOption option : values()) {
            if (option.value == value) {
                return option;
            }
        }
        return null;
    }
}
