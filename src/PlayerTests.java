import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import tetromino.TetrominoQueue;
import tetromino.TetrominoHolder;
import tetromino.Tetromino;

public class PlayerTests {

    private Player player;

    @BeforeEach
    public void setUp() {
        TetrominoQueue queue = new TetrominoQueue();
        TetrominoHolder holder = new TetrominoHolder();
        player = new Player(0, queue, holder); // posição inicial 0
    }

    @Test
    public void testInsertInHoldDoesNotThrow() {
        assertDoesNotThrow(() -> player.insertInHold());
    }

    @Test
    public void testSwapTetrominoDoesNotThrow() {
        player.insertInHold();  // preparar estado
        assertDoesNotThrow(() -> player.swapTetromino());
    }

    @Test
    public void testResetGeneratesNewTetromino() {
        player.reset();
        assertNotNull(player.tetromino);
    }
}
