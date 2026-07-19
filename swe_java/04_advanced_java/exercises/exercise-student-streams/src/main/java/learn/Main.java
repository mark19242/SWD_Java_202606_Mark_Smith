package learn;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.IntSummaryStatistics;

/**
 * Student Streams exercise.
 *
 * <p>The {@link DataProvider} models students and their course registrations.
 * Inspect {@link Student}, {@link Registration}, and {@link GradeType} first to
 * get a feel for how they relate.
 *
 * <p>Each numbered task below is a short description. There is very little
 * supporting plumbing — you decide how to approach each one. The only hard
 * requirement is that you use Java <b>streams</b> in your solution. A
 * {@code // TODO:} marks where your code goes; each task method already prints a
 * heading so the program runs before you start.
 *
 * <p><b>For every task whose number is divisible by 3</b> (3, 6, 9, 12), also
 * try solving it with an ordinary loop. Is it easier or harder? Which version
 * uses more code?
 *
 * <p>If a task is not clear, skip it, then loop back. Break each one into the
 * operations it needs — filter, sort, transform (map), aggregate — and work out
 * the order to apply them in.
 */
public class Main {

    public static void main(String[] args) {
        List<Student> students = DataProvider.getStudents();
        System.out.println("Loaded " + students.size() + " students.\n");

        task01(students);
        task02(students);
        task03(students);
        task04(students);
        task05(students);
        task06(students);
        task07(students);
        task08(students);
        task09(students);
        task10(students);
        task11(students);
        task12(students);
    }

    // ------------------------------------------------------------------
    // Task 1 — Filter
    // Print the full name of every student whose major is "Computer Science".
    // ------------------------------------------------------------------
    static void task01(List<Student> students) {
        System.out.println("Task 1 — Computer Science majors:");
        students.stream()
                .filter(student -> student.getMajor().equals("Computer Science"))
                .map(student -> student.getFullName())
                .forEach(name -> System.out.println(name));
    }

    // ------------------------------------------------------------------
    // Task 2 — Filter + collect
    // Collect into a List every student who has NO registrations, then print
    // how many there are and each one's full name.
    // ------------------------------------------------------------------
    static void task02(List<Student> students) {
        System.out.println("\nTask 2 — Students with no registrations:");
        List<Student> studentsWithNoRegistrations = students.stream()
                .filter(student -> student.getRegistrations().isEmpty())
                .toList();

        System.out.println("Count: " + studentsWithNoRegistrations.size());

        studentsWithNoRegistrations.forEach(
                student -> System.out.println(student.getFullName())
        );
    }

    // ------------------------------------------------------------------
    // Task 3 — Sort   (divisible by 3: also solve with a loop)
    // Print every student sorted by last name, then by first name.
    // ------------------------------------------------------------------
    static void task03(List<Student> students) {
        System.out.println("\nTask 3 — Students by last name, then first name:");

        System.out.println("Stream version:");

        students.stream()
                .sorted(
                        Comparator.comparing(Student::getLastName)
                                .thenComparing(Student::getFirstName)
                )
                .map(Student::getFullName)
                .forEach(System.out::println);

        System.out.println("\nLoop version:");

        // Copy the list so the original student list is not rearranged.
        List<Student> sortedStudents = new ArrayList<>(students);

        sortedStudents.sort(
                Comparator.comparing(Student::getLastName)
                        .thenComparing(Student::getFirstName)
        );

        for (Student student : sortedStudents) {
            System.out.println(student.getFullName());
        }
    }

