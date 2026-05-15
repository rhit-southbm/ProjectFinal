package model;

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

    // Student-style counter instead of systemic millisecond tracking
    private int invincibilityFrames = 0;

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

        int row = 0;
        Scanner scanner = new Scanner(stream);
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            for (int col = 0; col < line.length(); col++) {
                char ch = line.charAt(col);
                int x = col * TILE_SIZE;
                int y = row * TILE_SIZE;

                if (ch == 'P') {
                    this.player = new Player(x, y);
                } else if (ch == 'B') {
                    enemies.add(new Ball(x, y));
                } else if (ch == 'C') {
                    collectibles.add(new Item(x, y));
                }
            }
            row++;
        }
        scanner.close();
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
        if (player != null) player.move(dx, dy);
    }

    public int getScore() { return score; }
    public int getLives() { return lives; }
    public ArrayList<Ball> getEnemies() { return enemies; }
    public ArrayList<Item> getCollectibles() { return collectibles; }
    public Player getPlayer() { return player; }
}


