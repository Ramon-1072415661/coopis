package utils;

import java.awt.*;
import java.util.Random;

public final class TetrominoResources {

    private final Random rand;

    private TetrominoResources() {
        rand = new Random();
    }

    public static TetrominoResources getInstance() {
        return Holder.INSTANCE;
    }

    public Random getRandom() {
        return rand;
    }

    private static class Holder {
        private static final TetrominoResources INSTANCE = new TetrominoResources();
    }
}
