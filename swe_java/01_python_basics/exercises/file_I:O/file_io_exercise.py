# START

# Import pathlib and os

# Create text file

# Write data to text file

# Read text file

# Append data

# Read updated file

# Create binary file

# Write binary data

# Read binary file

# Delete binary file

# END

from pathlib import Path
import os

app_dir = Path(__file__).parent.resolve()


# Create and write to a text file

try:
    with open(f"{app_dir}/exercise_data.txt", "w") as file:
        file.write("1,apple,red\n")
        file.write("2,banana,yellow\n")
        file.write("3,cherry,red\n")
        file.write("4,date,brown\n")
        file.write("5,elderberry,black\n")

    print("Text file created successfully.")

except:
    print("Something went wrong while writing the text file.")

# Read text file

try:
    with open(f"{app_dir}/exercise_data.txt", "r") as file:
        lines = file.read().splitlines()

        print("\nOriginal File Contents:")

        for line in lines:
            print(line)

except:
    print("Something went wrong while reading the text file.")


# Append data to text file
try:
    with open(f"{app_dir}/exercise_data.txt", "a") as file:
        file.write("6,fig,purple\n")
        file.write("7,grape,green\n")

    print("Data appended successfully.")

except:
    print("Something went wrong while appending data.")

# Read updated file
try:
    with open(f"{app_dir}/exercise_data.txt", "r") as file:

        print("\nUpdated File Contents:")

        for line in file:
            print(line.strip())

except:
    print("Something went wrong while reading the updated file.")

# Create binary file and write binary data

try:
    binary_data = "101,carrot,orange\n102,potato,brown\n103,broccoli,green\n"

    with open(f"{app_dir}/exercise_data.bin", "wb") as file:
        file.write(bytes(binary_data, "utf-8"))

    print("\nBinary file created successfully.")

except:
    print("Something went wrong while writing the binary file.")

# Read binary file

try:
    with open(f"{app_dir}/exercise_data.bin", "rb") as file:
        binary_contents = file.read()

        print("\nBinary File Contents:")
        print(binary_contents.decode("utf-8"))

except:
    print("Something went wrong while reading the binary file.")

# Delete binary file

try:
    os.remove(f"{app_dir}/exercise_data.bin")
    print("Binary file deleted successfully.")

except:
    print("Something went wrong while deleting the binary file.")