# ReelVibe 🎬

**Stop scrolling. Start watching.**

### What's your movie vibe in reel time?

ReelVibe is a full-stack movie recommendation application that helps users
decide what to watch based on how they feel **right now** and the type of movie
experience they want.

Instead of endlessly scrolling through movie catalogs, users answer five
questions about their current mood, desired mood, movie vibe, intensity, and
runtime preference.

ReelVibe turns those answers into a custom recommendation profile, retrieves
live movie data from TMDB, scores the results using a Java-based recommendation
engine, and returns a ranked list of movie matches.

---

## The Problem ReelVibe Solves

Sometimes the hardest part of movie night isn't finding a movie.

It's deciding **what kind of movie you actually feel like watching**.

ReelVibe approaches movie recommendations from the user's current mood and
desired experience instead of asking them to search through hundreds of titles.

---

## Core Features

- User registration and login
- JWT-based authentication
- Role-based authorization
- Five-question movie vibe questionnaire
- Custom Java recommendation engine
- Live movie data from TMDB
- Ranked movie recommendations
- Personal saved-movie collection
- Watch-status tracking
- Personal 1–5 star ratings
- Notes for saved movies
- Remove movies from a saved collection
- Protected administrator dashboard
- Enable and disable user accounts
- Responsive mobile, tablet, and desktop layouts
- Interactive animated ReelVibe Movie Concierge

---

## How ReelVibe Works

The recommendation flow begins with five questions:

1. **How are you feeling right now?**
2. **How do you want the movie to make you feel?**
3. **What's your movie vibe?**
4. **How intense should the movie be?**
5. **How much time do you have?**

The backend converts those answers into weighted genre preferences.

The strongest genres are mapped to TMDB genre IDs and used to retrieve movie
candidates.

Each movie is then scored using:

- ReelVibe genre-match weights
- TMDB rating bonus

The strongest matches are ranked and returned to the user.

> ReelVibe's recommendation engine uses transparent Java business rules rather
> than artificial intelligence or machine learning.

### ReelVibe Match vs. Vibe Score

Each recommendation displays two scores:

**Vibe Score** represents how strongly a movie's genres match the weighted
preferences created from the user's questionnaire answers.

**ReelVibe Match** is the movie's final recommendation score. It starts with
the Vibe Score and adds a small bonus based on the movie's TMDB rating.

The rating bonus is:

- TMDB rating of 8.0 or higher: **+2**
- TMDB rating from 7.0–7.9: **+1**
- TMDB rating below 7.0: **No bonus**

For example:

````text
Vibe Score:       31
TMDB Rating:      8.2
Rating Bonus:     +2
--------------------
ReelVibe Match:   33
---

## Technology Stack

### Frontend

- React
- Vite
- JavaScript
- React Router
- CSS
- GSAP

### Backend

- Java
- Spring Boot
- Spring Security
- JWT Authentication
- Spring JDBC / JdbcClient
- REST APIs

### Database

- PostgreSQL

### External API

- TMDB — The Movie Database

---

## Application Architecture

ReelVibe follows a layered full-stack architecture.

```text
React Client
     ↓
Spring REST Controllers
     ↓
Service / Business Logic
     ↓
DAO / Data Access
     ↓
PostgreSQL
````

The recommendation workflow also communicates with TMDB:

```text
Recommendation Service
        ↓
TMDB Client
        ↓
