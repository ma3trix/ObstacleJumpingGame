package game;

import gui.GameWindow;
import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();
        Obstacle obstacle = new Obstacle(); // Assuming you have obstacles

        // Create the game window (with the GamePanel inside)
        GameWindow gameWindow = new GameWindow(player, obstacle);

        // Simple game loop
        while (true) {
            player.run(); // Update player movement
            player.applyGravity(); // Apply gravity to the player
            gameWindow.updateGameWindow(); // Repaint the game window to reflect the new state

            // Control the frame rate
            try {
                Thread.sleep(1000 / 60); // Approximately 60 FPS
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
