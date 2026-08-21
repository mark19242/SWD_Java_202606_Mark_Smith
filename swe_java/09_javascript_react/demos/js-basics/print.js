export function print(message) {
  if (typeof document !== "undefined") {
    // Running in a browser with an HTML document attached
    document.body.innerHTML += `<p>${message}</p>`
  } else {
    // Running natively in Node (or any environment without a DOM)
    console.log(message)
  }
}
