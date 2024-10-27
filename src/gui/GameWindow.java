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
import java.util.ArrayList;
import java.util.Iterator; // Import Iterator
import java.util.List;
import game.Player;
import game.Obstacle;

public class GameWindow extends JFrame implements KeyListener {
    private GamePanel gamePanel;
    private Player player;
    private List<Obstacle> obstacles;
    private int minObstacleSpacing = 300;
    private long lastObstacleAddedTime;
    private long obstacleSpawnDelay = 3000;
    private int score = 0;

    public GameWindow(Player player) {
        this.player = player;

        // Start with only one obstacle
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(800, 390));

        lastObstacleAddedTime = System.currentTimeMillis();

        gamePanel = new GamePanel(player, obstacles);

        setTitle("Obstacle Jumping Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setResizable(false);
        add(gamePanel);
        setVisible(true);

        addKeyListener(this);
    }

    public void updateGameWindow() {
        for (Iterator<Obstacle> iterator = obstacles.iterator(); iterator.hasNext();) {
            Obstacle obstacle = iterator.next();
            obstacle.updatePosition();
            if (obstacle.getX() < -50) { // Assuming obstacles go off screen when x < -50
                iterator.remove(); // Safely remove the obstacle from the list
            }
        }
        checkCollisions();
        long currentTime = System.currentTimeMillis();
        if (currentTime - lastObstacleAddedTime > obstacleSpawnDelay) {
            if (obstacles.size() < 3) {
                addNewObstacle();
            }
            lastObstacleAddedTime = currentTime;
        }
        gamePanel.updateGamePanel();
    }

    private void addNewObstacle() {
        int newObstacleX;
        boolean validPosition = false;

        while (!validPosition) {
            newObstacleX = 800 + (int) (Math.random() * 400);

            validPosition = true;
            for (Obstacle obstacle : obstacles) {
                if (Math.abs(newObstacleX - obstacle.getX()) < minObstacleSpacing) {
                    validPosition = false;
                    break;
                }
            }

            if (validPosition) {
                obstacles.add(new Obstacle(newObstacleX, 390));
            }
        }
    }

    private void checkCollisions() {
        Iterator<Obstacle> iterator = obstacles.iterator(); // Use Iterator to safely remove obstacles
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            if (player.getX() < obstacle.getX() + 50 &&
                    player.getX() + 50 > obstacle.getX() &&
                    player.getY() < obstacle.getY() + 50 &&
                    player.getY() + 50 > obstacle.getY()) {
                System.out.println("Collision Detected!");
                resetGame();
                break; // Exit the loop after a collision
            }
        }
    }

    private void resetGame() {
        System.out.println("Game Over! Resetting game...");
        score = 0;
        obstacles.clear(); // Remove all obstacles
        obstacles.add(new Obstacle(800, 390)); // Start with one obstacle again
    }

    class GamePanel extends JPanel {
        private BufferedImage backgroundImage;
        private Player player;
        private List<Obstacle> obstacles;

        public GamePanel(Player player, List<Obstacle> obstacles) {
            this.player = player;
            this.obstacles = obstacles;

            try {
                backgroundImage = ImageIO.read(new File("assets/images/gameBg.png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);

            if (backgroundImage != null) {
                g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
            }

            Image playerImage = player.getCurrentImage();
            if (playerImage != null) {
                g.drawImage(playerImage, player.getX(), player.getY(), null);
            }

            for (Obstacle obstacle : obstacles) {
                Image obstacleImage = obstacle.getImage();
                g.drawImage(obstacleImage, obstacle.getX(), obstacle.getY(), null);
            }

            g.drawString("Score: " + score, getWidth() - 100, 30);
        }

        public void updateGamePanel() {
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump();
            score++;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Handle key releases if needed
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not needed
    }
}
