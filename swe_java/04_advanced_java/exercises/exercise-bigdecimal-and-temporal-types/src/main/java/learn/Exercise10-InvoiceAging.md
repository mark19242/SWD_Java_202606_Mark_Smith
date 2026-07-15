# Exercise 10 — Invoice Aging (markdown exercise)

This is a **markdown exercise**: the instructions live here, and you complete
them by finishing a Java source file that has been added to the project for you.

A finance system needs to *age* invoices — figure out how many days an invoice
is overdue and compute a late fee. Aging is a date calculation
(`java.time`); the late fee is exact money math (`BigDecimal`). This exercise
combines both.

## Your task

Open **`Exercise10InvoiceAging.java`** (already in this `learn` package) and
implement its three stubbed methods. Each has a numbered comment and a
`// TODO:` gap.

1. **`daysOverdue(LocalDate dueDate, LocalDate asOf)`** — return the whole
   number of days from `dueDate` to `asOf`. If the invoice is not yet due
   (`asOf` is on or before `dueDate`), return `0` — never a negative number.
   Use `ChronoUnit.DAYS.between(...)` and clamp with `Math.max`.

2. **`lateFee(BigDecimal amount, BigDecimal dailyRate, long daysOverdue)`** —
   return `amount * dailyRate * daysOverdue`, rounded to **2 decimal places**
   with `RoundingMode.HALF_UP`. Multiply with `BigDecimal.multiply(...)` and
   turn `daysOverdue` into a BigDecimal with
   `BigDecimal.valueOf(daysOverdue)`. Never mix in a `double`.

3. **`balanceDue(BigDecimal amount, BigDecimal dailyRate, LocalDate dueDate, LocalDate asOf)`**
   — combine the two methods above: compute the days overdue, compute the late
   fee, and return `amount + lateFee`, scaled to 2 decimals.

## Worked example

- `amount = 1000.00`, `dailyRate = 0.001` (0.1% per day)
- `dueDate = 2025-06-01`, `asOf = 2025-07-01`

Then:

- `daysOverdue` = 30
- `lateFee` = 1000.00 × 0.001 × 30 = **30.00**
- `balanceDue` = 1000.00 + 30.00 = **1030.00**

## Verify

Run it:

```bash
mvn exec:java -Dexec.mainClass=learn.Exercise10InvoiceAging
```

The `main` method prints the worked example above; your numbers should match.
