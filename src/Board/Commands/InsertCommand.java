package Board.Commands;

import Board.Player;

public class InsertCommand implements Command {
    @Override
    public void execute(Grid grid, Player player) {
        player.insertInHold();
    }
}
