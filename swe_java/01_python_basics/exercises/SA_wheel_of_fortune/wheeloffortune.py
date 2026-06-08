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


words = load_words()
wheel = load_wheel()

phrase = random.choice(words)
spin = random.choice(wheel)

print("Words loaded:")
print(words)

print("\nRandom phrase:")
print(phrase)

print("\nWheel loaded:")
print(wheel)

print("\nRandom spin:")
print(spin)