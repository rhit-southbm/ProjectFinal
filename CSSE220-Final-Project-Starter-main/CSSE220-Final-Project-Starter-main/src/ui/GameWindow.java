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

        frame.setSize(600, 600);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Crucial for keyboard control
        component.requestFocusInWindow();

        // Timer runs every 16ms (~60 FPS)
        Timer timer = new Timer(16, e -> {
            model.update();
            component.repaint();
        });
        timer.start();
    }
}

