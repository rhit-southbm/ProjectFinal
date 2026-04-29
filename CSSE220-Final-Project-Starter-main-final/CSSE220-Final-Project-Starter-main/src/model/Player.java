package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;

public class Player {
    private int row;
    private int col;
    private static final int TILE_SIZE = 40;

    public Player(int startRow, int startCol) {
        this.row = startRow;
        this.col = startCol;
    }

    public void moveBy(int dRow, int dCol) {
        this.row += dRow;
        this.col += dCol;
    }

    public void drawOn(Graphics2D g2) {
        g2.setColor(java.awt.Color.RED); 
        int x = this.col * TILE_SIZE;
        int y = this.row * TILE_SIZE;
        g2.fillRect(x, y, TILE_SIZE, TILE_SIZE);
    }
    
   
}


