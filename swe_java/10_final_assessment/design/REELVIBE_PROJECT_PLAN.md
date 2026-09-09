# ReelVibe Capstone Project Plan

**Project:** ReelVibe

**Presentation Date:** September 11, 2026

**Internal Code-Freeze Target:** September 8, 2026

**Instructor Suggested Completion:** September 9, 2026

**Status:** Code Freeze / Final Deliverables and Presentation Preparation

---

## 1. Project Goal

ReelVibe is a full-stack movie recommendation application that helps users
decide what to watch based on how they are feeling **right now** and the type
of movie experience they want.

The application uses:

- React + Vite for the frontend
- Java + Spring Boot for the backend
- Spring Security + JWT for authentication and role-based access
- JDBC / JdbcClient for data access
- PostgreSQL for ReelVibe-owned persistence
- TMDB as the external movie data service
- GSAP for selected frontend animation and interaction

The development strategy for ReelVibe was:

> **MVP first. Polish second.**

Core full-stack functionality, database persistence, authentication,
recommendation logic, CRUD operations, external API integration, and testing
were completed before optional visual enhancements were added.

---

## 2. Definition of Done

ReelVibe is considered feature-complete when:

- ✅ Users can register and log in.
- ✅ Protected routes require authentication.
- ✅ At least one UI route is secured by role.
- ✅ Users can complete the five-question ReelVibe questionnaire.
- ✅ Spring converts questionnaire answers into a Vibe Profile.
- ✅ ReelVibe retrieves live movie candidates from TMDB.
- ✅ ReelVibe scores and ranks candidate movies.
- ✅ React displays ranked movie recommendations.
- ✅ Users can save movies.
- ✅ Users can view their saved movie collection.
- ✅ Users can update watch status, rating, and notes.
- ✅ Users can delete saved movies.
- ✅ Administrator functionality is role-protected.
- ✅ Administrators can enable and disable user accounts.
- ✅ PostgreSQL schema and setup scripts run cleanly.
- ✅ Automated backend tests pass.
- ✅ Frontend build and lint verification pass.
- ✅ TMDB attribution is included.
- ✅ Responsive layouts work across mobile, tablet, and desktop.
- ✅ Required design documentation is included.
- 🟡 Final repository/deliverable packaging is in progress.
- 🟡 Presentation materials are in progress.

---

## 3. Completed Foundation and Design Work

| Task                                                   | Status      |
| ------------------------------------------------------ | ----------- |
| Set up Rich's HTTPS/JWT full-stack starter             | ✅ Complete |
| Verify PostgreSQL connectivity                         | ✅ Complete |
| Verify Spring Boot backend                             | ✅ Complete |
| Verify React/Vite frontend                             | ✅ Complete |
| Verify register → login → JWT → protected request flow | ✅ Complete |
| Create ReelVibe recommendation logic design            | ✅ Complete |
| Create and validate PostgreSQL domain schema           | ✅ Complete |
| Create ERD                                             | ✅ Complete |
| Create layer architecture diagram                      | ✅ Complete |
| Create backend class diagram                           | ✅ Complete |
| Create application flow diagram                        | ✅ Complete |
| Create UI wireframes                                   | ✅ Complete |

---

# 4. Development Schedule

Implementation tasks were intentionally scoped into smaller development goals
so that each major layer of the application could be completed and verified
before moving forward.

---

## September 1 — Domain Foundation

| ID      | Task                                         | Estimate | Status      |
| ------- | -------------------------------------------- | -------: | ----------- |
| DEV-101 | Finalize and commit project plan             |   30 min | ✅ Complete |
| DEV-102 | Create ReelVibe Java model/package structure |   45 min | ✅ Complete |
| DEV-103 | Implement `VibeSession` model                |     1 hr | ✅ Complete |
| DEV-104 | Create `VibeSessionDao` interface            |   45 min | ✅ Complete |
| DEV-105 | Implement `JdbcVibeSessionDao`               |     2 hr | ✅ Complete |
| DEV-106 | Add DAO tests for `VibeSession`              |     2 hr | ✅ Complete |

**Daily goal:** Persist and retrieve a real Vibe Session from PostgreSQL
through the DAO layer.

**Result:** Completed.

---

## September 2 — Recommendation and Saved Movie Data Layers

