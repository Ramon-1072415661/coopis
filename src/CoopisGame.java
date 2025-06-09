import panel.*;
import tetromino.TetrominoHolder;
import tetromino.TetrominoQueue;
import utils.Colors;

import javax.swing.*;
import java.awt.*;

public class CoopisGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(Colors.BACKGROUND);
        frame.setLayout(new GridBagLayout());
        frame.setResizable(true);

        // Create shared components
        TetrominoHolder universalHold = new TetrominoHolder();
        TetrominoQueue universalNext = new TetrominoQueue();

        Player p1 = new Player(2, universalNext, universalHold);
        Player p2 = new Player(7, universalNext, universalHold);
        Board board = new Board(p1, p2);

        // Create panels
        HolderPanel holder = new HolderPanel(universalHold);
        LeftPanel leftPanel = new LeftPanel(holder, new TimerPanel());
        leftPanel.setPreferredSize(new Dimension(holder.getPreferredSize().width, board.getPreferredSize().height));

        NextPanel next = new NextPanel(universalNext);
        ScoresPanel panelTimer = new ScoresPanel();
        RightPanel rightPanel = new RightPanel(next, panelTimer);
        rightPanel.setPreferredSize(new Dimension(panelTimer.getPreferredSize().width, board.getPreferredSize().height));

        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false);
        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        centerPanel.add(leftPanel, c);
        c.gridx = 1;
        centerPanel.add(board, c);
        c.gridx = 2;
        centerPanel.add(rightPanel, c);

        // Center the whole block inside the frame
        GridBagConstraints outer = new GridBagConstraints();
        frame.add(centerPanel, outer);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
