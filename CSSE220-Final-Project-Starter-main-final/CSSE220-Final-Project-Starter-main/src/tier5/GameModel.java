package tier5;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.Scanner;

/**
 * Represents the core game state (the "model").
 *
 * Responsibilities:
 *   - Store all game data (ball, walls, and other objects)
 *   - Load a level from a text file
 *   - Update the game state over time (movement, collisions)
 *   - Provide data for drawing (but does NOT handle graphics directly)
 */
 
public class GameModel {
	// What game objects do we need to store (walls, enemies, player, coins?
	// TODO: store multiple balls
    // private ArrayList<Ball> balls;
	
	public static final int TILE_SIZE = 40;
	
	private Ball ball;

	public GameModel() {
		// TODO: load a level file (e.g., "level1.txt")
		loadLevel("level1.txt");
	}
	
	/**
	 * Loads a level from a text file. 
	 * Reads file and throws exceptions if invalid
	 *
	 * @param filename name of the level file
	 * @throws IllegalStateException if the level is invalid
	 */
	public void loadLevel(String filename) {
	    // TODO: read file and build game objects
		int row = 0;
		
		InputStream stream = GameModel.class.getResourceAsStream(filename);
		
		if(stream == null) {
			throw new RuntimeException("Level file not found: " + filename);
		}
		
		Scanner scanner = new Scanner(stream); 
		while (scanner.hasNextLine()) { 
			String line = scanner.nextLine(); 
			for (int col = 0; col < line.length(); col++) { 
				char ch = line.charAt(col); 
				if (ch == 'B') { 
					int x = col * TILE_SIZE; 
					int y = row * TILE_SIZE; 
					ball = new Ball(x, y, 14); 
					scanner.close(); 
					return; 
					} 
				} 
			row++; 
			} 
		scanner.close(); 
		throw new IllegalStateException("Level must contain a B.");
	}
	
	public void update() {
        // TODO: update each ball
		if(ball != null) {
			ball.update();
			}
    }

    public void draw(Graphics2D g2) {
        // TODO: draw each ball
    	if(ball != null) {
    		ball.draw(g2);
    	}
    }
    
    public void movePlayer(int dx) {
    	if(ball != null) {
    		ball.shift(dx);
    	}
    }
    
    public void centerPlayer() {
    	if(ball != null) {
    		ball.reset();
    	}
    }
	
}
