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
            result.addErrorMessage("Encounter `id` should not be set for an add.");
        }

        if (result.isSuccess()) {
            encounter = repository.add(encounter);
            result.setEncounter(encounter);
        }
        return result;
    }

    public EncounterResult update(Encounter encounter) throws DataException {
        EncounterResult result = validate(encounter);

        if (encounter != null && encounter.getEncounterId() <= 0) {
            result.addErrorMessage("Encounter `id` is required for an update.");
        }

        if (result.isSuccess()) {
            if (repository.update(encounter)) {
                result.setEncounter(encounter);
            } else {
                result.addErrorMessage(
                        String.format("Encounter id %s was not found.", encounter.getEncounterId()));
            }
        }
        return result;
    }

    public EncounterResult deleteById(int encounterId) throws DataException {
        EncounterResult result = new EncounterResult();
        if (!repository.deleteById(encounterId)) {
            result.addErrorMessage(String.format("Encounter id %s was not found.", encounterId));
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
