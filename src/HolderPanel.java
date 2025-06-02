import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class HolderPanel extends JPanel {
    private TetrominoPanel<TetrominoHolder> panel;
    private static final int CELL_SIZE = 30,PADDING = 80, SHADOW = CELL_SIZE/6;
    private static final int arcWidth = 50, arcHeight = 50, borderThickness = 3;
    private static final Color BACKGROUND_COLOR = new Color(0x21222c),borderColor = new Color(0x944BBB);
    public HolderPanel(Player player) {
        panel = new TetrominoPanel<>(player.getHolder(),CELL_SIZE,PADDING,SHADOW);
        setOpaque(true);
        setLayout(new BoxLayout(this,BoxLayout.Y_AXIS)); // ou outro layout
        JLabel tittle = new JLabel();
        tittle.setText("HOLD");
        tittle.setAlignmentX(CENTER_ALIGNMENT);
        Font currentFont = tittle.getFont();
        tittle.setFont(currentFont.deriveFont(Font.BOLD,22f));
        tittle.setForeground(Color.WHITE);
        tittle.setBorder(BorderFactory.createEmptyBorder(30,10,10,10));
        add(tittle);

        panel.setPreferredSize(getTetrominoPanelPreferedSize());
        add(panel);
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(BACKGROUND_COLOR);
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);

        g2.dispose();
    }

    @Override
    protected void paintBorder(Graphics g) {
        super.paintBorder(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(borderColor);
        g2.setStroke(new BasicStroke(borderThickness));
        int offset = borderThickness / 2;
        g2.drawRoundRect(offset, offset, getWidth() - borderThickness, getHeight() - borderThickness, arcWidth, arcHeight);
        g2.dispose();
    }

    @Override
    public boolean contains(int x, int y) {
        Shape round = new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), arcWidth, arcHeight);
        return round.contains(x, y);
    }
    // 6 times Cell_size for width is big enough to insert any piece with a little padding with parent Panel
    // Because the Tetrominos have, in max, 2 cells as height. So 5 times can hold 2 pieces (the max of the hold) with a little gap in bottom and upper parts.
    public Dimension getTetrominoPanelPreferedSize() {
        return new Dimension(6 * CELL_SIZE,5 * CELL_SIZE + PADDING);
    }
}
