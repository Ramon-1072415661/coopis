package tetromino;

import dataStructures.NextIterator;
import dataStructures.Queue;
import interfaces.PanelObservable;
import interfaces.PanelObserver;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoQueue implements Iterable<Tetromino>, PanelObservable {
    private final int TETROMINO_QUANTITY = 5;
    private final ArrayList<PanelObserver> observers = new ArrayList<>();
    private Queue<Tetromino> queue = new Queue<>();

    public TetrominoQueue() {
        for (int i = 0; i < TETROMINO_QUANTITY; i++) {
            Tetromino tetromino = Tetromino.random();
            queue.add(tetromino);
        }
    }

    public void add(Tetromino tetromino) {
        queue.add(tetromino);
        notifyObservers();
    }

    public Tetromino remove() {
        notifyObservers();
        return queue.remove();
    }

    public void reset() {
        queue = new Queue<>();
        for (int i = 0; i < TETROMINO_QUANTITY; i++) {
            Tetromino tetromino = Tetromino.random();
            queue.add(tetromino);
        }
        notifyObservers();
    }

    @Override
    public void addObserver(PanelObserver observer) {
        observers.add(observer);
    }

    @Override
    public void notifyObservers() {
        for (PanelObserver observer : observers) {
            observer.update();
        }
    }

    @Override
    public Iterator<Tetromino> iterator() {
        return new NextIterator<>(queue.peek());
    }
}