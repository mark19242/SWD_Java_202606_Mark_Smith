# Inventory Manager Project Plan

## Project Purpose

The Inventory Manager is a Java console application that allows a user to
manage products stored in an inventory.

## User

The application will have one general inventory user who can manage all
products. The project will not include separate user roles or permissions.

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

An ArrayList will store and manage multiple Product objects while the
application is running.

## Data Persistence

Inventory data will be saved to and loaded from a text or CSV file so that
products can remain available between program sessions.


## Project Classes

- App
- MainMenu
- Product (Abstract)
- StandardProduct
- PerishableProduct
- Expirable (Interface)
- InventoryService
- InventoryRepository

These classes separate user interaction, business logic, product models,
and file persistence to keep the project organized and maintainable.

## Project Scope

The project will be a console-based Java application.

The project will not include:

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
- Update quantity and price
- Delete products with confirmation
- Save inventory to a text file
- Load inventory from a text file
- Input validation
- Exception handling
- Unit testing for `PerishableProduct`
- Project planning documentation
- Class diagram
- Application flowchart