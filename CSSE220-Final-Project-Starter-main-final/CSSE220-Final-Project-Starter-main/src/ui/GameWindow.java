package ui;

import javax.swing.JFrame;
import javax.swing.Timer;
import model.GameModel;

public class GameWindow {
    public static void show() {
        GameModel model = new GameModel();
        JFrame frame = new JFrame("CSSE220 Final Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GameComponent component = new GameComponent(model);
        frame.add(component);
        
        frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        component.requestFocusInWindow();

        // Step 8: Conditionally update only if elements remain
        Timer timer = new Timer(30, e -> {
            if (!model.isGameOver()) {
                model.update();
            }
            component.repaint();
        });
        timer.start();
    }
}

