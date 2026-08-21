// ========================================
// Part 1: Declaring and Accessing Arrays
// ========================================

const fruits = ["apple", "banana", "cherry", "date"]

// Print the entire array
console.log(fruits)

// Print the first element
console.log(fruits[0])

// Print the last element
console.log(fruits[fruits.length - 1])

// ========================================
// Part 2: Modifying Arrays
// ========================================

// Replace "banana" with "blueberry"
fruits[1] = "blueberry"

// Add "elderberry" to the end
fruits.push("elderberry")

// Add "apricot" to the beginning
fruits.unshift("apricot")

// Remove and print the first element
const removedFirst = fruits.shift()
console.log("Removed first:", removedFirst)

// Remove and print the last element
const removedLast = fruits.pop()
console.log("Removed last:", removedLast)

// Print the updated array
console.log("Updated fruits:", fruits)
