# Exercise: Temporal Types in Java (Time Travel)

Practice working with Java's date and time classes by building a small program
that helps a business handle date-related tasks — formatting dates, calculating
deadlines, and understanding cultural date formats.

Work in `TimeTravelActivity.java`. Complete each `// TODO:` under the five
section headers. Add comments explaining what each section does.

## Your tasks

1. **Get the current date and time** — capture `LocalDate`, `LocalTime`,
   `LocalDateTime`, and `OffsetDateTime`, and print each.
2. **Format the date** — `MM-dd-yyyy`, `MM/dd/yy`, and full month name
   (`June 27, 2025`); then format for French (`fr-FR`) and Japanese (`ja-JP`).
3. **Extract date parts** — year, month, day, and day of the week.
4. **Date calculations** — total days and weeks between
   `projectStart = 2025-09-01` and `projectEnd = 2025-12-15`, plus days from
   today until the project starts.
5. **Build a date from parts** — New Year's Day, Independence Day, and your
   birthday for the current year.

## Requirements

- JDK 17 or later

## Compile and run

```bash
javac TimeTravelActivity.java && java TimeTravelActivity
```

The starter uses the default package and compiles and runs as-is, so you can
fill in one part at a time and re-run to check your progress.

## Deliverable

- `TimeTravelActivity.java` that runs without errors.
- Comments explaining what each section does.
