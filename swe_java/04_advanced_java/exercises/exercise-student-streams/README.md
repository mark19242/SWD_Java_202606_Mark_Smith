# Exercise: Student Streams

Practice the Java **Stream API** against a small data set of students and their
course registrations. You will filter, sort, transform (map), and aggregate the
data to answer a series of questions.

## The data

The project models students and their registrations in the `learn` package:

- **`Student`** — id, first name, last name, major, and a `List<Registration>`.
- **`Registration`** — course code, course name, credits, term, and a grade.
- **`GradeType`** — an enum of letter grades, each with a 4.0-scale grade-point
  value and an `isPassing()` flag.
- **`DataProvider`** — `getStudents()` returns the fixed sample list. A couple of
  students have no registrations at all — mind those edge cases.

Read those four types first. They are complete and read-only; you do not change
them.

## Your job

The exercises live in **`learn.Main`** — this is different from earlier
exercises. Each numbered task is a short description with a `// TODO:` marker.
There is very little plumbing: you decide how to approach each task. The only
requirement is that you use **streams** in your solution.

The twelve tasks, by category:

| # | Category | Task |
|---|----------|------|
| 1 | Filter | Print the full name of every Computer Science major. |
| 2 | Filter + collect | Collect students with no registrations; report count and names. |
| 3 | Sort *(÷3)* | Print students sorted by last name, then first name. |
| 4 | Transform | Build a `List<String>` of `"Full Name — Major"`. |
| 5 | flatMap + aggregate | Count total registrations across all students. |
| 6 | Grouping *(÷3)* | `Map<String, Long>` of student count per major. |
| 7 | anyMatch | Print students with at least one A. |
| 8 | flatMap + filter + sum | Total credits from passing registrations. |
| 9 | Grouping *(÷3)* | `Map<String, Double>` of average grade points per major. |
| 10 | Sort + limit | Top 3 students by total quality points. |
| 11 | Aggregate stats | Min/max/average registrations per student. |
| 12 | Grouping *(÷3)* | `Map<GradeType, Long>` of registrations per grade. |

**For every task whose number is divisible by 3** (3, 6, 9, 12), also solve it
with an ordinary loop and compare: is it easier or harder? Which uses more code?

If a task is not clear, skip it and loop back. Break each one into the
operations it needs — filter, sort, transform, aggregate — and work out the
order. Filter early to shrink the data; sort late for presentation.

## Build & run

```bash
# Compile
mvn compile

# Run the program (executes learn.Main)
mvn exec:java

# (Optional) run the tests
mvn test
```

## Reflection

When you finish, think about:

1. How does chaining stream operations compare to nested loops for readability?
2. When is `flatMap` the right tool, and what problem does it solve here?
3. Which tasks were genuinely shorter as streams, and which weren't?
4. Where did filtering early (or sorting late) change how much work the pipeline did?
