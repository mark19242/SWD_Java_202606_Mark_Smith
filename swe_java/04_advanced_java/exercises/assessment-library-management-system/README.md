# Assessment: Library Management System

A library management system tracks books, manages the library's inventory, and
keeps organized records. In this summative assessment you will **design, test,
and build** a console application to manage books in a library.

This is a **starter project**. It contains only an empty Maven skeleton and this
README. **You** design and build the application: the model, the data-access
layer, the service layer, and the user interface.

## What to build

You are building for a **library administrator**, who can:

- **Find books** in a specified category.
- **Add** a book to the library.
- **Update** the details of a book.
- **Remove** a book from the library.

## The Book model

Each book tracks the following details:

| Field          | Description                                             |
|----------------|---------------------------------------------------------|
| Category       | The category the book belongs to (e.g. Science Fiction) |
| Shelf Number   | The shelf within the category where the book is located |
| Position       | The position on the shelf where the book is placed      |
| Year Published | The year the book was published                         |
| Author         | The author of the book                                  |
| ISBN           | A unique identifier for each book                       |

A book is uniquely located by its **category, shelf number, and position** —
for example `Fantasy-10-15`.

## Validation rules

- **Category** is required and cannot be blank.
- **Shelf Number** must be a positive number ≤ 250.
- **Position** must be a positive number ≤ 250.
- **Year Published** must be in the past.
- **Author** is required and must be a valid string.
- **ISBN** is required and must be **unique**.

## Technical requirements

- **Architecture** — Use a **three-layer architecture**: model, service, and
  controller. (A separate data-access / repository layer is expected within
  this design.) If you have a reason to vary from this, consult your instructor.
- **Data Storage** — Store data in a **delimited file**. Data must persist
  between application restarts. Keep it under `./data/`.
- **Custom Exceptions** — Repositories must throw **custom exceptions**, not
  file-specific ones (e.g. not `IOException`).
- **Testing** — Repository and service classes must be **thoroughly tested**
  with both positive and negative cases. **Do not use the production data file
  for testing** — use a separate test file.
- **ISBN** — Use a unique identifier for each book.

## Suggested approach

1. **Planning** — Define the data types and names for the `Book` model.
2. **Scenario Planning** — For each use case, decide the responsibilities of
   each component (repository, service, controller, view).
3. **Trigger Scenarios** — Determine how each scenario is reached from the main
   menu.
4. **Diagrams** — Use sequence diagrams and flowcharts to refine the logic.

## Sample UI

### Main Menu

```
Welcome to the Library Management System
========================================

Main Menu
=========
0. Exit
1. Find Books by Category
2. Add a Book
3. Update a Book
4. Remove a Book
Select [0-4]:
```

### Find Books by Category

```
Find Books by Category
======================

Category: Science Fiction

Books in Science Fiction
Shelf Pos Year Author           ISBN
1     1   2020 Isaac Asimov     978-0451524935
1     2   2021 Philip K. Dick   978-0394800424
2     1   2019 Arthur C. Clarke 978-0345347958
```

### Add a Book

```
Add a Book
==========

Category: Fantasy
Shelf Number: 251
[Err]
Shelf Number must be between 1 and 250.
Shelf Number: 10
Position: 15
Author: J.K. Rowling
Year Published: 1997
ISBN: 978-0747532743

[Success]
Book Fantasy-10-15 added.
```

### Update a Book

```
Update a Book
=============

Category: Mystery
Shelf Number: 5
Position: 7

Editing Mystery-5-7
Press [Enter] to keep original value.

Category (Mystery):
Shelf Number (5): 6
Position (7):
Author (Agatha Christie):
Year Published (1934):
ISBN (978-0062073501):

[Success]
Book Mystery-6-7 updated.
```

### Remove a Book — Success

```
Remove a Book
=============

Category: Fiction
Shelf Number: 8
Position: 10

[Success]
Book Fiction-8-10 removed.
```

### Remove a Book — Failure

```
Remove a Book
=============

Category: Fiction
Shelf Number: 5
Position: 6

[Err]
There is no book Fiction-5-6.
```

## Stretch goals

- List the existing categories in the Find Books by Category scenario.
- Add search features: by publication-year range, shelf number, or author.
- If a book is a duplicate, offer to change details to prevent duplication.
- Implement a bulk update feature for multiple books.

## Build & run

```bash
mvn compile        # compile
mvn exec:java      # run the console app
mvn test           # run your tests
```

## How you'll be graded

The rubric weights four areas:

| Area                         | Weight | What it covers                                              |
|------------------------------|--------|-------------------------------------------------------------|
| **Functionality**            | 40%    | Add, Update, Remove, and Display books work and validate    |
| **Data Handling & Storage**  | 20%    | File persistence across sessions; custom-exception handling |
| **Code Quality & Design**    | 20%    | Clear three-layer architecture; readability; enums/exceptions |
| **User Interface**           | 20%    | Intuitive navigation; clear, helpful error messages         |

Aim for correct validation on every field, reliable file persistence, custom
exceptions in the data layer, positive-and-negative tests that avoid the
production file, and clear success/error messaging in the UI.
