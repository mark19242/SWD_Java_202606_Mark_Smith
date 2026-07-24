package learn.inventory.model;

import java.time.LocalDate;

/**
 * Defines expiration-related behavior for objects that can expire.
 *
 * <p>Any class implementing this interface must provide an expiration date
 * and determine whether that date has already passed.</p>
 */

public interface Expirable {

    /**
     * Returns the date on which the object expires.
     *
     * @return the expiration date
     */

    LocalDate getExpirationDate();

    /**
     * Determines whether the expiration date occurred before the current date.
     *
     * @return {@code true} when the object is expired; otherwise {@code false}
     */

    boolean isExpired();
}
