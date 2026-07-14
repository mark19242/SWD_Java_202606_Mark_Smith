package learn.encounters.domain;

import learn.encounters.data.DataException;
import learn.encounters.data.EncounterRepository;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.util.List;

public class EncounterService {

    private final EncounterRepository repository;

    public EncounterService(EncounterRepository repository) {
        this.repository = repository;
    }

    public List<Encounter> findAll() throws DataException {
        return repository.findAll();
    }

    public List<Encounter> findByType(EncounterType type) throws DataException {
        return repository.findByType(type);
    }

    public Encounter findById(int encounterId) throws DataException {
        return repository.findById(encounterId);
    }

    public EncounterResult add(Encounter encounter) throws DataException {
        EncounterResult result = validate(encounter);

        if (encounter != null && encounter.getEncounterId() > 0) {
            result.addErrorMessage("Encounter `id` should not be set.");
        }

        if (result.isSuccess()) {
            Encounter savedEncounter = repository.add(encounter);
            result.setEncounter(savedEncounter);
        }

        return result;
    }

    public EncounterResult update(Encounter encounter) throws DataException {

        // Validate the encounter first.
        EncounterResult result = validate(encounter);

        // An existing encounter must already have an ID.
        if (encounter != null && encounter.getEncounterId() <= 0) {
            result.addErrorMessage("Encounter `id` is required.");
        }

        // Only attempt the update if validation passed.
        if (result.isSuccess()) {

            boolean success = repository.update(encounter);

            if (success) {
                result.setEncounter(encounter);
            } else {
                result.addErrorMessage(
                        "Encounter id " + encounter.getEncounterId() + " was not found."
                );
            }
        }

        return result;
    }

    public EncounterResult deleteById(int encounterId) throws DataException {

        EncounterResult result = new EncounterResult();

        boolean success = repository.deleteById(encounterId);

        if (!success) {
            result.addErrorMessage(
                    "Encounter id " + encounterId + " was not found."
            );
        }

        return result;
    }

    private EncounterResult validate(Encounter encounter) {
        EncounterResult result = new EncounterResult();

        if (encounter == null) {
            result.addErrorMessage("Encounter cannot be null.");
            return result;
        }

        if (encounter.getType() == null) {
            result.addErrorMessage("Encounter `type` is required.");
        }

        if (encounter.getWhen() == null) {
            result.addErrorMessage("Encounter `when` is required.");
        }

        if (encounter.getDescription() == null || encounter.getDescription().isBlank()) {
            result.addErrorMessage("Encounter `description` is required.");
        }

        if (encounter.getOccurrences() < 1) {
            result.addErrorMessage("Encounter `occurrences` must be at least 1.");
        }

        return result;
    }
}
