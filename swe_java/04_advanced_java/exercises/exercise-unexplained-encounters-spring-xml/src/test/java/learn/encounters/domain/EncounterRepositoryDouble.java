package learn.encounters.domain;

import learn.encounters.data.EncounterRepository;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * In-memory test double for EncounterRepository. It lets us test EncounterService
 * without touching the file system.
 */
public class EncounterRepositoryDouble implements EncounterRepository {

    private final ArrayList<Encounter> encounters = new ArrayList<>();

    public EncounterRepositoryDouble() {
        encounters.add(new Encounter(1, EncounterType.UFO,
                LocalDate.of(2023, 5, 14), "Silver disc over the ridge", 3));
        encounters.add(new Encounter(2, EncounterType.CREATURE,
                LocalDate.of(2023, 6, 1), "Tall figure at the treeline", 1));
        encounters.add(new Encounter(3, EncounterType.UFO,
                LocalDate.of(2023, 9, 9), "Three lights in triangle formation", 4));
    }

    @Override
    public List<Encounter> findAll() {
        return new ArrayList<>(encounters);
    }

    @Override
    public Encounter findById(int encounterId) {
        for (Encounter encounter : encounters) {
            if (encounter.getEncounterId() == encounterId) {
                return encounter;
            }
        }
        return null;
    }

    @Override
    public List<Encounter> findByType(EncounterType type) {
        ArrayList<Encounter> result = new ArrayList<>();
        for (Encounter encounter : encounters) {
            if (encounter.getType() == type) {
                result.add(encounter);
            }
        }
        return result;
    }

    @Override
    public Encounter add(Encounter encounter) {
        encounter.setEncounterId(encounters.size() + 1);
        encounters.add(encounter);
        return encounter;
    }

    @Override
    public boolean update(Encounter encounter) {
        for (int i = 0; i < encounters.size(); i++) {
            if (encounters.get(i).getEncounterId() == encounter.getEncounterId()) {
                encounters.set(i, encounter);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean deleteById(int encounterId) {
        for (int i = 0; i < encounters.size(); i++) {
            if (encounters.get(i).getEncounterId() == encounterId) {
                encounters.remove(i);
                return true;
            }
        }
        return false;
    }
}
