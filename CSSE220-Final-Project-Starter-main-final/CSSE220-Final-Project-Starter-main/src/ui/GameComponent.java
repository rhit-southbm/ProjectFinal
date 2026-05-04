package ui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPanel;
import model.GameModel;

public class GameComponent extends JPanel {
    private GameModel model;

    public GameComponent(GameModel model) {
        this.model = model;
        this.setPreferredSize(new Dimension(600, 600)); // Fixes the shrinking issue
        this.setFocusable(true);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                int code = e.getKeyCode();
                if (code == KeyEvent.VK_W) model.getPlayer().move(0, -10);
                if (code == KeyEvent.VK_S) model.getPlayer().move(0, 10);
                if (code == KeyEvent.VK_A) model.getPlayer().move(-10, 0);
                if (code == KeyEvent.VK_D) model.getPlayer().move(10, 0);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        model.getEnemy().draw(g2);
        model.getPlayer().draw(g2);
    }
}