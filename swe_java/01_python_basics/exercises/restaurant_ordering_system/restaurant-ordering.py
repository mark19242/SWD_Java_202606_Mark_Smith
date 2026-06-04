# ==========================================
# ReMarkable Eats Restaurant Ordering System
# ==========================================
#
# PSEUDOCODE
#
# START
# Display welcome message
# Display menu items and prices
#
# WHILE user is ordering:
#     Ask user for menu item
#     Ask user for quantity
#     Calculate item total
#     Add item to order
#     Ask if user wants another item
#
# Display order summary
#
# Ask if user wants another order
#
# IF yes:
#     Restart ordering process
# ELSE:
#     Display final summary
#
# END
#
# ==========================================

menu = {
    "burger": 5.99,
    "pizza": 8.49,
    "salad": 4.99,
    "drink": 1.99
}

all_orders = []


def calculate_total(price, quantity):
    return round(price * quantity, 2)


print("""
===================================
        ReMarkable Eats
   Every Meal is ReMarkable!
===================================
""")

place_another_order = "yes"

while place_another_order == "yes":

    current_order = []
    order_total = 0

    print("\nWelcome to ReMarkable Eats!")

    ordering = "yes"

    while ordering == "yes":

        print("\nMenu")
        print("------------------")

        for item, price in menu.items():
            print(f"{item.title()}: ${price}")

        choice = input("\nSelect an item: ").lower()

        if choice not in menu:
            print("Sorry, that item is not on the menu.")
            continue

        quantity = int(input(f"How many {choice}s would you like? "))

        item_total = calculate_total(menu[choice], quantity)

        current_order.append({
            "item": choice,
            "quantity": quantity,
            "total": item_total
        })

        order_total += item_total

        print(
            f"Added {quantity} {choice}(s) "
            f"for ${item_total:.2f}"
        )

        ordering = input(
            "\nWould you like to add another item? (yes/no): "
        ).lower()

    print("\n====================")
    print("ORDER SUMMARY")
    print("====================")

    for item in current_order:
        print(
            f"{item['quantity']} {item['item']}(s) "
            f"- ${item['total']:.2f}"
        )

    print("--------------------")
    print(f"Order Total: ${order_total:.2f}")

    all_orders.append({
        "items": current_order,
        "total": order_total
    })

    place_another_order = input(
        "\nWould you like to place another order? (yes/no): "
    ).lower()

print("\n===================================")
print("FINAL SESSION SUMMARY")
print("===================================")

grand_total = 0

for order_number, order in enumerate(all_orders, start=1):

    print(f"\nOrder #{order_number}")

    for item in order["items"]:
        print(
            f"{item['quantity']} {item['item']}(s) "
            f"- ${item['total']:.2f}"
        )

    print(f"Order Total: ${order['total']:.2f}")

    grand_total += order["total"]

print("\n===================================")
print(f"Overall Total For All Orders: ${grand_total:.2f}")
print("Thank you for dining at ReMarkable Eats!")
print("===================================")