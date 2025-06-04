package Board.TetrominoLogic;

import Board.DSA.NextIterator;
import Board.DSA.Queue;
import Board.Panel.Base.PanelObservable;
import Board.Panel.Base.PanelObserver;
import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoQueue implements Iterable<Tetromino>, PanelObservable {
    private final int TETROMINO_QUANTITY = 5;
    private final ArrayList<PanelObserver> observers = new ArrayList<>();
    private Queue<Tetromino> queue = new Queue<>();

    public TetrominoQueue() {
        for (int i = 0; i < TETROMINO_QUANTITY; i++){
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