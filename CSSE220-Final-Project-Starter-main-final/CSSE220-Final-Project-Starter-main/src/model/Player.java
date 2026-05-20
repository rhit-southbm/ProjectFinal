package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player implements Collidable {
    private int x, y, size = 15;
    private BufferedImage sprite;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Player.class.getResource("zombie.png"));
        } catch (IOException e) {
            sprite = null;
        }
    }
    


    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }
    
    public int getX() {
    	return x;
    }
    
    public int getY() {
    	return y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, size, size, null);
        } else {
            g2.setColor(Color.GREEN);
            g2.fillRect(x, y, size, size);
        }
        g2.setColor(Color.RED);
        g2.draw(getBounds());
    }
}





