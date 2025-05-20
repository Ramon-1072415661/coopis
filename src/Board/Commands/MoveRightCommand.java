package Board.Commands;

import Board.Player;
import Board.TetrominoLogic.Tetromino;

public class MoveRightCommand extends MoveCommand implements Command {

    public MoveRightCommand(int columnStart, int columnEnd) {
        super( columnStart, columnEnd);
    }

    @Override
    public void execute(Grid grid, Player player) {
        Tetromino tetromino = player.tetromino;
        if (fitInGrid(1, tetromino) && canMoveWithoutCollision(grid,1, tetromino)) {
            tetromino.x += 1;
        }
    }
}