    // ------------------------------------------------------------------
    // Task 4 — Transform (map)
    // Build and print a List<String> where each entry is "Full Name — Major".
    // ------------------------------------------------------------------
    static void task04(List<Student> students) {
        System.out.println("\nTask 4 — \"Full Name — Major\" for every student:");
        List<String> studentInfo = students.stream()
                .map(student ->
                        student.getFullName() + " — " + student.getMajor())
                .toList();

        studentInfo.forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // Task 5 — flatMap + aggregate
    // Count the total number of registrations across ALL students and print it.
    // ------------------------------------------------------------------
    static void task05(List<Student> students) {
        System.out.println("\nTask 5 — Total registrations across all students:");

        long totalRegistrations = students.stream()
                .flatMap(student -> student.getRegistrations().stream())
                .count();

        System.out.println("Total registrations: " + totalRegistrations);
    }

    // ------------------------------------------------------------------
    // Task 6 — Grouping (count)   (divisible by 3: also solve with a loop)
    // Build a Map<String, Long> of how many students are in each major and
    // print every entry.
    // ------------------------------------------------------------------
    static void task06(List<Student> students) {
        System.out.println("\nTask 6 — Student count per major:");

        System.out.println("Stream version:");

        Map<String, Long> studentCountByMajor = students.stream()
                .collect(Collectors.groupingBy(
                        student -> student.getMajor(),
                        Collectors.counting()
                ));

        studentCountByMajor.forEach((major, count) ->
                System.out.println(major + ": " + count)
        );

        System.out.println("\nLoop version:");

        Map<String, Long> studentCountByMajorLoop = new HashMap<>();

        for (Student student : students) {
            studentCountByMajorLoop.merge(
                    student.getMajor(),
                    1L,
                    Long::sum
            );
        }

        studentCountByMajorLoop.forEach((major, count) ->
                System.out.println(major + ": " + count)
        );
    }

    // ------------------------------------------------------------------
    // Task 7 — anyMatch inside filter
    // Print the full name of every student who earned at least one A
    // (GradeType.A) in any course.
    // ------------------------------------------------------------------
    static void task07(List<Student> students) {
        System.out.println("\nTask 7 — Students with at least one A:");
            students.stream()
                    .filter(student ->
                            student.getRegistrations().stream()
                                    .anyMatch(registration ->
                                            registration.getGrade() == GradeType.A)
                    )
                    .map(Student::getFullName)
                    .forEach(System.out::println);
    }

    // ------------------------------------------------------------------
    // Task 8 — flatMap + filter + sum
    // Sum the credits from all PASSING registrations (grade.isPassing()) across
    // all students and print the total.
    // ------------------------------------------------------------------
    static void task08(List<Student> students) {
        System.out.println("\nTask 8 — Total passing credits:");
            int totalPassingCredits = students.stream()
                    .flatMap(student -> student.getRegistrations().stream())
                    .filter(registration -> registration.getGrade().isPassing())
                    .mapToInt(registration -> registration.getCredits())
                    .sum();

            System.out.println("Total passing credits: " + totalPassingCredits);
    }

    // ------------------------------------------------------------------
    // Task 9 — Grouping (average)   (divisible by 3: also solve with a loop)
    // Build a Map<String, Double> of the average grade points per major, over
    // every registration belonging to that major's students. Print each entry.
    // ------------------------------------------------------------------
    static void task09(List<Student> students) {
        System.out.println("\nTask 9 — Average grade points per major:");
            System.out.println("Stream version:");

            Map<String, Double> averageGradePointsByMajor = students.stream()
                    .flatMap(student -> student.getRegistrations().stream()
                            // Temporarily pair each registration with the student's major.
                            .map(registration ->
                                    Map.entry(student.getMajor(), registration)))
                    .collect(Collectors.groupingBy(
                            Map.Entry::getKey,
                            Collectors.averagingDouble(entry ->
                                    entry.getValue()
                                            .getGrade()
                                            .getGradePoints())
                    ));

            averageGradePointsByMajor.forEach((major, average) ->
                    System.out.printf("%s: %.2f%n", major, average)
            );

            System.out.println("\nLoop version:");

            Map<String, Double> gradePointTotals = new HashMap<>();
            Map<String, Integer> registrationCounts = new HashMap<>();

            for (Student student : students) {
                for (Registration registration : student.getRegistrations()) {
                    String major = student.getMajor();
                    double gradePoints =
                            registration.getGrade().getGradePoints();

                    gradePointTotals.merge(
                            major,
                            gradePoints,
                            Double::sum
                    );

                    registrationCounts.merge(
                            major,
                            1,
                            Integer::sum
                    );
                }
            }

            Map<String, Double> loopAverages = new HashMap<>();

            for (String major : gradePointTotals.keySet()) {
                double average = gradePointTotals.get(major)
                        / registrationCounts.get(major);

                loopAverages.put(major, average);
            }

            loopAverages.forEach((major, average) ->
                    System.out.printf("%s: %.2f%n", major, average)
            );
    }

    // ------------------------------------------------------------------
    // Task 10 — Sort + limit
    // Print the top 3 students by total quality points (sum of each
    // registration's credits × grade points), highest first.
    // ------------------------------------------------------------------
    static void task10(List<Student> students) {
        System.out.println("\nTask 10 — Top 3 students by quality points:");
            students.stream()
                    .sorted(
                            Comparator.comparingDouble(Main::calculateQualityPoints)
                                    .reversed()
                    )
                    .limit(3)
                    .forEach(student ->
                            System.out.printf(
                                    "%s: %.2f%n",
                                    student.getFullName(),
                                    calculateQualityPoints(student)
                            )
                    );
    }

        private static double calculateQualityPoints(Student student) {
            return student.getRegistrations().stream()
                    .mapToDouble(Registration::getQualityPoints)
                    .sum();
        }

    // ------------------------------------------------------------------
    // Task 11 — Aggregate statistics
    // Using mapToInt + summaryStatistics, print the minimum, maximum, and
    // average number of registrations per student.
    // ------------------------------------------------------------------
    static void task11(List<Student> students) {
        System.out.println("\nTask 11 — Registrations-per-student statistics:");

        IntSummaryStatistics statistics = students.stream()
                .mapToInt(student -> student.getRegistrations().size())
                .summaryStatistics();

        System.out.println("Minimum registrations: " + statistics.getMin());
        System.out.println("Maximum registrations: " + statistics.getMax());
        System.out.printf(
                "Average registrations: %.2f%n",
                statistics.getAverage()
        );
    }

    // ------------------------------------------------------------------
    // Task 12 — Grouping by enum   (divisible by 3: also solve with a loop)
    // Build a Map<GradeType, Long> counting how many registrations earned each
    // grade, then print it.
    // ------------------------------------------------------------------
    static void task12(List<Student> students) {
        System.out.println("\nTask 12 — Registration count per grade:");
        // TODO (stream): flatMap to registrations, then
        //                groupingBy(getGrade, counting()).
        // TODO (loop):   tally into a Map (or EnumMap) with merge. Compare them.
    }
}
