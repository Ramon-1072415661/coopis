package Board.Controls.Commands;

import Board.Controls.Grid;
import Board.Player;
import Board.TetrominoLogic.Tetromino;

public class RotateCommand implements Command{
    private final int columnStart, columnEnd;

    public RotateCommand(int columnStart, int columnEnd) {
        this.columnStart = columnStart;
        this.columnEnd = columnEnd;
    }

    @Override
    public void execute(Grid grid, Player player) {
        rotate(player,grid);
    }
    private void rotate(Player player, Grid grid) {
        Tetromino tetromino = player.tetromino;
        // Store the current rotation state
        int originalRotationState = tetromino.getRotationState();
        int newRotationState = (originalRotationState + 1) % 4;

        // Create rotated tetromino
        int[][] rotatedShape = tetromino.rotate();
        Tetromino rotated = new Tetromino(rotatedShape, tetromino.color);
        rotated.x = tetromino.x;
        rotated.y = tetromino.y;

        // Define wall kick offsets according to SRS (Super Rotation System)
        // Format: [rotation_state][test_number][x_offset, y_offset]
        int[][][] kickData = getKickData(tetromino);

        // Try each wall kick offset
        for (int[] offset : kickData[originalRotationState]) {
            int testX = rotated.x + offset[0];
            int testY = rotated.y + offset[1];
            int dx = testX - rotated.x;
            int width = rotated.shape[0].length;
            if (!grid.collides(dx, testY - rotated.y, rotated) && (rotated.x + dx + width >= columnStart && rotated.x + dx+width <=columnEnd)) {
                // Success - apply the rotation and the offset
                tetromino.shape = rotatedShape;
                tetromino.x = testX;
                tetromino.y = testY;
                tetromino.setRotationState(newRotationState);
                return;
            }
        }

        // If we get here, no wall kick succeeded
        // Rotation fails and the piece remains unchanged
    }
    private int[][][] getKickData(Tetromino tetromino) {
        int[][][] wallKickData = {
                // 0>>1, 1>>2, 2>>3, 3>>0 (clockwise)
                {{0, 0}, {-1, 0}, {-1, 1}, {0, -2}, {-1, -2}},
                {{0, 0}, {1, 0}, {1, -1}, {0, 2}, {1, 2}},
                {{0, 0}, {1, 0}, {1, 1}, {0, -2}, {1, -2}},
                {{0, 0}, {-1, 0}, {-1, -1}, {0, 2}, {-1, 2}}
        };

        // I-piece has different offsets
        int[][][] wallKickDataI = {
                // 0>>1, 1>>2, 2>>3, 3>>0 (clockwise)
                {{0, 0}, {-2, 0}, {1, 0}, {-2, -1}, {1, 2}},
                {{0, 0}, {-1, 0}, {2, 0}, {-1, 2}, {2, -1}},
                {{0, 0}, {2, 0}, {-1, 0}, {2, 1}, {-1, -2}},
                {{0, 0}, {1, 0}, {-2, 0}, {1, -2}, {-2, 1}}
        };

        // Choose which kick data to use based on tetromino type
        int[][][] kickData = isIShape(tetromino) ? wallKickDataI : wallKickData;
        return kickData;
    }

    // Helper method to determine if this is an I-piece
    private boolean isIShape(Tetromino tetromino) {
        // I-piece is typically 4x1 or 1x4
        return (tetromino.shape.length == 4 && tetromino.shape[0].length == 1) || (tetromino.shape.length == 1 && tetromino.shape[0].length == 4);
    }
}
