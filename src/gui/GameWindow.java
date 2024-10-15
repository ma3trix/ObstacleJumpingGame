package gui;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.awt.Graphics;
import game.Player;
import game.Obstacle;

public class GameWindow extends JFrame {
    private BufferedImage backgroundImage;


    private Player player;
    private Obstacle obstacle;

    public GameWindow(Player player, Obstacle obstacle) {
        // Load the background image
        try {
            backgroundImage = ImageIO.read(new File("assets/images/gameBg.png")); // Update the path if necessary
        } catch (IOException e) {
            e.printStackTrace();
            // Handle the exception (e.g., set a default image or notify the user)
        }

        // Set up your window (size, title, etc.)
        setTitle("Obstacle Jumping Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g); // Call the superclass paint method

        // Draw the background image
        if (backgroundImage != null) {
            g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), null);
        }

        // You can also draw other game components here (e.g., player, obstacles)
    }

    
    //
    
    
}
