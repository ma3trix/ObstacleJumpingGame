package game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private final int FIXED_X = 100; // Fixed horizontal position for endless run
    private int y;
    private boolean isJumping;
    private Image[] runImages;
    private int currentFrame = 0;
    private int jumpSpeed = 0;
    private int frameDelay = 3;
    private int frameCounter = 0;

    private boolean left, right; // Movement flags

    public Player() {
        this.y = 370; // Ground level position
        this.isJumping = false;
        loadRunImages();
    }

    private void loadRunImages() {
        runImages = new Image[8];
        for (int i = 0; i < 8; i++) {
            runImages[i] = new ImageIcon("assets/images/run" + (i + 1) + ".png").getImage();
        }
    }

    public void updateAnimation() {
        frameCounter++;
        if (frameCounter >= frameDelay) {
            frameCounter = 0;
            currentFrame = (currentFrame + 1) % runImages.length;
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpSpeed = -24; // Set initial jump speed
        }
    }

    public void applyGravity() {
        if (isJumping) {
            y += jumpSpeed; // Adjust y-position by jumpSpeed
            jumpSpeed += 1; // Apply gravity

            if (y >= 370) { // Return player to ground
                y = 370;
                isJumping = false; // Reset jump state
            }
        }
    }

    public Image getCurrentImage() {
        return runImages[currentFrame];
    }

    public int getX() {
        return FIXED_X;
    }

    public int getY() {
        return y;
    }

    // Setters for movement flags
    // public void setLeft(boolean left) {
    // this.left = left;
    // }

    // public void setRight(boolean right) {
    // this.right = right;
    // }

    public void run() {
        updateAnimation(); // Always cycle through running animation
        applyGravity(); // Apply gravity when jumping
    }
}
