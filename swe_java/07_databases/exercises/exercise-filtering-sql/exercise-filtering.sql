/* -------------------------------------------------------------------------- */
/* Exercise: Filtering SQL                                                    */
/* -------------------------------------------------------------------------- */

-- 1. Write a SELECT query that gets the StudentID, LastName, and FirstName
--    of all Students with a LastName that starts with "Cr".

SELECT student_id, last_name, first_name
FROM student
WHERE LEFT(last_name, 2) = 'Cr';

-- ANSWER: 2 rows;
-- 36  "Crutchfield"  "Marci"
-- 50  "Crooks"       "Baudoin"


-- 2. Write a SELECT query that gets all Courses with one of the following
--    SubjectIDs: 1, 2, 4. Use the OR keyword.

SELECT *
FROM course
WHERE subject_id = 1
   OR subject_id = 2
   OR subject_id = 4;
   
   -- ANSWER: 14 rows;
-- 1   1   "English 1"     3.00
-- 2   1   "English 2"     3.00
-- 3   1   "English 3"     3.00
-- 4   1   "AP English"    3.00
-- 5   2   "Algebra 1"     4.00
-- 6   2   "Geometry"      4.00
-- 7   2   "Algebra 2"     4.00
-- 8   2   "Statistics"    3.00
-- 9   2   "Calculus"      4.00
-- 14  4   "Art 1"         3.00
-- 15  4   "Art 2"         3.00
-- 16  4   "Choir"         3.00
-- 17  4   "Band"          3.00
-- 18  4   "Theater"       3.00


-- 3. Write a SELECT query that gets all Courses with one of the following
--    SubjectIDs: 1, 2, 4. Use the IN keyword.

SELECT *
FROM course
WHERE subject_id IN (1, 2, 4);

-- ANSWER: 14 rows;
-- 1   1   "English 1"     3.00
-- 2   1   "English 2"     3.00
-- 3   1   "English 3"     3.00
-- 4   1   "AP English"    3.00
-- 5   2   "Algebra 1"     4.00
-- 6   2   "Geometry"      4.00
-- 7   2   "Algebra 2"     4.00
-- 8   2   "Statistics"    3.00
-- 9   2   "Calculus"      4.00
-- 14  4   "Art 1"         3.00
-- 15  4   "Art 2"         3.00
-- 16  4   "Choir"         3.00
-- 17  4   "Band"          3.00
-- 18  4   "Theater"       3.00



-- 4. Write a SELECT query that gets the Student record with an ID of 42.

SELECT *
FROM student
WHERE student_id = 42;
-- 42  "Eal"  "Morphew"  "2026"


-- 5. Write a SELECT query that gets the Student FirstNames that start
--    with "C" using LIKE.

SELECT first_name
FROM student
WHERE first_name LIKE 'C%';

-- ANSWER: 5 rows;
-- "Carlita"
-- "Chiquita"
-- "Cecily"
-- "Clemens"
-- "Corrie"


-- 6. Write a SELECT query that gets the Student FirstNames that start
--    with "Ce" using BETWEEN.

SELECT first_name
FROM student
WHERE first_name BETWEEN 'Ce' AND 'Cf';

-- ANSWER: 1 row;
-- "Cecily"


-- 7. Write a SELECT query that gets the first 10 unique Student LastNames.

SELECT DISTINCT last_name
FROM student
LIMIT 10;

-- ANSWER: 10 rows;
-- "Hysom"
-- "Hustler"
-- "Sandilands"
-- "Tomczynski"
-- "Langrish"
-- "Snow"
-- "Rigolle"
-- "De Brett"
-- "Lamblot"
-- "Van Halen"



-- 8. Write a SELECT query that returns the first 10 Student records.

SELECT *
FROM student
LIMIT 10;

-- ANSWER: 10 rows;
-- 1   "Carlita"   "Charon"     "2025"
-- 2   "Deborah"   "Lowing"     "2027"
-- 3   "Rennie"    "Fitzjohn"   "2026"
-- 4   "Pam"       "Ellicott"   "2024"
-- 5   "Gisella"   "Daveren"    "2028"
-- 6   "Hazlett"   "Wickson"    "2027"
-- 7   "Jaquelin"  "Van Halen"  "2025"
-- 8   "Loralyn"   "Casaccia"   "2027"
-- 9   "Hailey"    "Aiton"      "2027"
-- 10  "Melonie"   "McAdam"     "2024"


-- 9. Write a SELECT query that returns the top five Students in reverse
--    alphabetical order by LastName.

SELECT *
FROM student
ORDER BY last_name DESC
LIMIT 5;

-- ANSWER: 5 rows;
-- 6   "Hazlett"   "Wickson"     "2027"
-- 22  "Jocelyne"  "Walak"       "2023"
-- 7   "Jaquelin"  "Van Halen"   "2025"
-- 34  "Elwin"     "Truss"       "2023"
-- 18  "Fletcher"  "Tomczynski"  "2023"