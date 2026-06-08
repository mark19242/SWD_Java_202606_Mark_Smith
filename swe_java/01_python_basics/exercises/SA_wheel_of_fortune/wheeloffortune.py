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


# =========================
# Main Program
# =========================

words = load_words()
wheel = load_wheel()
players = create_players()

phrase = random.choice(words)
hidden_phrase = mask_phrase(phrase)

current_player = "Player 1"

print(f"\nCurrent Player: {current_player}")

print("\nPlayers:")
print(players)

print("\nRandom phrase:")
print(phrase)

print("\nHidden phrase:")
print(hidden_phrase)

show_menu()

choice = input("\nEnter your choice: ")
if choice == "1":

    spin_result = spin_wheel(wheel)

    print("\nWheel Spin:")
    print(spin_result)

    if spin_result == "BANKRUPT":
        players[current_player] = 0
        print(f"{current_player} went BANKRUPT!")

    elif spin_result == "LOSE A TURN":
        print(f"{current_player} lost a turn.")

    else:
        print(f"{current_player} spun ${spin_result}.")

elif choice == "2":

    print("\nBuying a vowel...")

elif choice == "3":

    print("\nSolving the puzzle...")

else:

    print("\nInvalid choice.")