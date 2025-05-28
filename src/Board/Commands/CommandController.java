package Board.Commands;

import Board.Player;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

public class CommandController {
    private final Map<Integer, Command> keyMap = new HashMap<>();

    public CommandController(int columnStart, int columnEnd) {
        keyMap.put(KeyEvent.VK_A, new MoveLeftCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_D, new MoveRightCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_W, new RotateCommand(columnStart,columnEnd));
        keyMap.put(KeyEvent.VK_G, new SwapCommand());
        keyMap.put(KeyEvent.VK_H, new InsertCommand());
        keyMap.put(KeyEvent.VK_S, new DropCommand());
    }
    public void handleKey(Grid grid, int keyCode, Player player){
        Command cmd = keyMap.get(keyCode);
        if (cmd != null) cmd.execute(grid,player);
    }
}
