public class GetTetrominoLogic {
    private TetrominoQueue queue;


    public GetTetrominoLogic(TetrominoQueue queue) {
        this.queue = queue;
    }

    public Tetromino nextTetromino() {
        queue.add(randomTetromino());
        Tetromino tetromino = queue.remove();
        return tetromino;
    }

    private Tetromino randomTetromino() {
        Tetromino tetromino = Tetromino.random();
        return tetromino;
    }
}
