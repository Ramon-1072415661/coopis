public class GetTetrominoLogic {
    private int initialPosition;
    private TetrominoHolder holder;
//    private TetrominoQueue queue;


    public GetTetrominoLogic(int initialPosition,TetrominoHolder holder) {
        this.initialPosition = initialPosition;
        this.holder = holder;
    }

    public Tetromino nextTetromino() {
        if(!holder.isEmpty()) {
            Tetromino holder_tetromino = holder.pop();
            holder_tetromino.x = initialPosition;
            return holder_tetromino;
        }
        return randomTetromino(); // this is a place holder. Insert Next Queue Logic
    }


    private Tetromino randomTetromino() {
        Tetromino tetromino = Tetromino.random();
        tetromino.x = initialPosition;
        return tetromino;
    }
}
