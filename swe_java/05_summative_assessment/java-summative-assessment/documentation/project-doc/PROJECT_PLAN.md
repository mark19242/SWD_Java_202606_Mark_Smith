# Inventory Manager Project Plan

## Project Purpose

The Inventory Manager is a Java console application that allows a user to
manage products stored in an inventory.

## User

The application has one general inventory user who can manage all products.
The project does not include separate user roles or permissions.

## Core Features

- Add a product
- View all products
- Search for a product by ID or name
- Update a product's quantity or price
- Delete a product
- Save inventory data to a file
- Load inventory data from a file
- Validate user input and prevent the program from crashing

## Product Information

The application supports two product types:

### Standard Product

- Product ID
- Product Name
- Quantity
- Price

### Perishable Product

- Product ID
- Product Name
- Quantity
- Price
- Expiration Date

## Data Structure

An `ArrayList<Product>` stores and manages multiple product objects while
the application is running.

Because both `StandardProduct` and `PerishableProduct` inherit from
`Product`, the same collection can manage both product types.

## Data Persistence

Inventory data is saved to and loaded from a tab-separated text file.

Each saved record includes the product type so the application can recreate
either a `StandardProduct` or a `PerishableProduct` when the file is loaded.

## Project Classes

- `App`
- `MainMenu`
- `Product` — abstract parent class
- `StandardProduct`
- `PerishableProduct`
- `Expirable` — interface
- `InventoryService`
- `InventoryRepository` — interface
- `FileInventoryRepository`

These classes separate user interaction, business logic, product models,
and file persistence to keep the project organized and maintainable.

`InventoryService` depends on the `InventoryRepository` interface instead
of directly depending on the file-based implementation.

## Project Documentation

Additional project materials are located in the `documentation` folder:

- `documentation/project-doc` contains the project plan, class diagram,
  application flowchart, and handwritten pseudocode.
- `documentation/javadoc` contains generated HTML Javadoc documentation
  for the application's Java classes, interfaces, constructors, and methods.

Open `documentation/javadoc/index.html` in a browser to view the generated
documentation.

## Credits

The decorative ASCII divider used in the main menu was sourced from
ASCII Art Archive.

- Artist: Richard Kirk
- Category: Dividers
- Source: ASCII Art Archive
- Accessed: July 26, 2026

## Project Scope

The project is a console-based Java application.

The project does not include:

- A graphical user interface
- User authentication
- A database
- Advanced inventory reports
- Optional bonus features

## Current Project Status

The following features have been completed:

- Add standard products
- Add perishable products
- View inventory
- Search by product ID or product name
- Update product quantity and price
- Delete products with confirmation
- Save inventory to a text file
- Load inventory from a text file
- Recreate the correct product type when loading
- Input validation
- Exception handling
- Abstract class, inheritance, polymorphism, and interfaces
- Repository interface and file-based repository implementation
- Unit testing for `PerishableProduct`
- Unit testing for shared `Product` behavior
- Unit testing for `InventoryService`
- Unit testing for `FileInventoryRepository` save, load, and error-handling behavior
- Project planning documentation
- Class diagram
- Application flowchart
- Handwritten pseudocode documentation
- Javadoc comments for production classes
- Generated HTML Javadoc documentation

## Timeline and Milestones

| Date | Focus | Milestone |
|---|---|---|
| July 19 | Pre-assessment review and planning | Reviewed the rubric, identified requirements, and began handwritten pseudocode |
| July 20 | Continued preparation | Refined pseudocode, reviewed project expectations, and planned the initial class structure |
| July 21 | Official project start, setup, and core features | Created the project, product model, service, menu, and core CRUD workflow |
| July 22 | Advanced OOP and persistence | Added product hierarchy, interfaces, standard and perishable products, save, and load |
| July 23 | Documentation and stabilization | Added project status, pseudocode images, class diagram, flowchart, and documentation organization |
| July 24 | Architecture and code quality | Refactored persistence to code to an interface, added Javadocs, and generated HTML documentation |
| July 25 | Testing | Expanded model, service, and repository test coverage |
| July 26 | Final refinement | Updated documentation, completed repository testing, and refined console presentation |
| July 27 | Final review and presentation | Final application review, final push, demonstration practice, and code-defense preparation |