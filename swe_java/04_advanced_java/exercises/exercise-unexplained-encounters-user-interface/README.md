# Exercise: Unexplained Encounters - User Interface

The back end (data + domain) is complete and tested. Build the **front end** using
the MVC pattern: a `View` (all console I/O), a `Controller` (drives the flow), and
`App` (the composition root that wires everything together with manual DI).

## Your Tasks

1. **`App.main`** - wire the app by hand: create the `EncounterFileRepository`
   (`./data/encounters.csv`), the `EncounterService`, the `View`, and the
   `Controller`, then call `controller.run()`.
2. Implement the three use-case methods in **`Controller`** (they currently throw
   `UnsupportedOperationException`):
   - **Display Encounters By Type** (`viewByType`)
   - **Update An Encounter** (`updateEncounter`)
   - **Delete An Encounter** (`deleteEncounter`)

`MenuOption`, `View`, and the `addEncounter` use case are already complete - use
`addEncounter` as your worked example. The `View` never lets the `Controller` touch
`System.out` or the `Scanner` directly.

## Build & Run

```bash
mvn compile
mvn exec:java     # run the console app
```

## Reflection

- Why does the controller reference the view and service, but neither references
  the controller?
- Why keep all input/output inside the `View`?
