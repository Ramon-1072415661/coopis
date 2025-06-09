package cellRenders;

import java.awt.*;

public class CellRenderer extends Render {
    public CellRenderer(int cellSize, int shadowBevel) {
        super(cellSize, shadowBevel);
    }

    @Override
    public void drawCell(Graphics g, int gridX, int gridY, Color color) {
        int x = gridX * cellSize;
        int y = gridY * cellSize;
        super.drawCell(g, x, y, color);
    }
}
