import cellRenders.CellRenderer;
import managers.DisplayManager;
import managers.ScoreManager;
import managers.TimerManager;
import tetromino.Tetromino;
import utils.Colors;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {

    private static final int DELAY = 400;
    private final javax.swing.Timer timer = new javax.swing.Timer(DELAY, this);
    private final TimerManager timeRegister = TimerManager.getInstance();
    private final DisplayManager displayManager = DisplayManager.getInstance();
    private final CellRenderer cellRenderer;

    // Use DisplayManager for dimensions
    private final int rows = DisplayManager.getBoardRows();
    private final int cols = DisplayManager.getBoardCols();
    private final int cellSize;
    private final Player p1;
    private final Player p2;

    ScoreManager score = ScoreManager.getInstance();
    private Color[][] grid;
    private boolean gameOver = false;

    public Board(Player player1, Player player2) {
        // Initialize dimensions from DisplayManager
        cellSize = displayManager.getCellSize();
        int cellShadowBevel = displayManager.getCellShadowBevel();
        cellRenderer = new CellRenderer(cellSize, cellShadowBevel);
        grid = new Color[rows][cols];

        p1 = player1;
        p2 = player2;
        setFocusable(true);
        startGame();

        // Add key listener for game controls
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                int keyCode = e.getKeyCode();

                // If game is over, check for space key to restart
                if (gameOver) {
                    if (keyCode == KeyEvent.VK_SPACE) {
                        startGame();
                    }
                    return;
                }

                // Normal game controls
                if (keyCode == KeyEvent.VK_LEFT) {
                    move(-1, p2.tetromino);
                } else if (keyCode == KeyEvent.VK_RIGHT) {
                    move(1, p2.tetromino);
                } else if (keyCode == KeyEvent.VK_DOWN) {
                    drop(p2.tetromino);
                } else if (keyCode == KeyEvent.VK_UP) {
                    rotate(p2.tetromino);
                } else if (keyCode == KeyEvent.VK_N) {
                    p2.swapTetromino();
                } else if (keyCode == KeyEvent.VK_M) {
                    p2.swapHolder();
                } else if (keyCode == KeyEvent.VK_A) {
                    move(-1, p1.tetromino);
                } else if (keyCode == KeyEvent.VK_D) {
                    move(1, p1.tetromino);
                } else if (keyCode == KeyEvent.VK_S) {
                    drop(p1.tetromino);
                } else if (keyCode == KeyEvent.VK_W) {
                    rotate(p1.tetromino);
                } else if (keyCode == KeyEvent.VK_Q) {
                    p1.swapTetromino();
                } else if (keyCode == KeyEvent.VK_E) {
                    p1.swapHolder();
                }

                repaint();
            }
        });

        // Add mouse listener for click to restart
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (gameOver) {
                    startGame();
                    repaint();
                }
            }
        });
    }

    private void startGame() {
        p1.reset();
        p2.reset();
        grid = new Color[rows][cols];
        gameOver = false;
        actionPerformed(null); // First UI update before timer.start() to avoid a initial 400ms delay
        timer.start();
        timeRegister.start();
        score.reset();
    }

    public void actionPerformed(ActionEvent e) {
        boolean p1Collided = collides(0, 1, p1.tetromino);
        boolean p2Collided = collides(0, 1, p2.tetromino);

        if (!p1Collided) {
            p1.tetromino.y++;
        } else {
            fixToGrid(p1.tetromino);
            clearLines();
            p1.getNextTretomino();
        }

        if (!p2Collided) {
            p2.tetromino.y++;
        } else {
            fixToGrid(p2.tetromino);
            clearLines();
            p2.getNextTretomino();
        }

        if ((p1Collided && collides(0, 0, p1.tetromino)) || (p2Collided && collides(0, 0, p2.tetromino))) {
            gameOver = true;
            timer.stop();
        }

        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBackground(g);
        paintFilledCells(g);

        if (!gameOver) {
            paintCurrentShapeCells(g, p1.tetromino);
            paintCurrentShapeCells(g, p2.tetromino);
        } else {
            paintGameOverOverlay(g);
            timeRegister.stopAndNotifyScore(score.calculeScore());
        }
    }

    private void paintGameOverOverlay(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, cols * cellSize, rows * cellSize);

        // Scale font size based on cell size for adaptive display
        int baseFontSize = Math.max(24, cellSize / 2);
        int smallFontSize = Math.max(16, cellSize / 3);

        // Game over message
        g2d.setFont(new Font("Arial", Font.BOLD, baseFontSize));
        g2d.setColor(Color.WHITE);
        String gameOverText = "GAME OVER";
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(gameOverText);
        g2d.drawString(gameOverText, (cols * cellSize - textWidth) / 2, rows * cellSize / 2 - 20);

        // Retry message
        g2d.setFont(new Font("Arial", Font.PLAIN, smallFontSize));
        String retryText = "Press SPACE or click to retry";
        FontMetrics smallerMetrics = g2d.getFontMetrics();
        textWidth = smallerMetrics.stringWidth(retryText);
        g2d.drawString(retryText, (cols * cellSize - textWidth) / 2, rows * cellSize / 2 + 20);
        g2d.dispose();
    }

    private void fixToGrid(Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    grid[tetromino.y + row][tetromino.x + col] = tetromino.color;
                }
            }
        }
    }

    private void clearLines() {
        for (int row = rows - 1; row >= 0; row--) {
            boolean full = true;
            for (int col = 0; col < cols; col++) {
                if (grid[row][col] == null) {
                    full = false;
                    break;
                }
            }

            if (full) {
                int rowsCleaned = 0;
                for (int r = row; r > 0; r--) {
                    System.arraycopy(grid[r - 1], 0, grid[r], 0, cols);
                }
                grid[0] = new Color[cols];
                row++;
                rowsCleaned++;
                score.addCleanedRows(rowsCleaned);
                System.out.printf("Rows cleaned = %d%n", score.rowsCleaned);
            }
        }
    }

    private void paintBackground(Graphics g) {
        int x = cellSize * cols;
        int y = cellSize * rows;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(Colors.PANELS_BACKGROUND);
        g2.fillRect(0, 0, x, y);
        g2.drawRect(0, 0, cellSize * cols, cellSize * rows);
        g2.dispose();
    }

    private void paintFilledCells(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();
        int centerCol = cols / 2;
        int dividerX = centerCol * cellSize;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                int cellX = col * cellSize;
                int cellY = row * cellSize;

                g.setColor(Colors.GREY);
                g.drawRect(cellX, cellY, cellSize, cellSize);

                if (grid[row][col] != null) {
                    cellRenderer.drawCell(g, col, row, grid[row][col]);
                }
            }
        }

        drawCenterDivider(g2d, dividerX);

        g2d.dispose();
    }

    private void drawCenterDivider(Graphics2D g2d, int xPosition) {
        Stroke oldStroke = g2d.getStroke();
        Color oldColor = g2d.getColor();

        g2d.setColor(Colors.PURPLE);
        g2d.setStroke(new BasicStroke(1));
        g2d.drawLine(xPosition, 0, xPosition, rows * cellSize);

        g2d.setStroke(oldStroke);
        g2d.setColor(oldColor);
    }

    private void paintCurrentShapeCells(Graphics g, Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    cellRenderer.drawCell(g, tetromino.x + col, tetromino.y + row, tetromino.color);
                }
            }
        }
    }

    private boolean collides(int dx, int dy, Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    int newX = tetromino.x + col + dx;
                    int newY = tetromino.y + row + dy;

                    if (newX < 0 || newX >= cols || newY >= rows || (newY >= 0 && grid[newY][newX] != null))
                        return true;
                }
            }
        }
        return false;
    }

    private boolean canMoveWithoutCollision(int dx, int dy, Tetromino tetromino) {
        for (int row = 0; row < tetromino.shape.length; row++) {
            for (int col = 0; col < tetromino.shape[0].length; col++) {
                if (tetromino.shape[row][col] == 1) {
                    int newX = tetromino.x + col + dx;
                    int newY = tetromino.y + row + dy;

                    if (newX < 0 || newX >= cols || newY >= rows) return false;
                    if (newY >= 0 && grid[newY][newX] != null) return false;
                }
            }
        }
        return true;
    }

    private void move(int dx, Tetromino tetromino) {
        int width = tetromino.shape[0].length;

        if (tetromino == p1.tetromino && (tetromino.x + dx >= 0 && tetromino.x + dx + width <= cols / 2)) {
            if (canMoveWithoutCollision(dx, 0, tetromino)) {
                tetromino.x += dx;
            }
        } else if (tetromino == p2.tetromino && (tetromino.x + dx >= cols / 2 && tetromino.x + dx + width <= cols)) {
            if (canMoveWithoutCollision(dx, 0, tetromino)) {
                tetromino.x += dx;
            }
        }
    }

    private void drop(Tetromino tetromino) {
        while (!collides(0, 1, tetromino)) {
            tetromino.y++;
        }
    }

    private void rotate(Tetromino tetromino) {
        // Store the current rotation state
        int originalRotationState = tetromino.getRotationState();
        int newRotationState = (originalRotationState + 1) % 4;

        // Create rotated tetromino
        int[][] rotatedShape = tetromino.rotate();
        Tetromino rotated = new Tetromino(rotatedShape, tetromino.color, tetromino.type);
        rotated.x = tetromino.x;
        rotated.y = tetromino.y;

        // Define wall kick offsets according to SRS (Super Rotation System)
        int[][][] kickData = getKickData(tetromino);

        // Try each wall kick offset
        for (int[] offset : kickData[originalRotationState]) {
            int testX = rotated.x + offset[0];
            int testY = rotated.y + offset[1];
            int dx = testX - rotated.x;
            int width = rotated.shape[0].length;

            if (!collides(dx, testY - rotated.y, rotated)) {
                // Success - apply the rotation and the offset
                if (tetromino == p1.tetromino && (rotated.x + dx >= 0 && rotated.x + dx + width <= cols / 2)) {
                    tetromino.shape = rotatedShape;
                    tetromino.x = testX;
                    tetromino.y = testY;
                    tetromino.setRotationState(newRotationState);
                    return;
                } else if (tetromino == p2.tetromino && (rotated.x + dx >= cols / 2 && rotated.x + dx + width <= cols)) {
                    tetromino.shape = rotatedShape;
                    tetromino.x = testX;
                    tetromino.y = testY;
                    tetromino.setRotationState(newRotationState);
                    return;
                }
            }
        }
        // If we get here, no wall kick succeeded - rotation fails
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

        return isIShape(tetromino) ? wallKickDataI : wallKickData;
    }

    // Helper method to determine if this is an I-piece
    private boolean isIShape(Tetromino tetromino) {
        // I-piece is typically 4x1 or 1x4
        return (tetromino.shape.length == 4 && tetromino.shape[0].length == 1) ||
                (tetromino.shape.length == 1 && tetromino.shape[0].length == 4);
    }

    @Override
    public Dimension getPreferredSize() {
        return displayManager.getBoardSize();
    }
}