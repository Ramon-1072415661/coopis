import DSA.Stack;

import java.util.ArrayList;
import java.util.Iterator;

public class TetrominoHolder implements Iterable<Tetromino> {
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
            stack.add(actual);
            swaped = true;
            return peek_tetromino;
        } catch (NullPointerException e){
            return actual;
        }
    }

    public void insert(Tetromino actual) {
        stack.add(actual);
    }
    public Tetromino pop(){
        return stack.pop();
    }
    public void invert(){
        if(this.isEmpty() || stack.size() < 2) return;
        Stack<Tetromino> new_stack = new Stack<>();
        for (Tetromino tetromino: stack) new_stack.add(tetromino);
        stack = new_stack;
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
}
