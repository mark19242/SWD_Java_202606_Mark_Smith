# Exercise: BigDecimal and Temporal Types

Practice precise decimal arithmetic with `java.math.BigDecimal` and date/time
handling with the `java.time` package. The exercises are **ordered** — work
through them from `Exercise01` to `Exercise10` in sequence, because later
exercises build on ideas from earlier ones.

Each exercise is either a **Java source file** or a **markdown file**:

- For a **Java source exercise**, open the file and read the numbered comments.
  They are instructions. Each method has a `// TODO:` gap — replace the
  placeholder with a real implementation so the method does what the numbered
  comment describes.
- For a **markdown exercise**, the markdown document is the set of
  instructions. It asks you to add a new source file to the project. A
  clearly-named stub class is already provided for you to fill in.

All classes live in the `learn` package under `src/main/java/learn/`.

## The exercises (in order)

### BigDecimal

1. **`Exercise01CreatingBigDecimal`** — construct `BigDecimal` values from
   strings, ints, and doubles; handle `null`; catch `NumberFormatException`.
2. **`Exercise02BigDecimalArithmetic`** — `add`, `subtract`, `multiply`,
   `divide` (BigDecimal has no `+ - * /` operators — every operation is a
   method that returns a **new** immutable instance).
3. **`Exercise03ScaleAndRounding`** — read a value's scale and use
   `setScale(...)` with a `RoundingMode` to reshape it.
4. **`Exercise04RoundingModes`** — compare `RoundingMode` behaviors
   (`UP`, `DOWN`, `CEILING`, `FLOOR`, `HALF_UP`, `HALF_DOWN`, `HALF_EVEN`).
5. **`Exercise05ScalingDuringDivision`** — divide with an explicit scale and
   rounding mode to tame non-terminating results (e.g. 1 / 3).
6. **`Exercise06MakingChange`** — money math: accumulate pennies exactly and
   break a total into dollars/quarters/dimes/nickels/pennies with
   `divideAndRemainder`.

### Temporal types (`java.time`)

7. **`Exercise07TemporalBasics`** — `LocalDate`, `LocalTime`, `LocalDateTime`;
   build values from parts and extract year / month / day / day-of-week.
8. **`Exercise08FormattingDates`** — `DateTimeFormatter` patterns and
   separators.
9. **`Exercise09DateCalculations`** — compare dates, and measure gaps with
   `ChronoUnit`, `Period`, and `Duration`.

### Capstone (markdown exercise)

10. **`Exercise10-InvoiceAging.md`** — instructions to complete the provided
    `Exercise10InvoiceAging` stub, combining BigDecimal money math with
    `java.time` date arithmetic.

## Build & Run

```bash
# Compile every exercise
mvn compile

# Run a specific exercise (each class has its own main)
mvn exec:java -Dexec.mainClass=learn.Exercise01CreatingBigDecimal
mvn exec:java -Dexec.mainClass=learn.Exercise06MakingChange

# (Optional) run any tests you write
mvn test
```

Every class already **compiles and runs** before you start — the method bodies
are stubbed with placeholder return values and a `// TODO:` describing the real
work. Replace the placeholders one method at a time.

## Reflection

After you finish, think about:

1. Why is `double` unsafe for money, and what exactly does `BigDecimal` fix?
2. Why does `BigDecimal` force you to pass a `RoundingMode` when scaling down?
3. When would you reach for `Period` versus `Duration` versus `ChronoUnit`?
4. Why does `java.time` favor immutable value types?
