package ui;

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.Timer;
import model.GameModel;

public class GameWindow {
    public static void show() {
        GameModel model = new GameModel();
        JFrame frame = new JFrame("CSSE220 Final Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        
        GameComponent component = new GameComponent(model);
        component.setPreferredSize(new Dimension(model.getGridWidth(), model.getGridHeight())); // Restores form-fitting size
        frame.add(component);
        frame.pack();

        
        
        frame.add(component);
        frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        component.requestFocusInWindow();

        Timer timer = new Timer(30, e -> {
            if (!model.isGameOver()) {
                model.update();
            }
            component.repaint();
        });
        timer.start();
        
        
    }
}

