-- ==================================================
-- Exercise: Database Scripts
-- Movie Database Schema
-- ==================================================


-- ==================================================
-- DROP EXISTING TABLES
-- ==================================================

DROP TABLE IF EXISTS movie_genre;
DROP TABLE IF EXISTS credit;
DROP TABLE IF EXISTS genre;
DROP TABLE IF EXISTS actor;
DROP TABLE IF EXISTS movie;


-- ==================================================
-- CREATE
-- ==================================================

CREATE TABLE movie (
    movie_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    release_year SMALLINT NOT NULL,
    rating VARCHAR(10) NOT NULL,
    CONSTRAINT uq_movie_title_release_year
        UNIQUE (title, release_year)
);

CREATE TABLE actor (
    actor_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL
);

CREATE TABLE credit (
    credit_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    movie_id INT NOT NULL,
    actor_id INT NOT NULL,
    role_name VARCHAR(100) NOT NULL,
    CONSTRAINT uq_credit_movie_actor_role
        UNIQUE (movie_id, actor_id, role_name)
);

CREATE TABLE genre (
    genre_id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT uq_genre_name
        UNIQUE (name)
);

CREATE TABLE movie_genre (
    movie_id INT NOT NULL,
    genre_id INT NOT NULL,
    CONSTRAINT pk_movie_genre
        PRIMARY KEY (movie_id, genre_id)
);

-- ==================================================
-- ALTER
-- Add Director to Movie
-- ==================================================

ALTER TABLE movie
    ADD COLUMN director VARCHAR(100) NOT NULL;

-- ==================================================
-- RELATE
-- ==================================================

ALTER TABLE credit
    ADD CONSTRAINT fk_credit_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movie (movie_id);

ALTER TABLE credit
    ADD CONSTRAINT fk_credit_actor_id
        FOREIGN KEY (actor_id)
        REFERENCES actor (actor_id);

ALTER TABLE movie_genre
    ADD CONSTRAINT fk_movie_genre_movie_id
        FOREIGN KEY (movie_id)
        REFERENCES movie (movie_id);

ALTER TABLE movie_genre
    ADD CONSTRAINT fk_movie_genre_genre_id
        FOREIGN KEY (genre_id)
        REFERENCES genre (genre_id);
		

