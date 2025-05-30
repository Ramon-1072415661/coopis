package Board.Controls;

import Board.Player;

public interface Controller {
    public void handleKey(Grid grid, int keyCode, Player player);
}
