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

Each product will contain:

- Product ID
- Product name
- Quantity
- Price

## Data Structure

An ArrayList will store and manage multiple Product objects while the
application is running.

## Data Persistence

Inventory data will be saved to and loaded from a text or CSV file so that
products can remain available between program sessions.

## Planned Classes

- App
- Product
- PerishableProduct
- InventoryService
- InventoryRepository
- ConsoleIO or MainMenu

Additional abstract classes or interfaces may be introduced during the
advanced OOP stage.

## Project Scope

The project will be a console-based Java application.

The project will not include:

- A graphical user interface
- User authentication
- A database
- Advanced inventory reports
- Optional bonus features