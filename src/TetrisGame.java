import javax.swing.*;
import Timer.PlacardPainel;
import Timer.TimerPanel;
import java.awt.*;

public class TetrisGame {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Tetris");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(0x533364));
        frame.getContentPane().setLayout(new GridBagLayout()); // simpler layout
        frame.setResizable(false);
        frame.setPreferredSize(new Dimension(1920,1080));
        TetrominoHolder universalHold = new TetrominoHolder();
        TetrominoQueue universalNext = new TetrominoQueue();

        Player p1 = new Player(2,universalNext,universalHold);
        Player p2 = new Player(7,universalNext,universalHold);
        Board board = new Board(p1,p2);

        HolderPanel holder = new HolderPanel(universalHold);
        LeftPanel leftPanel = new LeftPanel(holder, new TimerPanel());
        leftPanel.setPreferredSize( new Dimension(holder.getPreferredSize().width,board.getPreferredSize().height));

        NextPanel next = new NextPanel(universalNext);
        PlacardPainel panelTimer = new PlacardPainel();
        RigthPanel RightPanel = new RigthPanel(next,panelTimer);
        RightPanel.setPreferredSize( new Dimension(panelTimer.getPreferredSize().width,board.getPreferredSize().height));

        frame.add(Box.createHorizontalStrut(250));
        GridBagConstraints contraint = new GridBagConstraints();
        contraint.gridx = 1;
        contraint.gridy = 0;
        contraint.insets = new Insets(0,0,0,0);
        frame.add(leftPanel,contraint);

        contraint.gridx = 2;
        contraint.insets = new Insets(0,20,0,20);
        frame.add(board, contraint);

        contraint.gridx = 3;
        contraint.insets = new Insets(0,0,0,0);
        frame.add(RightPanel,contraint);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
