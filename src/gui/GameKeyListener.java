package gui;

import game.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



public class GameKeyListener implements KeyListener {

    private Player player;

    public GameKeyListener(Player player) {
    	
        this.player = player;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        // Handle key press events
        if (e.getKeyCode() == KeyEvent.VK_SPACE) {
            // Make the player jump when the space bar is pressed
            player.jump();
            
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // Not used
    }
}
