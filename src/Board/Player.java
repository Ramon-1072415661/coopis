package Board;

import Board.Commands.Grid;
import Board.TetrominoLogic.GetTetrominoLogic;
import Board.TetrominoLogic.Tetromino;
import Board.TetrominoLogic.TetrominoHolder;
import Board.Commands.CommandController;
import Board.TetrominoLogic.TetrominoResources;

public class Player {
    public Tetromino tetromino;
    private final TetrominoHolder holder;
    private final GetTetrominoLogic next_tetromino_logic;
    private final CommandController controller;

    public Player(int initialPosition, int columnStart, int columnEnd) {
        holder = new TetrominoHolder();
        next_tetromino_logic = new GetTetrominoLogic(initialPosition);
        this.getNextTretomino();
        controller = new CommandController(initialPosition,columnStart,columnEnd);
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