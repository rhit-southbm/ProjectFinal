package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import model.Ball;
import model.Item;
import model.GameModel;

public class GameComponent extends JPanel {
    private GameModel model;

    public GameComponent(GameModel model) {
        this.model = model;
        this.setPreferredSize(new Dimension(model.getGridWidth(), model.getGridHeight()));
        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                
                if (code == KeyEvent.VK_W) model.movePlayer(0, -1);
                if (code == KeyEvent.VK_S) model.movePlayer(0, 1);
                if (code == KeyEvent.VK_A) model.movePlayer(-1, 0);
                if (code == KeyEvent.VK_D) model.movePlayer(1, 0);
                
                if (code == KeyEvent.VK_R) {
                    model.restartGame();
                }
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        model.drawGridWalls(g2);
        
        if (model.getPlayer() != null) model.getPlayer().draw(g2);
        for (Ball b : model.getEnemies()) b.draw(g2);
        for (Item item : model.getCollectibles()) {
            item.draw(g2);
        }
        
        g2.setColor(Color.BLACK);
        g2.setFont(new Font("Arial", Font.BOLD, 16));
        g2.drawString("Score: " + model.getScore(), 30, 40);
        g2.drawString("Lives Remaining: " + model.getLives(), 30, 60);
        g2.drawString("Current Level: " + model.getLevelNumber(), 30, 80);
        
        if (model.isGameOver()) {
            g2.setColor(Color.RED);
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("GAME OVER!!!!!", getWidth() / 2 - 130, getHeight() / 2 - 20);
            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            g2.drawString("Press 'R' to Restart the Game", getWidth() / 2 - 120, getHeight() / 2 + 20);
        }
        
        if (model.isGameWon()) {
            g2.setColor(new Color(0, 153, 76)); 
            g2.setFont(new Font("Arial", Font.BOLD, 36));
            g2.drawString("YOU WIN!!!!! 🚀", getWidth() / 2 - 120, getHeight() / 2 - 20);
            g2.setFont(new Font("Arial", Font.PLAIN, 18));
            g2.drawString("Amazing job! Press 'R' to play again.", getWidth() / 2 - 150, getHeight() / 2 + 20);
        }
    }
}



