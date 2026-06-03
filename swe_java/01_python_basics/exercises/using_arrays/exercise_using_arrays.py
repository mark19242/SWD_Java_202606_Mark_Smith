# Exercise: Using Arrays in Python

# Import modules
import array
import numpy as np

# ----------------------------------
# Create the Initial Array
# ----------------------------------

temperatures = array.array('f', [72.5, 74.0, 69.8, 70.2, 73.1, 75.6, 71.3])

print("Original Array:")
print(temperatures)

# ----------------------------------
# Insertion and Deletion
# ----------------------------------

# Remove incorrect reading at index 3
temperatures.pop(3)

print("\nAfter Removing Incorrect Reading:")
print(temperatures)

# Insert corrected reading
temperatures.insert(3, 70.0)

print("\nAfter Inserting Correct Reading:")
print(temperatures)

# Append extra reading
temperatures.append(78.0)

print("\nAfter Appending 78.0:")
print(temperatures)

# ----------------------------------
# Modify Array Without Loops
# ----------------------------------

# Convert to list and add 1.0 to each value
temperatures = array.array(
    'f',
    [temp + 1.0 for temp in temperatures]
)

print("\nAfter Recalibration (+1.0):")
print(temperatures)

# ----------------------------------
# Further Analysis Using NumPy
# ----------------------------------

np_temperatures = np.array(temperatures)

average_temperature = np.mean(np_temperatures)

print("\nAverage Temperature:")
print(average_temperature)

# Convert Fahrenheit to Celsius
celsius_temperatures = (np_temperatures - 32) * 5 / 9

print("\nTemperatures in Celsius:")
print(celsius_temperatures)