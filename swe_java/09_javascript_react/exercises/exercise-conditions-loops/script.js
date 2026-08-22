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

// ========================================
// Part 2: Switch Statement - Day of the Week
// ========================================

const readline = require("readline")

const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
})

rl.question("Enter a number from 0 to 6: ", (answer) => {
  const dayNumber = Number(answer)

  switch (dayNumber) {
    case 0:
      console.log("Sunday")
      break
    case 1:
      console.log("Monday")
      break
    case 2:
      console.log("Tuesday")
      break
    case 3:
      console.log("Wednesday")
      break
    case 4:
      console.log("Thursday")
      break
    case 5:
      console.log("Friday")
      break
    case 6:
      console.log("Saturday")
      break
    default:
      console.log("Error: Please enter a number from 0 to 6.")
  }

  // ========================================
  // Part 3: While Loop - Rolling a Dice
  // ========================================

  let roll = 0

  while (roll !== 6) {
    roll = Math.floor(Math.random() * 6) + 1
    console.log("Dice roll:", roll)
  }

  rl.close()
})
