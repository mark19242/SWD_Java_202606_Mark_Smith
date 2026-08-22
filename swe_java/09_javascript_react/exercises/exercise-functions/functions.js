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

// ========================================
// Part 3: Multiple Parameters
// ========================================

function addNumbers(number1, number2) {
  return number1 + number2
}

const firstSum = addNumbers(10, 5)
const secondSum = addNumbers(3, 8)

console.log(firstSum)
console.log(secondSum)

// ========================================
// Part 4: Random Color Generator
// ========================================

let colors = ["red", "blue", "green", "yellow", "purple", "orange"]

function getRandomColor() {
  const randomIndex = Math.floor(Math.random() * colors.length)

  return colors[randomIndex]
}

console.log(getRandomColor())
console.log(getRandomColor())
console.log(getRandomColor())

// ========================================
// Part 5: Random Fortune Teller
// ========================================

let fortunes = [
  "You will have a great day!",
  "A surprise is waiting for you.",
  "Something exciting is coming soon.",
  "Be cautious with your decisions today.",
  "Happiness is around the corner.",
]

function tellFortune() {
  const randomIndex = Math.floor(Math.random() * fortunes.length)

  return fortunes[randomIndex]
}

console.log(tellFortune())
