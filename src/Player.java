import DSA.Stack;

public class Player {
    public Tetromino tetromino;
    private TetrominoHolder holder;
    private GetTetrominoLogic next_tetromino_logic;

    public Player(int initialPosition) {
        holder = new TetrominoHolder();
        next_tetromino_logic = new GetTetrominoLogic(initialPosition);
        this.getNextTretomino();
    }

    public void getNextTretomino(){
        tetromino = next_tetromino_logic.nextTetromino();
        holder.resetSwap();
    }

    public void swapTetromino(){
        tetromino = holder.swap(tetromino);
    }

    public void insertInHold(){
        holder.insert(tetromino);
        this.getNextTretomino();
    }

    public TetrominoHolder getHolder() {
        return holder;
    }
}