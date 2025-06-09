package panel;

import cellRenders.AbsoluteCellRender;
import interfaces.PanelObservable;
import interfaces.PanelObserver;
import managers.DisplayManager;
import tetromino.Tetromino;
import tetromino.TetrominoRender;

import javax.swing.*;
import java.awt.*;

public class TetrominoPanel<T extends Iterable<Tetromino> & PanelObservable> extends JPanel implements PanelObserver {
    private static final int MAX_TETROMINOES = 4;

    private final DisplayManager displayManager = DisplayManager.getInstance();
    private final int cellSize = displayManager.getCellSize();
    private final int padding = displayManager.getVerticalPadding();
    private final int cellShadowBevel = displayManager.getCellShadowBevel();
    private final T collection;

    public TetrominoPanel(T collection) {
        this.collection = collection;
        collection.addObserver(this);
        setOpaque(false);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int y = padding;
        int drawn = 0;

        for (Tetromino element : collection) {
            if (drawn >= MAX_TETROMINOES) break;

            TetrominoRender render = new TetrominoRender(element, new AbsoluteCellRender(cellSize, cellShadowBevel));
            int renderWidth = render.maxWidth() * cellSize;
            int x = (getWidth() - renderWidth) / 2;

            render.draw(g2, x, y, cellSize);

            y += render.height() * cellSize + padding;
            drawn++;
        }

        g2.dispose();
    }

    @Override
    public void update() {
        repaint();
    }
}

