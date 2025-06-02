import javax.swing.*;

import javax.swing.*;
import java.awt.*;

public class TetrisGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x666b87));
        frame.setLayout(new BorderLayout()); // simpler layout
        frame.setResizable(false);
        Player p1 = new Player(2);
        Player p2 = new Player(7);
        PanelDisplay panel = new PanelDisplay(p1);
        PanelDisplay panel2 = new PanelDisplay(p2);
        frame.add(panel,BorderLayout.WEST);
        frame.add(panel2,BorderLayout.EAST);
        Board board = new Board(p1,p2);
        board.setPreferredSize(new Dimension(1920, 1080));
        frame.add(board, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
