package CellRenders;

import java.awt.*;

public class Render {
    private static final double LIGHT_MULTIPLIER = 1.3, DARK_MULTIPLIER = 0.7;
    final int cellSize;
    private final int shadowBevel;

    public Render(int cellSize, int shadowBevel) {
        this.cellSize = cellSize;
        this.shadowBevel = shadowBevel;
    }

    public void drawCell(Graphics g, int x, int y, Color color) {
        drawCellFace(g, x, y, color);
        drawTopEdge(g, x, y, color);
        drawLeftEdge(g, x, y, color);
        drawRightEdge(g, x, y, color);
        drawBottomEdge(g, x, y, color);
        drawCellOutline(g, x, y);
    }

    void drawCellFace(Graphics g, int x, int y, Color color) {
        g.setColor(color);
        g.fillRect(x, y, cellSize, cellSize);
    }

    void drawTopEdge(Graphics g, int x, int y, Color color) {
        Color lightShade = brighter(color);
        g.setColor(lightShade);

        int[] xPoints = {x, x + cellSize, x + cellSize - shadowBevel, x + shadowBevel};
        int[] yPoints = {y, y, y + shadowBevel, y + shadowBevel};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawLeftEdge(Graphics g, int x, int y, Color color) {
        Color lightShade = brighter(color);
        g.setColor(lightShade);

        int[] xPoints = {x, x + shadowBevel, x + shadowBevel, x};
        int[] yPoints = {y, y + shadowBevel, y + cellSize - shadowBevel, y + cellSize};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawRightEdge(Graphics g, int x, int y, Color color) {
        Color darkShade = darker(color);
        g.setColor(darkShade);

        int[] xPoints = {x + cellSize, x + cellSize, x + cellSize - shadowBevel, x + cellSize - shadowBevel};
        int[] yPoints = {y, y + cellSize, y + cellSize - shadowBevel, y + shadowBevel};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawBottomEdge(Graphics g, int x, int y, Color color) {
        Color darkShade = darker(color);
        g.setColor(darkShade);

        int[] xPoints = {x, x + cellSize, x + cellSize - shadowBevel, x + shadowBevel};
        int[] yPoints = {y + cellSize, y + cellSize, y + cellSize - shadowBevel, y + cellSize - shadowBevel};
        g.fillPolygon(xPoints, yPoints, 4);
    }

    private void drawCellOutline(Graphics g, int x, int y) {
        g.setColor(Color.BLACK);
        g.drawRect(x, y, cellSize, cellSize);
    }

    private Color brighter(Color color) {
        return new Color(
                Math.min(255, (int) (color.getRed() * LIGHT_MULTIPLIER)),
                Math.min(255, (int) (color.getGreen() * LIGHT_MULTIPLIER)),
                Math.min(255, (int) (color.getBlue() * LIGHT_MULTIPLIER))
        );
    }

    private Color darker(Color color) {
        return new Color(
                (int) (color.getRed() * DARK_MULTIPLIER),
                (int) (color.getGreen() * DARK_MULTIPLIER),
                (int) (color.getBlue() * DARK_MULTIPLIER)
        );
    }
}
