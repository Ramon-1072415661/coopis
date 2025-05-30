package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;

public interface Command {
    void execute(Grid grid, Player player);
}
