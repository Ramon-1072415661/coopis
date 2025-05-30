package Board.Controls;

import Board.Controls.Commands.*;
import Board.Player;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

 class ArrowCommandController implements Controller{
    private final Map<Integer, Command> keyMap = new HashMap<>();

     ArrowCommandController(int initialPosition, int columnStart, int columnEnd) {
        keyMap.put(KeyEvent.VK_LEFT, new MoveLeftCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_RIGHT, new MoveRightCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_UP, new RotateCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_M, new SwapCommand(initialPosition));
        keyMap.put(KeyEvent.VK_N, new InsertCommand());
        keyMap.put(KeyEvent.VK_DOWN, new DropCommand());
    }
    public void handleKey(Grid grid, int keyCode, Player player){
        Command cmd = keyMap.get(keyCode);
        if (cmd != null) cmd.execute(grid,player);
    }
}
