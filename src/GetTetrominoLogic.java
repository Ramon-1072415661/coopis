public class GetTetrominoLogic {
    private int initialXPosition;
//    private TetrominoQueue queue;


    public GetTetrominoLogic(int initialXPosition) {
        this.initialXPosition = initialXPosition;
    }

    public Tetromino nextTetromino() {
        return randomTetromino(); // this is a place holder. Insert Next Queue Logic
    }


    private Tetromino randomTetromino() {
        Tetromino tetromino = Tetromino.random();
        tetromino.x = initialXPosition;
        return tetromino;
    }
}
