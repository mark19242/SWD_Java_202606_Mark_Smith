# Exercise: Unexplained Encounters - Spring DI with XML

The Unexplained Encounters app is complete and wired by hand in `App.main`. Your
task is to hand that wiring to **Spring's XML DI container** - the Java classes do
not change.

## Your Tasks

1. Author `src/main/resources/di-config.xml`: register one `<bean>` per component
   (repository, service, view, controller). Use `<constructor-arg value="..."/>`
   for the repository's file path and `<constructor-arg ref="..."/>` to inject
   bean-to-bean dependencies. A skeleton file with detailed TODO notes is provided.
2. Replace the manual wiring in `App.main` with a
   `ClassPathXmlApplicationContext("di-config.xml")`, then
   `container.getBean(Controller.class).run()`.

`spring-context` is already on the classpath (see `pom.xml`).

## Build & Run

```bash
mvn compile
mvn exec:java
```

## Reflection

- What does the container do that your `App.main` used to do by hand?
- Why must the `<constructor-arg>` elements appear in constructor order?
