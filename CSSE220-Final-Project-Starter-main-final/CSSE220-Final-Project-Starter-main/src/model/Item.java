package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Item implements Collidable {
    private int x, y;
    private final int size = 24; 
    private BufferedImage sprite;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            this.sprite = ImageIO.read(Item.class.getResource("key.png"));
        } catch (IOException e) {
            this.sprite = null;
        }
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x + 8, y + 8, size, size);
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x + 8, y + 8, size, size, null);
        } else {
            g2.setColor(Color.ORANGE);
            g2.fillRect(x + 10, y + 10, 20, 20);
        }
    }
}
