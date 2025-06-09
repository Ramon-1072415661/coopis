package panel;

import managers.DisplayManager;
import tetromino.TetrominoQueue;
import utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Path2D;

public class NextPanel extends JPanel {
    private static final int PANEL_WIDTH_IN_CELLS = 5;
    private static final int PANEL_HEIGHT_IN_CELLS = 10;

    private final DisplayManager displayManager = DisplayManager.getInstance();
    private final int cellSize = displayManager.getCellSize();
    private final int cornerRadius = displayManager.getCornerRadius();

    public NextPanel(TetrominoQueue queue) {
        setOpaque(false);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        configurePanel(queue);
    }

    private void configurePanel(TetrominoQueue queue) {
        int verticalPadding = displayManager.getVerticalPadding();

        JLabel title = createTitleLabel(verticalPadding);
        add(title);

        TetrominoPanel<TetrominoQueue> tetrominoPanel = createTetrominoPanel(queue);
        add(tetrominoPanel);

        setBorder(BorderFactory.createEmptyBorder(0, 0, verticalPadding, 0));

        Dimension preferredSize = getTetrominoPanelPreferredSize();
        setPreferredSize(preferredSize);
        setMinimumSize(preferredSize);
        setMaximumSize(preferredSize);
    }

    private JLabel createTitleLabel(int topMargin) {
        JLabel title = new JLabel("Next");
        title.setAlignmentX(CENTER_ALIGNMENT);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 20f));
        title.setForeground(Color.WHITE);
        title.setBorder(BorderFactory.createEmptyBorder(topMargin, 0, 0, 0));
        return title;
    }

    private TetrominoPanel<TetrominoQueue> createTetrominoPanel(TetrominoQueue queue) {
        TetrominoPanel<TetrominoQueue> panel = new TetrominoPanel<>(queue);
        panel.setPreferredSize(getTetrominoPanelPreferredSize());
        return panel;
    }

    private Dimension getTetrominoPanelPreferredSize() {
        return new Dimension(PANEL_WIDTH_IN_CELLS * cellSize, PANEL_HEIGHT_IN_CELLS * cellSize + (displayManager.getVerticalPadding() * 5));
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
        path.moveTo(0, 0);
        path.lineTo(w - r, 0);
        path.quadTo(w, 0, w, r);
        path.lineTo(w, h - r);
        path.quadTo(w, h, w - r, h);
        path.lineTo(0, h);
        path.closePath();
        return path;
    }
}
