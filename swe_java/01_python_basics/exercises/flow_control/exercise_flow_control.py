items = {
    "soda": 1.50,
    "chips": 2.00,
    "candy": 1.00
}

funds = float(input("Insert money: $"))

print("\nAvailable Items:")
for item, price in items.items():
    print(f"{item}: ${price}")

while funds > 0:

    choice = input("\nSelect an item: ").lower()

    if choice not in items:
        print("Item not found.")
        continue

    price = items[choice]

    if funds >= price:
        funds -= price
        print(f"{choice} dispensed!")
        print(f"Remaining balance: ${funds:.2f}")

        another = input("Would you like another item? (yes/no): ").lower()

        if another != "yes":
            break

    else:
        print("Insufficient funds.")

        add_money = input("Would you like to add more money? (yes/no): ").lower()

        if add_money == "yes":
            funds += float(input("Enter amount to add: $"))
        else:
            break

print(f"\nThank you for using the vending machine!")
print(f"Remaining balance: ${funds:.2f}")