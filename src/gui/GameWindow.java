package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import game.Player;
import game.Obstacle;

public class GameWindow extends JFrame implements KeyListener {
    private GamePanel gamePanel; // The JPanel that will handle rendering
    private Player player;

    public GameWindow(Player player, Obstacle obstacle) {
        this.player = player;

        // Create a GamePanel for drawing the player and background
        gamePanel = new GamePanel(player, obstacle);

        // Set up the window (JFrame)
        setTitle("Obstacle Jumping Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        add(gamePanel); // Add the GamePanel to the JFrame
        setVisible(true);

        // Add KeyListener to capture keyboard inputs
        addKeyListener(this);
    }

    // Method to update the game window (repaint the panel)
    public void updateGameWindow() {
        gamePanel.updateGamePanel(); // Delegate the update to the GamePanel
    }

    // Inner class GamePanel extending JPanel for custom painting
    class GamePanel extends JPanel {
        private BufferedImage backgroundImage;
        private Player player; // Reference to the player object
        private Obstacle obstacle; // Reference to the obstacle object (if needed)

        public GamePanel(Player player, Obstacle obstacle) {
            this.player = player;
            this.obstacle = obstacle;

            // Load the background image
            try {
                backgroundImage = ImageIO.read(new File("assets/images/gameBg.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g); // Ensure the parent component is painted first

            // Draw the background image
            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }

            // Draw the player at the player's current position with the current animation
            // frame
            Image playerImage = player.getCurrentImage(); // Get the current animation frame
            if (playerImage != null) {
                g.drawImage(playerImage, player.getX(), player.getY(), null); // Draw the player at (x, y)
            }
        }

        // Method to update and repaint the screen
        public void updateGamePanel() {
            repaint(); // Repaint the panel to update the player’s position
        }
    }

    // KeyListener methods to capture keyboard input for player movement
    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(true); // Start moving right
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(true); // Start moving left
        }
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump(); // Make the player jump when space is pressed
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            player.setRight(false); // Stop moving right
        }
        if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            player.setLeft(false); // Stop moving left
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed, but required by the KeyListener interface
    }
}
