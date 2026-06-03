# Exercise: Flow Charting
# Morning Routine

# START

# Wake up

# Check the time

# If running late:
#     Get dressed
#     Grab coffee
# Else:
#     Workout
#     Shower
#     Eat breakfast

# Prepare apprenticeship materials

# Check internet connection

# If internet is working:
#     Join apprenticeship class
# Else:
#     Restart router
#     Recheck internet

# END

print("Morning Routine")

running_late = input("Are you running late? (yes/no): ")

if running_late.lower() == "yes":
    print("Get dressed")
    print("Grab coffee")
else:
    print("Workout")
    print("Shower")
    print("Eat breakfast")

internet_working = input("Is the internet working? (yes/no): ")

if internet_working.lower() == "yes":
    print("Join apprenticeship class")
else:
    print("Restart router")
    print("Recheck internet connection")

print("Have a great day!")

# Part 2: If Statement Example
# Pass or Fail

# START
# Input score
# If score >= 70:
#     Display PASS
# Else:
#     Display FAIL
# END

score = int(input("Enter your score: "))

if score >= 70:
    print("PASS")
else:
    print("FAIL")

# Part 3: Loop Example
# Rocket Launch Countdown

# START
# Set countdown to 10
# While countdown > 0:
#     Display countdown
#     countdown = countdown - 1
# Display LIFTOFF
# END

countdown = 10

while countdown > 0:
    print(countdown)
    countdown -= 1

print("LIFTOFF!")