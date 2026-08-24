// ========================================
// Optional Chaining Exercise
// ========================================

const book = {
  title: "JavaScript Basics",
  author: {
    name: "John Doe",
    social: {
      twitter: "@johndoe",
    },
  },
}

// Access an existing nested property safely
console.log(book.author?.social?.twitter)

// Access a non-existent property safely
console.log(book.publisher?.name)

// ========================================
// Reflection Questions
// ========================================

// 1. What happens when accessing book.publisher?.name?
// It returns undefined because publisher does not exist.

// 2. How does optional chaining prevent errors?
// It checks whether publisher exists before trying to access name.

// 3. What would happen without ?.
// Trying to access book.publisher.name would cause a TypeError
// because publisher is undefined.
