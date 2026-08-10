-- ==================================================
-- Task 1: Create a Simple Stored Procedure
-- Active customer = customer with an order
-- within the last 30 days.
-- ==================================================

DROP PROCEDURE IF EXISTS get_active_customers(refcursor);

CREATE PROCEDURE get_active_customers(INOUT active_customers refcursor)
LANGUAGE plpgsql
AS $$
BEGIN
    OPEN active_customers FOR
        SELECT c.*
        FROM customer c
        WHERE EXISTS (
            SELECT 1
            FROM customerorder co
            WHERE co.customerid = c.customerid
              AND co.orderdate >= CURRENT_DATE - INTERVAL '30 days'
        );
END;
$$;

BEGIN;

CALL get_active_customers('active_customers');

FETCH ALL FROM active_customers;

COMMIT;

-- ==================================================
-- Task 2: Create a Stored Procedure
-- with a Single Parameter
-- ==================================================

DROP PROCEDURE IF EXISTS get_customer_details(integer, refcursor);

CREATE PROCEDURE get_customer_details(
    IN customer_id_in INT,
    INOUT customer_details refcursor
)
LANGUAGE plpgsql
AS $$
BEGIN
    OPEN customer_details FOR
        SELECT *
        FROM customer
        WHERE customerid = customer_id_in;
END;
$$;

BEGIN;

CALL get_customer_details(1, 'customer_details');

FETCH ALL FROM customer_details;

COMMIT;

-- ==================================================
-- Task 3: Create a Stored Procedure
-- with Multiple Parameters
-- ==================================================

DROP PROCEDURE IF EXISTS get_customer_info(
    integer,
    character varying,
    refcursor
);

CREATE PROCEDURE get_customer_info(
    IN customer_id_in INT,
    IN customer_name_in VARCHAR(255),
    INOUT customer_info refcursor
)
LANGUAGE plpgsql
AS $$
BEGIN
    OPEN customer_info FOR
        SELECT *
        FROM customer
        WHERE customerid = customer_id_in
          AND customername = customer_name_in;
END;
$$;


BEGIN;

CALL get_customer_info(
    1,
    'Emma Rivera',
    'customer_info'
);

FETCH ALL FROM customer_info;

COMMIT;

-- ==================================================
-- Task 4: Modify a Stored Procedure
-- Active customer = customer with an order
-- within the last 30 days.
-- ==================================================

DROP PROCEDURE IF EXISTS get_customer_details(integer, refcursor);

CREATE PROCEDURE get_customer_details(
    IN customer_id_in INT,
    INOUT customer_details refcursor
)
LANGUAGE plpgsql
AS $$
BEGIN
    OPEN customer_details FOR
        SELECT c.*
        FROM customer c
        WHERE c.customerid = customer_id_in
          AND EXISTS (
              SELECT 1
              FROM customerorder co
              WHERE co.customerid = c.customerid
                AND co.orderdate >= CURRENT_DATE - INTERVAL '30 days'
          );
END;
$$;

BEGIN;

CALL get_customer_details(
    1,
    'customer_details'
);

FETCH ALL FROM customer_details;

COMMIT;

-- ==================================================
-- Task 5: Remove a Stored Procedure
-- ==================================================

DROP PROCEDURE IF EXISTS get_customer_info(
    integer,
    character varying,
    refcursor
);



