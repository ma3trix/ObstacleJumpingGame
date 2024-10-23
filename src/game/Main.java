package game;

import gui.GameWindow;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        // Create the game window (GameWindow handles rendering and obstacles)
        GameWindow gameWindow = new GameWindow(player);

        // Simple game loop
        while (true) {
            player.run(); // Update player movement and animation
            player.applyGravity(); // Apply gravity to the player
            gameWindow.updateGameWindow(); // Move obstacles and repaint the game window

            // Control the frame rate
            try {
                Thread.sleep(1000 / 60); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
