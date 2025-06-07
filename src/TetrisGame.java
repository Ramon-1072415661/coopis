import Timer.PlacardPanel;
import Timer.TimerPanel;

import javax.swing.*;
import java.awt.*;

public class TetrisGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x533364));
        frame.getContentPane().setLayout(new GridBagLayout()); // simpler layout
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1920, 1080));
        TetrominoHolder universalHold = new TetrominoHolder();
        TetrominoQueue universalNext = new TetrominoQueue();

        Player p1 = new Player(2, universalNext, universalHold);
        Player p2 = new Player(7, universalNext, universalHold);
        Board board = new Board(p1, p2);

        HolderPanel holder = new HolderPanel(universalHold);
        LeftPanel leftPanel = new LeftPanel(holder, new TimerPanel());
        leftPanel.setPreferredSize(new Dimension(holder.getPreferredSize().width, board.getPreferredSize().height));

        NextPanel next = new NextPanel(universalNext);
        PlacardPanel panelTimer = new PlacardPanel();
        RightPanel rightPanel = new RightPanel(next, panelTimer);
        rightPanel.setPreferredSize(new Dimension(panelTimer.getPreferredSize().width, board.getPreferredSize().height));

        GridBagConstraints constraint = new GridBagConstraints();
        constraint.gridx = 0;
        constraint.gridy = 0;
        constraint.insets = new Insets(0, 250, 0, 20);
        frame.add(leftPanel, constraint);

        constraint.gridx = 1;
        constraint.insets = new Insets(0, 20, 0, 20);
        frame.add(board, constraint);

        constraint.gridx = 2;
        constraint.insets = new Insets(0, 20, 0, 250);
        frame.add(rightPanel, constraint);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
