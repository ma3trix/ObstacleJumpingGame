package game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Obstacle {
    private int x, y;
    private int speed = 5; // Speed at which the obstacle moves left
    private Image obstacleImage;
    private int width = 50; // Desired width for scaling
    private int height = 50; // Desired height for scaling

    public Obstacle(int startX, int startY) {
        this.x = startX;
        this.y = 419;

        // Randomly load one of the 10 obstacle images
        int randomObstacleIndex = 1 + (int) (Math.random() * 10); // Random number between 1 and 10
        obstacleImage = new ImageIcon("assets/images/obstacle" + randomObstacleIndex + ".png").getImage();

        // Scale the obstacle image down to the desired size (50x50 in this case)
        obstacleImage = obstacleImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }

    // Move the obstacle left
    public void updatePosition() {
        x -= speed; // Move obstacle to the left
        if (x < -width) { // Once the obstacle goes off-screen, reset its position
            reset();
        }
    }

    // Reset the obstacle to start on the right side of the screen again
    private void reset() {
        x = 800 + (int) (Math.random() * 400); // Respawn off-screen with some random variation
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return obstacleImage;
    }
}
