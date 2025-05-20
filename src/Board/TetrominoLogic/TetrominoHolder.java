package Board.TetrominoLogic;

import Board.DSA.Stack;

import java.util.ArrayList;

public class TetrominoHolder {
    private boolean swaped = false;
    private  Stack<Tetromino> stack;
    private int initial_x;
    private int initial_y;

    public TetrominoHolder(int initial_x,int initial_y) {
        stack = new Stack<Tetromino>();
        this.initial_y = initial_y;
        this.initial_x = initial_x;
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
        Tetromino poped_tetromino = stack.pop();
        poped_tetromino.x = initial_x;
        poped_tetromino.y = initial_y;
        return poped_tetromino;
    }

    public void resetSwap(){
        swaped = false;
    }

    public boolean isEmpty(){
        return stack.get_list().isEmpty();
    }
    public ArrayList<Tetromino> get_holded_tetrominos(){
        return  stack.get_list();
    }
}
