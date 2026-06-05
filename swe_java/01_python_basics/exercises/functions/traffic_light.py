# ==========================================
# Traffic Light Function Exercise
# ==========================================

# START

# Create red_light function
# Create yellow_light function
# Create green_light function

# Create traffic_light function
#     IF state is red
#         Call red_light()
#     ELIF state is yellow
#         Call yellow_light()
#     ELIF state is green
#         Call green_light()
#     ELSE
#         Display error message

# Create custom function

# Test functions

# END




# Red Light Function
def red_light():
    print("Stop! The light is red.")


# Yellow Light Function
def yellow_light():
    print("Caution! The light is yellow.")


# Green Light Function
def green_light():
    print("Go! The light is green.")


# Traffic Light Controller Function
def traffic_light(color):

    if color == "red":
        red_light()

    elif color == "yellow":
        yellow_light()

    elif color == "green":
        green_light()

    else:
        print("Error: Invalid traffic light color.")


# ==========================================
# Testing
# ==========================================

traffic_light("red")

traffic_light("yellow")

traffic_light("green")

traffic_light("blue")   # Invalid test

traffic_light("red")