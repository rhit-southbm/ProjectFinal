package ui;

import javax.swing.JFrame;
import javax.swing.Timer;
import model.GameModel;

public class GameWindow {
    // This is the static method MainApp calls
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

        Timer timer = new Timer(30, e -> {
            model.update();
            component.repaint();
        });
        timer.start();
    }
}

