package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Ball implements Collidable {
    private int x, y, radius = 15;
    private int dx = 3;
    private int dy = 2;
    private BufferedImage sprite;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            sprite = ImageIO.read(Ball.class.getResource("tennis.png"));
        } catch (IOException e) {
            sprite = null;
        }
    }

    public void update() {
        x += dx;
        y += dy;
        if (x <= 0 || x + 2*radius >= 600) dx = -dx;
        if (y <= 0 || y + 2*radius >= 600) dy = -dy;
    }

    public void reverse() {
        dx = -dx;
        dy = -dy;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, 2 * radius, 2 * radius);
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, 2*radius, 2*radius, null);
        } else {
            g2.setColor(Color.RED);
            g2.fillOval(x, y, 2*radius, 2*radius);
        }
        g2.setColor(Color.RED);
        g2.draw(getBounds());
    }
}

