-- 1. WriteaSELECTquerythatgetsallcoursenames,hours,andsubjectnames where the subject name is "History," without table aliases, using the INNER keyword, and ordered by course name.

	SELECT
    course.course_name,
    course.credit_hours,
    subject.subject_name
	FROM course
	INNER JOIN subject
    ON course.subject_id = subject.subject_id
	WHERE subject.subject_name = 'History'
	ORDER BY course.course_name;
	
-- Answer:
-- This query joins the course and subject tables using subject_id.
-- It filters the results to only courses in the "History" subject.
-- The query does not use table aliases and includes the INNER keyword.
-- The results are ordered alphabetically by course_name.
--
-- Results:
-- African History
-- American History
-- Asian History
-- European History
-- Middle Eastern History
--
-- Total rows returned: 5

-- 2. WriteaSELECTquerythatgetsallcoursenames,hours,andsubjectnames where the subject name is "History," with table aliases, without the INNER keyword, and ordered by course name.

	SELECT
    c.course_name,
    c.credit_hours,
    s.subject_name
	FROM course c
	JOIN subject s
    ON c.subject_id = s.subject_id
	WHERE s.subject_name = 'History'
	ORDER BY c.course_name;
	
-- Answer:
-- This query joins the course and subject tables using subject_id.
-- It uses the table aliases c for course and s for subject.
-- The INNER keyword is omitted, but JOIN still performs an inner join.
-- The results are filtered to the "History" subject
-- and ordered alphabetically by course_name.
--
-- Results:
-- African History
-- American History
-- Asian History
-- European History
-- Middle Eastern History
--
-- Total rows returned: 5

-- 3. WriteaSELECTquerythatgetsallcoursenames,hours,andsubjectnames where the subject name is "History," with table aliases, using the INNER keyword, and ordered by course name.

	SELECT
    c.course_name,
    c.credit_hours,
    s.subject_name
	FROM course c
	INNER JOIN subject s
    ON c.subject_id = s.subject_id
	WHERE s.subject_name = 'History'
	ORDER BY c.course_name;

-- Answer:
-- This query joins the course and subject tables using subject_id.
-- It uses the table aliases c for course and s for subject.
-- The INNER keyword is included explicitly.
-- The results are filtered to the "History" subject
-- and ordered alphabetically by course_name.
--
-- Results:
-- African History
-- American History
-- Asian History
-- European History
-- Middle Eastern History
--
-- Total rows returned: 5


-- 4. WriteaSELECTquerythatgetsallcoursenames,hours,andsubjectnames where the subject name contains the word "Art" anywhere in the name, ordered by subject name then course name.

	SELECT
    c.course_name,
    c.credit_hours,
    s.subject_name
	FROM course c
	JOIN subject s
    ON c.subject_id = s.subject_id
	WHERE s.subject_name 
	LIKE '%Art%'
	ORDER BY
    s.subject_name,
    c.course_name;
	
-- Answer:
-- This query joins the course and subject tables using subject_id.
-- LIKE '%Art%' finds subject names containing "Art" anywhere in the name.
-- The results include courses from both Arts and Language Arts.
-- The results are ordered by subject_name first
-- and then alphabetically by course_name.
--
-- Total rows returned: 9


-- 5. WriteaSELECTquerythatgetsallroomnumbers,descriptions,andbuilding names for rooms missing description information.
	
	SELECT
    r.room_number,
    r.description,
    b.building_name
	FROM room r
	JOIN building b
    ON r.building_id = b.building_id
	WHERE r.description IS NULL;
	
-- Answer:
-- This query joins the room and building tables using building_id.
-- IS NULL filters the results to rooms with missing descriptions.
-- The query returns each room number, its NULL description,
-- and the name of the building where the room is located.
--
-- Total rows returned: 11


-- 6. Getallthecoursenamesthataremorethanthreecredithoursfortheteacher named "Geno Booy."

	SELECT
    c.course_name
	FROM teacher t
	JOIN section s
    ON t.teacher_id = s.teacher_id
	JOIN course c
    ON s.course_id = c.course_id
	WHERE t.first_name = 'Geno'
    AND t.last_name = 'Booy'
    AND c.credit_hours > 3;
	
-- Answer:
-- This query joins the teacher, section, and course tables.
-- The section table acts as the bridge between teachers and courses.
-- The results are filtered to the teacher named "Geno Booy"
-- and courses worth more than three credit hours.
--
-- Results:
-- Anatomy
-- Chemistry
--
-- Total rows returned: 2
