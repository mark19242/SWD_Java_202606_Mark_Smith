-- Part 1: Case Sensitivity & Case Formatting
-- 1. Write a query that standardizes the full customer name in Title Case (e.g., "John Doe").
-- Part 1: Case Sensitivity & Case Formatting
-- 1. Write a query that standardizes the full customer name
--    in Title Case (e.g., "John Doe").
	SELECT
    CONCAT(
    UPPER(LEFT(first_name, 1)),
    LOWER(SUBSTRING(first_name, 2)),
    ' ',
    UPPER(LEFT(last_name, 1)),
    LOWER(SUBSTRING(last_name, 2))) 
	AS customer_name
	FROM customers;
	
	-- Answer:
-- The query combines the customer's first and last names.
-- UPPER() capitalizes the first letter of each name.
-- LOWER() changes the remaining letters to lowercase.
--
-- Expected results:
-- John Doe
-- Sarah Smith
	
-- 2. Determinewhetheryourdatabaseiscase-sensitiveornot:
-- Try filtering using: SELECT * FROM customers WHERE First_Name = 'JOHN';
	
	SELECT *
	FROM customers
	WHERE first_name = 'JOHN';
	
-- Answer:
-- The query returned 0 rows.
-- The stored value is "john", but the query searched for "JOHN".
-- Therefore, the text comparison is case-sensitive.

-- Part 2: Casting
-- 1. Identify which columns need casting in the orders table.

-- Part 2: Casting

-- 1. Identify which columns need casting in the orders table.

-- Answer:
-- The order_total column needs casting because it is stored as VARCHAR,
-- even though it contains monetary values.
-- It should be cast to DECIMAL(10,2) before performing numeric operations
-- or applying numeric formatting.

-- 2. Writeaquerythatcastsorder_totalfromVARCHARtoDECIMAL(10,2)and
-- returns the result as formatted_total.
	SELECT
    order_total,
    CAST(order_total AS DECIMAL(10,2)) AS formatted_total
	FROM orders;
	
-- Answer:
-- CAST() explicitly converts order_total from VARCHAR
-- to DECIMAL(10,2).
-- The value 1234.5 is displayed as 1234.50,
-- confirming that the cast was successful.

-- Part 3: Formatting
-- 1. Format order_date to 'Month DD, YYYY' (e.g., "July 01, 2025").

	SELECT
    order_date,
    TO_CHAR(order_date, 'FMMonth DD, YYYY') AS formatted_order_date
	FROM orders;
	
-- Answer:
-- TO_CHAR() formats order_date into a more readable date.
-- FMMonth displays the full month name without extra spaces.
-- DD displays the day using two digits.
-- YYYY displays the four-digit year.


-- 2. Format order_total to include two decimal places
--    and a thousands separator.

	SELECT
    order_total,
    TO_CHAR(
     CAST(order_total AS DECIMAL(10,2)),
        'FM999,999,990.00') 	
	AS formatted_order_total
	FROM orders;

-- Answer:
-- CAST() converts order_total from VARCHAR to DECIMAL(10,2).
-- TO_CHAR() adds the thousands separator and displays two decimal places.

-- Part 4: Combined Query – Business Report
-- Using JOIN, write a final report query that returns:

-- ● Customer full name (in Title Case)
-- ● Formatted order date
-- ● Formatted order total with two decimal places and commas
-- Expected Columns:
-- ● customer_name
-- ● formatted_order_date ● formatted_order_total
-- Reflection Questions
-- ● What potential issues could arise from leaving order_total as a string?
-- ● How does your database's case sensitivity impact query design?
-- ● What are the risks of relying solely on implicit casting?
-- ● Why is formatting critical in customer-facing reports?
-- Extension Challenge (Optional)
-- Add logic to:
-- ● Pad customer IDs to 6 digits (e.g., 000101)
-- ● Trim whitespace if added intentionally to names or totals

SELECT
    CONCAT(
        UPPER(LEFT(c.first_name, 1)),
        LOWER(SUBSTRING(c.first_name, 2)),
        ' ',
        UPPER(LEFT(c.last_name, 1)),
        LOWER(SUBSTRING(c.last_name, 2))
    ) AS customer_name,

    TO_CHAR(
        o.order_date,
        'FMMonth DD, YYYY'
    ) AS formatted_order_date,

    TO_CHAR(
        CAST(o.order_total AS DECIMAL(10,2)),
        'FM999,999,990.00'
    ) AS formatted_order_total

FROM customers c
JOIN orders o
    ON c.customer_id = o.customer_id;

-- Answer:
-- The JOIN matches each customer with their order using customer_id.
-- The customer names are displayed in Title Case.
-- The order dates are formatted as Month DD, YYYY.
-- The order totals are cast to DECIMAL and formatted with
-- commas and two decimal places.


