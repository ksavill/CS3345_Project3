package project3;

import java.util.Iterator;

public class MyLinkedList<T> implements Iterable<T> {
    static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
            this.data = data;
            next = null;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    ", next=" + next +
                    '}';
        }
    }

    static class LinkedListIterator<T> implements Iterator<T> {
        Node<T> node;

        LinkedListIterator(Node<T> node) {
            this.node = node;
        }

        @Override
        public boolean hasNext() {
            return node != null;
        }

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

    @Override
    public Iterator<T> iterator() {
        return new LinkedListIterator<>(head);
    }

    Node<T> head;
    int size;

    MyLinkedList() {
        head = null;
        size = 0;
    }

    void insertAtHead(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
        size += 1;
    }

    public void add(T data) {
        insertAtHead(data);
    }

    public T removeAtHead() {
        T data = head.data;
        head = head.next;
        size -= 1;
        return data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder("[");
        Node<T> current = head;
        while (current != null) {
            stringBuilder.append(current.data);
            if (current.next != null) {
                stringBuilder.append(", ");
            }
            current = current.next;
        }

        return stringBuilder + "]";
    }
}
