package ui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JComponent;
import model.GameModel;

public class GameComponent extends JComponent {
    private GameModel model;

    public GameComponent(GameModel model) {
        this.model = model;
        this.setFocusable(true);

        // Key Listener (Tier 1 Requirement)
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_UP) model.getBall().setVelocity(0, -5);
                if (e.getKeyCode() == KeyEvent.VK_DOWN) model.getBall().setVelocity(0, 5);
                if (e.getKeyCode() == KeyEvent.VK_LEFT) model.getBall().setVelocity(-5, 0);
                if (e.getKeyCode() == KeyEvent.VK_RIGHT) model.getBall().setVelocity(5, 0);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        model.getBall().draw(g2); // Draw the ball
    }
}
