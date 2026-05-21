package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.Random;

public class Ball implements Collidable {
    private int x, y;
    private final int size = GameModel.TILE_SIZE;
    private BufferedImage sprite;
    private final Random rand = new Random();
    
    private int tickCounter = 0;
    private final int MOVE_DELAY = 20; 

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Ball.class.getResource("tennis.png"));
        } catch (IOException e) {
            sprite = null;
        }
    }

    public void update(GameModel model) {
        tickCounter++;
        if (tickCounter < MOVE_DELAY) return; 
        tickCounter = 0;

        int dir = rand.nextInt(4);
        int targetCol = x / GameModel.TILE_SIZE;
        int targetRow = y / GameModel.TILE_SIZE;

        if (dir == 0) targetRow--;
        if (dir == 1) targetRow++;
        if (dir == 2) targetCol--;
        if (dir == 3) targetCol++;

        // Ensure the target tile inside the maze layout isn't a wall block
        if (!model.isWall(targetRow, targetCol)) {
            x = targetCol * GameModel.TILE_SIZE;
            y = targetRow * GameModel.TILE_SIZE;
        }
    }

    public void reverse() {}

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, size, size, null);
        } else {
            g2.setColor(Color.RED);
            g2.fillOval(x, y, size, size);
        }
        g2.setColor(Color.RED);
        g2.draw(getBounds());
    }
}


