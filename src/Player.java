import DSA.Stack;

public class Player {
    public Tetromino tetromino;
    private TetrominoHolder holder;
    private GetTetrominoLogic next_tetromino_logic;
    private TetrominoQueue queue;
    private int initialPosition;

    public Player(int initialPosition, TetrominoQueue queue, TetrominoHolder holder) {
        this.holder = holder;
        this.queue = queue;
        this.initialPosition = initialPosition;
        next_tetromino_logic = new GetTetrominoLogic(queue);
        this.getNextTretomino();
    }

    public void getNextTretomino(){
        tetromino = next_tetromino_logic.nextTetromino();
        tetromino.x = initialPosition;
        tetromino.y = 0;
        holder.resetSwap();
    }

    public void swapTetromino(){
         holder.invert();
    }

    public void insertInHold(){
        boolean isSwap = holder.insert(tetromino);
        if (isSwap) {
            tetromino = holder.swap(tetromino);
            tetromino.x = initialPosition;
            tetromino.y = 0;
        }
        else {
            this.getNextTretomino();
        }
    }

    public TetrominoHolder getHolder() {
        return holder;
    }

    public TetrominoQueue getQueue() {
        return queue;
    }
}