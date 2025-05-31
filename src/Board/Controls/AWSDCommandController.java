package Board.Controls;

import Board.Controls.Commands.*;
import Board.Player;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

 class AWSDCommandController implements Controller {
    private final Map<Integer, Command> keyMap = new HashMap<>();

    public AWSDCommandController(int initialPosition, int columnStart, int columnEnd) {
        keyMap.put(KeyEvent.VK_A, new MoveLeftCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_D, new MoveRightCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_W, new RotateCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_E, new SwapCommand(initialPosition));
        keyMap.put(KeyEvent.VK_Q, new InsertCommand());
        keyMap.put(KeyEvent.VK_S, new DropCommand());
        keyMap.put(KeyEvent.VK_R, new InverseHoldCommand());
    }
    public void handleKey(Grid grid, int keyCode, Player player){
        Command cmd = keyMap.get(keyCode);
        if (cmd != null) cmd.execute(grid,player);
    }
}
