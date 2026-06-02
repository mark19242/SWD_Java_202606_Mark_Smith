print("Welcome to the basic calculator program.")


def add(x, y):
    return x + y


def subtract(x, y):
    return x - y


def multiply(x, y):
    return x * y


def divide(x, y):
    return x / y


while True:
    print("\nChoose Operation:")
    print("1: Add Two Numbers")
    print("2: Subtract Two Numbers")
    print("3: Multiply Two Numbers")
    print("4: Divide Two Numbers")
    print("x: Exit")

    choice = input("Enter a choice (1, 2, 3, 4, or x): ")

    if choice == "x":
        print("Goodbye!")
        break

    if choice in ("1", "2", "3", "4"):

        first_num = float(input("Enter first number: "))
        second_num = float(input("Enter second number: "))

        if choice == "1":
            print("Result:", add(first_num, second_num))

        elif choice == "2":
            print("Result:", subtract(first_num, second_num))

        elif choice == "3":
            print("Result:", multiply(first_num, second_num))

        elif choice == "4":

            if second_num == 0:
                print("Error: Cannot divide by zero!")
            else:
                print("Result:", divide(first_num, second_num))

    else:
        print("I don't understand.")