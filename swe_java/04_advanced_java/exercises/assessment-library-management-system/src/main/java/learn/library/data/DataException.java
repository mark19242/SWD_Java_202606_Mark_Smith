package learn.library.data;

/**
 * Keeps file-specific exceptions inside the data layer.
 */
public class DataException extends Exception {

    public DataException(String message) {
        super(message);
    }

    public DataException(String message, Throwable cause) {
        super(message, cause);
    }
}
