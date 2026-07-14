package learn.library.model;

/**
 * The categories available to the library administrator.
 * The display name is kept separate from the Java enum constant so the UI stays readable.
 */
public enum CategoryNames {
    ROMANCE("Romance"),
    MYSTERY_THRILLER("Mystery/Thriller"),
    SCI_FI_FANTASY("Science Fiction & Fantasy"),
    BIOGRAPHY_MEMOIR("Biography & Memoir"),
    SELF_HELP("Self Help");

    private final String displayName;

    CategoryNames(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }
}
