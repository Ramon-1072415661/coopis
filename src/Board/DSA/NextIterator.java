package Board.DSA;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NextIterator <T> implements Iterator<T> {
    private Node<T> current;

    public NextIterator(Node<T> current) {
        this.current = current;
    }

    @Override
    public boolean hasNext() {
        return current.next != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T item = current.data;
        current = current.next;
        return item;
    }
}
