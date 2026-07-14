package learn.encounters.domain;

import learn.encounters.data.DataException;
import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterServiceTest {

    private EncounterService service;

    @BeforeEach
    void setup() {
        // The service is injected with an in-memory repository double, so these tests
        // never touch the file system.
        service = new EncounterService(new EncounterRepositoryDouble());
    }

    // TODO: Test add() with a valid encounter -> result is success and has an id.
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
    // TODO: Test add() with a blank description -> result fails with the right message.
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

    // TODO: Test add() when the id is already set -> result fails.
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
    // TODO: Test update() for an existing encounter (success) and a missing one (failure).
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
    // TODO: Test findByType() returns only the matching encounters.
    // TODO: Test deleteById() for an existing id (success) and a missing id (failure).

    @Test
    void serviceIsConstructedWithInjectedRepository() {
        assertNotNull(service);
    }
}
