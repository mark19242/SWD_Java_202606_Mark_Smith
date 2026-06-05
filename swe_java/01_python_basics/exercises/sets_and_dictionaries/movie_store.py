# ==========================================
# Movie Store Program
# ==========================================
#
# START
#
# Create a set of available movies
#
# Create a dictionary of rented movies
#
# WHILE program is running
#
#     Display menu
#
#     IF user chooses Add Movie
#         Add movie to set
#
#     ELIF user chooses Remove Movie
#         Remove movie from set
#
#     ELIF user chooses Check Movie
#         Check if movie exists in set
#
#     ELIF user chooses Rent Movie
#         Check if movie is available
#         Add rental to dictionary
#
#     ELIF user chooses Return Movie
#         Remove rental from dictionary
#
#     ELIF user chooses View Rentals
#         Display all rented movies
#
#     ELIF user chooses Exit
#         End program
#
# END


# === Create a set of movies ===
movies = {
    "The Matrix",
    "Avatar",
    "Pressure",
    "Obsession"
}

# Add New Movies to the Collection

def add_movie(title):
    movies.add(title)
    print(f"{title} added.")

# add_movie("Rocky")

# print(movies)

# Remove a Movie from the Collection
def remove_movie(title):
    movies.discard(title)
    print(f"{title} removed.")

# remove_movie("Rocky")

# print(movies)

# Check if a Movie is in the Collection
def check_movie(title):

    if title in movies:
        print(f"{title} is available.")

    else:
        print(f"{title} is not available.")


# Create a Dictionary for Rented Movies

rented_movies = {
    "Mark": "The Matrix",
    "Lisa": "Obsession",
    "Arlene": "Pressure",
    "Kanesha": "Avatar"
}

def view_movies():

    print("\nAvailable Movies:")

    for movie in movies:
        print(movie)


# Add a New Rented Movie Record

def rent_movie(member, title):

    if title in movies:

        rented_movies[member] = title

        print(f"{member} rented {title}")

    else:
        print("Movie not available.")

def return_movie(member):

    if member in rented_movies:

        del rented_movies[member]

        print("Movie returned.")

    else:
        print("No rental record found.")

def view_rentals():

    print("\nCurrent Rentals:")

    for member, movie in rented_movies.items():
        print(member, "-", movie)


# START

# Create Movie Set

# Create Rental Dictionary

# WHILE True

#     Display Menu

#     IF Add Movie
#         Add Movie To Set

#     ELIF Remove Movie
#         Remove Movie From Set

#     ELIF Check Movie
#         Check Movie Availability

#     ELIF View Movies
#         Display Movie Set

#     ELIF Rent Movie
#         Add Rental To Dictionary

#     ELIF Return Movie
#         Remove Rental From Dictionary

#     ELIF View Rentals
#         Display Dictionary

#     ELIF Exit
#         END PROGRAM

#     ELSE
#         Display Invalid Choice

# END WHILE

while True:

    print("\n===== ReMarkable Movie Store =====")
    print("1. Add Movie")
    print("2. Remove Movie")
    print("3. Check Movie")
    print("4. View Movies")
    print("5. Rent Movie")
    print("6. Return Movie")
    print("7. View Rentals")
    print("8. Exit")

    choice = input("Choose an option: ")

    if choice == "1":
        title = input("Movie title: ")
        add_movie(title)

    elif choice == "2":
        title = input("Movie title: ")
        remove_movie(title)

    elif choice == "3":
        title = input("Movie title: ")
        check_movie(title)

    elif choice == "4":
        view_movies()

    elif choice == "5":
        member = input("Member name: ")
        title = input("Movie title: ")
        rent_movie(member, title)

    elif choice == "6":
        member = input("Member name: ")
        return_movie(member)

    elif choice == "7":
        view_rentals()

    elif choice == "8":
        print("Goodbye!")
        break

    else:
        print("Invalid choice.")