import CellRenders.Render;

import java.awt.*;
import java.util.Arrays;

public class TetrominoRender implements Drawable{
    private Tetromino tetromino;
    private Render render;
    public TetrominoRender(Tetromino tetromino, Render render) {
        this.tetromino = tetromino;
        this.render = render;
    }

    public int height(){
        return tetromino.shape.length;
    }

    public int maxWidth() {
        return  Arrays.stream(tetromino.shape).mapToInt(row -> row.length).max().orElse(0);
    }
    public int width(int row){
        return tetromino.shape[row].length;
    }

    @Override
    public void draw(Graphics g, int x, int y, int cellSize) {
        for (int row = 0; row < height(); row++) {
            for (int col = 0; col < width(row); col++) {
                if (tetromino.shape[row][col] == 1) { // se a célula faz parte da peça
                    int cellX = x + col * cellSize;
                    int cellY = y + row * cellSize;
                    render.drawCell(g, cellX, cellY , tetromino.color);

                }
            }
        }
    }
}
