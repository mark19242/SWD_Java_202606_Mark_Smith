package learn.encounters.data;

import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    private static final String TEST_FILE = "./data/encounters-test.csv";

    private EncounterFileRepository repository;

    @BeforeEach
    void setup() throws IOException {

        // Copy the clean seed data into the disposable test file before each test.
        Files.copy(
                Path.of("./data/encounters-seed.csv"),
                Path.of(TEST_FILE),
                StandardCopyOption.REPLACE_EXISTING
        );

        repository = new EncounterFileRepository(TEST_FILE);
    }

    @Test
    void shouldAddAndFindAll() throws DataException {

        // Arrange
        int startingCount = repository.findAll().size();

        // Act
        repository.add(new Encounter(
                0,
                EncounterType.UFO,
                LocalDate.of(2023, 5, 14),
                "Silver disc over the ridge",
                3
        ));

        repository.add(new Encounter(
                0,
                EncounterType.VOICE,
                LocalDate.of(2023, 6, 20),
                "A whisper with no source",
                2
        ));

        // Assert
        assertEquals(startingCount + 2, repository.findAll().size());
    }


    @Test
    void shouldUpdateEncounter() throws DataException {

        // Arrange
        Encounter encounter = repository.findAll().get(0);
        encounter.setDescription("**TESTY MCTESTER MCALIEN**");

        // Act
        boolean success = repository.update(encounter);

        // Assert
        assertTrue(success);

        Encounter updated = repository.findById(encounter.getEncounterId());

        assertEquals("**TESTY MCTESTER MCALIEN**",
                updated.getDescription());
    }

    @Test
    void shouldNotUpdateMissingEncounter() throws DataException {

        // Arrange
        Encounter missingEncounter = new Encounter(
                999,
                EncounterType.UFO,
                LocalDate.of(2026, 7, 13),
                "This encounter does not exist",
                1
        );

        // Act
        boolean success = repository.update(missingEncounter);

        // Assert
        assertFalse(success);
    }



    @Test
    void shouldDeleteExistingEncounter() throws DataException {

        // Arrange
        Encounter encounter = repository.findAll().get(0);
        int encounterId = encounter.getEncounterId();

        // Act
        boolean success = repository.deleteById(encounterId);

        // Assert
        assertTrue(success);
        assertNull(repository.findById(encounterId));
    }

    @Test
    void shouldNotDeleteMissingEncounter() throws DataException {

        // Arrange
        int missingEncounterId = 999;

        // Act
        boolean success = repository.deleteById(missingEncounterId);

        // Assert
        assertFalse(success);
    }

    @Test
    void shouldFindEncountersByType() throws DataException {

        // Act
        List<Encounter> ufoEncounters =
                repository.findByType(EncounterType.UFO);

        // Assert
        assertFalse(ufoEncounters.isEmpty());

        for (Encounter encounter : ufoEncounters) {
            assertEquals(EncounterType.UFO, encounter.getType());
        }
    }


}
