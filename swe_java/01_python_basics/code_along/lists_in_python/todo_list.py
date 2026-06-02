# Step 1: Create an Empty List
todo_list = []
print("Your to-do list:", todo_list)

# Step 2: Append Items to the List
todo_list.append("Buy groceries")
todo_list.append("Finish homework")
todo_list.append("Call mom")
print("Updated list:", todo_list)

# Step 3: Insert an Item at a Specific Position
todo_list.insert(1, "Pay bills")
print("After inserting a task:", todo_list)

# Step 4: View Items Using Indexing and Slicing
print("First task:", todo_list[0])
print("Last two tasks:", todo_list[-2:])

# Step 5: Mark a Task as Done (Pop it Out)
done_task = todo_list.pop(2)
print("You completed:", done_task)
print("To-do list after removal:", todo_list)

# Step 6: Modify an Existing Task
todo_list[0] = "Buy groceries and snacks"
print("After modifying a task:", todo_list)

# Step 7: Bonus – Print Tasks One by One
print("Here's what you still need to do:")
for task in todo_list:
    print("-", task)