package dataStructures;

import java.util.ArrayList;
import java.util.Iterator;

public class Stack<T> implements Iterable<T> {
    private Node<T> bottom;
    private Node<T> top;
    private int size = 0;

    public Stack() {
        this.bottom = null;
        this.top = null;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void add(T new_data) {
        this.add(new Node<>(new_data));
    }

    private void add(Node<T> new_node) {
        if (isEmpty()) {
            bottom = top = new_node;
            size++;
            return;
        }
        top.next = new_node;
        new_node.previous = top;
        top = new_node;
        size++;
    }

    public T peek() {
        return top.data;
    }

    public T pop() {
        if (isEmpty()) throw new NullPointerException();
        if (top == bottom) {
            size--;
            return bottom.data;
        }

        Node<T> old_last = top;
        Node<T> new_last = top.previous;
        top.previous = null;
        new_last.next = null;
        top = new_last;
        size--;
        return old_last.data;
    }

    public ArrayList<T> get_list() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> actual = bottom;

        for (int i = 0; i < size; i++) {
            list.add(actual.data);
            actual = actual.next;
        }

        return list;
    }

    public int size() {
        return size;
    }


    @Override
    public Iterator<T> iterator() {
        return new StackIterator<>(top);
    }
}
