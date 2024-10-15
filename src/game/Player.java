package game;

public class Player {
    private int x;
    private int y;

    private boolean isJumping, up, down, left, right; // To track if the player is currently jumping or moving

    public Player() {
    	
        this.x = 100; // Initial horizontal position
        this.y = 400; // Initial vertical position (ground level)
        this.isJumping = false; // Initially not jumping

    }


    public void jump() {
        if (!isJumping) {
            isJumping = true; // Set jumping state
            System.out.println("Jump button pressed");

            // Simulate jump by changing y position
            // Example jump logic (you'll need to manage the jump height and gravity)
            this.y -= 50; // Move up by 50 pixels (jump height)
            // You can add a timer or game loop logic to bring the player back down
        }
    }
    
    public void applyGravity() {
        if (isJumping) {
            // Logic to bring the player back down
            // For example, decrementing the y position to simulate falling
            this.y += 5; // Gravity effect (falling speed)
            if (this.y >= 400) { // Reset to ground level
                this.y = 400;
                isJumping = false; // Reset jumping state
            }
        }
    }
    
    public void run() {
    	x = 100;
    	y = 100;
    while(true) {
    	if (left == true) {
    		x--;
    	}
    	if (right == true) {
    		x++;
    	}
    	if (up == true) {
    		y--;
    	}
    }
    	
    }
    
    // Optional Getters for position 
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


}
