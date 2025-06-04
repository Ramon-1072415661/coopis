import javax.swing.*;
import java.awt.*;

public class HolderPanel extends JPanel {
    private TetrominoPanel<TetrominoHolder> panel;
    private static final int CELL_SIZE = 30,PADDING = 80, SHADOW = CELL_SIZE/6;
    public HolderPanel(TetrominoHolder holder) {
        panel = new TetrominoPanel<>(holder,CELL_SIZE,PADDING,SHADOW);
        setOpaque(false);
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
        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();

        // Suaviza a borda
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Cor de fundo
        g2.setColor(new Color(0x21222c));
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 30, 30);

        g2.dispose();
    }


    // 6 times Cell_size for width is big enough to insert any piece with a little padding with parent Panel
    // Because the Tetrominos have, in max, 2 cells as height. So 5 times can hold 2 pieces (the max of the hold) with a little gap in bottom and upper parts.
    private Dimension getTetrominoPanelPreferedSize() {
        return new Dimension(6 * CELL_SIZE,5 * CELL_SIZE + PADDING);
    }
}
