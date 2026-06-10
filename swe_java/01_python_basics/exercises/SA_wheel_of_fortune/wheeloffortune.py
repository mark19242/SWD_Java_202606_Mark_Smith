from pathlib import Path
import random
import config

# =========================
# Setup
# =========================

app_dir = Path(__file__).parent.resolve()


# =========================
# Functions
# =========================

def load_words():
    with open(f"{app_dir}/{config.dictionaryloc}", "r") as file:
        words = file.read().splitlines()

    return words


def load_wheel():
    with open(f"{app_dir}/{config.wheeltextloc}", "r") as file:
        wheel_values = file.read().splitlines()

    return wheel_values


def mask_phrase(phrase):
    masked_phrase = ""

    for letter in phrase:
        if letter == " ":
            masked_phrase += " "
        else:
            masked_phrase += "_"

    return masked_phrase


def create_players():
    players = {
        "Player 1": 0,
        "Player 2": 0,
        "Player 3": 0
    }

    return players


def spin_wheel(wheel):
    return random.choice(wheel)


def show_menu():
    print("\nChoose an action:")
    print("1. Spin Wheel")
    print("2. Buy Vowel")
    print("3. Solve Puzzle")

def update_hidden_phrase(phrase, guessed_letters):
    updated_phrase = ""

    for letter in phrase:
        if letter == " ":
            updated_phrase += " "
        elif letter.lower() in guessed_letters:
            updated_phrase += letter
        else:
            updated_phrase += "_"

    return updated_phrase

def get_next_player(player_names, current_player_index):
    current_player_index += 1

    if current_player_index >= len(player_names):
        current_player_index = 0

    return current_player_index

# =========================
# Main Program
# =========================

words = load_words()
wheel = load_wheel()
players = create_players()

phrase = random.choice(words)
hidden_phrase = mask_phrase(phrase)

player_names = list(players.keys())
current_player_index = 0
current_player = player_names[current_player_index]
guessed_letters = set()

print(f"\nCurrent Player: {current_player}")

print("\nPlayers:")
print(players)
print("\nHidden phrase:")
print(hidden_phrase)

game_running = True
round_number = 1

while game_running:

    print(f"\nCurrent Player: {current_player}")
    print(f"Phrase: {hidden_phrase}")
    print(players)

    show_menu()

    choice = input("\nEnter your choice: ")

    if choice == "1":

        spin_result = spin_wheel(wheel)

        print("\nWheel Spin:")
        print(spin_result)

        if spin_result == "BANKRUPT":
            players[current_player] = 0
            print(f"{current_player} went BANKRUPT!")
            current_player_index = get_next_player(player_names, current_player_index)
            current_player = player_names[current_player_index]

        elif spin_result == "LOSE A TURN":
            print(f"{current_player} lost a turn.")
            current_player_index = get_next_player(player_names, current_player_index)
            current_player = player_names[current_player_index]

        else:
            print(f"{current_player} spun ${spin_result}.")

            consonant = input("Guess a consonant: ").lower()

            if consonant in phrase.lower():
                guessed_letters.add(consonant)
                players[current_player] += int(spin_result)
                hidden_phrase = update_hidden_phrase(phrase, guessed_letters)
                
                if hidden_phrase == phrase:
                    print(f"{current_player} solved the puzzle!")
                    print(f"{current_player}'s score: ${players[current_player]}")

                    round_number += 1

                    if round_number > 2:
                        game_running = False
                    else:
                        phrase = random.choice(words)
                        hidden_phrase = mask_phrase(phrase)
                        guessed_letters = set()
                        print(f"\nStarting Round {round_number}")
                
                print(f"Correct! {current_player} earns ${spin_result}.")
                print(f"Phrase: {hidden_phrase}")
                print(players)

            else:
                print("Sorry, that consonant is not in the phrase.")
                current_player_index = get_next_player(player_names, current_player_index)
                current_player = player_names[current_player_index]

    elif choice == "2":

        if players[current_player] >= config.vowelcost:
            vowel = input("Choose a vowel: ").lower()

            if vowel in ["a", "e", "i", "o", "u"]:
                players[current_player] -= config.vowelcost

                if vowel in phrase.lower():
                    guessed_letters.add(vowel)
                    hidden_phrase = update_hidden_phrase(phrase, guessed_letters)
                    
                    if hidden_phrase == phrase:
                        print(f"{current_player} solved the puzzle!")
                        print(f"{current_player}'s score: ${players[current_player]}")

                        round_number += 1

                        if round_number > 2:
                            game_running = False
                        else:
                            phrase = random.choice(words)
                            hidden_phrase = mask_phrase(phrase)
                            guessed_letters = set()
                            print(f"\nStarting Round {round_number}")

                    print("Correct vowel!")
                    print(f"Phrase: {hidden_phrase}")
                    print(players)

                else:
                    print("That vowel is not in the phrase.")
                    current_player_index = get_next_player(player_names, current_player_index)
                    current_player = player_names[current_player_index]

            else:
                print("Invalid vowel.")
                current_player_index = get_next_player(player_names, current_player_index)
                current_player = player_names[current_player_index]

        else:
            print(f"You need at least ${config.vowelcost} to buy a vowel.")
            current_player_index = get_next_player(player_names, current_player_index)
            current_player = player_names[current_player_index]

    elif choice == "3":

        guess = input("Guess the full phrase: ").lower()

        if guess == phrase.lower():
            print(f"Correct! {current_player} solved the puzzle!")
            print(f"The phrase was: {phrase}")
            print(f"{current_player}'s score: ${players[current_player]}")

            round_number += 1

            if round_number > 2:
                game_running = False
            else:
                phrase = random.choice(words)
                hidden_phrase = mask_phrase(phrase)
                guessed_letters = set()

                print(f"\nStarting Round {round_number}")

        else:
            print("Sorry, that is not correct.")
            current_player_index = get_next_player(player_names, current_player_index)
            current_player = player_names[current_player_index]

    else:
        print("\nInvalid choice.")