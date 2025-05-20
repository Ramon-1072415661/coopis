package Board.DSA;

public class Node <T>{
    public T data;
    public Node<T> next = null;
    public Node<T> previous = null;

    public Node(T data) {
        this.data = data;
    }

}
