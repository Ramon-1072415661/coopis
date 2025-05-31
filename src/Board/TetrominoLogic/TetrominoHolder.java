package Board.TetrominoLogic;

import Board.DSA.Stack;
import Board.Panel.Base.PanelObservable;
import Board.Panel.Base.PanelObserver;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoHolder implements Iterable<Tetromino>, PanelObservable {
    private static final int HOLDER_LIMIT = 2;
    private final ArrayList<PanelObserver> observers = new  ArrayList<>();
    private boolean swaped = false;
    private  Stack<Tetromino> stack;

    public TetrominoHolder() {
        this(new Stack<>());
    }
    public TetrominoHolder(Stack<Tetromino> stack) {
        this.stack = stack;

    }


    public Tetromino swap(Tetromino actual) {
        if(swaped){
            return actual;
        }
        try {
            Tetromino peek_tetromino = this.pop();
            TetrominoResources resources = TetrominoResources.getInstance();
            Tetromino tetrominoToAdd = resources.createDefaultCopyOf(actual);
            stack.add(tetrominoToAdd);
            swaped = true;
            notifyObservers();
            return peek_tetromino;
        } catch (NullPointerException e){
            return actual;
        }
    }
    public boolean isOnLimit(){
        return stack.size() == HOLDER_LIMIT;
    }
    public void insert(Tetromino actual) {
        TetrominoResources resources = TetrominoResources.getInstance();
        Tetromino tetrominoToInsert = resources.createDefaultCopyOf(actual);
        stack.add(tetrominoToInsert);
        notifyObservers();

    }
    public Tetromino pop(){
        return stack.pop();
    }
    public void invert(){
        if(this.isEmpty() || stack.size() < 2) return;
        Stack<Tetromino> new_stack = new Stack<>();
        for (Tetromino tetromino: stack) new_stack.add(tetromino);
        stack = new_stack;
        notifyObservers();
    }

    public void resetSwap(){
        swaped = false;
    }

    public boolean isEmpty(){
        return stack.get_list().isEmpty();
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
        for( PanelObserver observer : observers) {
            observer.update();
        }
    }
}
