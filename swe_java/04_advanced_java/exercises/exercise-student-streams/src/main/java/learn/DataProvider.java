package learn;

import java.util.List;

/**
 * Supplies the fixed sample data set for the Student Streams exercise.
 *
 * <p>Think of {@link #getStudents()} as a stand-in for a database query: it
 * returns the same {@code List<Student>} every time so your stream pipelines
 * produce repeatable results. Notice that a couple of students have no
 * registrations at all — good edge cases to keep in mind.
 *
 * <p>This type is complete and read-only. Read it to understand the data; you
 * do not need to change it.
 */
public final class DataProvider {

    private DataProvider() {
        // Utility class — not meant to be instantiated.
    }

    public static List<Student> getStudents() {
        return List.of(
                new Student(1, "Alice", "Nguyen", "Computer Science", List.of(
                        new Registration("CS101", "Intro to Programming", 4, "Fall 2024", GradeType.A),
                        new Registration("MATH120", "Calculus I", 4, "Fall 2024", GradeType.B_PLUS),
                        new Registration("CS210", "Data Structures", 3, "Spring 2025", GradeType.A_MINUS)
                )),
                new Student(2, "Bob", "Okafor", "Computer Science", List.of(
                        new Registration("CS101", "Intro to Programming", 4, "Fall 2024", GradeType.C),
                        new Registration("ENG105", "College Writing", 3, "Fall 2024", GradeType.B)
                )),
                new Student(3, "Carla", "Mendes", "Mathematics", List.of(
                        new Registration("MATH120", "Calculus I", 4, "Fall 2024", GradeType.A),
                        new Registration("MATH220", "Linear Algebra", 3, "Spring 2025", GradeType.A),
                        new Registration("CS101", "Intro to Programming", 4, "Spring 2025", GradeType.B_PLUS),
                        new Registration("PHYS150", "Physics I", 4, "Spring 2025", GradeType.B)
                )),
                new Student(4, "Deepak", "Sharma", "Biology", List.of(
                        new Registration("BIO110", "General Biology", 4, "Fall 2024", GradeType.B_MINUS),
                        new Registration("CHEM101", "General Chemistry", 4, "Fall 2024", GradeType.C_PLUS)
                )),
                // Elena has registered for nothing yet — an empty registration list.
                new Student(5, "Elena", "Petrova", "Mathematics", List.of()),
                new Student(6, "Frank", "Byrne", "Computer Science", List.of(
                        new Registration("CS101", "Intro to Programming", 4, "Fall 2024", GradeType.F),
                        new Registration("CS101", "Intro to Programming", 4, "Spring 2025", GradeType.C_PLUS),
                        new Registration("MATH120", "Calculus I", 4, "Spring 2025", GradeType.D)
                )),
                new Student(7, "Grace", "Adeyemi", "Biology", List.of(
                        new Registration("BIO110", "General Biology", 4, "Fall 2024", GradeType.A),
                        new Registration("BIO210", "Genetics", 3, "Spring 2025", GradeType.A_MINUS),
                        new Registration("CHEM101", "General Chemistry", 4, "Fall 2024", GradeType.B_PLUS),
                        new Registration("STAT200", "Statistics", 3, "Spring 2025", GradeType.B)
                )),
                // Hiro has no registrations either.
                new Student(8, "Hiro", "Tanaka", "Computer Science", List.of())
        );
    }
}
