import CellRenders.AbsoluteCellRender;
import DSA.Stack;

import javax.swing.*;
import java.awt.*;

public class TetrominoPanel extends JPanel {
    private static final int CELL_SIZE = 30, OFFSET_Y = 40,SHADOW = CELL_SIZE/6, PADDING = 50;
    private final Iterable<Tetromino> collection;


    public TetrominoPanel(Iterable<Tetromino> collection) {
        this.collection = collection;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = PADDING;
        for (Tetromino element : collection) {
            TetrominoRender tetrominoRender = new TetrominoRender(element, new AbsoluteCellRender(CELL_SIZE,SHADOW));
            int x = (getWidth() - tetrominoRender.maxWidth() * CELL_SIZE)/2;
            tetrominoRender.draw(g, x, y,CELL_SIZE);
            int tetrominoHeight = tetrominoRender.height() * CELL_SIZE;
            y += tetrominoHeight+ OFFSET_Y;
        }
    }
}
