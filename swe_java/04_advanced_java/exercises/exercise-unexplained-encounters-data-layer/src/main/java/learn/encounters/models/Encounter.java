package learn.encounters.models;

import java.time.LocalDate;
import java.util.Objects;

public class Encounter {

    private int encounterId;
    private EncounterType type;
    private LocalDate when;
    private String description;
    private int occurrences;

    public Encounter() {
    }

    public Encounter(int encounterId, EncounterType type, LocalDate when, String description, int occurrences) {
        this.encounterId = encounterId;
        this.type = type;
        this.when = when;
        this.description = description;
        this.occurrences = occurrences;
    }

    public int getEncounterId() {
        return encounterId;
    }

    public void setEncounterId(int encounterId) {
        this.encounterId = encounterId;
    }

    public EncounterType getType() {
        return type;
    }

    public void setType(EncounterType type) {
        this.type = type;
    }

    public LocalDate getWhen() {
        return when;
    }

    public void setWhen(LocalDate when) {
        this.when = when;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getOccurrences() {
        return occurrences;
    }

    public void setOccurrences(int occurrences) {
        this.occurrences = occurrences;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Encounter that = (Encounter) o;
        return encounterId == that.encounterId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(encounterId);
    }

    @Override
    public String toString() {
        return String.format("Encounter{encounterId=%s, type=%s, when=%s, description='%s', occurrences=%s}",
                encounterId, type, when, description, occurrences);
    }
}
