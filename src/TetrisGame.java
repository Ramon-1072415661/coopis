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

        Board board = new Board();
        board.setPreferredSize(new Dimension(1920, 1080));
        frame.add(board, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
