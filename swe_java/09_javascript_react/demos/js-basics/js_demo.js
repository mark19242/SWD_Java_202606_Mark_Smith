import { print } from "./print.js"

let greeting = "Hello"

print(greeting) // run environment agnostic print function

print(greeting + " World!")

// ----------------------------------------------------
// VARIABLES let & const ONLY
// ----------------------------------------------------

let x = 1
let y = 2
let z = "5"
let a = "Five"

// addition behavior
print(x + y)
print(x + z)
print(x + a)

// subtraction behavior
print(x - y)
print(x - z)
print(x - a)

// casting as a solution ??
let zStr = Number(z)

print(x + zStr)

// ...what about with true NaNs as an example
let aStr = Number(a)

print(x + aStr)

if (aStr) {
  print(x + aStr)
} else {
  print("I can't math that")
}

// ----------------------------------------------------
// LOOPS
// ----------------------------------------------------

let counter = 0

while (counter < 2) {
  print(counter++)
}

counter = 5

do {
  print(counter++)
} while (counter < 10)

for (let i = 0; i < 10; i++) {
  print(i + 1 + ") Option " + i)
}

// ----------------------------------------------------
// STRICT EQUALITY
// ----------------------------------------------------

if (1 === "1") {
  print("1 is loosely '1'")
  print("This works because of loose equality")
} else {
  print("But they are not STRICTLY equivalent")
}

// print("This works because of loose equality");

// ----------------------------------------------------
// ARRAYS
// ----------------------------------------------------

// DECLARE EMPTY ARRAYS AND ADDING ELEMENTS

const newArray = []

newArray.push("Bob")

print("newArray contains: " + newArray)

newArray.push("Mary")

print("newArray now contains: " + newArray)

// ----------------------------------------------------
// ARRAY LITERALS
// ----------------------------------------------------

let two

const mixedArray = [1, "one", 2, two, 5]

console.log(mixedArray)

print(mixedArray)

// ----------------------------------------------------
// USERS ARRAY
// ----------------------------------------------------

const users = ["Bob", "Mary", "Dave", "Jamie", "Mike"]

let acount = 0

// RETRIEVING INDEX

for (const user in users) {
  print("User " + ++acount + ": " + user)
}

acount = 0

// RETRIEVING VALUE

for (const user of users) {
  print("User " + ++acount + ": " + user)
}

// ----------------------------------------------------
// ARRAYS (Array.prototype) HAVE UTILITY METHODS - YAY
// ----------------------------------------------------

users.forEach((u) => {
  print(u + " is in array")
  print("Wow!, Partying with " + u)
})

// ----------------------------------------------------
// FILTER
// ----------------------------------------------------

const BUsers = users.filter((u) => {
  return u.startsWith("B")
})

print(BUsers)

// ----------------------------------------------------
// FIND
// ----------------------------------------------------

// let mFirstUserName = users.find((u) => u.toLowerCase().startsWith("m"))

print(mFirstUserName)

// How can we deal with multiple like entries in an array with find?

// POP ONLY WORKS ON THE ARRAY AS A STACK
// users.pop();
// users.pop();

// let mLastUserName = users.findLast((u) => u.toLowerCase().startsWith("m"))

if (users.indexOf(mFirstUserName) === users.lastIndexOf(mLastUserName)) {
  print("There is only one M name Highlander")
} else {
  print("There are at least 2 Ms")
}

print(mFirstUserName)
print(mLastUserName)

// ----------------------------------------------------
// REMOVE AN ELEMENT BY VALUE
// ----------------------------------------------------

// TO TRULY REMOVE AN ELEMENT BY VALUE...
// GET THE INDEX AND REMOVE IT.

// IMPORTANT:
// Rich has not finished the body of this loop yet.
// Leaving it active while empty would create an infinite loop
// because Mary and Mike are still inside the users array.

// while (
//   users.find((u) => u.toLowerCase().startsWith("m")) !== undefined
// ) {
//
// }

// ----------------------------------------------------
// FILTER - ORIGINAL ARRAY IS NOT MUTATED
// ----------------------------------------------------

const bUsers = users.filter((u) => !u.toLowerCase().startsWith("m"))

print("ORIGINAL - UNMUTATED!!")
print(users)

print("THE NEW FILTERED ARRAY")
print(bUsers)

// ----------------------------------------------------
// FIND / FINDLAST
// ----------------------------------------------------

let mFirstUserName = users.find((u) => u.toLowerCase().startsWith("m"))

print(mFirstUserName)

// How can we deal with multiple like entries in an array with find?
// POP ONLY WORKS ON THE ARRAY AS A STACK
// users.pop();
// users.pop();

let mLastUserName = users.findLast((u) => u.toLowerCase().startsWith("m"))

if (users.indexOf(mFirstUserName) === users.lastIndexOf(mLastUserName)) {
  print("There is only one M name Highlander")
} else {
  print("There are at least 2 Ms")
}

// ----------------------------------------------------
// REMOVE ELEMENTS BY VALUE
// ----------------------------------------------------

// ORIGINAL ARRAY BEFORE SPLICE
print("Original")
print(users)

while (users.find((u) => u.toLowerCase().startsWith("m")) !== undefined) {
  let user = users.find((u) => u.toLowerCase().startsWith("m"))

  let targetIndex = users.indexOf(user)

  users.splice(targetIndex, 1)
}

print("Sliced")
print(users)

// ----------------------------------------------------
// FUNCTIONS
// ----------------------------------------------------

function getSome(something) {
  return "Get some " + something
}

print(getSome("Lunch"))

print(getSome)

const altGetSome = getSome

print(altGetSome("Whatever else you want"))

// ----------------------------------------------------
// FUNCTION WITH MULTIPLE PARAMETERS
// ----------------------------------------------------

function nameStartsWith(value, char) {
  let lowerChar = char.toLowerCase()

  return value.toLowerCase().startsWith(lowerChar)
}

print(nameStartsWith("Alien Queen", "a"))

// ----------------------------------------------------
// REUSING OUR FUNCTION WITH FILTER
// ----------------------------------------------------

const noBs = users.filter((u) => nameStartsWith(u, "b"))

print(noBs)
