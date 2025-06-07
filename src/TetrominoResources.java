import java.awt.*;
import java.util.Random;

public final class TetrominoResources {

    private final int[][][] shapes;
    private final Color[] colors;
    private final Random rand;

    private TetrominoResources() {
        shapes = new int[][][]{
                {{1}, {1}, {1}, {1}},              // I
                {{1, 1}, {1, 1}},                  // O
                {{0, 1, 0}, {1, 1, 1}},            // T
                {{1, 1, 0}, {0, 1, 1}},            // S
                {{0, 1, 1}, {1, 1, 0}},            // Z
                {{1, 0, 0}, {1, 1, 1}},            // J
                {{0, 0, 1}, {1, 1, 1}}             // L
        };

        colors = new Color[]{
                Colors.BLUE,
                Colors.GREEN,
                Colors.ORANGE,
                Colors.PINK,
                Colors.PURPLE,
                Colors.RED,
                Colors.YELLOW,
        };

        rand = new Random();
    }

    public static TetrominoResources getInstance() {
        return Holder.INSTANCE;
    }

    public int[][][] getShapes() {
        return shapes;
    }

    public Color[] getColors() {
        return colors;
    }

    public Random getRandom() {
        return rand;
    }

    private static class Holder {
        private static final TetrominoResources INSTANCE = new TetrominoResources();
    }
}
