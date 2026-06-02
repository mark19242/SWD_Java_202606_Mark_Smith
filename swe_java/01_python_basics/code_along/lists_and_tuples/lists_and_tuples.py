# Lists and Tuples Code-Along

# Lists
our_goals = ["Assess", "Educate", "Advance"]
grade_point_averages = [3.5, 4.0, 3.75, 3.5, 3.6, 3.9]
student_counts = [8, 6, 7, 5, 3, 0, 9]

print(our_goals)
print(grade_point_averages)
print(student_counts)

# Tuples
games = ("Dominion", "Eschaton", "Spirit Island", "Coup")
addresses = (
    1234,
    "127.0.0.1",
    "684D:1111:222:3333:4444:5555:6:77",
    90210
)

print(games)
print(addresses)

# Indexing
print(our_goals[0])
print(addresses[1])

# Negative Indexing
print(grade_point_averages[-1])
print(games[-1])

# Slicing
print(student_counts[0:4])
print(games[1:])

# Looping Through a List
for goal in our_goals:
    print(goal)

# Looping Through a Tuple
for game in games:
    print(game)

# Modifying Lists
fruit_basket = ["apple", "pomegranate", "apricot", "blueberries"]

fruit_basket[1] = "pear"
fruit_basket.append("durian")
fruit_basket.insert(2, "longan")
fruit_basket.remove("blueberries")

print(fruit_basket)

# Membership Operators
print("blueberries" in fruit_basket)
print("Risk" not in games)

# List Comprehensions
evens = [num for num in range(0, 11) if num % 2 == 0]
print(evens)

squares = [x ** 2 for x in range(10)]
print(squares)

fizzbuzz_numbers = [x for x in range(1, 101) if x % 3 == 0 if x % 5 == 0]
print(fizzbuzz_numbers)

# Converting Data Types
string1 = "Hello, everyone!"
print(list(string1))

games_list = list(games)
print(games_list)

# Nested Lists
data_books = [
    ["title", "ISBN-10", "publishedYear", "price"],
    ["Python Data Science Handbook", "1491912057", 2016, 54.00],
    ["Hello World!", "161729702X", 2019, 39.99],
    ["Knowledge is Beautiful", "9780007427925", 2013, 33.99]
]

for book in data_books:
    print(book)

headers = data_books[0]

for book in data_books:
    if book[0] != "title":
        for count, detail in enumerate(book):
            print(f"{headers[count]}: {detail}")
        print("----")