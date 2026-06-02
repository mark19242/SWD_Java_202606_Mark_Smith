# 1. Split values into individual names
to_be_changed = "John Glenn|Neil Armstrong|Sally Ride|Douglas Wheelock|Mae Jemison"

changed_values = to_be_changed.split("|")
print(changed_values)

# 2. Split lyrics using split()
lyrics = """
Katie Casey was baseball mad,
Had the fever and had it bad.
Just to root for the home town crew,
Ev'ry sou
Katie blew.
On a Saturday her young beau
Called to see if she'd like to go
To see a show, but Miss Kate said "No,
I'll tell you what you can do:"
"""

lyrics_split = lyrics.split("\n")
print(lyrics_split)

# 3. Split lyrics using something other than split()
changed_values = lyrics.splitlines()
print(changed_values)

# 4. Length of village name
long_village_name = "Llanfairpwllgwyngyllgogerychwyrndrobwllllantysiliogogogoch"

string_length = len(long_village_name)
print(string_length)

# 5. Remove spaces from path
my_path = ' /c/Users/instructor/Downloads/Submit Relating the Nonrelational Assessment Download May 10, 2021 917 AM '

my_folders = my_path.strip()
print(my_folders)

# 6. Mozart formatting
composers = "Beethoven,Ludwig von;Liszt,Franz;Mozart,Wolfgang;Copland,Aaron"

composers_split = composers.split(";")

third_composer = composers_split[2]

comma_position = third_composer.find(",")

last_name = third_composer[0:comma_position]
first_name = third_composer[comma_position + 1:]

third_composer_name = first_name + " " + last_name

print(third_composer_name)

# 7. Clean padded strings
left_padded = ' Operators are standing by'
right_padded = 'Call now '

message = right_padded.rstrip() + "! " + left_padded.lstrip()

print(message)

# 8. Old-style formatting
student_name = "Owen"
grade = 94.75
assignment_number = 12

print("Student name: %s, Assignment ID: %04d, Grade: %.2f%%" %
      (student_name, assignment_number, grade))

# 9. Zero padding
employee_id = "30"

employee_id_padded = employee_id.zfill(6)

print(employee_id_padded)

# 10. Raw string
print(r"\n represents a new line.")

# 11. Case conversions
i_want_to_yell = 'yeah'
I_NEED_TO_BE_QUIET = 'SHHHHH'
this_is_a_title = 'this is a title'
sWAPcASE = 'sWAPcASE'
capitalize_this = 'capitalize this'

print(i_want_to_yell.upper())
print(I_NEED_TO_BE_QUIET.lower())
print(this_is_a_title.title())
print(sWAPcASE.swapcase())
print(capitalize_this.capitalize())