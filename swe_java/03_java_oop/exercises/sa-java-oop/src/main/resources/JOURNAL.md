# Java OOP Summative Assessment Journal

**Student Name:**
**Date Started:** **Date Completed:**

> This journal documents the design decisions, technical implementation, and learning that took place during this project. Complete each section as you work — journaling *during* development produces better, more honest entries than reconstructing your reasoning after the fact. Every prompt below maps to a "Knowing of Things" discussion topic you'll be assessed on, so treat weak or empty answers here as a preview of a weak answer in your defense.

---

## 1. Solution Discussion and Functional Overview

*Goal: Be able to competently discuss your solution as a whole — what it does, how it's structured, and why you built it the way you did.*

### 1.1 Problem Summary
What problem does this application solve? Describe it in a few sentences, as if explaining it to someone who has never seen the requirements.

### 1.2 Functional Walkthrough
Walk through the application's main features (menu options, cart operations, checkout, etc.). For each feature, briefly note what it does and which class(es) are responsible for it.

| Feature | Responsible Class(es) | Notes |
|---|---|---|
| Display Cart | ShoppingCartController, ShoppingCartService, CartItem | The controller handles the menu option. The service gets the cart items and total. CartItem helps show each product, quantity, and line total. |
| Remove an Item | ShoppingCartController, ShoppingCartService | The controller asks the user which item and quantity to remove. The service checks if the item exists and only removes a valid quantity. |
| Add an Item | ShoppingCartController, ShoppingCartService, Product | The controller shows the available products and asks for a product id and quantity. The service adds the item to the cart or increases the quantity if it is already there. |
| Checkout | ShoppingCartController, ShoppingCartService | The controller displays the checkout summary. The service calculates the total, clears the cart, and returns the amount due. |
| Exit | ShoppingCartController | The controller stops the menu loop and exits the program cleanly. |

So far, the ShoppingCartController is responsible for the menu flow, but it does not handle the main cart rules by itself. When the user chooses an option, the controller sends the work to ShoppingCartService. 
This keeps the menu code separate from the cart logic and makes the project easier to test and explain.

### 1.3 Class Design Decisions
Describe your major classes and why you divided responsibilities the way you did. Include or reference any flow charts / class diagrams you created.
I am splitting my project into smaller folders so the code is easier to organize and understand. Each part of the project has its own job. The controller will handle the menu choices, 
the service will handle the shopping cart rules, the model classes will store the product and cart information, and the UI class will handle what the user sees and types into the console.

This should make the project easier to build, easier to test, and easier to explain.
### 1.4 Design Principles Applied
Briefly explain, in your own words, and note where each shows up in your code:

