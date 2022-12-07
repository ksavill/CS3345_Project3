package project3;

import java.util.Iterator;

// Implements a linked list data structure.
public class MyLinkedList<T> implements Iterable<T> {
    // Represents a node in the linked list.
    static class Node<T> {
        T data; // The data stored in the node.
        Node<T> next; // A reference to the next node in the list.

        // Constructs a new Node instance with the specified data.
        Node(T data) {
            this.data = data;
            next = null;
        }

        // Returns a string representation of the Node instance.
        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    // Implements an iterator that allows you to iterate over the elements in the linked list.
    static class LinkedListIterator<T> implements Iterator<T> {
        Node<T> node; // The current node in the iteration.

        // Constructs a new LinkedListIterator instance with the specified starting node.
        LinkedListIterator(Node<T> node) {
            this.node = node;
        }

        // Returns true if there are more elements to iterate over, false otherwise.
        @Override
        public boolean hasNext() {
            return node != null;
        }

        // Returns the next element in the iteration.
        @Override
        public T next() {
            if (hasNext()) {
                T data = node.data;
                node = node.next;
                return data;
            }
            return null;
        }
    }

    // Returns an iterator that allows you to iterate over the elements in the linked list.
    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    Node<T> head; // The head of the linked list.
    int size; // The number of elements in the linked list.

    // Constructs an empty linked list.
    MyLinkedList() {
        head = null;
        size = 0;
    }

    // Inserts the specified element at the head of the linked list.
    void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size += 1;
    }

    // Adds the specified element to the linked list.
    public void add(T data) {
        insertAtHead(data);
    }

    // Removes and returns the element at the head of the linked list.
    public T removeAtHead() {
        T data = head.data;
        head = head.next;
        size -= 1;
        return data;
    }

    // Returns true if the linked list is empty, false otherwise.
    public boolean isEmpty() {
        return head == null;
    }

    // Override the toString() method of the Object class to create a custom string representation of the LinkedList
    @Override
    public String toString() {
        // Create a new StringBuilder to build the string representation
        StringBuilder stringBuilder = new StringBuilder("[");
        // Get the head (first element) of the LinkedList
        Node<T> current = head;
        // Loop through the LinkedList until we reach the end
        while (current != null) {
            // Append the data of the current Node to the StringBuilder
            stringBuilder.append(current.data);
            // If the current Node has a next Node, append a comma and a space to the StringBuilder
            if (current.next != null) {
                stringBuilder.append(", ");
            }
            // Move to the next Node in the LinkedList
            current = current.next;
        }

        // Return the string representation of the LinkedList
        return stringBuilder + "]";
    }   
}
