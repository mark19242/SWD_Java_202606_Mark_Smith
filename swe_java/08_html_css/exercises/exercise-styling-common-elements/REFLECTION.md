# Styling Common Elements Reflection

## Task Questions

### What happens if you use a custom font that is not installed on a user's device?

If the custom font is not available, the browser will try to use the next font listed in the CSS font stack. That is why it is helpful to include fallback fonts so the text still displays correctly and stays readable.

### What is the difference between `list-style-type: none;` and `list-style-position: inside;`?

`list-style-type: none;` removes the bullet or number from the list completely. `list-style-position: inside;` keeps the bullet or number, but moves it inside the content area of the list item.

## Reflection Questions

### Why is it important to use fallback fonts in CSS?

Fallback fonts help make sure the page is still readable if the first font choice is not available on someone's computer. Instead of letting the browser choose something random, I can give it other font options to use.

### How do spacing and alignment impact the readability of text?

Good spacing makes text easier to follow and keeps the page from feeling crowded. Things like line height, letter spacing, word spacing, and alignment can make the content more comfortable to read. Too much or too little spacing can make the page harder to understand.

### How can pseudo-classes improve user experience with links?

Pseudo-classes give the user visual feedback while interacting with a link. For example, `:hover` can show that a link is clickable, `:focus` helps keyboard users see which link is selected, and `:active` shows feedback while the link is being clicked.

### Why should images be styled carefully to maintain aspect ratio?

Maintaining the aspect ratio keeps an image from looking stretched or squished. Using a controlled width with `height: auto` allows the image to resize while keeping its original proportions. Making the image responsive also helps prevent it from overflowing its container on smaller screens.
