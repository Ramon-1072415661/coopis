package managers;

import java.awt.*;

/**
 * Singleton class that manages display settings for the Tetris game.
 * Handles screen size detection and cell size calculations for adaptive layout.
 */
public class DisplayManager {

    // Game board dimensions
    private static final int BOARD_COLS = 10;
    private static final int BOARD_ROWS = 20;
    private static final double BOARD_WIDTH_RATIO = 0.4; // Board takes 40% of screen width
    private static final double BOARD_HEIGHT_RATIO = 0.9; // Board takes 90% of screen height
    private static final int CORNER_RADIUS = 45;
    private static DisplayManager instance;

    // Screen dimensions
    private final int screenWidth;
    private final int screenHeight;

    // Calculated dimensions
    private final int cellSize;
    private final int cellShadowBevel;
    private final int boardWidth;
    private final int boardHeight;
    private final int sidePanelWidth;

    // Private constructor to prevent instantiation
    private DisplayManager() {
        // Get screen dimensions
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.screenWidth = screenSize.width;
        this.screenHeight = screenSize.height;

        // Calculate optimal cell size
        this.cellSize = calculateOptimalCellSize();
        this.cellShadowBevel = cellSize / 5;

        // Calculate board dimensions
        this.boardWidth = BOARD_COLS * cellSize;
        this.boardHeight = BOARD_ROWS * cellSize;

        // Calculate side panel width
        this.sidePanelWidth = (screenWidth - boardWidth) / 2 - 40; // 40px for margins
    }

    /**
     * Get the singleton instance of DisplayManager
     *
     * @return The DisplayManager instance
     */
    public static DisplayManager getInstance() {
        if (instance != null) {
            return instance;
        }
        synchronized (DisplayManager.class) {
            if (instance == null) {
                instance = new DisplayManager();
            }
        }
        return instance;
    }

    // Board constants
    public static int getBoardCols() {
        return BOARD_COLS;
    }

    public static int getBoardRows() {
        return BOARD_ROWS;
    }

    /**
     * Reset the singleton instance (useful for testing or screen changes)
     */
    public static void reset() {
        synchronized (DisplayManager.class) {
            instance = null;
        }
    }

    /**
     * Calculate the optimal cell size based on screen dimensions
     *
     * @return The calculated cell size in pixels
     */
    private int calculateOptimalCellSize() {
        // Calculate based on width constraint
        int maxBoardWidth = (int) (screenWidth * BOARD_WIDTH_RATIO);
        int cellSizeByWidth = maxBoardWidth / BOARD_COLS;

        // Calculate based on height constraint
        int maxBoardHeight = (int) (screenHeight * BOARD_HEIGHT_RATIO);
        int cellSizeByHeight = maxBoardHeight / BOARD_ROWS;

        // Use the smaller value to ensure the board fits on screen
        int optimalCellSize = Math.min(cellSizeByWidth, cellSizeByHeight);

        // Ensure minimum playable size
        int minCellSize = 30;
        return Math.max(optimalCellSize, minCellSize);
    }

    // Getter methods for screen dimensions
    public int getScreenWidth() {
        return screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public Dimension getScreenSize() {
        return new Dimension(screenWidth, screenHeight);
    }

    // Getter methods for cell dimensions
    public int getCellSize() {
        return cellSize;
    }

    public int getCellShadowBevel() {
        return cellShadowBevel;
    }

    public int getCornerRadius() {
        return CORNER_RADIUS;
    }

    // Getter methods for board dimensions
    public int getBoardWidth() {
        return boardWidth;
    }

    public int getBoardHeight() {
        return boardHeight;
    }

    public Dimension getBoardSize() {
        return new Dimension(boardWidth, boardHeight);
    }

    // Getter methods for layout
    public int getSidePanelWidth() {
        return sidePanelWidth;
    }

    /**
     * Get the vertical padding needed to center the board on screen
     *
     * @return Vertical padding in pixels
     */
    public int getVerticalPadding() {
        return Math.max(0, (screenHeight - boardHeight - 40) / 2); // 40px for margins
    }

    /**
     * Get the horizontal margin for side panels
     *
     * @return Horizontal margin in pixels
     */
    public int getHorizontalMargin() {
        return 20;
    }

    /**
     * Convert grid coordinates to pixel coordinates
     *
     * @param gridX Grid X coordinate
     * @param gridY Grid Y coordinate
     * @return Point in pixel coordinates
     */
    public Point gridToPixel(int gridX, int gridY) {
        return new Point(gridX * cellSize, gridY * cellSize);
    }

    /**
     * Convert pixel coordinates to grid coordinates
     *
     * @param pixelX Pixel X coordinate
     * @param pixelY Pixel Y coordinate
     * @return Point in grid coordinates
     */
    public Point pixelToGrid(int pixelX, int pixelY) {
        return new Point(pixelX / cellSize, pixelY / cellSize);
    }

    /**
     * Check if the display is in portrait mode
     *
     * @return true if height > width
     */
    public boolean isPortraitMode() {
        return screenHeight > screenWidth;
    }

    /**
     * Check if the display is in landscape mode
     *
     * @return true if width > height
     */
    public boolean isLandscapeMode() {
        return screenWidth > screenHeight;
    }

    /**
     * Get the aspect ratio of the screen (width/height)
     *
     * @return Screen aspect ratio
     */
    public double getScreenAspectRatio() {
        return (double) screenWidth / screenHeight;
    }

    @Override
    public String toString() {
        return String.format("DisplayManager{screen=%dx%d, cell=%d, board=%dx%d}",
                screenWidth, screenHeight, cellSize, boardWidth, boardHeight);
    }
}