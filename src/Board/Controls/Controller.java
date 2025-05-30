package Board.Commands;

import Board.Player;

public interface Controller {
    public void handleKey(Grid grid, int keyCode, Player player);
}
