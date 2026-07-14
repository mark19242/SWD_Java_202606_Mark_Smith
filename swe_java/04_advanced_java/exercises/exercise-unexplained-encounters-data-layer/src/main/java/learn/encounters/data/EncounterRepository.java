package learn.encounters.data;

import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;

import java.util.List;

public interface EncounterRepository {

    List<Encounter> findAll() throws DataException;

    Encounter findById(int encounterId) throws DataException;

    List<Encounter> findByType(EncounterType type) throws DataException;

    Encounter add(Encounter encounter) throws DataException;

    boolean update(Encounter encounter) throws DataException;

    boolean deleteById(int encounterId) throws DataException;
}
