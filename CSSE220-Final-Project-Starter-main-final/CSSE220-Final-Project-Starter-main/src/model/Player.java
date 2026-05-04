package model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Player {
    private int x, y, size = 15;
    private BufferedImage sprite;

    public Player(int x, int y) {
        this.x = x;
        this.y = y;
        this.size = size;
//        try {
//			sprite = ImageIO.read(Player.class.getResource("zombie.png"));
//		} catch (IOException e) {
//			sprite = null;
//		}

    }

    public void move(int dx, int dy) {
        x += dx;
        y += dy;
        // Basic screen boundary check
        if (x < 0) x = 0;
        if (x > 600 - size) x = 600 - size;
        if (y < 0) y = 0;
        if (y > 600 - size) y = 600 - size;
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, size, size, null);
        } else {
            g2.setColor(java.awt.Color.GREEN);
            g2.fillRect(x, y, size, size);
        }
    }
}




