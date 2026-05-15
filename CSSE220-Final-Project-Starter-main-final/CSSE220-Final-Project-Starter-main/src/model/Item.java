package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Item implements Collidable {
    private int x, y, size = 20;

    public Item(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(x, y, size, size);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.ORANGE);
        g2.fillRect(x, y, size, size);
        g2.setColor(Color.RED);
        g2.draw(getBounds());
    }
}
