package DSA;

public class Queue <T> {

    private Node<T> first;
    private Node<T> last;
    private int size;

    public Queue() {
        first = last = null;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void add(T new_data) {
        add(new Node<>(new_data));
    }
    private void add(Node<T> new_node) {
        if (isEmpty()) {
            first = last = new_node;
        } else {
            last.next = new_node;
            last = new_node;
        }
        size ++;
    }

    public T poll() {
        return first.data;
    }

    public T remove() {
        if (isEmpty()) throw new NullPointerException("Queue is empty.");
        Node<T> old_first = first;
        first = first.next;
        size --;
        return old_first.data;
    }

    public int getSize() {
        return size;
    }

    public Node<T> getFirst() {
        return first;
    }
}
