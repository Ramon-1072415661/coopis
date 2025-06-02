import DSA.NextIterator;
import DSA.Queue;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoQueue implements Iterable<Tetromino>, PanelObservable {
    private final int TETROMINO_QUANTITY = 5;
    private final ArrayList<PanelObserver> observers = new ArrayList<>();
    private Queue<Tetromino> queue = new Queue<>();

    public TetrominoQueue(int initialXPosition) {
        for (int i = 0; i < TETROMINO_QUANTITY; i++){
            Tetromino tetromino = Tetromino.random();
            tetromino.x = initialXPosition;
            queue.add(tetromino);
        }
    }

    public void add(Tetromino tetromino) {
        queue.add(tetromino);
    }

    public Tetromino remove() {
        return queue.remove();
    }

    @Override
    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for( PanelObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public Iterator<Tetromino> iterator() {
        return new NextIterator<>(queue.getFirst());
    }
}