| ID      | Task                                                    | Estimate | Status      |
| ------- | ------------------------------------------------------- | -------: | ----------- |
| DEV-201 | Implement `Recommendation` model                        |   45 min | ✅ Complete |
| DEV-202 | Implement `RecommendationDao` + `JdbcRecommendationDao` |   2.5 hr | ✅ Complete |
| DEV-203 | Add Recommendation DAO tests                            |   1.5 hr | ✅ Complete |
| DEV-204 | Implement `SavedMovie` model                            |   45 min | ✅ Complete |
| DEV-205 | Implement `SavedMovieDao` + `JdbcSavedMovieDao`         |   2.5 hr | ✅ Complete |
| DEV-206 | Add SavedMovie DAO tests                                |     2 hr | ✅ Complete |

**Daily goal:** All ReelVibe database concepts have tested DAO implementations.

**Result:** Completed.

---

## September 3 — Recommendation Engine

| ID      | Task                                      | Estimate | Status      |
| ------- | ----------------------------------------- | -------: | ----------- |
| DEV-301 | Implement questionnaire enums/value types |   1.5 hr | ✅ Complete |
| DEV-302 | Implement `VibeProfile` model             |     1 hr | ✅ Complete |
| DEV-303 | Implement current-feeling weighting rules |     1 hr | ✅ Complete |
| DEV-304 | Implement desired-feeling weighting rules |   1.5 hr | ✅ Complete |
| DEV-305 | Implement movie-vibe weighting rules      |   1.5 hr | ✅ Complete |
| DEV-306 | Implement intensity and runtime rules     |   1.5 hr | ✅ Complete |
| DEV-307 | Add RecommendationService unit tests      |     3 hr | ✅ Complete |

**Daily goal:** Questionnaire answers reliably produce a tested Vibe Profile
without calling TMDB.

**Result:** Completed.

The ReelVibe recommendation engine uses explicit Java business rules rather
than artificial intelligence or machine learning. Questionnaire answers modify
genre weights and runtime preferences used later in the recommendation
workflow.

---

## September 4 — TMDB Integration + Recommendation API

| ID      | Task                                              | Estimate | Status      |
| ------- | ------------------------------------------------- | -------: | ----------- |
| DEV-401 | Configure TMDB credential locally and safely      |   45 min | ✅ Complete |
| DEV-402 | Implement `MovieResult` DTO                       |     1 hr | ✅ Complete |
| DEV-403 | Implement `TmdbClient`                            |     3 hr | ✅ Complete |
| DEV-404 | Convert Vibe Profile into TMDB discovery criteria |     2 hr | ✅ Complete |
| DEV-405 | Implement movie scoring/ranking                   |     2 hr | ✅ Complete |
| DEV-406 | Add TMDB-related service testing                  |     2 hr | ✅ Complete |
| DEV-407 | Implement recommendation API endpoint(s)          |     2 hr | ✅ Complete |
| DEV-408 | Verify recommendation API                         |     1 hr | ✅ Complete |

**Daily goal:** An authenticated API request can return ranked ReelVibe movie
recommendations.

**Result:** Completed.

### Recommendation Scoring

Each candidate movie receives a **Vibe Score**, which represents how strongly
its genres match the weighted profile created from the user's questionnaire
answers.

A small TMDB rating bonus is then added:

- TMDB rating 8.0 or higher: +2
- TMDB rating 7.0–7.9: +1
- TMDB rating below 7.0: +0

The resulting final score is displayed as the **ReelVibe Match**.

This keeps the user's vibe as the primary recommendation factor while allowing
well-rated movies to receive a small boost.

---

## September 5 — Frontend Questionnaire + Results

| ID      | Task                                            | Estimate | Status      |
| ------- | ----------------------------------------------- | -------: | ----------- |
| DEV-501 | Create ReelVibe navigation/layout foundation    |   1.5 hr | ✅ Complete |
| DEV-502 | Build landing page hero from approved wireframe |   2.5 hr | ✅ Complete |
| DEV-503 | Build questionnaire state model                 |   1.5 hr | ✅ Complete |
| DEV-504 | Build one-question-at-a-time questionnaire UI   |     3 hr | ✅ Complete |
| DEV-505 | Add questionnaire progress/back navigation      |   1.5 hr | ✅ Complete |
| DEV-506 | Connect questionnaire submit to Spring API      |     2 hr | ✅ Complete |
| DEV-507 | Build ReelVibe loading/reveal experience        |     1 hr | ✅ Complete |
| DEV-508 | Build recommendation movie cards/results page   |     3 hr | ✅ Complete |

**Daily goal:** User can complete the questionnaire and see real ranked TMDB
recommendations in React.

**Result:** Completed.

---

## September 6 — Saved Movies CRUD

