import DSA.Queue;

public class TetrominoQueue {

    private Queue<Tetromino> queue = new Queue<>();

    public TetrominoQueue() {
    }

    public void add(Tetromino tetromino) {
        queue.add(tetromino);
    }

    public Tetromino remove() {
        return queue.remove();
    }
}