package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Ball {
    private Point2D position;
    private double vx = 2.0;
    private double vy = 2.0;
    private final int SIZE = 30;

    public Ball(double x, double y) {
        this.position = new Point2D.Double(x, y);
    }

    public void update() {
        position.setLocation(position.getX() + vx, position.getY() + vy);
    }

    public void draw(Graphics2D g2) {
        g2.setColor(Color.RED);
        g2.fillOval((int)position.getX(), (int)position.getY(), SIZE, SIZE);
    }

    public void setVelocity(double vx, double vy) {
        this.vx = vx;
        this.vy = vy;
    }
    
}

