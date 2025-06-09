package dataStructures;

import java.util.NoSuchElementException;

public class Queue<T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public Queue() {
        first = last = null;
        size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T new_data) {
        add(new Node<>(new_data));
    }

    private void add(Node<T> newNode) {
        if (isEmpty()) {
            first = last = newNode;
        } else {
            last.next = newNode;
            last = newNode;
        }
        size++;
    }

    public T remove() {
        if (isEmpty()) throw new NoSuchElementException("Queue is empty.");
        T data = first.data;
        first = first.next;
        if (first == null) {
            last = null;
        }
        size--;
        return data;
    }

    public T poll() {
        return isEmpty() ? null : remove();
    }

    public Node<T> peek() {
        return isEmpty() ? null : first;
    }

    public int getSize() {
        return size;
    }
}
