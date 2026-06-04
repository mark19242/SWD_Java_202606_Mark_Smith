# ==========================================
# Exercise: Lists and Tuples in Python
# ==========================================

# Instructions:
#
# 1. Create a List
#    - Initialize an empty list to store grocery items.
#
# 2. Add Items to the List
#    - Create a function that takes an item name
#      and adds it to the grocery list.
#
# 3. View the List
#    - Create a function that displays all items.
#
# 4. Sort the List
#    - Create a function that sorts the list alphabetically.
#
# 5. Use Tuples
#    - Create a tuple containing example grocery items.
#    - Demonstrate accessing tuple elements.
#
# 6. Main Program Loop
#    - Allow the user to:
#        Add Items
#        View List
#        Sort List
#        Exit Program
#
# ==========================================

# Create an empty grocery list
grocery_list = []


# Create a tuple of example grocery items
example_items = ("Milk", "Eggs", "Bread")

# print("Example Grocery Tuple:")
# print("First Item:", example_items[0])
# print("Second Item:", example_items[1])
# print("Third Item:", example_items[2])
# print()

# Function to add an item
def add_item(item):
    grocery_list.append(item)
    print(f"{item} added to the grocery list.")

add_item("Milk")
print(grocery_list)

# Function to view the grocery list

def view_list():
  print("\nGrocery List:")

  for item in grocery_list:
      print(item)

view_list()

# Function to sort the grocery list
grocery_list = ["Bread", "Apples", "Milk"]

def sort_list():
  grocery_list.sort()
  print ("\nGrocery list sorted alphabetically.")

sort_list()

print(grocery_list)

# Main Program Loop
#
# WHILE program is running
#     Display menu
#     Ask user for choice
#
#     IF choice is 1
#         Add item
#
#     ELIF choice is 2
#         View list
#
#     ELIF choice is 3
#         Sort list
#
#     ELIF choice is 4
#         Exit program
#
#     ELSE
#         Display invalid choice message



while True:

    print ("\n==== Shopping Cart ====")
    print("1. Add Item")
    print("2. View List")
    print("3. Sort List")
    print("4. Exit")

    choice = input("Choose an option: ")

    if choice == "1":
        item = input("Enter an item: ")
        add_item(item)

    elif choice == "2":
        view_list()

    elif choice == "3":
        sort_list()

    elif choice == "4":
        print("Goodbye!")
        break

    else:
        print("Invalid choice.")