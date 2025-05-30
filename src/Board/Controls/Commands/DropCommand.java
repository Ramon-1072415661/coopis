package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.Tetromino;

public class DropCommand implements Command{
    @Override
    public void execute(Grid grid, Player player) {
        Tetromino tetromino = player.tetromino;
        while (!grid.collides(0, 1, tetromino)) {
            tetromino.y++;
        }
    }
}
