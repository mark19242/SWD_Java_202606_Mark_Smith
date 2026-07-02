package org.example;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class App {

    public static void main(String[] args) {

        // Step 2
        // Create one Box that holds an Integer.
        Box<Integer> integerBox = new Box<>("Number Box");

        // Create one Box that holds a String.
        Box<String> stringBox = new Box<>("Message Box");

        // Put values inside both boxes.
        integerBox.setContent(42);
        stringBox.setContent("Hello, Generics!");

        // Print the label and value from each box.
        System.out.println(integerBox.getLabel() + " contains: " + integerBox.getContent());
        System.out.println(stringBox.getLabel() + " contains: " + stringBox.getContent());

        System.out.println();

        // Step 3
        // Create an ArrayList that stores Box<Integer> objects.
        List<Box<Integer>> boxedIntegerList = new ArrayList<>();

        // Create three boxes that each hold a different number.
        Box<Integer> box1 = new Box<>("Box 1");
        box1.setContent(10);

        Box<Integer> box2 = new Box<>("Box 2");
        box2.setContent(20);

        Box<Integer> box3 = new Box<>("Box 3");
        box3.setContent(30);

        // Add the boxes to the ArrayList.
        boxedIntegerList.add(box1);
        boxedIntegerList.add(box2);
        boxedIntegerList.add(box3);

        // Loop through the ArrayList and print the contents of each box.
        System.out.println("ArrayList of Boxes:");

        for (Box<Integer> box : boxedIntegerList) {
            System.out.println(box.getLabel() + " contains: " + box.getContent());
        }

        System.out.println();

        // Step 4
        // Create a Queue that stores Box<String> objects.
        // A Queue processes items in FIFO order: First In, First Out.
        Queue<Box<String>> boxedStringQueue = new LinkedList<>();

        // Create three boxes that each hold a different word/phrase.
        Box<String> queueBox1 = new Box<>("Queue Box 1");
        queueBox1.setContent("First in Line");

        Box<String> queueBox2 = new Box<>("Queue Box 2");
        queueBox2.setContent("Second in Line");

        Box<String> queueBox3 = new Box<>("Queue Box 3");
        queueBox3.setContent("Third in Line");

        // Add the boxes to the queue in order.
        boxedStringQueue.add(queueBox1);
        boxedStringQueue.add(queueBox2);
        boxedStringQueue.add(queueBox3);

        // Process the queue.
        // poll() removes and returns the first item in the queue.
        System.out.println("Processing Queue:");

        while (!boxedStringQueue.isEmpty()) {
            Box<String> currentBox = boxedStringQueue.poll();
            System.out.println(currentBox.getLabel() + " contains: " + currentBox.getContent());
        }
    }
}