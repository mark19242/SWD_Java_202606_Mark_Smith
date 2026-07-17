package learn;

import java.util.List;

/**
 * A student and the courses they have registered for.
 *
 * <p>Registrations are the "second dimension" of this data set: a student may
 * have zero, one, or many. Tasks that ask about courses across all students
 * will need to flatten these lists into a single stream.
 *
 * <p>This type is complete and read-only. Read it to understand the data; you
 * do not need to change it.
 */
public class Student {

    private final int id;
    private final String firstName;
    private final String lastName;
    private final String major;
    private final List<Registration> registrations;

    public Student(int id, String firstName, String lastName, String major, List<Registration> registrations) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.major = major;
        this.registrations = registrations;
    }

    public int getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getMajor() {
        return major;
    }

    public List<Registration> getRegistrations() {
        return registrations;
    }

    /** Convenience accessor: first and last name joined with a space. */
    public String getFullName() {
        return firstName + " " + lastName;
    }

    @Override
    public String toString() {
        return String.format("#%d %s [%s] — %d registration(s)",
                id, getFullName(), major, registrations.size());
    }
}
