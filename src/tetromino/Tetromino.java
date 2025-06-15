package tetromino;

import utils.TetrominoResources;

import java.awt.*;
import java.util.Random;

public class Tetromino {
    public final TetrominoType type;
    public int[][] shape;
    public int x = 0, y = 0;
    public Color color;
    private int rotationState = 0; // Tracks current rotation (0-3)

    public Tetromino(int[][] shape, Color color, TetrominoType type) {
        this.type = type;
        this.shape = shape;
        this.color = color;
    }

    public Tetromino(TetrominoType type) {
        this.type = type;
        this.shape = type.getShape();
        this.color = type.getColor();
    }

    public static Tetromino random() {
        Random rand = TetrominoResources.getInstance().getRandom();
        TetrominoType type = TetrominoType.random(rand);
        return new Tetromino(type);
    }

    /**
     * Rotates the tetromino 90 degrees clockwise around its center.
     *
     * @return A new rotated shape matrix
     */
    public int[][] rotate() {
        int rows = shape.length;
        int cols = shape[0].length;

        // Calculate the center point of the matrix (floating point for odd sizes)
        float centerRow = (rows - 1) / 2.0f;
        float centerCol = (cols - 1) / 2.0f;

        // Determine the dimensions of the rotated matrix
        // For standard rotation, this is just swapping rows and cols
        int[][] rotated = new int[cols][rows];

        // For each cell in the original matrix
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                // Calculate position relative to center
                float relRow = row - centerRow;
                float relCol = col - centerCol;

                // Apply rotation transform (90 degrees clockwise)
                // x' = y, y' = -x

                // Convert back to array indices (round to nearest integer)
                int newRow = Math.round(-relCol + centerCol);
                int newCol = Math.round(relRow + centerRow);

                // Copy the value if it's within bounds
                if (newRow >= 0 && newRow < cols && newCol >= 0 && newCol < rows) {
                    rotated[newRow][newCol] = shape[row][col];
                }
            }
        }

        // Clean up any potential gaps by shifting content
        return normalize(rotated);
    }

    /**
     * Normalizes the shape by removing any empty rows/columns and
     * ensuring the shape is packed at the top-left
     */
    private int[][] normalize(int[][] matrix) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        // Find the bounds of the actual content
        int minRow = rows, maxRow = -1;
        int minCol = cols, maxCol = -1;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (matrix[row][col] == 1) {
                    minRow = Math.min(minRow, row);
                    maxRow = Math.max(maxRow, row);
                    minCol = Math.min(minCol, col);
                    maxCol = Math.max(maxCol, col);
                }
            }
        }

        // If no content, return the original
        if (minRow > maxRow || minCol > maxCol) {
            return matrix;
        }

        // Create a new matrix with just the content
        int newRows = maxRow - minRow + 1;
        int newCols = maxCol - minCol + 1;
        int[][] normalized = new int[newRows][newCols];

        for (int row = 0; row < newRows; row++) {
            System.arraycopy(matrix[row + minRow], minCol, normalized[row], 0, newCols);
        }

        return normalized;
    }

    /**
     * Gets the current rotation state (0-3)
     * Used for SRS wall kick calculations
     */
    public int getRotationState() {
        return rotationState;
    }

    /**
     * Sets the rotation state (0-3)
     */
    public void setRotationState(int state) {
        this.rotationState = state % 4;
    }
}