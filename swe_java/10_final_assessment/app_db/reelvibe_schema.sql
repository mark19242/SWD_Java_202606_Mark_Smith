--------------------------------------------------
-- ReelVibe Domain Schema
--------------------------------------------------
-- Database: app_db
-- Purpose:
--   Adds ReelVibe-owned domain tables while preserving
--   Rich's Spring Security users/authorities tables.
--
-- Safe during transition:
--   This script does NOT drop the starter notes table yet.
--   We can remove notes after the ReelVibe backend/frontend
--   has replaced the starter Notes feature.
--------------------------------------------------

\c app_db

--------------------------------------------------
-- Drop ReelVibe domain tables in dependency order
-- so this script can be re-run during development.
--------------------------------------------------

DROP TABLE IF EXISTS recommendations;
DROP TABLE IF EXISTS saved_movies;
DROP TABLE IF EXISTS vibe_sessions;

--------------------------------------------------
-- VIBE SESSIONS
--------------------------------------------------
-- Represents one "what's your vibe right now?"
-- questionnaire completed by an authenticated user.
--------------------------------------------------

CREATE TABLE vibe_sessions (
    vibe_session_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,

    current_feeling VARCHAR(30) NOT NULL,
    desired_feeling VARCHAR(40) NOT NULL,

    primary_movie_vibe VARCHAR(40) NOT NULL,
    secondary_movie_vibe VARCHAR(40),

    intensity VARCHAR(30) NOT NULL,
    runtime_preference VARCHAR(30) NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_vibe_sessions_users
        FOREIGN KEY (username)
        REFERENCES users(username)
        ON DELETE CASCADE,

    CONSTRAINT chk_current_feeling
        CHECK (current_feeling IN (
            'RELAXED',
            'STRESSED',
            'TIRED',
            'HAPPY',
            'DOWN',
            'BORED',
            'ENERGETIC',
            'RESTLESS'
        )),

    CONSTRAINT chk_desired_feeling
        CHECK (desired_feeling IN (
            'MAKE_ME_LAUGH',
            'COMFORT_ME',
            'GET_ME_EXCITED',
            'SCARE_ME',
            'BLOW_MY_MIND',
            'MAKE_ME_THINK',
            'GIVE_ME_THE_FEELS',
            'HELP_ME_ESCAPE'
        )),

    CONSTRAINT chk_primary_movie_vibe
        CHECK (primary_movie_vibe IN (
            'LIGHT_AND_FUNNY',
            'ACTION_PACKED',
            'ROMANTIC',
            'SUSPENSEFUL',
            'DARK_AND_INTENSE',
            'MIND_BENDING',
            'EMOTIONAL',
            'EPIC_AND_ADVENTUROUS',
            'SURPRISE_ME'
        )),

    CONSTRAINT chk_secondary_movie_vibe
        CHECK (
            secondary_movie_vibe IS NULL
            OR secondary_movie_vibe IN (
                'LIGHT_AND_FUNNY',
                'ACTION_PACKED',
                'ROMANTIC',
                'SUSPENSEFUL',
                'DARK_AND_INTENSE',
                'MIND_BENDING',
                'EMOTIONAL',
                'EPIC_AND_ADVENTUROUS'
            )
        ),

    CONSTRAINT chk_movie_vibes_are_different
        CHECK (
            secondary_movie_vibe IS NULL
            OR secondary_movie_vibe <> primary_movie_vibe
        ),

    CONSTRAINT chk_surprise_me_is_exclusive
        CHECK (
            primary_movie_vibe <> 'SURPRISE_ME'
            OR secondary_movie_vibe IS NULL
        ),

    CONSTRAINT chk_intensity
        CHECK (intensity IN (
            'CHILL',
            'LIGHT_INTENSITY',
            'BRING_IT_ON',
            'GO_ALL_OUT',
            'ANY_INTENSITY'
        )),

    CONSTRAINT chk_runtime_preference
        CHECK (runtime_preference IN (
            'QUICK',
            'STANDARD',
            'EXTENDED',
            'ANY_RUNTIME'
        ))
);

CREATE INDEX ix_vibe_sessions_username
    ON vibe_sessions(username);

--------------------------------------------------
-- RECOMMENDATIONS
--------------------------------------------------
-- Stores the ReelVibe recommendation result for a
-- specific vibe session.
--
-- TMDB owns movie metadata. ReelVibe stores only
-- the external TMDB ID plus its own scoring/ranking.
--------------------------------------------------

CREATE TABLE recommendations (
    recommendation_id BIGSERIAL PRIMARY KEY,
    vibe_session_id BIGINT NOT NULL,
    tmdb_movie_id BIGINT NOT NULL,

    match_score INTEGER NOT NULL,
    rating_bonus SMALLINT NOT NULL DEFAULT 0,
    recommendation_rank SMALLINT NOT NULL,

    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_recommendations_vibe_sessions
        FOREIGN KEY (vibe_session_id)
        REFERENCES vibe_sessions(vibe_session_id)
        ON DELETE CASCADE,

    CONSTRAINT chk_tmdb_movie_id_positive
        CHECK (tmdb_movie_id > 0),

    CONSTRAINT chk_rating_bonus
        CHECK (rating_bonus BETWEEN 0 AND 2),

    CONSTRAINT chk_recommendation_rank_positive
        CHECK (recommendation_rank > 0),

    CONSTRAINT uq_recommendation_movie_per_session
        UNIQUE (vibe_session_id, tmdb_movie_id),

    CONSTRAINT uq_recommendation_rank_per_session
        UNIQUE (vibe_session_id, recommendation_rank)
);

CREATE INDEX ix_recommendations_vibe_session
    ON recommendations(vibe_session_id);

--------------------------------------------------
-- SAVED MOVIES
--------------------------------------------------
-- Represents movies an authenticated user has saved
-- to their personal ReelVibe collection/watchlist.
--
-- Movie metadata remains owned by TMDB. The database
-- stores the TMDB movie ID plus ReelVibe-owned user data.
--------------------------------------------------

CREATE TABLE saved_movies (
    saved_movie_id BIGSERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    tmdb_movie_id BIGINT NOT NULL,

    watch_status VARCHAR(20) NOT NULL DEFAULT 'WANT_TO_WATCH',
    personal_rating SMALLINT,
    notes TEXT,

    saved_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,

    CONSTRAINT fk_saved_movies_users
        FOREIGN KEY (username)
        REFERENCES users(username)
        ON DELETE CASCADE,

    CONSTRAINT chk_saved_tmdb_movie_id_positive
        CHECK (tmdb_movie_id > 0),

    CONSTRAINT chk_watch_status
        CHECK (watch_status IN (
            'WANT_TO_WATCH',
            'WATCHING',
            'WATCHED'
        )),

    CONSTRAINT chk_personal_rating
        CHECK (
            personal_rating IS NULL
            OR personal_rating BETWEEN 1 AND 5
        ),

    CONSTRAINT chk_notes_length
        CHECK (
            notes IS NULL
            OR CHAR_LENGTH(notes) <= 2000
        ),

    CONSTRAINT uq_saved_movie_per_user
        UNIQUE (username, tmdb_movie_id)
);

CREATE INDEX ix_saved_movies_username
    ON saved_movies(username);

--------------------------------------------------
-- Verification Query
--------------------------------------------------

SELECT table_name
FROM information_schema.tables
WHERE table_schema = 'public'
ORDER BY table_name;
