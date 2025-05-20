package Board;

import Board.Commands.Grid;
import Board.TetrominoLogic.Tetromino;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Board extends JPanel implements ActionListener {
    private static final int ROWS = 20, COLS = 10, CELL_SIZE = 48, CELL_SHADOW_BEVEL = CELL_SIZE / 5;
    private static int DELAY = 400;

    private Grid grid;
//    private Color[][] grid = new Color[ROWS][COLS];
    private final Timer timer = new Timer(DELAY, this);
    private Player p1;
    private Player p2;


    private final CellRenderer cellRenderer = new CellRenderer(CELL_SIZE, CELL_SHADOW_BEVEL);

    private boolean gameOver = false;

    private void startGame() {
        p1 = new Player(2,0,COLS/2);
        p2 = new Player(7,COLS/2,COLS);
        grid = new Grid(COLS,ROWS);
        gameOver = false;
        timer.start();
    }

    public Board() {
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
                p1.action(keyCode,grid);
                p2.action(keyCode,grid);
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

    public void actionPerformed(ActionEvent e) {
        boolean p1Collided = grid.collides(0, 1, p1.tetromino);
        boolean p2Collided = grid.collides(0, 1, p2.tetromino);

        if (!p1Collided) {
            p1.tetromino.y++;
        } else {
            grid.fixToGrid(p1.tetromino);
            grid.clearLines();
            p1.getNextTretomino();
        }

        if (!p2Collided) {
            p2.tetromino.y++;
        } else {

            grid.fixToGrid(p2.tetromino);
            grid.fixToGrid(p2.tetromino);
            p2.getNextTretomino();

        }

        if ((p1Collided && grid.collides(0, 0, p1.tetromino)) || (p2Collided && grid.collides(0, 0, p2.tetromino))) {
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
        }
    }

    private void paintGameOverOverlay(Graphics g) {
        Graphics2D g2d = (Graphics2D) g.create();

        // Semi-transparent overlay
        g2d.setColor(new Color(0, 0, 0, 180));
        g2d.fillRect(0, 0, COLS * CELL_SIZE, ROWS * CELL_SIZE);

        // Game over message
        g2d.setFont(new Font("Arial", Font.BOLD, 36));
        g2d.setColor(Color.WHITE);
        String gameOverText = "GAME OVER";
        FontMetrics fontMetrics = g2d.getFontMetrics();
        int textWidth = fontMetrics.stringWidth(gameOverText);
        g2d.drawString(gameOverText, (COLS * CELL_SIZE - textWidth) / 2, ROWS * CELL_SIZE / 2 - 20);

        // Retry message
        g2d.setFont(new Font("Arial", Font.PLAIN, 20));
        String retryText = "Press SPACE or click to retry";
        textWidth = fontMetrics.stringWidth(retryText);
        FontMetrics smallerMetrics = g2d.getFontMetrics();
        textWidth = smallerMetrics.stringWidth(retryText);
        g2d.drawString(retryText, (COLS * CELL_SIZE - textWidth) / 2, ROWS * CELL_SIZE / 2 + 20);

        g2d.dispose();
    }

    private void paintBackground(Graphics g) {
        int finalX = CELL_SIZE * COLS;
        int finalY = CELL_SIZE * ROWS;
        int strokeWidth = 4;

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(new Color(0x21222c));
        g2.fillRect(0, 0, finalX, finalY);

        g2.setStroke(new BasicStroke(strokeWidth));
        g2.setColor(new Color(0xBD93F9));
        g2.drawRect(0, 0, finalX + strokeWidth, finalY + strokeWidth);

        g2.dispose();
    }

    private void paintFilledCells(Graphics g) {
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                int cellX = col * CELL_SIZE;
                int cellY = row * CELL_SIZE;

                g.setColor(Color.BLACK);
                g.drawRect(cellX, cellY, CELL_SIZE, CELL_SIZE);

                if (grid.grid[row][col] != null) {
                    cellRenderer.drawCell(g, col, row, grid.grid[row][col]);
                }

                if ((row == ROWS - 1) && col == 5) {
                    g.setColor(Color.GREEN);
                    g.drawLine(cellX, 0, cellX, cellY + CELL_SIZE);
                }
            }
        }
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

}