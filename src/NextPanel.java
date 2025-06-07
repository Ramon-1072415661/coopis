import javax.swing.*;
import java.awt.*;

public class NextPanel extends JPanel {

    public NextPanel(TetrominoQueue queue) {
        TetrominoPanel<TetrominoQueue> panel = new TetrominoPanel<>(queue, CELL_SIZE, PADDING, SHADOW);
        setBackground(new Color(30, 30, 30));
        setOpaque(true);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel tittle = new JLabel();
        tittle.setText("NEXT");
        tittle.setAlignmentX(CENTER_ALIGNMENT);
        Font currentFont = tittle.getFont();
        tittle.setFont(currentFont.deriveFont(Font.BOLD, 20f));
        tittle.setForeground(Color.WHITE);
        tittle.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(tittle);

        panel.setPreferredSize(getTetrominoPanelPreferedSize());
        add(panel);
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    private static final int CELL_SIZE = 30, PADDING = 80, SHADOW = CELL_SIZE / 6;

    private Dimension getTetrominoPanelPreferedSize() {
        return new Dimension(6 * CELL_SIZE, 10 * CELL_SIZE + PADDING);
    }
}
