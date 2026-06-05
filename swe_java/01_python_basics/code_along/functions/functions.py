# ==========================================
# Code Along: Functions
# ==========================================

# 1. Explain how to create functions.
# A function is a named block of code.
# You create a function with the def keyword.
# A function can accept parameters.
# A function can be called later by using its name.

def get_fizzbuzz(my_input):
    my_fizzbuzz_result = ""

    if my_input % 3 == 0 and my_input % 5 == 0:
        my_fizzbuzz_result = "Fizzbuzz"
    elif my_input % 3 == 0:
        my_fizzbuzz_result = "Fizz"
    elif my_input % 5 == 0:
        my_fizzbuzz_result = "Buzz"
    else:
        my_fizzbuzz_result = ""

    print(my_fizzbuzz_result)


# Calling the function
get_fizzbuzz(45)


# ==========================================

# 2. Explain how to handle exceptions.
# Exception handling helps prevent a program from crashing.
# We use try and except to handle errors.

def get_fizzbuzz_with_exceptions(my_input):
    my_fizzbuzz_result = ""

    try:
        if type(my_input) is not int:
            raise ValueError

        if my_input % 3 == 0 and my_input % 5 == 0:
            my_fizzbuzz_result = "Fizzbuzz"
        elif my_input % 3 == 0:
            my_fizzbuzz_result = "Fizz"
        elif my_input % 5 == 0:
            my_fizzbuzz_result = "Buzz"
        else:
            my_fizzbuzz_result = ""

        print(my_fizzbuzz_result)

    except ValueError:
        print("Input needs to be an integer")

    except:
        print("An exception has occurred")


# Calling the function with good and bad input
get_fizzbuzz_with_exceptions(15)
get_fizzbuzz_with_exceptions("Not a number")


# ==========================================

# 3. Identify if Python supports default values for parameters.
# Python does support default parameter values.
# If no value is passed in, the default value will be used.

def calc_exp(num, power=1):
    return num ** power


print(calc_exp(3))
print(calc_exp(3, 3))


# ==========================================

# 4. Explain why function overloading is not in Python.
# Python does not support function overloading like some other languages.
# If you create another function with the same name,
# Python replaces the old function with the new one.

def add_two_numbers(num1, num2):
    return num1 + num2


print(add_two_numbers(1, 2))


# This is a different function name so it will not overwrite the first one.
def add_three_numbers(num1, num2, num3):
    return num1 + num2 + num3


print(add_three_numbers(1, 2, 3))


# ==========================================

# 5. Explain what functions with magic parameters do.
# *args allows a function to accept many values.
# The values are stored like a tuple.

def add_many_numbers(*nums):
    total = 0

    for num in nums:
        total = total + num

    return total


print(add_many_numbers(1, 2))
print(add_many_numbers(1, 2, 3))
print(add_many_numbers(1, 2, 3, 4, 5))


# ==========================================

# 6. Explain what **kwargs does.
# **kwargs allows a function to accept named values.
# The values are stored like a dictionary.

def print_food_basket(**contents):
    if len(contents) > 0:
        for key, value in contents.items():
            print(key + ": " + value)
    else:
        print("The basket is empty!")


print_food_basket(
    maincourse="Coney Dog",
    dessert="Mackinac Island fudge",
    fruit="cherries",
    beverage="Faygo"
)

print_food_basket()


# ==========================================

# 7. Differentiate between local and global scope.
# Global variables are created outside of functions.
# Local variables are created inside of functions.
# Local variables usually cannot be used outside of the function.

global_name = "Mark"


def show_scope():
    local_name = "Rich"

    print("Global name inside function:", global_name)
    print("Local name inside function:", local_name)


show_scope()

print("Global name outside function:", global_name)

# This would cause an error because local_name only exists inside the function.
# print(local_name)


# ==========================================
# End of Code Along: Functions
# ==========================================