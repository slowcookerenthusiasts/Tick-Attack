import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
public class GameView extends JFrame {
	
	JPanel panel;
	
	public GameView(String title, String prompt)  {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		JButton startButton = makeStartButton();
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
				System.out.println("Hi!");
				removeFromView(startButton);
			}
			}); 
		return startButton;
	}
	public void removeFromView(Component c) {
		panel.remove(c);
		panel.updateUI();
	}
	
	public void showHealth() {
		
	}
	
	public void showStreetCred() {
		
	}
	
	public void showInventory() {
		
	}

	//show health,streetcred, inventory, etc.
}
