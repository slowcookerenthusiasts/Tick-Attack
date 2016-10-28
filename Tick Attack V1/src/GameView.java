import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
public class GameView extends JFrame {
	
	JPanel panel;
	private JLabel health;
	private JLabel streetCred;
	
	public GameView(String title)  {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		JButton startButton = makeStartButton();
		streetCred = makeStreetCredButton();
		
		panel.add(startButton);
		
		pack();
		setSize(400,400);
		setVisible(true);
	}
	
	private JButton makeStartButton() {
		JButton startButton = new JButton();
		JLabel startText = new JLabel();
		
		startText.setText("Let's start!");
		startText.setHorizontalAlignment(JButton.CENTER);
		startText.setVerticalAlignment(JButton.CENTER);
		startButton.add(startText);
		
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				removeFromView(startButton);
				panel.add(streetCred);
			}
			}); 
		return startButton;
	}
	public void removeFromView(Component c) {
		panel.remove(c);
		panel.updateUI();
	}
	
	private JLabel makeStreetCredButton() {
		JLabel streetCredButton = new JLabel();
		streetCredButton.setText("Current street cred: ");
		return streetCredButton;
	}
	
	public void showHealth(int value) {
				
	}
	
	public void showStreetCred(int value) {
		streetCred.setText("Current street cred: " + value);
		panel.updateUI();
	}
	
	public void showInventory() {
		
	}

	//show health,streetcred, inventory, etc.
}
