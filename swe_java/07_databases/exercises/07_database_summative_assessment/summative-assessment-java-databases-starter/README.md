# Summative Assessment: Java Databases

*Note: These instructions are based on the reference document "Summative Assessment_ Java Databases.pdf".*

## 1. Introduction
You are tasked with implementing and testing the database connectivity portions of a partially written restaurant management application. This application allows users to enter complete orders and payment information, review past orders, and edit orders.

## 2. Starter Files
- **Entity Relationship Diagram (ERD):** Represents the database schema for the application.
- **SQL Scripts:** Provided to create and populate the `simple_bistro` database.
- **Starter Project:** Contains the UI, POJO classes for database entities, and interfaces for each required repository.

## 3. Startup & Environment Setup
Before beginning development or running tests, complete the following setup steps:
1. Create the `simple_bistro` database.
2. Run the `set_known_good_state_postgres.sql` script located in the `test/sql` folder in the `simple_bistro` database.
3. Create the `simple_bistro_test` database.
4. Go to `src/main/resources` and modify the `spring.datasource.username=[UNAME]` and `spring.datasource.password=[PWD]` lines to use the proper username and password for your server.
5. Go to `src/test/org.example.service/BaseJdbcTest` and modify the DB_USER andDB_PASSWORD variables to use the proper username and password for your server.

## 4. Core Requirements
You need to provide the following components:
- **Repository Implementations:** Implement the provided database interfaces located in the `data` package. Comments are provided in the code to guide you on non-obvious methods.
- **Mappers:** Create mappers for each table/object produced by the repository queries.
- **SQL Query Planning:** Before fully implementing the Java interfaces, plan and test your queries in separate SQL files organized by repository name (e.g., `Order.sql`).
- **Unit Tests:** Write comprehensive unit tests for the implemented interfaces.

## 5. Testing Setup & Configuration
To properly test this application, a dedicated test environment is required:
1. **Test Database:** Set up a test database named `simple_bistro_test`.
2. **Properties Configuration:** Update the `application.properties` files in both `main/resources` and `test/resources` to point to your specific database, username, and password.
3. **Database Reset Strategy:**
    - Locate the stored procedures in the `test/sql` directory.
    - Run the appropriate procedure inside your `simple_bistro_test` database to install it.
    - Call this stored procedure *before each test* to reset the data to its original state, ensuring all tests run independently.
4. **Handling Dates:** *Important Note:* Date comparisons in unit tests may fail due to timezone differences between your database server and OS. If you encounter this, you may omit date comparisons in your unit tests.

## 6. Deliverables
Ensure the following are included in your final submission:
- [ ] **Project Journal/Log:** Documenting your process.
- [ ] **SQL Files:** Containing queries developed for each repository (one file per repository).
- [ ] **Java Source Code:** Fully implemented data repositories and JUnit tests.
- [ ] **Mappers:** Created for each object.
- [ ] **Exception Handling:** Repositories must throw the custom exceptions specified in their interface definitions.
- [ ] **Maven Configuration:** Ensure Maven (`pom.xml`) is correctly configured for PostgreSQL.
- [ ] **Code Styling:** Java code must strictly follow standard styling conventions regarding capitalization, naming, and indentation.

## 7. Conclusion
Once implementations are complete, run the application to verify its functionality. Completing this project will successfully demonstrate your ability to connect to and manage a database using Java.