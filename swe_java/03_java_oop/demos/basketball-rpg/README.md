# 1-on-1 Basketball Hustle

## Project Description

1-on-1 Basketball Hustle is a Java console basketball RPG game.

The player starts with $100 and works their way through pickup games, tournaments, practice, and power-up items. The main goal is to earn enough money and win enough tournaments to unlock the final boss tournament against King Supreme, the best 1-on-1 player in the nation.

The game is mostly based on luck, but the player can improve their chances by earning money, buying items, equipping gear, practicing skills, managing stamina, and reading scouting reports.

## Game Goal

The main goal is to:

1. Earn $2,000
2. Win the Pro-Am Invitational
3. Unlock the National 1-on-1 Tournament
4. Defeat King Supreme

If the player beats King Supreme, they become the king of 1-on-1 basketball.

## Main Features

- Create a custom player
- Play free pickup games
- Enter 1-on-1 tournaments
- Earn money from wins
- Win prize items
- Store items in a locker
- Equip power-up gear
- Sell locker items for money
- Visit the item shop
- Train at the practice facility
- Rest to recover stamina
- Track wins and losses
- View completed tournaments in the trophy case
- View scouting reports before matchups
- Unlock the final boss tournament

## Menu Options

When the game starts, the player can choose from several menu options:

1. View Player Info
2. View / Equip Locker Item
3. Enter Tournament
4. Sell Locker Item
5. Rest and Recover Stamina
6. View Final Boss Goal
7. Play Pickup Game
8. View Trophy Case
9. View Scouting Reports
10. Visit Item Shop
11. Practice Facility
12. Exit Game

## How the Game Works

Each 1-on-1 game is played to 15 points.

The player chooses from three offensive moves:

1. Drive to the basket
2. Pull-up jumper
3. Step-back three

When the opponent has the ball, the player chooses from three defensive options:

1. Play tight defense
2. Sag off
3. Go for steal

Each move has a scoring chance. The chance is affected by:

- Player boosts
- Opponent difficulty
- Stamina
- Equipped items
- Defensive choices

The game uses random numbers to decide whether shots go in or miss.

## Power-Up Items

The player can earn or buy items such as:

- Gatorade
- Shooting Sleeve
- Speed Sneakers
- Jump Sneakers
- Defensive Headband
- Ankle Braces
- Film Study Notebook
- Lockdown Badge
- Pro Level Sneakers

Some items improve shooting, speed, jumping, defense, or stamina.

## Stamina System

The player loses stamina during games.

Different moves use different amounts of stamina:

- Drives use more stamina
- Jumpers use a medium amount
- Step-back threes use less stamina
- Defensive choices also cost stamina

If stamina gets too low, it becomes harder to score and defend.

The player can recover stamina by resting or using stamina items.

## Practice Facility

The player can spend money to improve skills.

Practice options include:

- Shooting Workout
- Speed Workout
- Jump Training
- Defensive Slides

Each skill has a max boost limit, so the player cannot train forever.

## Java Concepts Used

This project uses several Java concepts from the apprenticeship, including:

- Classes and objects
- Constructors
- Getters and methods
- If statements
- Switch statements
- Loops
- ArrayList
- HashSet
- HashMap
- Random
- Scanner
- Encapsulation
- Basic project organization
- Helper methods
- Console output formatting

## Project Classes

The project includes these main classes:

- `Main`
- `Player`
- `Opponent`
- `Item`
- `Locker`
- `Tournament`
- `Game`
- `ConsoleEffects`

## How to Run the Game

1. Open the project in IntelliJ IDEA.
2. Open the `Main.java` file.
3. Click the green run button.
4. Follow the console instructions.
5. Choose menu options by typing the number and pressing Enter.

## Notes

This is a console-based Java game. It does not use a graphical interface.

The ASCII art title screen is used to make the console game feel more like a real game intro.