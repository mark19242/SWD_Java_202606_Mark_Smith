package learn.encounters.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class EncounterServiceTest {

    private EncounterService service;

    @BeforeEach
    void setup() {
        // The service is injected with an in-memory repository double, so these tests
        // never touch the file system.
        service = new EncounterService(new EncounterRepositoryDouble());
    }

    // TODO: Test add() with a valid encounter -> result is success and has an id.
    // TODO: Test add() with a blank description -> result fails with the right message.
    // TODO: Test add() when the id is already set -> result fails.
    // TODO: Test update() for an existing encounter (success) and a missing one (failure).
    // TODO: Test findByType() returns only the matching encounters.
    // TODO: Test deleteById() for an existing id (success) and a missing id (failure).

    @Test
    void serviceIsConstructedWithInjectedRepository() {
        assertNotNull(service);
    }
}
