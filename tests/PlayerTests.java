import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tetromino.TetrominoHolder;
import tetromino.TetrominoQueue;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class PlayerTests {

    private Player player;

    @BeforeEach
    public void setUp() {
        TetrominoQueue queue = new TetrominoQueue();
        TetrominoHolder holder = new TetrominoHolder();
        player = new Player(0, queue, holder);
    }

    @Test
    public void testSwapTetrominoDoesNotThrow() {
        assertDoesNotThrow(() -> player.swapTetromino());
    }

    @Test
    public void testSwapHolderDoesNotThrow() {
        player.swapTetromino();
        assertDoesNotThrow(() -> player.swapHolder());
    }

    @Test
    public void testResetGeneratesNewTetromino() {
        player.reset();
        assertNotNull(player.tetromino);
    }
}
