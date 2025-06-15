import tetromino.Tetromino;
import tetromino.TetrominoHolder;
import tetromino.TetrominoQueue;
import tetromino.TetrominoType;
import utils.TetrominoProvider;

public class Player {
    private final TetrominoHolder holder;
    private final TetrominoProvider next_tetromino_logic;
    private final TetrominoQueue queue;
    private final int initialPosition;
    public Tetromino tetromino;

    public Player(int initialPosition, TetrominoQueue queue, TetrominoHolder holder) {
        this.holder = holder;
        this.queue = queue;
        this.initialPosition = initialPosition;
        next_tetromino_logic = new TetrominoProvider(queue);
        this.getNextTretomino();
    }

    public void getNextTretomino() {
        tetromino = next_tetromino_logic.nextTetromino();
        tetromino.x = initialPosition;
        tetromino.y = 0;
        holder.resetSwap();
    }

    public void swapHolder() {
        holder.invert();
    }

    public void swapTetromino() {
        Tetromino normalizedTetromino = new Tetromino(tetromino.type);
        boolean isSwap = holder.insert(normalizedTetromino);
        if (isSwap) {
            tetromino = holder.swap(normalizedTetromino);
            tetromino.x = initialPosition;
            tetromino.y = 0;
        } else {
            this.getNextTretomino();
        }
    }

    public void reset() {
        queue.reset();
        holder.reset();
        this.getNextTretomino();
    }
}