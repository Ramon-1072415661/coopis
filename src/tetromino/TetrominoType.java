package tetromino;

import utils.Colors;

import java.awt.*;
import java.util.Random;

public enum TetrominoType {
    I_SHAPE(new int[][]{{1}, {1}, {1}, {1}}, Colors.BLUE), // Cyan
    O_SHAPE(new int[][]{{1, 1}, {1, 1}}, Colors.GREEN),     // Yellow
    T_SHAPE(new int[][]{{0, 1, 0}, {1, 1, 1}}, Colors.ORANGE), // Purple
    S_SHAPE(new int[][]{{1, 1, 0}, {0, 1, 1}}, Colors.PINK), // Green
    Z_SHAPE(new int[][]{{0, 1, 1}, {1, 1, 0}}, Colors.PURPLE), // Red
    J_SHAPE(new int[][]{{1, 0, 0}, {1, 1, 1}}, Colors.RED), // Blue
    L_SHAPE(new int[][]{{0, 0, 1}, {1, 1, 1}}, Colors.YELLOW); // Orange

    private final int[][] shape;
    private final Color color;

    TetrominoType(int[][] shape, Color color) {
        this.shape = shape;
        this.color = color;
    }

    public int[][] getShape() {
        return shape;
    }

    public Color getColor() {
        return color;
    }

    public static TetrominoType random(Random rand) {
        TetrominoType[] values = TetrominoType.values();
        return values[rand.nextInt(values.length)];
    }
}
