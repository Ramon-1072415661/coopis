package tetromino;

import dataStructures.Stack;
import interfaces.PanelObservable;
import interfaces.PanelObserver;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoHolder implements Iterable<Tetromino>, PanelObservable {
    private final ArrayList<PanelObserver> observers = new ArrayList<>();
    private boolean swapped = false;
    private Stack<Tetromino> stack;

    public TetrominoHolder() {
        this(new Stack<>());
    }

    public TetrominoHolder(Stack<Tetromino> stack) {
        this.stack = stack;
    }

    public Tetromino swap(Tetromino actual) {
        if (swapped || stack.isEmpty()) {
            return actual;
        }
        Tetromino peek_tetromino = this.pop();
        stack.add(actual);
        swapped = true;
        notifyObservers();
        return peek_tetromino;
    }

    public boolean insert(Tetromino actual) {
        if (stack.isEmpty() || stack.size() == 1) {
            stack.add(actual);
            notifyObservers();
            return false;
        }

        return true;
    }

    private Tetromino pop() {
        return stack.pop();
    }

    public void invert() {
        if (this.isEmpty() || stack.size() < 2) return;
        Stack<Tetromino> new_stack = new Stack<>();
        for (Tetromino tetromino : stack) new_stack.add(tetromino);
        stack = new_stack;
        notifyObservers();
    }

    public void resetSwap() {
        swapped = false;
    }

    public boolean isEmpty() {
        return stack.get_list().isEmpty();
    }

    public void reset() {
        stack = new Stack<>();
        resetSwap();
        notifyObservers();
    }

    @Override
    public Iterator<Tetromino> iterator() {
        return stack.iterator();
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
}
