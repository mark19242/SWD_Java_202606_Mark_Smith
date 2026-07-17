package learn;

/**
 * A single course a student registered for in one term, with the grade earned.
 *
 * <p>This type is complete and read-only. Read it to understand the data; you
 * do not need to change it.
 */
public class Registration {

    private final String courseCode;
    private final String courseName;
    private final int credits;
    private final String term;
    private final GradeType grade;

    public Registration(String courseCode, String courseName, int credits, String term, GradeType grade) {
        this.courseCode = courseCode;
        this.courseName = courseName;
        this.credits = credits;
        this.term = term;
        this.grade = grade;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseName() {
        return courseName;
    }

    public int getCredits() {
        return credits;
    }

    public String getTerm() {
        return term;
    }

    public GradeType getGrade() {
        return grade;
    }

    /** Quality points = credits &times; grade points. The building block of a GPA. */
    public double getQualityPoints() {
        return credits * grade.getGradePoints();
    }

    @Override
    public String toString() {
        return String.format("%s %s (%d cr, %s, %s)", courseCode, courseName, credits, term, grade.getLabel());
    }
}
