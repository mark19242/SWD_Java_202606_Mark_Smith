// ========================================
// Part 1: String Manipulation
// ========================================

let message = "Welcome to JavaScript!"

// Print the length of the message
console.log("Message Length:", message.length)

// Print the first and last characters
console.log("First Character:", message.charAt(0))
console.log("Last Character:", message.charAt(message.length - 1))

// Convert to uppercase and lowercase
console.log("Uppercase:", message.toUpperCase())
console.log("Lowercase:", message.toLowerCase())

// Find the position of "JavaScript"
const javaScriptIndex = message.indexOf("JavaScript")
console.log('Position of "JavaScript":', javaScriptIndex)

// Extract "JavaScript" using substring()
const extractedWord = message.substring(
  javaScriptIndex,
  javaScriptIndex + "JavaScript".length,
)

console.log("Extracted Word:", extractedWord)

// Replace "JavaScript" with "Coding"
const modifiedMessage = message.replace("JavaScript", "Coding")

console.log("Modified Message:", modifiedMessage)

// ========================================
// Part 2: Working with the Math Object
// ========================================

// Generate a random number between 1 and 100
const randomNumber = Math.floor(Math.random() * 100) + 1
console.log("Random Number (1-100):", randomNumber)

// Square root of 144
console.log("Square Root of 144:", Math.sqrt(144))

// 3 to the power of 4
console.log("3^4:", Math.pow(3, 4))

// Absolute value of -25
console.log("Absolute Value of -25:", Math.abs(-25))

// Round 7.8 using different Math methods
console.log("Rounded (7.8):", Math.round(7.8))
console.log("Ceiled (7.8):", Math.ceil(7.8))
console.log("Floored (7.8):", Math.floor(7.8))
console.log("Truncated (7.8):", Math.trunc(7.8))

// Calculate cosine of 45 degrees
const radians = 45 * (Math.PI / 180)
console.log("Cosine of 45 degrees:", Math.cos(radians))
