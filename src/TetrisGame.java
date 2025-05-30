import Board.Board;
import Board.Controls.Controls;
import Board.Panel.PanelDisplay;

import javax.swing.*;
import Board.Player;
import javax.swing.*;
import java.awt.*;

public class TetrisGame {
    private static final int ROWS = 20, COLS = 10, CELL_SIZE = 48, CELL_SHADOW_BEVEL = CELL_SIZE / 5;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x666b87));
        frame.setLayout(new BorderLayout()); // simpler layout
        frame.setResizable(false);
        Player player = new Player(2,0,COLS/2, Controls.WASD);
        PanelDisplay painel = new PanelDisplay(player);
        frame.add(painel,BorderLayout.WEST);
        Board board = new Board(player);
        board.setPreferredSize(new Dimension(1920, 1080));
        frame.add(board, BorderLayout.CENTER);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
