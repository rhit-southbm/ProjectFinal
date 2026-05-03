package app;

import javax.swing.SwingUtilities;
import ui.GameWindow;

/**
 * MainApp starts the program
 */
public class MainApp {
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			new MainApp().run();
		});
	}
	
	public void run() {
		// Just call the static show method. 
		// It handles the Model and Window creation internally.
		GameWindow.show();
	}
}
