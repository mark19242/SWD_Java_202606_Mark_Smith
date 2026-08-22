// ========================================
// Part 1: Function Basics
// ========================================

function greetUser(name) {
  console.log("Hello, " + name + "!")
}

greetUser("Mark")
greetUser("Rich")

// ========================================
// Part 2: Returning Values
// ========================================

function squareNumber(number) {
  return number * number
}

const squareOfFour = squareNumber(4)
const squareOfSeven = squareNumber(7)

console.log(squareOfFour)
console.log(squareOfSeven)
