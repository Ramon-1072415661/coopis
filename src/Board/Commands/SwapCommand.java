package Board.Commands;

import Board.Player;

public class SwapCommand implements Command {

    @Override
    public void execute(Grid grid, Player player) {
        player.swapTetromino();
    }

}