| ID      | Task                                            | Estimate | Status      |
| ------- | ----------------------------------------------- | -------: | ----------- |
| DEV-601 | Implement `SavedMovieService`                   |   1.5 hr | ✅ Complete |
| DEV-602 | Implement `SavedMovieController` CRUD endpoints |     2 hr | ✅ Complete |
| DEV-603 | Test saved-movie API endpoints                  |   1.5 hr | ✅ Complete |
| DEV-604 | Build Saved Movies React page                   |     3 hr | ✅ Complete |
| DEV-605 | Add save-movie action to recommendation cards   |   1.5 hr | ✅ Complete |
| DEV-606 | Add watch-status update UI                      |   1.5 hr | ✅ Complete |
| DEV-607 | Add rating/notes update UI                      |     2 hr | ✅ Complete |
| DEV-608 | Add remove-saved-movie UI                       |     1 hr | ✅ Complete |

**Daily goal:** Full Create/Read/Update/Delete workflow works from React through
Spring to PostgreSQL.

**Result:** Completed.

### Saved Movie Workflow

Users can:

- Create a saved movie record
- Read their saved movie collection
- Update watch status
- Add or update a 1–5 rating
- Add or update personal notes
- Delete a saved movie

Saved movies are associated with the authenticated user's account.

---

## September 7 — Role Security + Starter Cleanup + Core Polish

| ID      | Task                                                             | Estimate | Status      |
| ------- | ---------------------------------------------------------------- | -------: | ----------- |
| DEV-701 | Finalize admin-only backend functionality                        |   1.5 hr | ✅ Complete |
| DEV-702 | Build/update admin-only React route                              |     2 hr | ✅ Complete |
| DEV-703 | Verify role-based Spring Security protection                     |     1 hr | ✅ Complete |
| DEV-704 | Remove Notes demo UI/code after ReelVibe replacements are stable |     2 hr | ✅ Complete |
| DEV-705 | Remove starter `notes` table from final ReelVibe SQL             |     1 hr | ✅ Complete |
| DEV-706 | Test full user journey end-to-end                                |     2 hr | ✅ Complete |
| DEV-707 | Fix validation and error-state gaps discovered during testing    |     3 hr | ✅ Complete |

**Daily goal:** MVP is feature-complete and no starter Notes functionality
remains.

**Result:** Completed.

### Administrator Functionality

The final administrator experience includes:

- Protected Admin navigation
- Protected Admin API endpoints
- Registered-user listing
- Enabled-user count
- Disabled-user count
- Enable account action
- Disable account action
- Protection against the active administrator disabling their own account

---

## September 8 — Testing, Documentation, and UI Polish

| ID      | Task                                                         | Estimate | Status                |
| ------- | ------------------------------------------------------------ | -------: | --------------------- |
| DEV-801 | Run complete backend test suite and fix failures             |     3 hr | ✅ Complete           |
| DEV-802 | Run frontend build/lint and fix issues                       |     2 hr | ✅ Complete           |
| DEV-803 | Verify PostgreSQL scripts from clean setup                   |   1.5 hr | ✅ Complete           |
| DEV-804 | Write/update local setup documentation                       |     2 hr | 🟡 In Progress        |
| DEV-805 | Add TMDB attribution/documentation                           |   45 min | ✅ Complete           |
| DEV-806 | Responsive/mobile CSS pass                                   |     2 hr | ✅ Complete           |
| DEV-807 | Accessibility/keyboard/focus verification                    |   1.5 hr | 🟡 Final Verification |
| DEV-808 | Add Movie Concierge login animation after core app is stable |     3 hr | ✅ Complete           |
| DEV-809 | Final visual polish against wireframes                       |     3 hr | ✅ Complete           |

**Daily goal:** Code freeze. Everything required is working, tested,
documented, and presentable.

### Verification Results

Backend automated test suite:

```text
Tests run: 61
Failures: 0
Errors: 0
Skipped: 3
```

The three skipped tests are live TMDB integration tests that are intentionally
opt-in so the normal automated test suite does not depend on a live external
service.

Frontend verification:

```text
Production build: PASS
Lint errors: 0
Lint warnings: 0
```

A clean PostgreSQL setup was also verified from the provided SQL scripts.

---

## September 9 — Final Deliverables / Instructor Target

