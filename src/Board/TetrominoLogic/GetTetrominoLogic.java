package Board.TetrominoLogic;

public class GetTetrominoLogic {
    private TetrominoQueue queue;
    private int initialPosition;
    public GetTetrominoLogic(TetrominoQueue queue, int initialPosition) {
        this.queue = queue;
        this.initialPosition = initialPosition;
    }

    public Tetromino nextTetromino() {
        queue.add(randomTetromino());
        Tetromino tetromino = queue.remove();
        tetromino.x = initialPosition;
        tetromino.y = 0;
        return tetromino;
    }

    private Tetromino randomTetromino() {
        Tetromino tetromino = Tetromino.random();
        return tetromino;
    }
}
