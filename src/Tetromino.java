import java.awt.*;
import java.util.Random;

class Tetromino {
    public int[][] shape;
    public int x = 0, y = 0;
    public Color color;

    public Tetromino(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public static Tetromino random() {
        TetrominoResources resources = TetrominoResources.getInstance();
        int i = resources.getRandom().nextInt(resources.getShapes().length);
        return new Tetromino(resources.getShapes()[i], resources.getColors()[i]);
    }

    public int[][] rotate() {
        int rows = shape.length;
        int cols = shape[0].length;
        int[][] rotated = new int[cols][rows];

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                rotated[col][rows - 1 - row] = shape[row][col];
            }
        }
        return rotated;
    }
}