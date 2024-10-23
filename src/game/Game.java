package game;

import gui.GameWindow;

public class Game {
    private Player player;
    private GameWindow gameWindow;
    private boolean isRunning;

    public Game() {
        // Initialize the player
        player = new Player();

        // Create the game window (GameWindow handles rendering and obstacles)
        gameWindow = new GameWindow(player);

        // Start the game loop
        isRunning = true;
        startGameLoop();
    }

    // Start the game loop
    public void startGameLoop() {
        // Main game loop
        while (isRunning) {
            // Update player movement
            player.run();
            player.applyGravity();

            // Update obstacles and repaint game window
            gameWindow.updateGameWindow();

            // Control the frame rate (approximately 60 FPS)
            try {
                Thread.sleep(1000 / 60); // 60 frames per second
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            // Example: You can check for collisions here (optional)
            // if (checkCollision()) {
            // endGame(); // End the game if there's a collision
            // }
        }
    }

    // Method to end the game
    public void endGame() {
        isRunning = false;
        System.out.println("Game Over!");
    }

    // Optional: Collision detection method (to be implemented)
    public boolean checkCollision() {
        // Collision detection logic between the player and obstacles
        // Return true if there is a collision, otherwise return false
        return false;
    }
}
