package Board.Commands;
import Board.TetrominoLogic.Tetromino;

public class MoveCommand {
    protected  int columnStart, columnEnd;

    public MoveCommand( int columnStart, int columnEnd) {
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }
    boolean fitInGrid(int dx, Tetromino tetromino){
        int width = tetromino.shape[0].length;
        return tetromino.x + dx >= columnStart && tetromino.x + dx + width <= columnEnd;
    }
    boolean canMoveWithoutCollision(Grid grid,int dx, Tetromino tetromino){
        return grid.canMoveWithoutCollision(dx,0,tetromino);
    }

}
