import CellRenders.AbsoluteCellRender;
import DSA.Stack;

import javax.swing.*;
import java.awt.*;

public class TetrominoPanel<T extends Iterable<Tetromino> & PanelObservable> extends JPanel implements PanelObserver {
    private static final int OFFSET_Y = 20;
    private int cellSize,padding,shadow;
    private final T  collection;




    public TetrominoPanel( T collection, int cellSize, int padding, int shadow) {
        this.cellSize = cellSize;
        this.padding = padding/2;
        this.shadow = shadow;
        this.collection = collection;
        collection.addObserver(this);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int y = padding;
        for (Tetromino element : collection) {
            TetrominoRender tetrominoRender = new TetrominoRender(element, new AbsoluteCellRender(cellSize,shadow));
            int x = (getWidth() - tetrominoRender.maxWidth() * cellSize)/2;
            tetrominoRender.draw(g, x, y,cellSize);
            int tetrominoHeight = tetrominoRender.height() * cellSize;
            y += tetrominoHeight+ OFFSET_Y;
        }
    }

    @Override
    public void update() {
        repaint();
    }
}
