package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * GameModel manages the game state, layout loading, and entity updates.
 */
public class GameModel {
    public static final int TILE_SIZE = 40;
    
    private Player player;
    private ArrayList<Ball> enemies;
    private ArrayList<Item> collectibles;
    
    private int score;
    private int lives;
    private String currentFilename;
    private int invincibilityFrames = 0;
    private char[][] grid;

    /**
     * Initializes lists and kicks off level generation.
     */
    public GameModel() {
        this.enemies = new ArrayList<>();
        this.collectibles = new ArrayList<>();
        this.score = 0;
        this.lives = 3;
        loadLevel("level1.txt");
    }

    /**
     * Parses level configurations out of a text source asset.
     */
    public void loadLevel(String filename) {
        this.currentFilename = filename;
        this.enemies.clear();
        this.collectibles.clear();
        
        InputStream stream = GameModel.class.getResourceAsStream(filename);
        if (stream == null) {
            throw new RuntimeException("Level file not found: " + filename);
        }
        
        ArrayList<String> lines = new ArrayList();
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()) {
        	lines.add(scanner.nextLine());
        }
        scanner.close();
        
        grid = new char[lines.size()][];
        
        for (int row = 0; row < lines.size(); row++) {
        	String line = lines.get(row);
        	grid[row] = line.toCharArray();
        	
        	for (int col = 0; col < grid[row].length; col++) {
        		char ch = grid[row][col];
        		int pixelsX = col*TILE_SIZE;
        		int pixelsY = row*TILE_SIZE;
        		
        		if (ch == 'P') {
        			this.player = new Player(pixelsX, pixelsY);
        		} else if (ch == 'Z' || ch == 'B') {
        			enemies.add(new Ball(pixelsX, pixelsY));
        		} else if (ch == 'C' ) {
        			collectibles.add(new Item(pixelsX, pixelsY));
        		}
        		}
        	
        				
        	}
        }
    
    public boolean isWall(int row, int col) {
    	if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
    		return true;
    	}
    	return grid[row][col] == '#';
    	}
    
    public int getGridWidth() {
    	if (grid == null || grid.length == 0) return 600;
    	return grid[0].length*TILE_SIZE;
    }
    
    public int getGridHeight() {
    	if (grid == null) return 600;
    	return grid.length*TILE_SIZE;
    }
    
    public void drawGridWalls(Graphics2D g2) {
    	g2.setColor(Color.black);
    	for (int row = 0; row < grid.length; row++) {
    		for (int col = 0; col < grid[row].length; col++) {
    			if (grid[row][col] == '*' || grid[row][col] == '#') {
    				g2.fillRect(col*TILE_SIZE, row*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    			}
    		}
    	}
    }

    /**
     * Resets system tracking parameters and triggers a reload.
     */
    public void restartGame() {
        this.score = 0;
        this.lives = 3;
        this.invincibilityFrames = 0;
        loadLevel(this.currentFilename);
    }

    /**
     * Evaluates frame movements, bounces, and interactive overlap triggers.
     */
    public void update() {
        if (isGameOver()) return;

        // Count down safety frames each loop iteration
        if (invincibilityFrames > 0) {
            invincibilityFrames--;
        }

        for (Ball b : enemies) {
            b.update();
        }

        for (int i = 0; i < enemies.size(); i++) {
            for (int j = i + 1; j < enemies.size(); j++) {
                Ball a = enemies.get(i);
                Ball b = enemies.get(j);
                if (a.getBounds().intersects(b.getBounds())) {
                    a.reverse();
                    b.reverse();
                }
            }
        }

        for (Ball b : enemies) {
            if (player.getBounds().intersects(b.getBounds())) {
                if (invincibilityFrames == 0) {
                    lives--;
                    invincibilityFrames = 15; // Safe buffer for 15 updates (~500ms)
                }
                b.reverse();
            }
        }

        for (int i = collectibles.size() - 1; i >= 0; i--) {
            if (player.getBounds().intersects(collectibles.get(i).getBounds())) {
                collectibles.remove(i);
                score += 10;
            }
        }
    }

    /**
     * Checks if the active player has lost all health attributes.
     */
    public boolean isGameOver() {
        return lives <= 0;
    }

    /**
     * Updates character positioning bounds if execution stays active.
     */
    public void movePlayer(int dx, int dy) {
        if (isGameOver()) return;
        if (player != null) {
        	int currentLeftCol = player.getX() /TILE_SIZE;
        	int currentTopRow = player.getY() /TILE_SIZE;
        	
        	int targetCol = currentLeftCol + dx;
        	int targetRow = currentTopRow + dy;
        	
        	if (!isWall(targetRow, targetCol)) {
        		player.move(dx*TILE_SIZE, dy*TILE_SIZE);
    }
        	
        }
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public ArrayList<Ball> getEnemies() { return enemies; }
    public ArrayList<Item> getCollectibles() { return collectibles; }
    public Player getPlayer() { return player; }
}


