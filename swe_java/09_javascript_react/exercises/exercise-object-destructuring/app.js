// ========================================
// Object Destructuring Exercise
// ========================================

const book = {
  title: "The Great Gatsby",
  author: "F. Scott Fitzgerald",
  year: 1925,
}

// Access properties without destructuring
console.log(book.title, book.author, book.year)

// ========================================
// Refactor Using Object Destructuring
// ========================================

const { title, author, year } = book

console.log(title, author, year)

// ========================================
// Challenge: Rename Destructured Variables
// ========================================

const { title: bookTitle, author: writer } = book

console.log(bookTitle, writer)
