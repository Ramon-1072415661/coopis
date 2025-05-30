package Board.Controls;

import Board.TetrominoLogic.Tetromino;

import java.awt.*;

public class Grid {
    public final Color[][] grid;
    private int COLS, ROWS;

    public Grid(int cols, int rows) {
        this.grid = new Color[rows][cols];
        COLS = cols;
        ROWS = rows;

    }
    public void fixToGrid(Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    grid[tetromino.y + row][tetromino.x + col] = tetromino.color;
                }
            }
        }
    }

    public boolean collides(int dx, int dy, Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    int newX = tetromino.x + col + dx;
                    int newY = tetromino.y + row + dy;

                    if (newX < 0 || newX >= COLS || newY >= ROWS || (newY >= 0 && grid[newY][newX] != null))
                        return true;
                }
            }
        }
        return false;
    }

    public boolean canMoveWithoutCollision(int dx, int dy, Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    int newX = tetromino.x + col + dx;
                    int newY = tetromino.y + row + dy;

                    if (newX < 0 || newX >= COLS || newY >= ROWS) return false;
                    if (newY >= 0 && grid[newY][newX] != null) return false;
                }
            }
        }
        return true;
    }
    public void clearLines() {
        for (int row = ROWS - 1; row >= 0; row--) {
            boolean full = true;

            for (int col = 0; col < COLS; col++) {
                if (grid[row][col] == null) {
                    full = false;
                    break;
                }
            }

            if (full) {
                for (int r = row; r > 0; r--) {
                    System.arraycopy(grid[r - 1], 0, grid[r], 0, COLS);
                }
                grid[0] = new Color[COLS];
                row++;
            }
        }
    }
}
