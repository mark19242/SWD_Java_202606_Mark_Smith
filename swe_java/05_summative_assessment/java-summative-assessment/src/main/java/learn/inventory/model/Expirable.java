package learn.inventory.model;

import java.time.LocalDate;

public interface Expirable {

    LocalDate getExpirationDate();

    boolean isExpired();
}
