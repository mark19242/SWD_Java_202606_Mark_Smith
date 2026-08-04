-- ============================================================
-- Exercise: Subqueries in SQL (Bookstore Edition)
-- ============================================================


-- ============================================================
-- Part 1: Using NOT EXISTS
-- ============================================================

-- Write a query to list all genre names that do not have any
-- associated books.

	SELECT
    g.genre_name
	FROM genres g
	WHERE NOT EXISTS (
    SELECT 1
    FROM books b
    WHERE b.genre_id = g.genre_id
	);

-- Answer: Poetry is the only genre with no associated books.


-- Bonus:
-- Write the equivalent query to list all authors who have no
-- associated books.

	SELECT
    a.author_id,
    a.first_name,
    a.last_name
	FROM authors a
	WHERE NOT EXISTS (
    SELECT 1
    FROM books b
    WHERE b.author_id = a.author_id
	);

-- Answer: Chinua Achebe, author ID 91, is the only author
-- with no associated books.



-- ============================================================
-- Part 2: Subqueries in the WHERE Clause
-- ============================================================

-- 1. Find the title(s) of the book(s) with the maximum price.

	SELECT
    title,
    price
	FROM books
	WHERE price = (
    SELECT MAX(price)
    FROM books
	);
	
	
-- Answer: The maximum book price is $19.99.
-- The books at that price are:
-- The Pillars of the Earth
-- War and Peace
-- Steve Jobs



-- 2. Write a query to list all book titles whose price is
-- greater than the average price across all books.

	SELECT title, price
	FROM books
	WHERE price > (
    SELECT AVG(price)
    FROM books
	)
	ORDER BY price DESC;
	
-- Answer: 49 books have a price greater than the average
-- book price of approximately $14.63.
-- Their prices range from $14.99 to $19.99.


-- ============================================================
-- Part 3: Subquery in the SELECT Clause
-- ============================================================

-- For each genre, show:
-- 1. The number of unique books in that genre.
-- 2. The total number of unique genres in the system
--    as a column in each row.

	SELECT
    g.genre_name,
    COUNT(DISTINCT b.book_id) AS unique_book_count,
    ( SELECT COUNT(DISTINCT genre_id)
        FROM genres ) 
	AS total_genre_count
	FROM genres g
	LEFT JOIN books b
    ON g.genre_id = b.genre_id
	GROUP BY
    g.genre_id,
    g.genre_name
	ORDER BY
    g.genre_name;
	
-- Answer: The query returned all 14 genres.
-- The total number of unique genres is 14.
-- Poetry has 0 associated books.
-- Fantasy, Fiction, Mystery, and Science Fiction have the
-- highest unique book count, with 10 books each.


-- ============================================================
-- Part 4: Subquery in the HAVING Clause
-- ============================================================

-- Write a query to return all staff IDs whose total sales amount
-- is less than the average individual sale amount across all
-- sales.

	SELECT
    staff_id,
    SUM(total) AS total_sales
	FROM sales
	GROUP BY staff_id
	HAVING SUM(total) < (
    SELECT AVG(total)
    FROM sales
	);
	
-- Answer: No staff members have total sales less than the
-- average individual sale amount across all sales.
-- The query returned 0 rows.



-- ============================================================
-- Part 5: Correlated Subquery
-- ============================================================

-- Return the top 10 authors based on total potential inventory
-- value:
--
-- price * stock_quantity
--
-- summed across each author's books, using a correlated
-- subquery.

	SELECT
    a.author_id,
    a.first_name,
    a.last_name,
    ( SELECT SUM(b.price * b.stock_quantity)
    FROM books b
    WHERE b.author_id = a.author_id ) 
	AS total_potential_inventory_value
	FROM authors a
	ORDER BY
    total_potential_inventory_value DESC NULLS LAST
	LIMIT 10;

-- Answer: The query returned the top 10 authors by total
-- potential inventory value.
--
-- 1. J.K. Rowling - $1,888.85
-- 2. Stephen King - $1,452.65
-- 3. George Orwell - $1,183.98
-- 4. Gillian Flynn - $936.90
-- 5. Jane Austen - $849.15
-- 6. Suzanne Collins - $839.40
-- 7. Yuval Noah Harari - $835.56
-- 8. Agatha Christie - $828.85
-- 9. Andy Weir - $799.50
-- 10. George R.R. Martin - $797.58
	