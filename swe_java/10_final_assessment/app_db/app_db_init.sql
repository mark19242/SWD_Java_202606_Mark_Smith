---------------------------------------
-- Create DB Instance On PSQL Server --
---------------------------------------
-- 0. Must run at command line using:
--    psql -U postgres -f app_db_init.sql
-- -or-
--	  Parsed and run against the appropriate
--      DBs, ignoring the \c directives. 
-- 1. Run once to initialize BFF SQL
-- 2. Create all Domain Scripts
--     separately
---------------------------------------
-- Created: 08/28/2026
-- Created By: R. Seeds
---------------------------------------

-- Switch context by connecting ( \c ) to admin DB: Command Line Only!
\c postgres

-- DROP IF EXISTS Cannot be run within transaction block
--  Check your run settings or DROP manually if and when needed
DROP DATABASE IF EXISTS app_db;
CREATE DATABASE app_db;

-------------------------------------------
-- Initialize Schema for Spring Security --
-------------------------------------------
-- 0. Make sure you switch your DB Connection
--    to app_db before executing if parsing
--    this script in PGAmin
-------------------------------------------

-- Switch context by connecting ( \c ) to app_db DB: Command Line Only!
\c app_db

-- Spring Security Auth Schema
CREATE TABLE IF NOT EXISTS users (
    username VARCHAR(50) NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled BOOLEAN NOT NULL
);

CREATE TABLE IF NOT EXISTS authorities (
    username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users(username)
);

CREATE UNIQUE INDEX IF NOT EXISTS ix_auth_username ON authorities (username, authority);



------------------------------------------------------
--  USER ADMIN
------------------------------------------------------

-- Example INSERT statement to add admin role to default admin user
--   TODO: Use API to register a default admin user first. Then
--          execute to add admin role. Consider adding an endpoint
--          to the controller for /admin/user/addRole.

INSERT INTO authorities
SELECT 'admin', 'ROLE_ADMIN'

