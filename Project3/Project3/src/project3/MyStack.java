package project3;

import java.util.Iterator;

public class MyStack<T> implements Iterable<T> {
    MyLinkedList<T> linkedList;

    public MyStack() {
        linkedList = new MyLinkedList<>();
    }

    @Override
    public Iterator<T> iterator() {
        return linkedList.iterator();
    }

    public void push(T data) {
        linkedList.insertAtHead(data);
    }

    public T peek() {
        return linkedList.head.data;
    }

    public T pop() {
        return linkedList.removeAtHead();
    }

    public boolean empty() {
        return linkedList.isEmpty();
    }

    public int size() {
        return linkedList.size;
    }

    @Override
    public String toString() {
        return linkedList.toString();
    }
}
