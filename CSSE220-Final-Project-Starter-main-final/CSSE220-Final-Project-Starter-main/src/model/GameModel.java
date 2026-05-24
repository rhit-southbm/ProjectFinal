package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

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

    private int levelNumber = 1;
    private boolean gameWon = false;
    private boolean hasKey = false;

    private BufferedImage wallSprite;
    private BufferedImage bgSprite;

    public GameModel() {
        this.enemies = new ArrayList<>();
        this.collectibles = new ArrayList<>();
        this.score = 0;
        this.lives = 3;
        
        try {
            this.wallSprite = ImageIO.read(GameModel.class.getResource("wall.png"));
            this.bgSprite = ImageIO.read(GameModel.class.getResource("bg.png"));
        } catch (Exception e) {
        }
        
        loadLevel("level1.txt");
    }

    public void loadLevel(String filename) {
        this.currentFilename = filename;
        this.enemies.clear();
        this.collectibles.clear();
        this.hasKey = false;
        
        InputStream stream = GameModel.class.getResourceAsStream(filename);
        if (stream == null) {
            throw new RuntimeException("Level file not found: " + filename);
        }

        ArrayList<String> lines = new ArrayList<>();
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
                int pixelX = col * TILE_SIZE;
                int pixelY = row * TILE_SIZE;

                if (ch == 'P') {
                    this.player = new Player(pixelX, pixelY);
                } else if (ch == 'Z' || ch == 'B') { 
                    enemies.add(new Ball(pixelX, pixelY));
                } else if (ch == 'C') {
                    collectibles.add(new Item(pixelX, pixelY));
                }
            }
        }
    }

    public boolean isWall(int row, int col) {
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[row].length) {
            return true;
        }
        char tile = grid[row][col];
        if (tile == '#' || tile == '*') return true;
        if (tile == 'D' && !hasKey) return true;
        return false;
    }

    public int getGridWidth() {
        if (grid == null || grid.length == 0) return 600;
        return grid[0].length * TILE_SIZE;
    }
    
    public int getGridHeight() {
        if (grid == null) return 600;
        return grid.length * TILE_SIZE;
    }
    
    public void drawGridWalls(Graphics2D g2) {
        int ts = TILE_SIZE;
        
        if (bgSprite != null) {
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[row].length; col++) {
                    g2.drawImage(bgSprite, col * ts, row * ts, ts, ts, null);
                }
            }
        } else {
            g2.setColor(Color.BLACK);
            g2.fillRect(0, 0, getGridWidth(), getGridHeight());
        }

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                char tile = grid[row][col];
                int x = col * ts;
                int y = row * ts;

                if (tile == '*' || tile == '#') {
                    if (wallSprite != null) {
                        g2.drawImage(wallSprite, x, y, ts, ts, null);
                    } else {
                        g2.setColor(Color.DARK_GRAY);
                        g2.fillRect(x, y, ts, ts);
                    }
                } else if (tile == 'E') {
                    g2.setColor(Color.BLUE); 
                    g2.fillRect(x, y, ts, ts);
                } else if (tile == 'D') {
                    g2.setColor(new Color(102, 51, 0)); 
                    g2.fillRect(x, y, ts, ts);
                }
            }
        }
    }

    public void restartGame() {
        this.score = 0;
        this.lives = 3;
        this.levelNumber = 1;
        this.gameWon = false;
        this.invincibilityFrames = 0;
        loadLevel("level1.txt");
    }

    public void update() {
        if (isGameOver() || isGameWon()) return;

        if (invincibilityFrames > 0) {
            invincibilityFrames--;
        }

        for (Ball b : enemies) {
            b.update(this);
        }

        for (int i = collectibles.size() - 1; i >= 0; i--) {
            if (player.getBounds().intersects(collectibles.get(i).getBounds())) {
                collectibles.remove(i);
                score += 10;
            }
        }

        for (Ball b : enemies) {
            if (player.getBounds().intersects(b.getBounds())) {
                if (invincibilityFrames == 0) {
                    lives--;
                    invincibilityFrames = 15;
                }
            }
        }

        int playerCol = (player.getX() + TILE_SIZE / 2) / TILE_SIZE;
        int playerRow = (player.getY() + TILE_SIZE / 2) / TILE_SIZE;
        
        if (playerRow >= 0 && playerRow < grid.length && playerCol >= 0 && playerCol < grid[playerRow].length) {
            char currentTile = grid[playerRow][playerCol];
            
            if (currentTile == 'K') {
                hasKey = true;
                grid[playerRow][playerCol] = '.'; 
            }
            if (currentTile == 'D' && hasKey) {
                grid[playerRow][playerCol] = '.'; 
            }

            if (currentTile == 'E' && collectibles.isEmpty()) {
                if (levelNumber == 1) {
                    levelNumber = 2;
                    loadLevel("level2.txt");
                    return; 
                } else if (levelNumber == 2) {
                    this.gameWon = true; 
                }
            }
        }
    }

    public void movePlayer(int dx, int dy) {
        if (isGameOver() || isGameWon()) return;
        if (player != null) {
            int currentLeftCol = (player.getX() + TILE_SIZE / 2) / TILE_SIZE;
            int currentTopRow = (player.getY() + TILE_SIZE / 2) / TILE_SIZE;
            
            int targetCol = currentLeftCol + dx;
            int targetRow = currentTopRow + dy;
            
            if (!isWall(targetRow, targetCol)) {
                player.move(dx * TILE_SIZE, dy * TILE_SIZE);
            }
        }
    }

    public boolean isGameOver() { return lives <= 0; }
    public boolean isGameWon() { return gameWon; }
    public int getLevelNumber() { return levelNumber; }
    public int getScore() { return score; }
    public int getLives() { return lives; }
    public boolean hasKey() { return hasKey; }
    public ArrayList<Ball> getEnemies() { return enemies; }
    public ArrayList<Item> getCollectibles() { return collectibles; }
    public Player getPlayer() { return player; }
}




