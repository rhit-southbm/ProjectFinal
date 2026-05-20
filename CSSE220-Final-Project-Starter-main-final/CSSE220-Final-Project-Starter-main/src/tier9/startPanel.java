package tier9;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class startPanel extends JPanel {
	
	private JButton startButton;
	
	public startPanel() {
		
		setLayout(new BorderLayout());
		
		JLabel title = new JLabel("Game Start");
		title.setHorizontalAlignment(SwingConstants.CENTER);
		startButton = new JButton("Start");
		
		add(title, BorderLayout.CENTER);
		add(startButton, BorderLayout.SOUTH);
	}
	
	public JButton getStartButton() {
		return startButton;
	}
}
