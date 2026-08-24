// ========================================
// DOM Manipulation Exercise
// ========================================

// Get references to the buttons and the list
const changeColorBtn = document.getElementById("changeColorBtn")
const addItemBtn = document.getElementById("addItemBtn")
const itemList = document.getElementById("itemList")

let itemCount = 0

// Change background color function
changeColorBtn.addEventListener("click", () => {
  document.body.style.backgroundColor = "lightblue"
})

// Add list item function
addItemBtn.addEventListener("click", () => {
  itemCount++

  const newItem = document.createElement("li")

  newItem.textContent = "List Item " + itemCount

  itemList.appendChild(newItem)
})