TMDB API
```

This separation keeps responsibilities organized between the user interface,
business rules, data access, database, and external movie service.

---

## Saved Movie Collection

Authenticated users can save movies from their recommendation results.

Each saved movie can include:

- **Watch Status**
  - Want to Watch
  - Watching
  - Watched
- **Personal Rating**
  - 1–5
- **Personal Notes**

Saved movie information is stored in PostgreSQL and associated with the
authenticated user.

Users can create, view, update, and delete saved movie records through the
application.

---

## Authentication and Roles

ReelVibe uses Spring Security and JWT-based authentication.

### User

A standard user can:

- Complete the ReelVibe questionnaire
- Generate movie recommendations
- Save movies
- View a saved movie collection
- Update watch status
- Add a personal rating
- Add notes
- Remove saved movies

### Admin

An administrator can perform all standard user actions and also access the
protected Admin Dashboard.

Administrators can:

- View registered users
- View enabled and disabled account totals
- Enable user accounts
- Disable user accounts

Administrative API routes are protected by role-based authorization.

The currently logged-in administrator is also protected from accidentally
disabling their own account.

---

## Project Structure

```text
10_final_assessment/
│
├── app-bff/
│   └── Spring Boot backend
│
├── app-frontend/
│   └── React / Vite frontend
│
├── app_db/
│   └── PostgreSQL database scripts
│
├── design/
│   └── ReelVibe planning and design documentation
│
└── README.md
```

---

## Design Documentation

The `design` directory contains supporting project documentation, including:

- Entity Relationship Diagram
- Class Diagram
- Layer Architecture Diagram
- Application Flow Diagram
- UI Wireframes
- Recommendation Logic
- Project Plan

These documents show how ReelVibe progressed from planning and design into the
final full-stack application.

---

# Local Setup

## Prerequisites

Before running ReelVibe locally, install:

- Java
- Maven
- Node.js / npm
- PostgreSQL
- Git

---

## 1. Database Setup

The database scripts are located in:

```text
app_db/
```

Run:

```text
app_db_init.sql
```

to create the application database and starter authentication tables.

Then run:

```text
reelvibe_schema.sql
```

against the application database to create the ReelVibe domain tables.

The ReelVibe database includes:

- `users`
- `authorities`
- `vibe_sessions`
- `recommendations`
- `saved_movies`

These scripts can be executed through PostgreSQL tools such as pgAdmin or
`psql`.

---

## 2. Backend Configuration

Navigate to:

```text
app-bff/src/main/resources/
```

Use:

```text
application.properties.example
```

as the template for your local:

```text
application.properties
```

Configure your local:

- PostgreSQL connection
- SSL / keystore settings
- TMDB API Read Access Token

`application.properties` is intentionally excluded from source control because
it may contain local credentials and API secrets.

---

## 3. Start the Backend

From the project root:

```bash
cd app-bff
./mvnw spring-boot:run
```

The Spring Boot application runs locally using HTTPS.

---

## 4. Start the Frontend

Open another terminal:

```bash
cd app-frontend
npm install
npm run dev
```

Vite will display the local frontend address in the terminal, normally:

```text
http://localhost:5173
```

---

# Testing

## Backend Test Suite

Run:

```bash
cd app-bff
./mvnw test
```

Current normal test-suite result:

```text
Tests run: 61
Failures: 0
Errors: 0
Skipped: 3
```

The skipped tests are live TMDB integration tests that are intentionally
disabled during the normal automated test suite.

Live API tests can be enabled separately when a valid TMDB token is configured.

---

## Frontend Verification

From `app-frontend`:

```bash
npm run build
npm run lint
```

The current frontend build and lint checks complete successfully with no
errors.

---

## Responsive Design

ReelVibe was built using a mobile-first approach.

The interface progressively adapts for:

- Mobile
- Tablet
- Desktop

CSS Grid is used for larger page layouts, while Flexbox is used for component
alignment and internal layout.

---

## Movie Concierge

The login and registration experience includes an animated ReelVibe Movie
Concierge.

The concierge responds to user interaction by:

- Following username input with eye movement
- Using subtle head movement
- Reacting when the password field is selected
- Covering his eyes while the user enters a password

The animation is built with SVG and GSAP.

---

## TMDB Attribution

ReelVibe uses movie information, posters, and other movie data provided by
TMDB.

**This product uses the TMDB API but is not endorsed or certified by TMDB.**

Official TMDB attribution is also available through the Credits page inside
the ReelVibe application.

---

## Author

**Mark Smith**

Full-Stack Software Development Capstone  
2026
