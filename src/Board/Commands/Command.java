package Board.Commands;

import Board.Player;

public interface Command {
    void execute(Grid grid, Player player);
}
