package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.Tetromino;
import Board.TetrominoLogic.TetrominoHolder;
import Board.TetrominoLogic.TetrominoResources;

import javax.naming.SizeLimitExceededException;

public class InsertCommand implements Command {
    @Override
    public void execute(Grid grid, Player player) {
        Tetromino tetromino = player.tetromino;
        TetrominoHolder holder = player.getHolder();
        if (holder.isOnLimit()) return;
        try {
            TetrominoResources resources = TetrominoResources.getInstance();
            int position = resources.tetrominoPosition(tetromino);
            Tetromino tetrominoToHold = new Tetromino(resources.getShapes()[position],tetromino.color);
            holder.insert(tetrominoToHold);
            player.getNextTretomino();
        }
        catch (ClassNotFoundException e) {
            holder.insert(tetromino);
            player.getNextTretomino();
        }
    }
}
