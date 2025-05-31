package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.Tetromino;
import Board.TetrominoLogic.TetrominoHolder;

public class SwapCommand implements Command {
    int initialPositionX;
    int initialPositionY = 0;

    public SwapCommand(int initialPositionX) {
        this.initialPositionX = initialPositionX;
    }

    @Override
    public void execute(Grid grid, Player player) {
        Tetromino tetromino = player.tetromino;
        TetrominoHolder holder = player.getHolder();
        tetromino = holder.swap(tetromino);
        if(player.tetromino == tetromino) return;
        player.tetromino = tetromino;
        tetromino.x = initialPositionX;
        tetromino.y = initialPositionY;
    }

}
