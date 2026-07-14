# Exercise: Unexplained Encounters - Domain Layer

The data layer is complete and tested. Now add the **domain layer** that enforces
the app's business rules on top of the repository.

## Your Tasks

Implement the methods in **`EncounterService`** (they currently throw
`UnsupportedOperationException`):

- `findAll`, `findByType`, `findById` - simple pass-throughs to the repository.
- `add(Encounter)` - `validate` first; the id must **not** be set; on success add
  via the repository and put the saved encounter on the `EncounterResult`.
- `update(Encounter)` - `validate` first; the id **is** required; on success update
  via the repository, else add a "not found" error.
- `deleteById(int)` - delete via the repository; add a "not found" error on failure.

The `validate` helper and the `EncounterResult` object are already provided. This
mirrors the `MemoryService` / `MemoryResult` pattern from the domain-layer
code-along.

Then finish the tests in **`EncounterServiceTest`**, using the provided in-memory
`EncounterRepositoryDouble` (no file system needed).

## Build & Run

```bash
mvn test
```

## Reflection

- Why enforce rules in the domain layer instead of the UI or data layer?
- Why does the service return an `EncounterResult` instead of throwing on bad input?
