from pathlib import Path
import random
import config

app_dir = Path(__file__).parent.resolve()


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

current_player = "Player 1"

print(f"\nCurrent Player: {current_player}")

words = load_words()
wheel = load_wheel()

phrase = random.choice(words)
hidden_phrase = mask_phrase(phrase)
players = create_players()

spin = random.choice(wheel)

print("\nPlayers:")
print(players)



print("Words loaded:")
print(words)

print("\nRandom phrase:")
print(phrase)

print("\nWheel loaded:")
print(wheel)

print("\nRandom spin:")
print(spin)

print("\nHidden phrase:")
print(hidden_phrase)