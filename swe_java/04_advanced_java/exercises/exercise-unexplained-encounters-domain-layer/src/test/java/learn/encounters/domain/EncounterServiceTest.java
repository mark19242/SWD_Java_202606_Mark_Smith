package learn.encounters.domain;

import learn.encounters.data.DataException;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

class EncounterServiceTest {

    private EncounterService service;

    @BeforeEach
    void setup() {
        // The service is injected with an in-memory repository double, so these tests
        // never touch the file system.
        service = new EncounterService(new EncounterRepositoryDouble());
    }


    @Test
    void shouldFindOnlyUfoEncounters() throws DataException {

        // Act
        List<Encounter> encounters =
                service.findByType(EncounterType.UFO);

        // Assert
        assertEquals(2, encounters.size());

        for (Encounter encounter : encounters) {
            assertEquals(EncounterType.UFO, encounter.getType());
        }
    }

    @Test
    void shouldAddEncounter() throws DataException {

        // Arrange
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen(LocalDate.of(2026, 7, 14));
        encounter.setDescription("Bright lights over the lake");
        encounter.setOccurrences(1);

        // Act
        EncounterResult result = service.add(encounter);

        // Assert
        assertTrue(result.isSuccess());
        assertNotNull(result.getEncounter());
        assertTrue(result.getEncounter().getEncounterId() > 0);
    }


    @Test
    void shouldNotAddBlankDescription() throws DataException {

        // Arrange
        Encounter encounter = new Encounter();
        encounter.setType(EncounterType.UFO);
        encounter.setWhen(LocalDate.of(2026, 7, 14));
        encounter.setDescription("   ");
        encounter.setOccurrences(1);

        // Act
        EncounterResult result = service.add(encounter);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`description`"));
    }

    @Test
    void shouldNotAddEncounterWithExistingId() throws DataException {

        // Arrange
        Encounter encounter = new Encounter(
                99,
                EncounterType.UFO,
                LocalDate.of(2026, 7, 14),
                "Bright lights over the lake",
                1
        );

        // Act
        EncounterResult result = service.add(encounter);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0).contains("`id`"));
    }

    @Test
    void shouldUpdateEncounter() throws DataException {

        // Arrange
        Encounter encounter = service.findAll().get(0);
        encounter.setDescription("Updated encounter description");

        // Act
        EncounterResult result = service.update(encounter);

        // Assert
        assertTrue(result.isSuccess());
        assertEquals("Updated encounter description",
                result.getEncounter().getDescription());
    }

    @Test
    void shouldNotUpdateMissingEncounter() throws DataException {

        // Arrange
        Encounter missingEncounter = new Encounter(
                999,
                EncounterType.VISION,
                LocalDate.of(2026, 7, 14),
                "This encounter does not exist",
                1
        );

        // Act
        EncounterResult result = service.update(missingEncounter);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0)
                .contains("Encounter id 999 was not found."));
    }

    @Test
    void shouldDeleteExistingEncounter() throws DataException {

        // Arrange
        int encounterId = 1;

        // Act
        EncounterResult result = service.deleteById(encounterId);

        // Assert
        assertTrue(result.isSuccess());
    }

    @Test
    void shouldNotDeleteMissingEncounter() throws DataException {

        // Arrange
        int missingEncounterId = 999;

        // Act
        EncounterResult result = service.deleteById(missingEncounterId);

        // Assert
        assertFalse(result.isSuccess());
        assertEquals(1, result.getErrorMessages().size());
        assertTrue(result.getErrorMessages().get(0)
                .contains("Encounter id 999 was not found."));
    }
    
    @Test
    void serviceIsConstructedWithInjectedRepository() {
        assertNotNull(service);
    }
}
