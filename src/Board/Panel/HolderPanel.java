package Board.Panel;

import Board.Panel.Base.TetrominoPanel;
import Board.Player;
import Board.TetrominoLogic.TetrominoHolder;

import javax.swing.*;
import java.awt.*;

public class HolderPanel extends JPanel {
    private TetrominoPanel<TetrominoHolder> panel;
    private static final int CELL_SIZE = 30,PADDING = 80, SHADOW = CELL_SIZE/6;
    public HolderPanel(Player player) {
        panel = new TetrominoPanel<>(player.getHolder(),CELL_SIZE,PADDING,SHADOW);
        setBackground(new Color(30, 30, 30));
        setOpaque(true);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); // ou outro layout

        JLabel tittle = new JLabel();
        tittle.setText("HOLDER");
        tittle.setAlignmentX(CENTER_ALIGNMENT);
        Font currentFont = tittle.getFont();
        tittle.setFont(currentFont.deriveFont(Font.BOLD,20f));
        tittle.setForeground(Color.WHITE);
        tittle.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));
        add(tittle);

        panel.setPreferredSize(getTetrominoPanelPreferedSize());
        add(panel);
    }


    // 6 times Cell_size for width is big enough to insert any piece with a little padding with parent Panel
    // Because the Tetrominos have, in max, 2 cells as height. So 5 times can hold 2 pieces (the max of the hold) with a little gap in bottom and upper parts.
    public Dimension getTetrominoPanelPreferedSize() {
        return new Dimension(6 * CELL_SIZE,5 * CELL_SIZE + PADDING);
    }
}
