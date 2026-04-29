package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import javax.swing.JComponent;

import model.GameModel;

public class GameComponent extends JComponent {

    private GameModel model;

    public GameComponent(GameModel model) {
        this.model = model;
        
        this.setFocusable(true);

        this.addKeyListener(new java.awt.event.KeyAdapter() {
            @Override
            public void keyPressed(java.awt.event.KeyEvent e) {
                char key = Character.toUpperCase(e.getKeyChar());

                // Key Press → GameComponent → GameModel
                if (key == 'W') {
                    model.movePlayerUp();
                } else if (key == 'A') {
                    model.movePlayerLeft();
                } else if (key == 'S') {
                    model.movePlayerDown();
                } else if (key == 'D') {
                    model.movePlayerRight();
                }

                // After moving, repaint the screen
                repaint();
            }
        });
    }



    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        g2.drawString("Final Project Starter: UI is running ✅", 20, 30);

        // Draw the player
        if (model.getPlayer() != null) {
            model.getPlayer().drawOn(g2);
        }

        // TODO: Draw Walls
        // TODO: Draw Gems
        // TODO: Draw Zombies
        // TODO: Draw Score/Lives overlay
    }
}
