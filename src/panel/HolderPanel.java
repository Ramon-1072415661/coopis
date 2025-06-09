package panel;

import managers.DisplayManager;
import tetromino.TetrominoHolder;
import utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class HolderPanel extends JPanel {
    private final DisplayManager displayManager = DisplayManager.getInstance();
    private final int cellSize = displayManager.getCellSize();
    private final int cornerRadius = displayManager.getCornerRadius();

    public HolderPanel(TetrominoHolder holder) {
        TetrominoPanel<TetrominoHolder> panel = new TetrominoPanel<>(holder);

        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Hold");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(title);

        panel.setPreferredSize(getTetrominoPanelPreferredSize());
        add(panel);

        setMaximumSize(getPreferredSize());
        setMinimumSize(getPreferredSize());
    }

    private Dimension getTetrominoPanelPreferredSize() {
        return new Dimension(5 * cellSize, 6 * cellSize);
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Path2D path = getPath2D();

        g2.setColor(Colors.PANELS_BACKGROUND);
        g2.fill(path);
        g2.dispose();

        super.paintComponent(g);
    }

    private Path2D getPath2D() {
        int w = getWidth();
        int h = getHeight();
        int r = cornerRadius;

        // Build a custom shape with rounded top-left and bottom-left corners
        Path2D path = new Path2D.Float();
        path.moveTo(r, 0);
        path.quadTo(0, 0, 0, r);
        path.lineTo(0, h - r);
        path.quadTo(0, h, r, h);
        path.lineTo(w, h);
        path.lineTo(w, 0);
        path.closePath();
        return path;
    }
}
