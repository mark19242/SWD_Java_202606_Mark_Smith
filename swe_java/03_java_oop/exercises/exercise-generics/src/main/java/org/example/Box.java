package org.example;

// Step 1 + Bonus
// This is a generic class.
// The T is a placeholder for whatever type we want the box to hold.
public class Box<T> {

    // This is the actual value stored inside the box.
    private T content;

    // Bonus: this gives each box a name/label.
    private String label;

    // Empty constructor so we can still create a box with new Box<>()
    public Box() {
    }

    // Constructor that lets us name the box right away.
    public Box(String label) {
        this.label = label;
    }

    // Gets the value stored inside the box.
    public T getContent() {
        return content;
    }

    // Sets the value stored inside the box.
    public void setContent(T content) {
        this.content = content;
    }

    // Gets the label/name of the box.
    public String getLabel() {
        return label;
    }

    // Sets the label/name of the box.
    public void setLabel(String label) {
        this.label = label;
    }
}