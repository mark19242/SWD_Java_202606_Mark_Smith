# CSS Box Model Reflection

## Display Types

### What is the difference between inline, block, and inline-block elements?

Block elements normally start on a new line and take up the available width. Inline elements stay within the flow of text and only take up the space they need. Inline-block is kind of a mix of both because it can stay on the same line like an inline element, but I can also control its padding, width, and height more like a block element.

### How does using flex or grid display modes alter the traditional box model behaviors?

Flex and Grid change how the boxes are arranged on the page. Flexbox is useful for arranging items in one direction, such as a row or column. Grid can control rows and columns at the same time. The box model still applies to each element, but Flexbox and Grid give me more control over where those boxes are placed.

## Sizing and Spacing

### How do margin and padding influence the total size of an element?

Padding adds space inside the element between the content and the border. Margin adds space outside of the element between it and other elements. With `content-box`, padding and borders are added on top of the width that I set, which can make the final box larger than expected.

### In what scenarios might you choose `box-sizing: border-box` over `content-box`?

I would use `border-box` when I want the width I set in CSS to represent the actual width of the box. The padding and border are included inside that width, which makes the size easier to predict and can help prevent layout problems.

## Advanced Behaviors

### How does margin collapsing work in nested structures?

Vertical margins can sometimes combine instead of being added together. For example, if one element has a bottom margin and the next element has a top margin, the browser may use the larger margin instead of adding both together. A child's top margin can also collapse with its parent's margin when there is nothing separating them.

### What are the effects of negative margins, and when might they be useful?

A negative margin can pull an element closer to another element or move it into space that would normally be left empty. It can be useful when I need to make a small layout adjustment, but I would use it carefully because too much negative margin could cause elements to overlap or make the layout harder to understand.
