package utils;

import tetromino.Tetromino;
import tetromino.TetrominoQueue;

public class TetrominoProvider {
    private final TetrominoQueue queue;

    public TetrominoProvider(TetrominoQueue queue) {
        this.queue = queue;
    }

    public Tetromino nextTetromino() {
        queue.add(randomTetromino());
        return queue.remove();
    }

    private Tetromino randomTetromino() {
        return Tetromino.random();
    }
}
