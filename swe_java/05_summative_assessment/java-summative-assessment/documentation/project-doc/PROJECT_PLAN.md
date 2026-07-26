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