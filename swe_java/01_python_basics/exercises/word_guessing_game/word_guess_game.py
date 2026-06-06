# START

# secret_word = "python"
# guesses_left = 7
# guessed_letters = empty set

# WHILE guesses_left > 0

#     display current word progress

#     get user guess
#     convert guess to lowercase

#     IF guess is one letter

#         IF letter in secret_word
#             add letter to guessed_letters

#         ELSE
#             guesses_left -= 1

#     ELSE IF guess equals secret_word
#         player wins
#         stop game

#     ELSE
#         guesses_left -= 1

#     IF all letters revealed
#         player wins
#         stop game

# END WHILE

# player loses

# END

# ------ You Got This -------

from pathlib import Path
import random

app_dir = Path(__file__).parent.resolve()

def read_file():
  with open(f'{app_dir}/words.txt', "r") as text:
       lines = text.readlines()

  return random.choice(lines).strip()


secret_word = read_file()

 

guesses_left = 7
guessed_letters = set()

def show_progress(secret_word, guessed_letters):
    display = ""

    for letter in secret_word:
        if letter in guessed_letters:
            display += letter + " "
        else:
            display += "_ "

    print(f"\nWord: {display}")
    print(f"Guesses left: {guesses_left}")
    print(f"Guessed letters: {guessed_letters}")


def play_game():
    global guesses_left

    while guesses_left > 0:
        show_progress(secret_word, guessed_letters)

        guess = input("Guess a letter or the full word: ").lower()

        if guess == "":
            print("Please enter a letter or word.")

        elif len(guess) == 1:
            if guess in guessed_letters:
                print("You already guessed that letter.")

            elif guess in secret_word:
                guessed_letters.add(guess)
                print("Good guess!")

            else:
                guessed_letters.add(guess)
                guesses_left -= 1
                print("Wrong letter.")

        elif guess == secret_word:
            print(f"You won! The word was {secret_word}.")
            return

        else:
            guesses_left -= 1
            print("Wrong word guess.")

        all_letters_found = True

        for letter in secret_word:
            if letter not in guessed_letters:
                all_letters_found = False

        if all_letters_found:
            print(f"You won! The word was {secret_word}.")
            return

    print(f"You lost! The word was {secret_word}.")


play_game()

