# Release Notes: Lab - Class Design

**Student:** Mark Smith
**Branch:** main
**PR:** [link to pull request]
**Date:** July 1, 2026

---

## Summary

In this lab, I worked on an existing music database app and added new features to it. The app now lets a user log in, log out, add reviews to albums, and view reviews when looking at album details.

I also cleaned up some of the starter code by using `ArrayList` instead of fixed-size arrays, adding a config object for the main menu, renaming unclear methods, and changing the menu choices into an enum.

---

## Features Implemented

Mark each item as Done, Partial, or Not Started, and add a short note on where the code lives and any decisions you made.

| #   | Feature                                                        | Status | Notes |
| --- | -------------------------------------------------------------- | ------ | ----- |
| 1   | Hard-coded set of users (like Artists/Albums)                  | Done   |       |
| 2   | Menu item to identify current user                             | Done   |       |
| 3   | Menu shows logged-in state + username                          | Done   |       |
| 4   | Menu item toggles "Log In" / "Log Out"                         | Done   |       |
| 5   | Logged-in users can add a review to an album                   | Done   |       |
| 6   | Album details list reviews after song list (text + author)     | Done   |       |
| 7   | "No reviews have been submitted for this album yet" when empty | Done   |       |

---

## Refactoring (Senior Developer Notes)

| # | Note | Status | Notes

| Partial |
| 1 | Removed inappropriate comments, broke up long/duplicated methods, addressed Large Class smell in `MainMenuController`. I cleaned up some of the controller by renaming `doTheThing()` to `viewAllAlbums()`, adding helper methods, and using an enum for menu choices. The controller is better, but it could still be broken down more later. |

| Done |
| 2 | Replaced individual service parameters in `MainMenuController` constructor with a configuration object. I added `MainMenuConfig` so the controller receives one config object instead of several separate objects. |

| Partial |
| 3 | Wrote a test case exposing the array-capacity crash risk, and implemented graceful failure instead of a crash. I changed the album and artist repositories to use `ArrayList` instead of fixed-size arrays. I still need to add the specific test for the old array-size crash problem. |

---

## Outstanding Work

- Add the specific test case for the old array-size crash issue.
- Possibly break `MainMenuController` into smaller classes later.
- Possibly update the repo interfaces later so they return `List` instead of arrays.

## Known Issues / Bugs

- I do not know of any runtime bugs right now.
- The main thing still missing is the specific test case for the old array-capacity issue.

## Design Decisions / Trade-offs

- I kept the repo interfaces returning arrays so I would not have to rewrite too much of the starter code at once.
- I changed the inside of the repos to use `ArrayList` because it is safer than a fixed-size array.
- I kept constructor parameters like `maxAlbums` and `maxArtists` so the existing factories and tests would still work.
- I used `MainMenuConfig` so the controller constructor would stay cleaner as more repos were added.
- I used an enum for the menu choices because the menu options are a fixed list.
- I used a separate `ReviewRepo` instead of putting reviews directly inside the `Album` class so review storage has its own place.

## Questions for Reviewer

- Should I eventually change the repo interfaces to return `List` instead of arrays?
- Is the current cleanup of `MainMenuController` enough for this lab?
- Do you prefer reviews being stored in a separate repo, or should they be stored directly on each album?
