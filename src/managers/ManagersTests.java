package managers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ManagersTests {

    private ScoreManager scoreManager;

    @BeforeEach
    public void setUp() {
        scoreManager = ScoreManager.getInstance();
        scoreManager.reset();  // limpar estado entre testes, se necessário
    }

    @Test
    public void testAddCleanedRowsAndScoreCalculation() {
        scoreManager.addCleanedRows(1);
        assertTrue(scoreManager.calculeScore() > 0);
    }

    @Test
    public void testScoreReset() {
        scoreManager.addCleanedRows(3);
        scoreManager.reset();
        assertEquals(0, scoreManager.calculeScore());
    }
}
