package learn.encounters.domain;

import learn.encounters.data.DataException;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    private EncounterService service;

    @BeforeEach
    void setup() {
        service = new EncounterService(new EncounterRepositoryDouble());
    }

    @Test
    void shouldAddValid() throws DataException {
        Encounter encounter = new Encounter(0, EncounterType.SOUND,
                LocalDate.of(2023, 10, 31), "Knocking from inside the wall", 2);
        EncounterResult result = service.add(encounter);
        assertTrue(result.isSuccess());
        assertNotNull(result.getEncounter());
        assertTrue(result.getEncounter().getEncounterId() > 0);
    }

    @Test
    void shouldNotAddBlankDescription() throws DataException {
        Encounter encounter = new Encounter(0, EncounterType.VOICE,
                LocalDate.of(2023, 6, 20), "  ", 1);
        EncounterResult result = service.add(encounter);
        assertFalse(result.isSuccess());
        assertTrue(result.getErrorMessages().contains("Encounter `description` is required."));
    }

    @Test
    void shouldNotAddWhenIdIsSet() throws DataException {
        Encounter encounter = new Encounter(5, EncounterType.UFO,
                LocalDate.of(2023, 5, 14), "Should not carry an id", 1);
        EncounterResult result = service.add(encounter);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldNotAddOccurrencesBelowOne() throws DataException {
        Encounter encounter = new Encounter(0, EncounterType.VISION,
                LocalDate.of(2023, 8, 12), "A figure in the mirror", 0);
        EncounterResult result = service.add(encounter);
        assertFalse(result.isSuccess());
        assertTrue(result.getErrorMessages().contains("Encounter `occurrences` must be at least 1."));
    }

    @Test
    void shouldUpdateExisting() throws DataException {
        Encounter encounter = new Encounter(1, EncounterType.UFO,
                LocalDate.of(2023, 5, 14), "Silver disc (updated)", 9);
        EncounterResult result = service.update(encounter);
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotUpdateMissing() throws DataException {
        Encounter encounter = new Encounter(999, EncounterType.UFO,
                LocalDate.of(2023, 5, 14), "Missing encounter", 1);
        EncounterResult result = service.update(encounter);
        assertFalse(result.isSuccess());
    }

    @Test
    void shouldFindByType() throws DataException {
        assertEquals(2, service.findByType(EncounterType.UFO).size());
    }

    @Test
    void shouldDeleteExisting() throws DataException {
        assertTrue(service.deleteById(2).isSuccess());
    }

    @Test
    void shouldNotDeleteMissing() throws DataException {
        assertFalse(service.deleteById(999).isSuccess());
    }
}
