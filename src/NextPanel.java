import javax.swing.*;
import java.awt.*;

public class NextPanel extends JPanel {
    private TetrominoPanel<TetrominoQueue> panel;
    private static final int CELL_SIZE = 30,PADDING = 80, SHADOW = CELL_SIZE/6;
    public NextPanel(Player player) {
        panel = new TetrominoPanel<>(player.getQueue(),CELL_SIZE,PADDING,SHADOW);
        setBackground(new Color(30, 30, 30));
        setOpaque(true);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); // ou outro layout

        JLabel tittle = new JLabel();
        tittle.setText("NEXT");
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
    private Dimension getTetrominoPanelPreferedSize() {
        return new Dimension(6 * CELL_SIZE,10 * CELL_SIZE + PADDING);
    }
}
