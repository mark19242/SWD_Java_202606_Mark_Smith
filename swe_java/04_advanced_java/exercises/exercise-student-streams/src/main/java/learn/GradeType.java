package learn;

/**
 * A letter grade with its value on a 4.0 grade-point scale.
 *
 * <p>This type is complete and read-only. Read it to understand the data; you
 * do not need to change it.
 */
public enum GradeType {
    A("A", 4.0),
    A_MINUS("A-", 3.7),
    B_PLUS("B+", 3.3),
    B("B", 3.0),
    B_MINUS("B-", 2.7),
    C_PLUS("C+", 2.3),
    C("C", 2.0),
    D("D", 1.0),
    F("F", 0.0);

    private final String label;
    private final double gradePoints;

    GradeType(String label, double gradePoints) {
        this.label = label;
        this.gradePoints = gradePoints;
    }

    /** The short display form of the grade, such as {@code "A-"}. */
    public String getLabel() {
        return label;
    }

    /** The grade's value on the 4.0 scale. */
    public double getGradePoints() {
        return gradePoints;
    }

    /** A registration earns credit for any grade other than a failing {@code F}. */
    public boolean isPassing() {
        return this != F;
    }

    @Override
    public String toString() {
        return label;
    }
}