| ID      | Task                                              |   Estimate | Status                      |
| ------- | ------------------------------------------------- | ---------: | --------------------------- |
| DEV-901 | Full application smoke test                       |       1 hr | ✅ Complete                 |
| DEV-902 | Fix only blocking/high-priority bugs              | Up to 4 hr | ✅ No Blocking Issues Found |
| DEV-903 | Verify repository and required deliverables       |       1 hr | 🟡 In Progress              |
| DEV-904 | Prepare final presentation/demo data and accounts |       1 hr | 🟡 In Progress              |
| DEV-905 | Complete final documentation                      |       2 hr | 🟡 In Progress              |
| DEV-906 | Complete 4–6 slide presentation                   |     2.5 hr | 🟡 In Progress              |

**Rule:** No new major features after code freeze.

### User Acceptance / Beta Testing

The application has been exercised repeatedly through the main user workflows.

An additional informal beta test was performed by a first-time user who was
encouraged to explore the application and try to find problems.

Feedback from this session led to minor interface improvements, including:

- Clearer visual treatment for the `Surprise Me` questionnaire option
- Warmer Movie Concierge facial styling
- Improved card/button alignment
- Responsive navigation polish

The tester was able to use the application successfully and reported an
enjoyable overall experience.

No blocking application issues were identified during the final user testing
pass.

---

## September 10 — Presentation Preparation

| ID       | Task                                      | Estimate | Status |
| -------- | ----------------------------------------- | -------: | ------ |
| DEV-1001 | Finalize 4–6 slide presentation           |   2.5 hr | ⬜     |
| DEV-1002 | Write concise architecture talking points |     1 hr | ⬜     |
| DEV-1003 | Prepare likely Q&A answers                |   1.5 hr | ⬜     |
| DEV-1004 | Run complete timed demo                   |     1 hr | ⬜     |
| DEV-1005 | Run backup demo/recovery plan             |   45 min | ⬜     |

### Presentation Target

Total presentation time:

**Approximately 15 minutes**

Planned emphasis:

1. Briefly introduce the problem ReelVibe solves
2. Demonstrate the working application first
3. Show the five-question recommendation workflow
4. Explain Vibe Score and ReelVibe Match
5. Demonstrate Saved Movies CRUD
6. Demonstrate role-based Admin functionality
7. Explain the major application layers
8. Briefly discuss testing and debugging lessons
9. Finish with key takeaways and Q&A

---

## September 11 — Presentation Day

| Task                                          | Status |
| --------------------------------------------- | ------ |
| Start PostgreSQL before presentation          | ⬜     |
| Start Spring Boot backend before presentation | ⬜     |
| Start React frontend before presentation      | ⬜     |
| Verify login/demo accounts                    | ⬜     |
| Verify TMDB connectivity                      | ⬜     |
| Verify Saved Movies workflow                  | ⬜     |
| Verify Admin account                          | ⬜     |
| Open presentation slides                      | ⬜     |
| Present ReelVibe                              | ⬜     |

---

# 5. MVP vs. Optional Enhancements

## MVP — Complete

- ✅ Authentication and JWT
- ✅ Role-based security
- ✅ Five-question questionnaire
- ✅ Java recommendation rules
- ✅ TMDB external REST integration
- ✅ Ranked recommendation results
- ✅ Vibe Score
- ✅ ReelVibe Match final scoring
- ✅ Saved Movies CRUD
- ✅ PostgreSQL persistence
- ✅ Automated backend testing
- ✅ Frontend build verification
- ✅ Frontend lint verification
- ✅ Responsive layouts
- ✅ TMDB attribution
- 🟡 Final deliverable packaging/documentation

---

## Optional Enhancements Completed After MVP Stabilized

- ✅ Animated Movie Concierge
- ✅ Username eye-tracking interaction
- ✅ Password-field eye-cover interaction
- ✅ Cinematic landing/login/register styling
- ✅ Hover and glow effects
- ✅ Responsive navigation polish
- ✅ Admin dashboard statistics
- ✅ Recommendation score presentation
- ✅ Credits page
- ✅ Additional interface micro-interactions

These enhancements were intentionally added only after the required full-stack
workflow was stable.

---

# 6. Final Application Architecture

ReelVibe follows a layered full-stack design:

```text
React / Vite Frontend
        ↓
Spring REST Controllers
        ↓
Service / Business Logic
        ↓
DAO / JdbcClient
        ↓
PostgreSQL
```

The recommendation workflow also communicates with an external movie service:

```text
Questionnaire
     ↓
Recommendation Service
     ↓
Vibe Profile
     ↓
TMDB Client
     ↓
TMDB API
     ↓
Movie Candidates
     ↓
ReelVibe Scoring / Ranking
     ↓
React Recommendations Page
```

The purpose of this separation is to keep:

- UI concerns in React
- HTTP/API handling in Controllers
- business rules in Services
- persistence logic in DAOs
- relational data in PostgreSQL
- external movie communication in the TMDB client

