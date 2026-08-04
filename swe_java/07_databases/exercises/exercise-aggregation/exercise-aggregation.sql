/* -------------------------------------------------------------------------- */
/* Exercise: Aggregation in SQL                                               */
/* -------------------------------------------------------------------------- */

/* -------------------------------------------------------------------------- */
/* Part 1: Book Pricing Summary                                               */
/* -------------------------------------------------------------------------- */

-- 1. Write a query to calculate the minimum, maximum, and average book price.
	SELECT
    MIN(price) AS minimum_price,
    MAX(price) AS maximum_price,
    AVG(price) AS average_price
	FROM books;
	
-- ANSWER: 1 row;
-- min_price: 9.99
-- max_price: 19.99
-- avg_price: 14.63


-- 2. Group books by GenreName and find the average price per genre.

	SELECT
    g.genre_name,
    CAST(AVG(b.price) AS NUMERIC(12,2)) AS avg_price
	FROM genres g
	JOIN books b
    ON g.genre_id = b.genre_id
	GROUP BY g.genre_name
	ORDER BY g.genre_name;
	
-- ANSWER: 13 rows;
-- "Biography"            16.62
-- "Classic Literature"   13.68
-- "Dystopian"            13.13
-- "Fantasy"              16.04
-- "Fiction"              14.74
-- "Historical Fiction"   16.74
-- "Horror"               13.99
-- "Mystery"              14.39
-- "Non-Fiction"          15.57
-- "Romance"              12.56
-- "Science Fiction"      15.89
-- "Thriller"             14.49
-- "Young Adult"          12.41

-- 3. Filter to show only genres where the average price exceeds $15.

	SELECT
    g.genre_name,
    AVG(b.price) AS average_price
	FROM books b
	JOIN genres g
    ON b.genre_id = g.genre_id
	GROUP BY g.genre_name
	HAVING AVG(b.price) > 15;
	
	
-- ANSWER: 5 rows;
-- "Biography"            16.62
-- "Fantasy"              16.04
-- "Historical Fiction"   16.74
-- "Non-Fiction"          15.57
-- "Science Fiction"      15.89



/* -------------------------------------------------------------------------- */
/* Part 2: Staff Sales Performance                                            */
/* -------------------------------------------------------------------------- */

-- 1. Count the total number of sales made by each staff member.

	SELECT
    staff_id,
    COUNT(*) AS total_sales
	FROM sales
	GROUP BY staff_id;
	
-- ANSWER: 4 rows;
-- 3   3
-- 6   4
-- 2   3
-- 1   4



-- 2. Calculate total sales value per staff using SUM.

	SELECT
    staff_id,
    SUM(total) AS total_sales_value
	FROM sales
	GROUP BY staff_id;
	
-- ANSWER: 4 rows;
-- 3   629.99
-- 6   1184.99
-- 2   1055.70
-- 1   965.75

-- 3. Show only staff whose sales exceed $800.
	
	SELECT
    staff_id,
    SUM(total) AS total_sales_value
	FROM sales
	GROUP BY staff_id
	HAVING SUM(total) > 800;
	
-- ANSWER: 3 rows;
-- 6   1184.99
-- 2   1055.70
-- 1    965.75



-- 4. Sort staff by total sales descending.


	SELECT
    staff_id,
    SUM(total) AS total_sales_value
	FROM sales
	GROUP BY staff_id
	ORDER BY total_sales_value DESC;
	
-- ANSWER: 4 rows;
-- 6   1184.99
-- 2   1055.70
-- 1    965.75
-- 3    629.99


/* -------------------------------------------------------------------------- */
/* Part 3: Staff Hire Summary                                                 */
/* -------------------------------------------------------------------------- */

-- 1. Group staff by HireDate and return a comma-separated list of
--    last names hired on each date.
	SELECT
    hire_date,
    STRING_AGG(last_name, ', ') AS staff_members
	FROM staff
	GROUP BY hire_date;
	
	-- ANSWER: 4 rows;
-- 2020-01-01   "Nguyen, Patel"
-- 2022-06-10   "Johnson"
-- 2023-02-01   "Kim"
-- 2021-03-15   "Smith, Garcia"
