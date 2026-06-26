import model.Locker;

import java.util.ArrayList;

public class LockerManager {

    private ArrayList<Locker> lockers;

    public LockerManager() {
        lockers = new ArrayList<>();
    }

    public void addLocker(String lockerId) {
        lockers.add(new Locker(lockerId));
        System.out.println("Locker " + lockerId + " added.");
    }

    public Locker getLocker(String lockerId) {

        for (Locker locker : lockers) {

            if (locker.getLockerId().equalsIgnoreCase(lockerId)) {
                return locker;
            }

        }

        return null;
    }

    public void removeLocker(String lockerId) {

        Locker locker = getLocker(lockerId);

        if (locker != null) {
            lockers.remove(locker);
            System.out.println("Locker " + lockerId + " removed.");
        } else {
            System.out.println("Locker not found.");
        }

    }

    public void displayAllLockers() {

        if (lockers.isEmpty()) {
            System.out.println("No lockers available.");
            return;
        }

        for (Locker locker : lockers) {
            System.out.println(locker.getSummary());
        }

    }
}