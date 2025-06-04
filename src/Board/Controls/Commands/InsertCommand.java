package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.Tetromino;
import Board.TetrominoLogic.TetrominoHolder;

public class InsertCommand implements Command {
    private SwapCommand swap;

    public InsertCommand(int initialPosition) {
    swap = new SwapCommand(initialPosition);
    }

    @Override
    public void execute(Grid grid, Player player) {
        Tetromino tetromino = player.tetromino;
        TetrominoHolder holder = player.getHolder();
        if (holder.isOnLimit()) swap.execute(grid,player);
        holder.insert(tetromino);
        player.getNextTretomino();

    }
}
