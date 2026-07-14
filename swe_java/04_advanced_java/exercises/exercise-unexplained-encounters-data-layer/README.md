# Exercise: Unexplained Encounters - Data Layer

Build out the file-backed data access layer for **Unexplained Encounters**, an app
that tracks mysterious events. This starter already reads and writes encounters
with `findAll`, `findById`, `add`, and `deleteById`. Your job is to finish the
repository and strengthen its tests.

## The Model

An `Encounter` has: `encounterId` (sequential id), `type` (an `EncounterType`:
UFO, CREATURE, VOICE, SOUND, VISION), `when` (a `LocalDate`), `description`, and
`occurrences`. Records are stored one-per-line in a CSV file
(`./data/encounters.csv`).

## Your Tasks

1. **`EncounterFileRepository.findByType(EncounterType)`** - return every encounter
   of a given type. (The method contract is already on the `EncounterRepository`
   interface.)
2. **`EncounterFileRepository.update(Encounter)`** - replace an existing encounter
   and return `true`; return `false` if no encounter has that id.
3. **Tests** in `EncounterFileRepositoryTest`:
   - Test `findByType` and `update` (both a found and a not-found case for update).
   - Test `deleteById` for both a positive and a negative case.
   - **Improve Test Setup**: instead of building state by calling `add`, copy the
     provided seed file `./data/encounters-seed.csv` into a disposable test file
     (`./data/encounters-test.csv`) in a `@BeforeEach`, and point the repository at
     the test file. The seed stays pristine; each test starts from a known state.

## Build & Run

```bash
mvn test      # run the tests
mvn compile   # compile
```

The data files live in `./data/` at the project root.

## Reflection

- Why does the repository hide the file details behind an interface?
- Why is a seed-copy setup easier to debug than one built with `add`/`update`?
