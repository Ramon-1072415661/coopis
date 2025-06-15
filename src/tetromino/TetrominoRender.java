package tetromino;

import cellRenders.Render;
import interfaces.Drawable;

import java.awt.*;
import java.util.Arrays;

public class TetrominoRender implements Drawable {
    private final Tetromino tetromino;
    private final Render render;

    public TetrominoRender(Tetromino tetromino, Render render) {
        this.tetromino = normalizeTetrominoes(tetromino);
        this.render = render;
    }

    public int height() {
        return tetromino.shape.length;
    }

    public int maxWidth() {
        return Arrays.stream(tetromino.shape).mapToInt(row -> row.length).max().orElse(0);
    }

    public int width(int row) {
        return tetromino.shape[row].length;
    }

    public Tetromino normalizeTetrominoes(Tetromino tetromino) {
        if (isI(tetromino)) return new Tetromino(tetromino.rotate(), tetromino.color, tetromino.type);
        return tetromino;
    }

    private boolean isI(Tetromino tetromino) {
        return tetromino.shape.length == 4 && tetromino.shape[0].length == 1;
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(row); col++) {
                if (tetromino.shape[row][col] == 1) {
                    int cellX = x + col * cellSize;
                    int cellY = y + row * cellSize;
                    render.drawCell(g, cellX, cellY, tetromino.color);
                }
            }
        }
    }
}
