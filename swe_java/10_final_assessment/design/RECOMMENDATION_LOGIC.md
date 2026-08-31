# ReelVibe Recommendation Logic

**Project:** ReelVibe  
**Document:** Recommendation Logic Design  
**Version:** 1.0  
**Status:** MVP Design

---

## 1. Purpose

ReelVibe helps users decide what movie to watch based on how they feel **right now** and the type of movie experience they want **right now**.

The external movie API provides movie data. ReelVibe provides the recommendation logic.

The goal is not to ask the user to select a genre directly. Instead, ReelVibe uses a short five-question questionnaire and converts the answers into weighted movie preferences. Those weights are then used to find and rank movie recommendations.

---

## 2. Core Recommendation Flow

```text
User completes questionnaire
        ↓
ReelVibe creates a Vibe Profile
        ↓
Questionnaire answers add or subtract genre weights
        ↓
Runtime preference becomes a filter
        ↓
Spring requests candidate movies from TMDB
        ↓
ReelVibe scores the returned movies
        ↓
Movies are ranked by best vibe match
        ↓
React displays the top recommendations
```

### Design Principle

Different questionnaire answers should have different levels of influence.

| Signal | Purpose | Strength |
|---|---|---|
| Current feeling | Gentle recommendation nudge | ±1 |
| Desired feeling | Major preference | +1 to +5 |
| Movie vibe | Major preference | +1 to +4 |
| Intensity | Adjusts the experience | -3 to +3 |
| Runtime | Hard filter | No points |

The user's current mood should influence the result, but the user's explicit viewing preferences should matter more.

---

# 3. Questionnaire

## Question 1: How are you feeling right now?

This establishes the user's current emotional state.

| Answer | Genre Adjustments |
|---|---|
| Relaxed | Comedy +1, Romance +1, Adventure +1 |
| Stressed | Comedy +1, Family +1, Animation +1, Horror -1, Thriller -1 |
| Tired | Comedy +1, Romance +1, Family +1, Action -1, Horror -1 |
| Happy | Comedy +1, Adventure +1, Romance +1 |
| Feeling Down | Comedy +1, Animation +1, Family +1 |
| Bored | Action +1, Adventure +1, Mystery +1, Thriller +1 |
| Energetic | Action +1, Adventure +1, Comedy +1 |
| Restless | Action +1, Thriller +1, Mystery +1 |

### Internal Values

```text
RELAXED
STRESSED
TIRED
HAPPY
DOWN
BORED
ENERGETIC
RESTLESS
```

---

## Question 2: How do you want to feel right now?

This is one of the strongest recommendation signals because the user is directly describing the experience they want.

| Answer | Genre Adjustments |
|---|---|
| Make Me Laugh | Comedy +5, Animation +2, Romance +1 |
| Comfort Me | Comedy +4, Romance +3, Family +2, Animation +2, Fantasy +1 |
| Get Me Excited | Action +5, Adventure +4, Thriller +2, Science Fiction +2 |
| Scare Me | Horror +5, Thriller +3, Mystery +2 |
| Blow My Mind | Science Fiction +5, Mystery +4, Thriller +2, Fantasy +2 |
| Make Me Think | Drama +4, Mystery +4, Documentary +3, History +2, Science Fiction +2 |
| Give Me the Feels | Drama +5, Romance +3, Family +2, Animation +1 |
| Help Me Escape | Adventure +5, Fantasy +5, Science Fiction +3, Animation +2, Action +1 |

### Internal Values

```text
MAKE_ME_LAUGH
COMFORT_ME
GET_ME_EXCITED
SCARE_ME
BLOW_MY_MIND
MAKE_ME_THINK
GIVE_ME_THE_FEELS
HELP_ME_ESCAPE
```

---

## Question 3: What are you in the mood to watch right now?

The user may select **up to two** movie-vibe choices.

| Answer | Genre Adjustments |
|---|---|
| Light & Funny | Comedy +4, Family +2, Animation +2, Romance +1 |
| Action-Packed | Action +4, Adventure +3, Thriller +2, Science Fiction +1 |
| Romantic | Romance +4, Comedy +1, Drama +1 |
| Suspenseful | Thriller +4, Mystery +3, Crime +2, Horror +1 |
| Dark & Intense | Thriller +4, Crime +3, Drama +2, Horror +2, Mystery +1 |
| Mind-Bending | Science Fiction +4, Mystery +4, Thriller +2, Fantasy +2 |
| Emotional | Drama +4, Romance +2, Family +1 |
| Epic & Adventurous | Adventure +4, Action +3, Fantasy +3, Science Fiction +2, History +1, War +1 |
| Surprise Me | Special behavior; no direct genre points |

### Internal Values

