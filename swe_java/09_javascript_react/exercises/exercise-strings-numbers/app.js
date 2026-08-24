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
