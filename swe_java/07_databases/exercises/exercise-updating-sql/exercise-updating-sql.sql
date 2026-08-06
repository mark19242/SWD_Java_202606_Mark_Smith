-- ============================================
-- Exercise: Updating SQL
-- ============================================


-- ============================================
-- Part 1: Update a Single Record
-- ============================================

-- 1. Update the price of 'Notebook' to $6.25.

-- Preview the record before updating.
	SELECT *
	FROM Product
	WHERE ProductId = 101;
	
-- Answer:
-- 1 row returned.
-- ProductId 101 is 'Notebook', and its current price is $5.00.

-- Update the price.
	UPDATE Product
	SET Price = 6.25
	WHERE ProductId = 101;
	
-- Answer:
-- UPDATE 1
-- One record was updated.


-- Verify the update.
	SELECT *
	FROM Product
	WHERE ProductId = 101;
	
-- Answer:
-- 1 row returned.
-- The price of 'Notebook' was successfully updated to $6.25.


-- ============================================
-- Part 2: Update Multiple Columns
-- ============================================

-- 2. Update 'Pen Set' to:
-- New name: 'Executive Pen Set'
-- New price: $8.99

-- Preview the record before updating.
	SELECT *
	FROM Product
	WHERE ProductId = 102;
	
-- Answer:
-- 1 row returned.
-- ProductId 102 is 'Pen Set', and its current price is $7.50.

-- Update the product name and price.
	UPDATE Product
	SET ProductName = 'Executive Pen Set',
    Price = 8.99
	WHERE ProductId = 102;
	
-- Answer:
-- UPDATE 1
-- One record was updated.

-- Verify the update.
	SELECT *
	FROM Product
	WHERE ProductId = 102;

-- Answer:
-- 1 row returned.
-- ProductId 102 is now named 'Executive Pen Set',
-- and its price was successfully updated to $8.99.

-- ============================================
-- Part 3: Update Multiple Records
-- ============================================

-- 3. Set the EndDate to '2024-12-31' for all
-- products in the 'Stationery' category.

-- Preview all Stationery products.
	SELECT *
	FROM Product
	WHERE CategoryId = 2;
	
-- Answer:
-- 3 rows returned.
-- ProductIds 101, 102, and 103 belong to the Stationery category.
-- All three products currently have an EndDate of NULL.
	

-- Update all Stationery products.
	UPDATE Product
	SET EndDate = DATE '2024-12-31'
	WHERE CategoryId = 2;
	
-- Answer:
-- UPDATE 3
-- Three Stationery records were updated.

-- Verify the updated records.
	SELECT *
	FROM Product
	WHERE CategoryId = 2;

-- Answer:
-- 3 rows returned.
-- The EndDate for all three Stationery products
-- was successfully updated to 2024-12-31.


-- ============================================
-- Part 4: Handle Foreign Key Relationships
-- ============================================

-- 4A. Try to update the CategoryId of
-- 'Calendar 2023' to a value that does not exist.

-- Preview the record.
	SELECT *
	FROM Product
	WHERE ProductId = 103;
	
-- Answer:
-- 1 row returned.
-- ProductId 103 is 'Calendar 2023'.
-- Its current CategoryId is 2, and its EndDate is 2024-12-31.


-- Attempt to assign a CategoryId that does not exist.
	
	UPDATE Product
	SET CategoryId = 999
	WHERE ProductId = 103;

-- Answer:
-- The UPDATE failed because CategoryId 999 does not exist
-- in the ProductCategory table.
--
-- PostgreSQL error message:
-- ERROR: insert or update on table "product" violates foreign key
-- constraint "product_categoryid_fkey"
-- SQL state: 23503
-- Detail: Key (categoryid)=(999) is not present in table
-- "productcategory".


-- 4B. Update 'Calendar 2023' to the
-- 'Clearance' category instead.

	UPDATE Product
	SET CategoryId = 3
	WHERE ProductId = 103;
	
-- Answer:
-- UPDATE 1
-- One record was successfully updated.

-- Verify the valid update.
	SELECT *
	FROM Product
	WHERE ProductId = 103;
	
-- Answer:
-- 1 row returned.
-- 'Calendar 2023' was successfully moved from CategoryId 2
-- to CategoryId 3, which represents the 'Clearance' category.


-- ============================================
-- Part 5: Bonus Challenge
-- ============================================

-- 5. In one UPDATE statement, change
-- 'Classic Fiction' to:
-- New name: 'Vintage Novel'
-- New category: 'Clearance'
-- New price: $10.00

-- Preview the record.
SELECT *
FROM Product
WHERE ProductId = 105;

-- Answer:
-- 1 row returned.
-- ProductId 105 is currently named 'Classic Fiction'.
-- Its current CategoryId is 1, and its price is $12.50.

-- Update all three columns.
UPDATE Product
SET ProductName = 'Vintage Novel',
    CategoryId = 3,
    Price = 10.00
WHERE ProductId = 105;

-- Answer:
-- UPDATE 1
-- One record was successfully updated.

-- Verify the update.
SELECT *
FROM Product
WHERE ProductId = 105;


-- Answer:
-- 1 row returned.
-- ProductId 105 was successfully renamed to 'Vintage Novel'.
-- Its CategoryId was updated to 3, representing 'Clearance',
-- and its price was updated to $10.00.

-- ============================================
-- Final Verification
-- ============================================

-- Display every product with its category name.
SELECT
    p.ProductId,
    p.ProductName,
    pc.CategoryName,
    p.Price,
    p.EndDate
FROM Product AS p
INNER JOIN ProductCategory AS pc
    ON p.CategoryId = pc.CategoryId
ORDER BY p.ProductId;

-- Answer:
-- 5 rows returned.
-- ProductId 101, 'Notebook', has a price of $6.25
-- and an EndDate of 2024-12-31.
-- ProductId 102 was renamed to 'Executive Pen Set',
-- has a price of $8.99, and an EndDate of 2024-12-31.
-- ProductId 103, 'Calendar 2023', was moved to the
-- 'Clearance' category and kept its EndDate of 2024-12-31.
-- ProductId 104, 'Mystery Novel', remained unchanged.
-- ProductId 105 was renamed to 'Vintage Novel',
-- moved to the 'Clearance' category, and updated to $10.00.