-- ==================================================
-- Exercise: Deleting SQL
-- ==================================================


SELECT *
FROM Product
ORDER BY ProductId;

-- Answer:
-- 6 rows returned.
-- ProductIds 101 through 106 are available.
-- The Product table was successfully seeded.


-- ==================================================
-- Part 1: Delete a Single Record
-- ==================================================

-- 1. Write a SELECT query to find the product
-- with the name 'Calendar 2023'.

SELECT *
FROM Product
WHERE ProductName = 'Calendar 2023';

-- Answer:
-- 1 row returned.
-- ProductId 103 is 'Calendar 2023'.
-- Its CategoryId is 2 and its price is $9.99.


-- 2. Remove 'Calendar 2023' from the Product table.

-- Use the primary key after confirming the record.
DELETE FROM Product
WHERE ProductId = 103;

-- Answer:
-- DELETE 1
-- One product record was deleted.


-- Verify that Calendar 2023 was removed.

SELECT *
FROM Product
WHERE ProductId = 103;

-- Expected Answer:
-- 0 rows returned.
-- 'Calendar 2023' was successfully deleted.


-- ==================================================
-- Part 2: Delete Multiple Records
-- ==================================================

-- 3. Preview all remaining products
-- in the 'Stationery' category.

SELECT *
FROM Product
WHERE CategoryId = 2
ORDER BY ProductId;

-- Answer:
-- 2 rows returned.
-- ProductId 101 is 'Notebook'.
-- ProductId 102 is 'Pen Set'.
-- Both products belong to the Stationery category.


-- 4. Delete all products in the Stationery category.

DELETE FROM Product
WHERE CategoryId = 2;

-- Answer:
-- DELETE 2
-- Two Stationery product records were deleted.


-- Verify that no Stationery products remain.

SELECT *
FROM Product
WHERE CategoryId = 2;

-- Expected Answer:
-- 0 rows returned.
-- No products remain in the Stationery category.


-- ==================================================
-- Part 3: Handle Foreign Key Dependencies
-- ==================================================

-- 5. Try to delete the 'Books' category.
--
-- IMPORTANT:
-- Run this DELETE by itself so we can capture
-- the exact PostgreSQL error message.

DELETE FROM ProductCategory
WHERE CategoryId = 1;

-- Answer:
-- The DELETE failed because products still reference
-- CategoryId 1 in the Product table.

-- PostgreSQL error message:
-- ERROR: update or delete on table "productcategory" violates foreign key
-- constraint "product_categoryid_fkey" on table "product"
-- SQL state: 23503
-- Detail: Key (categoryid)=(1) is still referenced from table "product".


SELECT *
FROM ProductCategory
WHERE CategoryId = 1;

-- Answer:
-- 1 row returned.
-- CategoryId 1 is the Books category.
-- The Books category was not deleted because
-- it is still referenced by Product records.


-- ==================================================
-- Part 3, Question 6:
-- Safely Remove the Books Category
-- ==================================================

-- Preview the products that depend on CategoryId 1.

SELECT *
FROM Product
WHERE CategoryId = 1
ORDER BY ProductId;

-- Answer:
-- 2 rows returned.
-- ProductId 104 is 'Mystery Novel'.
-- ProductId 105 is 'Classic Fiction'.
-- Both products reference the Books category.


DELETE FROM Product
WHERE CategoryId = 1;

-- Answer:
-- DELETE 2
-- Two Books product records were deleted.


SELECT *
FROM Product
WHERE CategoryId = 1;

-- Answer:
-- 0 rows returned.
-- No Product records reference CategoryId 1.


DELETE FROM ProductCategory
WHERE CategoryId = 1;

-- Answer:
-- DELETE 1
-- The Books category was successfully deleted.


SELECT *
FROM ProductCategory
WHERE CategoryId = 1;

-- Answer:
-- 0 rows returned.
-- The Books category was successfully removed.


-- ==================================================
-- Part 4: Bonus Challenge
-- ==================================================

-- 7. Delete any category only if no products
-- are currently assigned to it.

SELECT *
FROM ProductCategory AS pc
WHERE NOT EXISTS (
    SELECT 1
    FROM Product AS p
    WHERE p.CategoryId = pc.CategoryId
);

-- Answer:
-- 1 row returned.
-- CategoryId 2 is the Stationery category.
-- Stationery has no products assigned to it.
-- Clearance was not returned because 'Sticker Pack'
-- is still assigned to that category.



DELETE FROM ProductCategory AS pc
WHERE NOT EXISTS (
    SELECT 1
    FROM Product AS p
    WHERE p.CategoryId = pc.CategoryId
);

-- Answer:
-- DELETE 1
-- The empty Stationery category was successfully deleted.
-- The Clearance category was not deleted because
-- 'Sticker Pack' is still assigned to it.


-- ==================================================
-- Final Verification
-- ==================================================

-- Display all remaining products and categories.

SELECT
    p.ProductId,
    p.ProductName,
    pc.CategoryName,
    p.Price
FROM Product AS p
INNER JOIN ProductCategory AS pc
    ON p.CategoryId = pc.CategoryId
ORDER BY p.ProductId;

-- Answer:
-- 1 row returned.
-- ProductId 106 is 'Sticker Pack'.
-- It remains in the Clearance category with a price of $2.99.


-- Display all remaining categories.

SELECT *
FROM ProductCategory
ORDER BY CategoryId;

-- Answer:
-- 1 row returned.
-- CategoryId 3 is the Clearance category.
-- Clearance is the only remaining category.