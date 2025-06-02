public class GetTetrominoLogic {
    private int initialXPosition;
    private TetrominoQueue queue;


    public GetTetrominoLogic(int initialXPosition,TetrominoQueue queue) {
        this.initialXPosition = initialXPosition;
        this.queue = queue;
    }

    public Tetromino nextTetromino() {
        queue.add(randomTetromino());
        return queue.remove(); // this is a place holder. Insert Next Queue Logic
    }

    private Tetromino randomTetromino() {
        Tetromino tetromino = Tetromino.random();
        tetromino.x = initialXPosition;
        return tetromino;
    }
}
