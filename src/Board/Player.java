package Board;

import Board.Controls.Controller;
import Board.Controls.ControllerFactory;
import Board.Controls.Controls;
import Board.Controls.Grid;
import Board.TetrominoLogic.GetTetrominoLogic;
import Board.TetrominoLogic.Tetromino;
import Board.TetrominoLogic.TetrominoHolder;
import Board.TetrominoLogic.TetrominoQueue;

public class Player {
    public Tetromino tetromino;
    private final TetrominoHolder holder;
    private final GetTetrominoLogic next_tetromino_logic;
    private final Controller controller;
    public Player(int initialPosition, int columnStart, int columnEnd, Controls controls, TetrominoQueue queue, TetrominoHolder universalHold) {
        holder = universalHold;
        next_tetromino_logic = new GetTetrominoLogic(queue,initialPosition);
        this.getNextTretomino();
        controller =  ControllerFactory.getController(initialPosition,columnStart,columnEnd,controls);
    }

    public void getNextTretomino(){
        tetromino = next_tetromino_logic.nextTetromino();
        holder.resetSwap();
    }


    public void action(int key, Grid grid){
        controller.handleKey(grid,key,this);
    }

    public TetrominoHolder getHolder(){ return holder;}
}