```text
LIGHT_AND_FUNNY
ACTION_PACKED
ROMANTIC
SUSPENSEFUL
DARK_AND_INTENSE
MIND_BENDING
EMOTIONAL
EPIC_AND_ADVENTUROUS
SURPRISE_ME
```

### Surprise Me Behavior

`SURPRISE_ME` should be exclusive. If the user selects it, they should not select a second movie-vibe option.

ReelVibe will still use Questions 1, 2, 4, and 5 to create a valid recommendation profile. Instead of always returning only the highest-scoring movies, ReelVibe can randomly select from a small group of highly ranked matches.

This keeps the result surprising without ignoring the user's other preferences.

---

## Question 4: How intense should we go right now?

Intensity works like a volume control for the recommendation profile.

| Answer | Genre Adjustments |
|---|---|
| Keep It Chill | Comedy +2, Romance +2, Family +2, Animation +1, Horror -3, Thriller -2, Crime -1, War -2 |
| A Little Intensity | Adventure +1, Mystery +1, Horror -1 |
| Bring It On | Action +2, Thriller +2, Adventure +1, Crime +1, Horror +1 |
| Go All Out | Action +3, Thriller +3, Horror +3, Crime +2, War +2 |
| Doesn't Matter | No adjustment |

### Internal Values

```text
CHILL
LIGHT_INTENSITY
BRING_IT_ON
GO_ALL_OUT
ANY_INTENSITY
```

---

## Question 5: How much time do you have right now?

Runtime does not add genre points. It is used as a movie filter.

| Answer | Runtime Rule |
|---|---|
| Quick Watch — Under 90 Minutes | Maximum runtime: 89 minutes |
| Movie Night — Up to 2 Hours | Maximum runtime: 120 minutes |
| I've Got Time — Up to 2.5 Hours | Maximum runtime: 150 minutes |
| Time Doesn't Matter | No runtime restriction |

### Internal Values

```text
QUICK
STANDARD
EXTENDED
ANY_RUNTIME
```

---

# 4. Building the Vibe Profile

Every supported movie genre begins with a score of `0`.

Example:

```text
Action           0
Adventure        0
Animation        0
Comedy           0
Crime            0
Documentary      0
Drama            0
Family           0
Fantasy          0
History          0
Horror           0
Mystery          0
Romance          0
Science Fiction  0
Thriller         0
War              0
```

ReelVibe processes the questionnaire in order and adds or subtracts the appropriate weights.

The final result is the user's **Vibe Profile**.

Example:

```text
Comedy       13
Romance       8
Animation     6
Family        5
Drama         1
Crime        -1
War          -2
Thriller     -3
Horror       -4
```

The strongest positive genres represent the best match for the user's current viewing preference.

Negative values do not automatically ban a genre. They lower that genre's ranking unless a stronger answer overrides the penalty.

---

# 5. Example Recommendation

### User Answers

```text
Current Feeling:
STRESSED

Desired Feeling:
MAKE_ME_LAUGH

Movie Vibes:
LIGHT_AND_FUNNY
ROMANTIC

Intensity:
CHILL

Runtime:
STANDARD
```

### Weight Calculation

#### From STRESSED

```text
Comedy      +1
Family      +1
Animation   +1
Horror      -1
Thriller    -1
```

#### From MAKE_ME_LAUGH

```text
Comedy      +5
Animation   +2
Romance     +1
```

#### From LIGHT_AND_FUNNY

```text
Comedy      +4
Family      +2
Animation   +2
Romance     +1
```

#### From ROMANTIC

```text
Romance     +4
Comedy      +1
Drama       +1
```

#### From CHILL

```text
Comedy      +2
Romance     +2
Family      +2
Animation   +1
Horror      -3
Thriller    -2
Crime       -1
War         -2
```

### Final Profile

```text
Comedy       13
Romance       8
Animation     6
Family        5
Drama         1
Crime        -1
War          -2
Thriller     -3
Horror       -4

Maximum Runtime:
120 minutes
```

The user never directly selected "Romantic Comedy," but ReelVibe identified Comedy and Romance as the strongest combination based on the complete questionnaire.

---

# 6. TMDB Candidate Retrieval

TMDB provides the movie information. ReelVibe should use the final Vibe Profile to determine which candidate movies to request.

Conceptually:

```text
Vibe Profile
     ↓
Select strongest positive genres
     ↓
Apply runtime constraint
     ↓
Request candidate movies from TMDB
     ↓
Return candidate list to RecommendationService
```

The exact TMDB request strategy may be adjusted during implementation after testing the quality and size of the returned candidate pool.

ReelVibe should avoid sending every questionnaire rule directly to TMDB. TMDB should provide a reasonable candidate pool, while ReelVibe remains responsible for the final recommendation ranking.

---

# 7. Movie Scoring

