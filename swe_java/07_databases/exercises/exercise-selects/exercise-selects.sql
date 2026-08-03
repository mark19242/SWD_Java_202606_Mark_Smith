/* ------------------------------------------------------------------------------------ */
/* SELECT LITERAL VALUES */
/* ------------------------------------------------------------------------------------ */

-- 1. Write a SELECT query that uses the string All for one, and one for all.

SELECT 'All for one, and one for all' as phrase;

-- 2. Write a SELECT query that uses the CONCAT function to combine All for one,
--      and and one for all.

SELECT CONCAT('All for one,',' and one for all') as concat_phrase;

-- 3. Write a SELECT query that adds 6 and 6.

SELECT 6 + 6 as six_six_sum;

-- 4. Write a SELECT query that divides 5 by 2.

SELECT 5/2 as five_by_two;

-- 5. Write a SELECT query that divides 5.0 by 2.0.

SELECT 5.0 / 2.0 AS five_point_zero_div_two_point_zero;

-- 6. Write a SELECT query with 2 values - 6 divided by 4 and the remainder.

SELECT
    6 / 4 AS quotient,
    6 % 4 AS remainder;

-- 7. Write a SELECT query for 6 squared.

SELECT POWER(6, 2) AS six_squared;

/* ------------------------------------------------------------------------------------ */
/* SELECT AGAINST TABLES EXERCISE: ANSWERS INCLUDED AFTER THE SOURCE QUERY AS A COMMENT */
/* ------------------------------------------------------------------------------------ */

-- 1. Select all the rows from the Building table.

	SELECT * FROM building;

	-- ANSWER: 2 rows; 1 "Main Campus" and 2 "Vocational Tech Campus"

-- 2. What are the period name, start, and end times?

	SELECT period_name, start_time, end_time
	FROM period;

	-- ANSWER: 8 rows; 	"1st Period"	"08:00:00"	"08:45:00"
-- 						"2nd Period"	"09:00:00"	"09:45:00"
-- 						"3rd Period"	"10:00:00"	"10:45:00"
-- 						"4th Period"	"11:00:00"	"11:45:00"
-- 						"5th Period"	"12:00:00"	"12:45:00"
-- 						"6th Period"	"13:00:00"	"13:45:00"
-- 						"7th Period"	"14:00:00"	"14:45:00"
-- 						"8th Period"	"15:00:00"	"15:45:00"
	
-- 3. Which table is empty?

	SELECT
    schemaname AS schema_name,
    relname AS table_name,
    n_live_tup AS estimated_row_count
	FROM
	    pg_stat_user_tables
	WHERE
	    schemaname = 'public' -- Change 'public' to your specific schema name
	ORDER BY
	    n_live_tup DESC;

	-- VALIDATING SCHEMA QUERY

	SELECT COUNT(*) FROM building;
	SELECT COUNT(*) FROM course;
	SELECT COUNT(*) FROM grade;
	SELECT COUNT(*) FROM grade_item;
	SELECT COUNT(*) FROM grade_type;
	SELECT COUNT(*) FROM period;
	SELECT COUNT(*) FROM room;
	SELECT COUNT(*) FROM section;
	SELECT COUNT(*) FROM section_roster;
	SELECT COUNT(*) FROM semester;
	SELECT COUNT(*) FROM student;
	SELECT COUNT(*) FROM subject;
	SELECT COUNT(*) FROM teacher;		

	-- ANSWER: 13 rows; 0, all schema tables appear to have data.	

-- 4. List all courses and credit hours in the format: CourseName (CreditHours)

	SELECT course_name || ' (' || credit_hours || ')' as course_credits FROM course;

	-- ANSWER: 23 rows;
	-- "English 1 (3.00)"
	-- "English 2 (3.00)"
	-- "English 3 (3.00)"
	-- "AP English (3.00)"
	-- "Algebra 1 (4.00)"
	-- "Geometry (4.00)"
	-- "Algebra 2 (4.00)"
	-- "Statistics (3.00)"
	-- "Calculus (4.00)"
	-- "Biology (4.00)"
	-- "Chemistry (4.00)"
	-- "Anatomy (4.00)"
	-- "Geology (4.00)"
	-- "Art 1 (3.00)"
	-- "Art 2 (3.00)"
	-- "Choir (3.00)"
	-- "Band (3.00)"
	-- "Theater (3.00)"
	-- "European History (3.00)"
	-- "American History (3.00)"
	-- "African History (3.00)"
	-- "Middle Eastern History (3.00)"
	-- "Asian History (3.00)"

-- 5. What are the teachers' full names (first names and last initials) for the first five
--      teachers?

      SELECT CONCAT(first_name, ' ', LEFT(last_name, 1)) AS teacher_name
      FROM teacher
      LIMIT 5;
	  
-- ANSWER: 5 rows; 
-- 	"Michail E"
-- 	"Sybille S"
-- 	"Salomi A"
-- 	"Amil B"
-- 	"Dimitri D"


-- 6. How many rooms are there?
	SELECT COUNT(*) AS room_count
	FROM room;
	
-- ANSWER: 1 row; 13 rooms


-- 7. RoomNumber is an integer type. What is the range of room numbers? (Hint:
--      Consider BuildingID too.)

	SELECT
    building_id,
    MIN(room_number) AS minimum_room,
    MAX(room_number) AS maximum_room
	FROM room
	GROUP BY building_id;
	
-- ANSWER: 2 rows;
-- BuildingID 2: Room numbers range from 100 to 101.
-- BuildingID 1: Room numbers range from 100 to 204.
	
	


-- 8. Examine the Description field of the Room table. What are your observations of
--      the description field?
	
	SELECT room_number, description
	FROM room;
	
-- ANSWER: 13 rows;
-- Most room descriptions are NULL (empty).
-- Only two rooms have descriptions:
-- Building 1, Room 100: "Gymnasium"
-- Building 2, Room 100: "Chemistry Lab"

-- 9. How many unique SubjectIDs appear in the Course table?

	SELECT COUNT(DISTINCT subject_id) AS unique_subjects
	FROM course;
	
-- ANSWER: 1 row; There are 5 unique SubjectIDs.;


-- 10.How many grade types are there?
	
	SELECT COUNT(*) AS grade_type_count
	FROM grade_type;
	
-- ANSWER: 1 row; There are 4 grade types.
	

-- 11. What are the IDs and Names of the grade types? (Hint: Name the columns
--      appropriately in the ResultSet)
	
	SELECT
    grade_type_id AS id,
    grade_type_name AS name
	FROM grade_type;
	
	
-- ANSWER: 4 rows;
-- 1  "Homework"
-- 2  "Quiz"
-- 3  "Project"
-- 4  "Exam"
	
	
	
-- 12.What grade types appear in the GradeItem table?
	
	SELECT DISTINCT
    grade_type_id
	FROM grade_item;
	
-- ANSWER: 2 rows;
-- 3 "Project"
-- 4 "Exam"

	
	
-- 13.What grade types are not utilized in the GradeItem table? (Hint: You can't write a
--      query for this yet. You have to utilize the previous queries.)

	SELECT grade_type_name
	FROM grade_type
	WHERE grade_type_id NOT IN ( SELECT DISTINCT grade_type_id
    FROM grade_item );
	
-- ANSWER: 2 rows;
-- "Homework"
-- "Quiz"
