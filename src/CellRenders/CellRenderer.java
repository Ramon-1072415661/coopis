package CellRenders;

import java.awt.Color;
import java.awt.Graphics;

public class CellRenderer  extends Render{
    public CellRenderer(int cellSize, int shadowBevel) {
        super(cellSize,shadowBevel);
    }

    @Override
    public void drawCell(Graphics g, int gridX, int gridY, Color color) {
        // Use cellSize to calculate the x and y positions from the grid position
        int x = gridX * cellSize;
        int y = gridY * cellSize;
        super.drawCell(g,x,y, color);
    }

}
