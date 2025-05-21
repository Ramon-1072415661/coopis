package DSA;

import java.util.ArrayList;

public class Stack <T> {
    private Node<T> first;
    private Node<T> last;
    private int size = 0;

    public Stack() {
        this.first = null;
        this.last = null;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void add(T new_data){
        this.add(new Node<>(new_data));
    }
    private void add(Node<T> new_node){
        if(isEmpty()) {
            first = last = new_node;
            size++;
            return;
        }
        last.next = new_node;
        new_node.previous = last;
        last = new_node;
        size++;
    }
    public T peek(){
        return last.data;
    }
    public T pop(){
        if (isEmpty()) throw new NullPointerException();
        if (last == first) {
            size--;
            return first.data;
        }

        Node<T> old_last = last;
        Node<T> new_last = last.previous;
        last.previous = null;
        new_last.next = null;
        last = new_last;
        size--;
        return old_last.data;
    }
    public ArrayList<T> get_list() {
        ArrayList<T> list = new ArrayList<>();
        Node<T> actual = first;

        for (int i = 0; i < size; i++) {
            list.add(actual.data);
            actual = actual.next;
        }

        return list;
    }

}
