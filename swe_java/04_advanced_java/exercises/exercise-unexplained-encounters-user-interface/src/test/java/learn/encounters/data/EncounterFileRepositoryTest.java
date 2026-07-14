package learn.encounters.data;

import learn.encounters.models.Encounter;
import learn.encounters.models.EncounterType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EncounterFileRepositoryTest {

    private static final Path SEED_PATH = Path.of("./data/encounters-seed.csv");
    private static final Path TEST_PATH = Path.of("./data/encounters-test.csv");
    private static final String TEST_FILE = "./data/encounters-test.csv";

    private EncounterFileRepository repository;

    @BeforeEach
    void setup() throws IOException {
        // Copy the known-good seed file into a disposable test file before each test.
        // The seed file is never modified; the test file is safe to add/update/delete.
        Files.copy(SEED_PATH, TEST_PATH, StandardCopyOption.REPLACE_EXISTING);
        repository = new EncounterFileRepository(TEST_FILE);
    }

    @Test
    void shouldFindAll() throws DataException {
        List<Encounter> all = repository.findAll();
        assertEquals(6, all.size());
    }

    @Test
    void shouldFindById() throws DataException {
        Encounter encounter = repository.findById(1);
        assertNotNull(encounter);
        assertEquals(EncounterType.UFO, encounter.getType());
    }

    @Test
    void shouldNotFindMissingId() throws DataException {
        assertNull(repository.findById(999));
    }

    @Test
    void shouldFindByType() throws DataException {
        List<Encounter> ufos = repository.findByType(EncounterType.UFO);
        assertEquals(2, ufos.size());
    }

    @Test
    void shouldAdd() throws DataException {
        Encounter encounter = new Encounter(0, EncounterType.SOUND,
                LocalDate.of(2023, 10, 31), "Knocking from inside the wall", 2);
        Encounter added = repository.add(encounter);
        assertEquals(7, added.getEncounterId());
        assertEquals(7, repository.findAll().size());
    }

    @Test
    void shouldUpdateExisting() throws DataException {
        Encounter encounter = repository.findById(2);
        encounter.setOccurrences(9);
        assertTrue(repository.update(encounter));
        assertEquals(9, repository.findById(2).getOccurrences());
    }

    @Test
    void shouldNotUpdateMissing() throws DataException {
        Encounter ghost = new Encounter(999, EncounterType.VISION,
                LocalDate.of(2023, 1, 1), "Never recorded", 1);
        assertFalse(repository.update(ghost));
    }

    @Test
    void shouldDeleteExistingId() throws DataException {
        assertTrue(repository.deleteById(3));
        assertEquals(5, repository.findAll().size());
        assertNull(repository.findById(3));
    }

    @Test
    void shouldNotDeleteMissingId() throws DataException {
        assertFalse(repository.deleteById(999));
        assertEquals(6, repository.findAll().size());
    }
}
