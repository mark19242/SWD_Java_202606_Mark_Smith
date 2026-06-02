# Exercise: Lists in Python
# Movie Night Snack Inventory

# Step 1: Initial Inventory
snacks = ["popcorn", "chocolate", "soda", "chips"]

print(snacks)
print("First snack:", snacks[0])
print("Last snack:", snacks[-1])

# Step 2: Changing Inventory
snacks[2] = "juice"

snacks.append("gummies")

print("Updated snack list:", snacks)

# Step 3: Organizing Snacks
chocolate = snacks.pop(1)

snacks.insert(0, chocolate)

print("Reorganized snack list:", snacks)

# Step 4: Slice & Dice
print("First three snacks:", snacks[:3])

print("All except last snack:", snacks[:-1])

# Step 5: Clean-Up
removed_snack = snacks.pop()

print("Removed snack:", removed_snack)

print("Final snack list:", snacks)