- **Single Responsibility Principle (SRP):** How did you decide what belonged in the controller/app class vs. a service class vs. a model class?
- **DRY (Don't Repeat Yourself):** Where did you notice repetition, and what did you do about it (or decide not to)?
- **YAGNI (You Aren't Gonna Need It):** Was there a feature, abstraction, or generalization you considered building but deliberately left out? Why?

---

## 2. Technical Implementations

*Goal: Be able to explain not just that you used these constructs, but why you chose them and how they behave.*

### 2.1 Use of List / ArrayList
- Where in the application is a `List` (or `ArrayList`) used?
- What is it storing, and why is a List the right structure for that data (as opposed to a Map, Set, or array)?
- Any notable methods or operations you relied on (sorting, searching, removal by index vs. by value, etc.)?

I used a List called cartItems in the ShoppingCartService class, and this List stores the items that the cashier adds to the shopping cart. 
It made sense for me to use a List because the cart can have multiple items, and I need to be able to loop through them, display them, add to them, and remove from them. 
I also used a List because the cart items can be shown in order, which makes it easier for the cashier to understand what is currently in the cart. 
I used methods like add(), remove(), clear(), isEmpty(), and a for loop to search through the cart items.


### 2.2 Use of Map / HashMap
- Where in the application is a `Map` (or `HashMap`) used?
- What are the keys and values, and why did a Map fit better than a List here?
- Did you run into anything related to key uniqueness, `equals()`/`hashCode()`, or iteration order worth noting?

I used a Map called inventory in the ShoppingCartService class. The key is the product id as a String, and the value is the Product object.
So, a Map made sense for inventory because each product needs a unique id. This lets the program quickly find a product when the cashier enters the product id. 
Instead of looping through every product in a List, the program can use the product id to look up the product directly.
One thing I had to keep in mind is that Map keys must be unique. If I used the same product id twice, the newer product would replace the old one.

### 2.3 Use of Interfaces
- What interface(s) did you define or implement?
- What contract does the interface establish, and what classes implement it?
- Why did an interface make sense here rather than a concrete class or inheritance?

I created a CartService interface and implemented it with the ShoppingCartService class.
The interface sets up a contract for what the cart service should be able to do. 
For example, it includes methods for adding an item, removing an item, getting the cart total, checking out, and checking if the cart is empty.
The interface is like a plan for the cart service. It says what actions the cart service must have, like adding items, removing items, and checking out. 
I feel like this helps keep the code organized and easier to test.

---

## 3. Discovery — Code Smells Encountered and Refactoring

*Goal: Show that you can recognize problems in your own code and describe how (or whether) you addressed them.*

### 3.1 Code Smells Identified
For each smell you noticed in your own code, log it here — even ones you chose not to fix.

| Smell | Where Found | Fixed? | Justification (if not fixed) |
|---|---|---|---|
| | | | |
| | | | |
| | | | |

### 3.2 Refactoring Log
Describe at least one refactor in detail:
- **Before:** What did the code look like, and what was wrong with it?
- **After:** What did you change?
- **Why:** What principle or smell drove the change (SRP violation, duplication, long method, large class, etc.)?

### 3.3 Revisiting SRP and DRY
Now that you've gone through a refactor, did your understanding of SRP or DRY change from what you wrote in Section 1.4? Note anything you'd do differently if starting over.

---

## 4. Unit Testing — Strategy, Implementation, and Challenges
*Goal: Be able to explain your testing approach, not just that tests exist.*

### 4.1 Testing Strategy
- What did you decide to test, and what did you intentionally leave untested (e.g., I/O, console prompts)? Why?
- Did you test the service/business logic layer directly, separate from the controller? How did your class design (or SRP decisions above) make that easier or harder?
  
I decided to test the ShoppingCartService class because that is where my main business logic is located. 
This class handles the rules for adding items, removing items, calculating the cart total, checking if the cart is empty, and checking out.
I did not focus on testing the App class, ShoppingCartController, or ConsoleIO right now because those classes mostly deal with starting the program, menu flow, and user input. 
I wanted my unit tests to focus on the cart rules instead of testing console prompts.
Separating my cart logic into a service class made testing easier, because I could test the cart behavior directly without having to run the full application.


### 4.2 Implementation Notes
- What framework/tools did you use (JUnit version, Mockito, etc.)?
- List a few representative test cases and what each one verifies.
- How did you handle edge cases (e.g., removing more items than exist in the cart, adding a quantity of zero)?

I used JUnit 5 for my unit tests. I created a ShoppingCartServiceTest class inside the test service package.
Some of my test cases check that an item can be added to the cart and that adding the same item again increases the quantity instead of creating a duplicate item. 
I also tested invalid situations, such as entering a product id that does not exist or trying to add a zero or negative amount. 
For removing items, I tested that removing part of an item decreases the quantity and removing the full quantity takes the item out of the cart. 
I also tested that the cart total is calculated correctly and that checkout returns the total amount due while emptying the cart.
I also used @BeforeEach so every test starts with a fresh ShoppingCartService and an empty cart. This helps keep the tests isolated from each other.

### 4.3 Challenges
- Did any part of your design make something hard to test? Did that reveal a design problem (a smell, an SRP violation) you hadn't noticed before?
- Did any tests fail and reveal an actual bug? What was it?
One challenge was deciding what should be tested, I took some advice from my pears. 
And, I decided not to test every console message because that would make the tests harder to write and less focused. 
Instead, I tested the service layer because that is where the important cart behavior happens.
The tests also helped confirm that my design was working well. Since the business logic was not trapped inside the controller, 
I was able to test adding, removing, totals, and checkout directly.
A specific issue I had to think through was the remove item logic. I had to make sure the program did not remove the whole cart item too early. 
If the cashier removes only part of the quantity, the item should stay in the cart with the updated quantity. 
The item should only be removed from the List when its quantity reaches zero. 
I added tests for both situations, and that helped confirm that the remove logic was working correctly.


---

## 5. Reflection and Future Enhancements

*Goal: Demonstrate honest self-assessment and the ability to think beyond the current submission.*

### 5.1 What Went Well
What part of this project are you most satisfied with, technically?

### 5.2 What You'd Do Differently
Knowing what you know now, what would you change about your approach — architecture, class design, testing, or otherwise?

### 5.3 Future Enhancements
If you had another week, what would you add or improve? (New feature, persistence, better error handling, more Map/List/Interface usage, etc.)

### 5.4 Open Questions
Is there anything about SRP, DRY, YAGNI, code smells, refactoring, or unit testing that you're still unsure about? Note it here — this is a good thing to raise before your defense, not during it.
