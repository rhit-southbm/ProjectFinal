package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Ball {
    private int x, y, radius = 15;
    private int dx = 3, dy = 2; // Auto-movement speed
    private BufferedImage sprite;

    public Ball(int x, int y) {
        this.x = x;
        this.y = y;
        try {
            this.sprite = ImageIO.read(getClass().getResourceAsStream("/ui/tennis.png"));
        } catch (Exception e) {
            System.out.println("Could not find tennis.png in src/ui/");
            this.sprite = null;
        }
    }

    public void update() {
        x += dx;
        y += dy;
        // Bounce off 600x600 boundaries
        if (x <= 0 || x + 2*radius >= 600) dx = -dx;
        if (y <= 0 || y + 2*radius >= 600) dy = -dy;
    }

    public void draw(Graphics2D g2) {
        if (sprite != null) {
            g2.drawImage(sprite, x, y, 2*radius, 2*radius, null);
        } else {
            g2.setColor(Color.RED);
            g2.fillOval(x, y, 2*radius, 2*radius);
        }
    }
}


