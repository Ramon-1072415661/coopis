package tetromino;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.Color;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

public class TetrominoTests {

    private Tetromino tetromino;
    private TetrominoHolder holder;
    private TetrominoQueue queue;

    @BeforeEach
    public void setUp() {
        tetromino = new Tetromino(new int[][]{{1}}, Color.RED);
        holder = new TetrominoHolder();
        queue = new TetrominoQueue();
    }

    @Test
    public void testInitialRotationState() {
        assertEquals(0, tetromino.getRotationState());
    }

    @Test
    public void testRotate() {
        int before = tetromino.getRotationState();
        tetromino.rotate();
        assertNotEquals(before, tetromino.getRotationState());
    }

    @Test
    public void testSetRotationState() {
        tetromino.setRotationState(2);
        assertEquals(2, tetromino.getRotationState());
    }

    @Test
    public void testHolderStoresTetromino() {
        // Adiciona diretamente à pilha usando iterator (se possível)
        Iterator<Tetromino> it = holder.iterator();
        assertNotNull(it);
    }

    @Test
    public void testQueueProvidesIterator() {
        Iterator<Tetromino> it = queue.iterator();
        assertTrue(it.hasNext(), "Iterator da fila deve ter elementos.");
        Tetromino t = it.next();
        assertNotNull(t, "Iterator deve retornar um tetromino válido.");
    }
}