---

# 7. Final Recommendation Flow

A user completes five questionnaire questions:

1. Current feeling
2. Desired feeling
3. Movie vibe
4. Intensity
5. Runtime preference

Spring converts the answers into a `VibeProfile`.

The profile contains weighted genre preferences and runtime criteria.

The strongest genres are converted to TMDB genre IDs and used to retrieve
candidate movies.

Each movie receives:

### Vibe Score

The raw compatibility score based primarily on the weighted ReelVibe genre
profile.

### ReelVibe Match

The final recommendation score:

```text
ReelVibe Match = Vibe Score + TMDB Rating Bonus
```

Rating bonus:

```text
8.0 or higher    +2
7.0–7.9          +1
Below 7.0        +0
```

Movies are ranked by the final ReelVibe Match score.

---

# 8. Testing and Quality Verification

Testing and verification included:

- DAO integration tests
- Recommendation service tests
- Controller/service verification
- Authentication testing
- Role-security testing
- Saved Movies CRUD testing
- TMDB integration testing
- Clean database setup testing
- Frontend build verification
- Frontend lint verification
- Responsive/mobile testing
- Manual end-to-end user testing
- Informal first-time-user beta testing

Final automated backend result:

```text
61 tests
0 failures
0 errors
3 intentionally skipped live TMDB tests
```

Final frontend verification:

```text
npm run build → PASS
npm run lint  → 0 warnings / 0 errors
```

---

# 9. Key Development Challenges and Lessons Learned

## External Client Configuration

The TMDB integration initially failed because Spring did not automatically
provide the required `RestClient.Builder`.

A dedicated configuration bean was added and the TMDB integration was
successfully restored.

**Lesson:** When dependency injection fails, verify that every required
dependency is actually registered as a Spring bean.

---

## Recommendation Test Expectations

One recommendation test initially appeared to fail because the expected genre
weight was incorrect rather than the production logic.

The calculation was manually traced and the test expectation was corrected.

**Lesson:** A failing test does not always mean the implementation is wrong.
The expected result must also be verified.

---

## Backend Restart During Admin Development

An administrator enable/disable request initially appeared broken even though
the new code was correct.

The running Spring application was still using the previous build. Restarting
the backend exposed the new endpoint and resolved the issue.

**Lesson:** During full-stack development, confirm that the currently running
application actually contains the latest backend changes.

---

## Clean Database Verification

Testing the database scripts against a new temporary database exposed an old
starter admin-role insert that referenced an administrator account that did
not exist.

The starter SQL was corrected so administrator roles are assigned only after
the corresponding user exists.

**Lesson:** A database that works after weeks of development is not proof that
the setup scripts work from scratch. Clean-install testing is important.

---

# 10. Final Deliverables

The final ReelVibe deliverables include:

- Full Spring Boot backend
- React/Vite frontend
- PostgreSQL database scripts
- Five-question recommendation workflow
- Java recommendation engine
- TMDB integration
- Saved Movies CRUD
- JWT authentication
- Role-based authorization
- Administrator dashboard
- Responsive user interface
- Movie Concierge animation
- Automated tests
- Root README / setup documentation
- Recommendation logic documentation
- ERD
- Class diagram
- Layer architecture diagram
- Application flow diagram
- UI wireframes
- Project plan
- TMDB attribution
- 4–6 slide presentation
- 15-minute presentation/demo preparation

---

# 11. Current Final Status — September 9, 2026

## Completed

- ReelVibe MVP
- Database persistence
- Authentication
- Authorization
- Recommendation engine
- TMDB integration
- Questionnaire
- Ranked recommendations
- Vibe Score
- ReelVibe Match
- Saved Movies CRUD
- Administrator functionality
- Responsive/mobile design
- Movie Concierge
- Backend automated tests
- Frontend build verification
- Frontend lint verification
- Clean PostgreSQL setup verification
- TMDB attribution
- Final UI polish
- Manual user testing
- Informal beta testing

## In Progress

- Final documentation
- Deliverable verification
- Slide presentation
- Presentation talking points
- Timed demo preparation

## Blocked

- None

## Current Rule

> **No new major features. Only documentation, deliverable verification,
> presentation preparation, and blocking fixes are allowed after code freeze.**

---

# 12. Development Rule

> **MVP first. Polish second. New features only after the required full-stack
> flow and tests are stable.**

This rule remained the guiding development strategy throughout ReelVibe.

The result is a completed full-stack application with a stable core workflow
and additional visual enhancements added only after the required functionality
was working.
