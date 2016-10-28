import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.*;
public class GameView extends JFrame {
	
	public GameView(String title, String prompt)  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		JPanel panel = (JPanel) getContentPane();
		panel.setLayout(new BorderLayout());
		pack();
		setSize(400,400);
		setVisible(true);
	}
	
	public void removeFromView() {
		
	}
	
	public void showHealth() {
		
	}
	
	public void showStreetCred() {
		
	}
	
	public void showInventory() {
		
	}

	//show health,streetcred, inventory, etc.
}
