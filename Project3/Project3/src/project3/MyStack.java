package project3;

import java.util.Iterator;

// Implements a stack data structure using a linked list.
public class MyStack<T> implements Iterable<T> {
    MyLinkedList<T> linkedList; // The linked list used to store the stack elements.

    // Constructs an empty stack.
    public MyStack() {
        linkedList = new MyLinkedList<>();
    }

    // Returns an iterator that allows you to iterate over the stack elements.
    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }

    // Pushes the specified element onto the top of the stack.
    public void push(T data) {
        linkedList.insertAtHead(data);
    }

    // Returns the element at the top of the stack without removing it.
    public T peek() {
        return linkedList.head.data;
    }

    // Removes and returns the element at the top of the stack.
    public T pop() {
        return linkedList.removeAtHead();
    }

    // Returns true if the stack is empty, false otherwise.
    public boolean empty() {
        return linkedList.isEmpty();
    }

    // Returns the number of elements in the stack.
    public int size() {
        return linkedList.size;
    }

    // Returns a string representation of the stack elements.
    @Override
    public String toString() {
        return linkedList.toString();
    }
}