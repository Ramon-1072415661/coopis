# Coopis

This project implements a two-player Tetris game as a practical demonstration of fundamental data structures including
**Linked Lists**, **Queues**, and **Stacks**. It serves as an educational tool to showcase how these abstract data types
can be applied in a real-world application with visual feedback.

## Game Features

- **Two-player competitive gameplay**: Play against a friend on the same keyboard
- **Standard Tetris mechanics**: Clear lines by filling rows completely
- **Super Rotation System (SRS)**: Modern Tetris rotation mechanics with wall kicks
- **Visual styling**: Clean interface with modern aesthetic

## Getting Started

### Prerequisites

- Java JDK 24 or higher
- Swing library (included in standard JDK)

### How to Run the Game

1. Compile the Java files:
   ```
   javac *.java
   ```
2. Run the main class:
   ```
   java TetrisGame
   ```

Or just open it through IntelliJ IDEA Community and run the  `TetrisGame` class :)

## Game Controls

### Player 1 (Left Side):

- **A**: Move tetromino left
- **D**: Move tetromino right
- **S**: Hard drop (move down faster)
- **W**: Rotate tetromino clockwise

### Player 2 (Right Side):

- **Left Arrow**: Move tetromino left
- **Right Arrow**: Move tetromino right
- **Down Arrow**: Hard drop (move down faster)
- **Up Arrow**: Rotate tetromino clockwise

### General Controls:

- **Space**: Start new game / Restart after game over

## Game Rules

1. Each player controls tetromino pieces on their side of the board
2. Player 1 plays on the left half, Player 2 plays on the right half
3. When a piece lands, it becomes fixed in place
4. When a row is completely filled across the board, it disappears and all pieces above it move down
5. The game ends when a new tetromino cannot be placed on the board
6. After game over, press SPACE or click anywhere to restart

## Implementation Details

The game implements several key data structures:

- **Board Grid**: A 2D array representing the game state with color information
- **Tetromino Class**: Represents the falling pieces with rotation states
- **Game Loop**: Timer-based event system for game progression
- **Rendering System**: Custom graphics for blocks with shadow effects

## Project Structure

- `Board.java`: Main game logic and rendering
- `Tetromino.java`: Tetromino shapes, rotations, and behaviors
- `CellRenderer.java`: Visual representation of tetromino blocks
- `TetrisGame.java`: Entry point and window management

## Acknowledgments

- Based on the classic Tetris game designed by [Alexey Pajitnov](https://tetris.fandom.com/wiki/History)
- Implements modern Tetris guidelines including [Super Rotation System](https://tetris.fandom.com/wiki/Tetris_Guideline)
- Educational implementation inspired by data structure curriculum requirements