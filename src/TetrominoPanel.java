import CellRenders.AbsoluteCellRender;
import DSA.Stack;

import javax.swing.*;
import java.awt.*;

public class TetrominoPanel<T extends Iterable<Tetromino> & PanelObservable> extends JPanel implements PanelObserver {
    private static final int CELL_SIZE = 30, OFFSET_Y = 40,SHADOW = CELL_SIZE/6, PADDING = 50;
    private final T  collection;


    public TetrominoPanel(T collection) {
        this.collection = collection;
        collection.addObserver(this);
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

    @Override
    public void update() {
        repaint();
    }
}
