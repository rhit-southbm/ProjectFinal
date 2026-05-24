package ui;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class startPanel extends JPanel {
    
    private JButton startButton;
    
    public startPanel() {
        setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        setLayout(new BorderLayout());
        
        JLabel title = new JLabel("CSSE220 Zombie Maze Game");
        title.setFont(new Font("Arial", Font.BOLD, 24));
        title.setHorizontalAlignment(SwingConstants.CENTER);
        
        startButton = new JButton("Start Game");
        startButton.setFont(new Font("Arial", Font.PLAIN, 16));
        
        add(title, BorderLayout.CENTER);
        add(startButton, BorderLayout.SOUTH);
    }
    
    public JButton getStartButton() {
        return startButton;
    }
}

