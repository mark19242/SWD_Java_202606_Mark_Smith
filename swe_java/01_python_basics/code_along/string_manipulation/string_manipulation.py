# Valid strings
string1 = 'This is a valid string'
string2 = "This is also a valid string"

# Nested quotes
string3 = "'Always look on the bright side of life,' he said."
string4 = "\"Always look on the bright side of life,\" he said."

print(string3)
print(string4)

# Length
print(len(string4))

# Strip whitespace
filepath = ' /var/www/java/packages/are/too/long/here '
print(filepath)

filepath = filepath.strip()
print(filepath)

# Split path
folders = filepath.split('/')
print(folders)
print(type(folders))

# Join path
windowsPath = "\\".join(folders)
print(windowsPath)

# Multiline string
multi = '''
Now this is a story all about how
My life got flipped turned upside down
'''

print(multi)
print(multi.splitlines())

# Name formatting
reservation_name = "Froman, Abe"

char_location = reservation_name.find(",")
print(char_location)

last_name = reservation_name[0:char_location]
first_name = reservation_name[char_location + 1:len(reservation_name)]

first_name = first_name.lstrip()

formatted_name = first_name + " " + last_name

print(formatted_name)

# Case transformations
print(formatted_name.upper())
print(formatted_name.lower())
print(formatted_name.title())
print(formatted_name.swapcase())
print(formatted_name.capitalize())

# f-Strings
name = "Patrick"
age = 36

print(f"Hello, my name is {name} and I am {age} years old.")
print(f"{(35 + 84) / 7}")
print(f"{formatted_name.lower()} is a very funny man.")

a = 3
b = 9

print(f"We can count to {b} by {a}: {a} {a*2} {a*3}")

# Raw strings
print(r"\m/ Raw string... Rock on! \m/")
print(R"\m/ Raw string... Rock on! \m/")

# Count
pattern = "xx"

print("xxaxbxcxx".count(pattern))
print("xaxxbxxcx".count(pattern))
print("xaxxxbxxxcx".count(pattern))

# Replace
print("xaxxxbxxxcx".replace("x", "*"))
print("xaxxxbxxxcx".replace("x", "*", 3))

# zfill
print("10".zfill(3))