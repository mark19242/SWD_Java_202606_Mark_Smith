package model;

public class Locker {

    private String lockerId;
    private boolean isOccupied;
    private String contents;

    public Locker(String lockerId) {
        this.lockerId = lockerId;
        this.isOccupied = false;
        this.contents = "";
    }

    public String getLockerId() {
        return lockerId;
    }

    public void storeItem(String item) {
        contents = item;
        isOccupied = true;
    }

    public void removeItem() {
        contents = "";
        isOccupied = false;
    }

    public String getSummary() {

        return "Locker ID: " + lockerId
                + ", Occupied: " + isOccupied
                + ", Contents: " + contents;

    }
}