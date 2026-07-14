package learn.encounters.domain;

import learn.encounters.models.Encounter;

import java.util.ArrayList;
import java.util.List;

public class EncounterResult {

    private final ArrayList<String> messages = new ArrayList<>();
    private Encounter encounter;

    public List<String> getErrorMessages() {
        return new ArrayList<>(messages);
    }

    public void addErrorMessage(String message) {
        messages.add(message);
    }

    public boolean isSuccess() {
        return messages.isEmpty();
    }

    public Encounter getEncounter() {
        return encounter;
    }

    public void setEncounter(Encounter encounter) {
        this.encounter = encounter;
    }
}
