package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.TetrominoHolder;

public class InverseHoldCommand implements Command{
    @Override
    public void execute(Grid grid, Player player) {
        TetrominoHolder holder =  player.getHolder();
        holder.invert();

    }
}
