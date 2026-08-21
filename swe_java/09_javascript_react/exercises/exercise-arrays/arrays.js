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

// ========================================
// Part 3: Looping Through an Array
// ========================================

// Print every element in the fruits array
for (const fruit of fruits) {
  console.log(fruit)
}

// Skip every other element
for (let i = 0; i < fruits.length; i += 2) {
  console.log(fruits[i])
}

// ========================================
// Part 4: Advanced Array Methods
// ========================================

// Find the index of "cherry"
const cherryIndex = fruits.indexOf("cherry")
console.log("Cherry index:", cherryIndex)

// Remove "cherry" from the array
fruits.splice(cherryIndex, 1)

// Create another array
const moreFruits = ["fig", "grape", "honeydew"]

// Combine the arrays
const finalFruits = fruits.concat(moreFruits)

// Print the final array
console.log("Final fruits:", finalFruits)
