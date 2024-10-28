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
import java.util.Iterator;
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
    private int score = 0; // Current score
    private int highScore = 0; // High score tracking

    public GameWindow(Player player) {
        this.player = player;

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
            if (obstacle.getX() < -50) {
                iterator.remove();
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
        Iterator<Obstacle> iterator = obstacles.iterator();
        while (iterator.hasNext()) {
            Obstacle obstacle = iterator.next();
            if (player.getX() < obstacle.getX() + 50 &&
                    player.getX() + 50 > obstacle.getX() &&
                    player.getY() < obstacle.getY() + 50 &&
                    player.getY() + 50 > obstacle.getY()) {
                System.out.println("Collision Detected!");
                updateHighScore(); // Update high score if the player has beaten it
                resetGame();
                break;
            }
        }
    }

    private void updateHighScore() {
        if (score > highScore) {
            highScore = score; // Update high score
        }
    }

    private void resetGame() {
        System.out.println("Game Over! Resetting game...");
        score = 0;
        obstacles.clear();
        obstacles.add(new Obstacle(800, 390));
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

            // Display score and high score
            g.drawString("Score: " + score, getWidth() - 100, 30);
            g.drawString("High Score: " + highScore, getWidth() - 100, 50);
        }

        public void updateGamePanel() {
            repaint();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            player.jump();
            score++; // Increase score after each jump
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