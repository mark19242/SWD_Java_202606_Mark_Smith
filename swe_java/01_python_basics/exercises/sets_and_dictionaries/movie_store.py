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

add_movie("Rocky")

print(movies)

# Remove a Movie from the Collection
def remove_movie(title):
    movies.discard(title)
    print(f"{title} removed.")


remove_movie("Rocky")

print(movies)

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