After TMDB returns candidate movies, ReelVibe calculates a match score for each movie.

For each genre attached to a movie:

```text
movie score += user's weight for that genre
```

### Example

User profile:

```text
Comedy     13
Romance     8
Animation   6
Drama       1
```

Candidate Movie A:

```text
Genres:
Comedy
Romance

Vibe Score:
13 + 8 = 21
```

Candidate Movie B:

```text
Genres:
Comedy
Animation

Vibe Score:
13 + 6 = 19
```

Candidate Movie C:

```text
Genres:
Romance
Drama

Vibe Score:
8 + 1 = 9
```

Initial ranking:

```text
1. Movie A — 21
2. Movie B — 19
3. Movie C — 9
```

---

# 8. Quality Bonus

A movie should not rank highly only because its genres match the questionnaire.

TMDB rating information may be used as a **small quality bonus**, not as the primary recommendation factor.

### Proposed MVP Rule

| TMDB Rating | Bonus |
|---|---:|
| 8.0 or higher | +2 |
| 7.0–7.9 | +1 |
| Below 7.0 | +0 |

Example:

```text
Vibe Match Score: 21
Rating Bonus:      +1
Final Score:       22
```

ReelVibe should also use a reasonable minimum vote count when retrieving candidate movies so that a movie with only a few ratings does not receive an artificially strong quality score.

The exact minimum vote threshold may be tuned during testing.

---

# 9. Final Ranking

The recommendation process should follow this order:

```text
1. Build genre-weight profile
2. Determine runtime constraint
3. Retrieve candidate movies from TMDB
4. Score each movie using ReelVibe genre weights
5. Add the small quality bonus
6. Sort candidates from highest to lowest score
7. Return a small recommendation list
```

For MVP, the target recommendation list should be approximately **6–10 movies**.

If `SURPRISE_ME` is active, ReelVibe may randomly select from a small pool of the highest-scoring results instead of always returning the exact same highest-ranked movies.

---

# 10. Recommended Java Responsibility

The recommendation rules belong in the **domain/service layer**, not in React and not primarily in PostgreSQL.

Conceptually:

```text
RecommendationController
        ↓
RecommendationService
        ├── Current Feeling Rules
        ├── Desired Feeling Rules
        ├── Movie Vibe Rules
        ├── Intensity Rules
        ├── Runtime Rules
        ├── Movie Scoring
        └── Final Ranking
        ↓
TMDB Client
```

React is responsible for collecting questionnaire answers and displaying results.

The Spring service is responsible for interpreting those answers.

TMDB is responsible for supplying movie data.

PostgreSQL is responsible for storing ReelVibe-owned application data.

---

# 11. Testing Strategy

The recommendation engine should be testable without making live TMDB requests.

Examples of domain tests:

### Current Mood Rule

```text
Given:
STRESSED

Expect:
Comedy receives +1
Horror receives -1
Thriller receives -1
```

### Desired Feeling Rule

```text
Given:
MAKE_ME_LAUGH

Expect:
Comedy receives the strongest positive weight
```

### Combined Answers

```text
Given:
STRESSED
MAKE_ME_LAUGH
LIGHT_AND_FUNNY

Expect:
Comedy has a higher score than Horror
```

### High-Intensity Horror Profile

```text
Given:
SCARE_ME
DARK_AND_INTENSE
GO_ALL_OUT

Expect:
Horror and Thriller receive strong positive scores
```

### Runtime Rule

```text
Given:
QUICK

Expect:
maximum runtime = 89
```

### Ranking Rule

```text
Given:
Movie A matches Comedy + Romance
Movie B matches Comedy + Drama

And:
Romance has a higher user weight than Drama

Expect:
Movie A ranks above Movie B
```

### Quality Bonus Rule

```text
Given:
Two movies have the same vibe score

And:
Movie A has a higher qualifying TMDB rating

Expect:
Movie A receives the higher final score
```

---

# 12. MVP Boundaries

The first version of the recommendation engine will **not** use:

```text
Machine Learning
Artificial Intelligence recommendation models
Web scraping
WebSockets
Cloud-based recommendation services
User behavior prediction
```

The MVP uses transparent Java business rules that can be explained, tested, and adjusted.

---

# 13. Design Notes

- The weighting values in this document are the **Version 1 starting values**.
- Weights may be adjusted after manual testing if recommendations are consistently too broad, too narrow, or unintuitive.
- Changes to weights should preserve the principle that explicit user preferences have more influence than current mood.
- Runtime is a filter rather than a score.
- TMDB supplies candidate movie data, but ReelVibe owns the recommendation decision.
- The recommendation logic should remain understandable enough to explain during the Capstone presentation.

---

## ReelVibe Recommendation Principle

> **TMDB provides the movies. ReelVibe decides which movies fit your vibe right now.**
