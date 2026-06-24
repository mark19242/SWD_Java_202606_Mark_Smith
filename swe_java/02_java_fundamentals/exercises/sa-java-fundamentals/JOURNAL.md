# Work Journal


## June 22, 2026

- Created the `StorageLockerApp` class.
- Initialized the `Scanner` for user input.
- Created a `String` array to represent the storage lockers.
- Added the `displayMainMenu()` method as the starting point of the application.
- Verified the project compiled successfully.
- Implemented the main menu using a `while` loop.
- Added menu options for renting, accessing, and releasing lockers.
- Allowed the user to exit the application by entering any key other than the listed menu options.

## June 23, 2026

### Rent a Locker
- Implemented the Rent a Locker feature.
- Created a method to find the next available locker.
- Generated a random four-digit PIN using `String.format()` to preserve leading zeros.
- Stored the PIN in the lockers array.
- Displayed the assigned locker number and PIN to the user.
- Tested the feature by renting multiple lockers.

### Access a Locker
- Implemented the Access a Locker feature.
- Prompted the user for a locker number and PIN.
- Validated the locker number against the lockers array.
- Checked that the locker was currently rented.
- Compared the entered PIN with the stored PIN.
- Displayed appropriate messages for successful access or invalid input.
- Tested the feature with both valid and invalid PINs.

### Release a Locker
- Implemented the Release a Locker feature.
- Prompted the user for the locker number and PIN.
- Validated the locker information before releasing it.
- Added a confirmation prompt before clearing the locker.
- Set the locker back to `null` when the release was confirmed.
- Tested both the confirmation and cancellation scenarios.

### Input Validation

- Added a helper method to safely read the locker number.
- Used a `try/catch` block to prevent the program from crashing when invalid input was entered.
- Returned `-1` for invalid input and displayed an appropriate error message.
- Updated the Access and Release features to use the new helper method.
- Tested the application with both valid and invalid user input.

### Menu Updates

- Added a helper method to determine whether all lockers are full.
- Updated the main menu to hide the "Rent a Locker" option when no lockers are available.
- Prevented users from selecting the Rent option after all lockers had been assigned.
- Tested the menu to verify it updated correctly based on locker availability.

## Conclusion

### Design Implementation Summary

I built a Storage Lockers application that allows a user to rent, access, and release lockers. 
I used a `String` array to keep track of the locker PINs and created separate methods for each feature to help keep the code organized and easier to follow.

### Prototype-Building Experience and Lessons Learned

This project helped me put together many of the Java concepts we have learned in class. 
I practiced using arrays, methods, loops, `if` statements, and `try/catch` blocks in one program. 
One thing I learned was that breaking a large project into smaller methods makes it much easier to build and debug. Overall, this assessment gave me more confidence in writing Java programs and solving problems one step at a time.