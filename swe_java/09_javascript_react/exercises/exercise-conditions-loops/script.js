// ========================================
// Part 1: If Statements - Even or Odd
// ========================================

// Generate a random number between 1 and 50
const randomNumber = Math.floor(Math.random() * 50) + 1

console.log("Random number:", randomNumber)

// Check if the number is even or odd
if (randomNumber % 2 === 0) {
  console.log(randomNumber + " is even.")
} else {
  console.log(randomNumber + " is odd.")
}
