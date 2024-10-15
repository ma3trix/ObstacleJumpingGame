package game;

import gui.GameWindow;
import gui.GameKeyListener;
import javax.swing.JFrame;

public class Game {
    private Player player;
    private Obstacle obstacle;
    private GameWindow gameWindow;

    public Game() {
        // Initialize game components
        player = new Player();
        obstacle = new Obstacle();
        gameWindow = new GameWindow(player, obstacle);
        
        // Set up KeyListener
        gameWindow.addKeyListener(new GameKeyListener(player));
    }

    public void start() {
        // Start the game loop or any initialization needed
        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameWindow.setSize(800, 600); // Set the desired size of the game window
        gameWindow.setVisible(true); // Make the window visible

        // Implement your game loop here, possibly using a timer
    }

    // You can add more methods here to manage game logic
}
