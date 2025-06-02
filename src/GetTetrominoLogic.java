public class GetTetrominoLogic {
    private int initialXPosition;
    private TetrominoQueue queue = new TetrominoQueue();


    public GetTetrominoLogic(int initialXPosition) {
        this.initialXPosition = initialXPosition;
        for (int i = 0; i < 4; i++) {
            queue.add(randomTetromino());
        }
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
