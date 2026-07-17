# Exercise: Unexplained Encounters - Spring DI with Annotations

Same complete app, wired a different way. This time configure the DI container with
**annotations** instead of XML.

## Your Tasks

1. Annotate the components with Spring stereotypes:
   - `EncounterFileRepository` -> `@Repository`, and annotate its constructor's
     `filePath` parameter with `@Value("${dataFilePath}")`.
   - `EncounterService` -> `@Service`
   - `View` -> `@Component`
   - `Controller` -> `@Component`
2. Annotate `App` with `@ComponentScan` and
   `@PropertySource("classpath:application.properties")`.
3. Fill in `src/main/resources/application.properties` with
   `dataFilePath=./data/encounters.csv`.
4. Replace the manual wiring in `App.main` with an
   `AnnotationConfigApplicationContext(App.class)`, then
   `context.getBean(Controller.class).run()`.

`spring-context` is already on the classpath.

## Build & Run

```bash
mvn compile
mvn exec:java
```

## Reflection

- `@ComponentScan` finds children but never parents or siblings - why does putting
  it on `App` (in `learn.encounters`) reach every component?
- What does `@PropertySource` buy you over hard-coding the file path with `@Value`?
