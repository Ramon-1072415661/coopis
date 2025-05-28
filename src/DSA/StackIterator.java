package DSA;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class StackIterator <T> implements Iterator<T> {
    private Node<T> current;

    StackIterator(Node<T> top) {
        this.current = top;
    }

    @Override
    public boolean hasNext() {
        return current != null;
    }

    @Override
    public T next() {
        if (!hasNext()) throw new NoSuchElementException();
        T item = current.data;
        current = current.previous;
        return item;
    }
}
