package game;

import java.awt.Image;
import javax.swing.ImageIcon;

public class Player {
    private int x, y;
    private boolean isJumping;
    private Image[] runImages; // Store player running animation frames
    private int currentFrame = 0; // Track the current animation frame
    private int jumpSpeed = 0; // Speed of the jump (will change during jumping)

    // Movement flags
    private boolean left, right, up, down;

    public Player() {
        // Initial position
        this.x = 100; // Horizontal position
        this.y = 370; // Ground level position
        this.isJumping = false; // Initially not jumping
        loadRunImages(); // Load the player's running animation frames
    }

    // Load the player's running animation frames from assets
    private void loadRunImages() {
        runImages = new Image[8];
        for (int i = 0; i < 8; i++) {
            runImages[i] = new ImageIcon("assets/images/run" + (i + 1) + ".png").getImage();
        }
    }

    // Movement methods (can be controlled by KeyListener)
    public void moveRight() {
        if (x < 750) { // Limit the right movement to avoid moving off-screen
            x += 5; // Move to the right
            currentFrame = (currentFrame + 1) % runImages.length; // Cycle through running animation frames
        }
    }

    public void moveLeft() {
        if (x > 0) { // Limit the left movement to avoid moving off-screen
            x -= 5; // Move to the left
            currentFrame = (currentFrame + 1) % runImages.length; // Cycle through running animation frames
        }
    }

    // Jump method
    public void jump() {
        if (!isJumping) {
            isJumping = true; // Set jumping state
            jumpSpeed = -15; // Set jump speed (negative means moving up)
        }
    }

    // Apply gravity to bring the player back down
    public void applyGravity() {
        if (isJumping) {
            y += jumpSpeed; // Adjust player's vertical position based on jump speed
            jumpSpeed += 1; // Gravity gradually increases the fall speed

            if (y >= 370) { // Check if the player hits the ground
                y = 370; // Reset to ground level
                isJumping = false; // Reset jumping state
                jumpSpeed = 0; // Reset jump speed
            }
        }
    }

    // Get the current frame of the player's running animation
    public Image getCurrentImage() {
        return runImages[currentFrame];
    }

    // Getters for player position
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    // Setters for movement control (set by KeyListener)
    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public void setUp(boolean up) {
        this.up = up;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    // Run method to continuously update movement (used in the game loop)
    public void run() {
        if (left) {
            moveLeft();
        }
        if (right) {
            moveRight();
        }
        // Jumping logic is handled separately in applyGravity() and jump()
    }
}
