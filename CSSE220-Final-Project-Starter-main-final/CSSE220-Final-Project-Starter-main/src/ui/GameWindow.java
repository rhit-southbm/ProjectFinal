package ui;

import java.awt.CardLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import model.GameModel;

public class GameWindow {
    public static void show() {
        GameModel model = new GameModel();
        JFrame frame = new JFrame("CSSE220 Final Project");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

   
        CardLayout cl = new CardLayout();
        JPanel cards = new JPanel(cl);

        startPanel startScreen = new startPanel();
        GameComponent component = new GameComponent(model);
        component.setPreferredSize(new Dimension(model.getGridWidth(), model.getGridHeight())); 

     
        cards.add(startScreen, "START");
        cards.add(component, "GAME");
        
        frame.setContentPane(cards);
        frame.pack(); 
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        
        cl.show(cards, "START");

        
        Timer timer = new Timer(30, e -> {
            if (!model.isGameOver()) {
                model.update();
            }
            component.repaint();
        });

       
        startScreen.getStartButton().addActionListener(e -> {
            cl.show(cards, "GAME");
            
            component.setPreferredSize(new Dimension(model.getGridWidth(), model.getGridHeight() + 60));
            frame.pack(); 
            frame.setLocationRelativeTo(null);
            
            component.requestFocusInWindow();
            timer.start();
        });
    }
}

