import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.event.ListSelectionListener;
public class GameView extends JFrame {
	
	private JPanel panel;
	private JLabel health;
	private JLabel streetCred;
	private JScrollPane inventory;
	private DefaultListModel inventoryLM;
	
	public GameView(String title)  {
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		
		JButton startButton = makeStartButton();
		
		streetCred = makeStreetCredLabel();
		health = makeHealthLabel();
		inventory = makeInventoryList();
		
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
				panel.add(health);
				panel.add(streetCred);
				panel.add(inventory);
				panel.updateUI();
			}
			}); 
		return startButton;
	}
	public void removeFromView(Component c) {
		panel.remove(c);
		panel.updateUI();
	}
	
	private JLabel makeStreetCredLabel() {
		JLabel streetCredLabel = new JLabel();
		streetCredLabel.setText("Current street cred: ");
		return streetCredLabel;
	}
	
	private JLabel makeHealthLabel() {
		JLabel healthLabel = new JLabel();
		healthLabel.setText("Current health points: ");
		return healthLabel;
	}
	
	public void showHealth(int value) {
		health.setText("Current health points: " + value + "\n");	
		panel.updateUI();
	}
	
	public void showStreetCred(int value) {
		streetCred.setText("Current street cred: " + value + "\n");
		panel.updateUI();
	}
	
	public JScrollPane makeInventoryList() {
		inventoryLM = new DefaultListModel<JLabel>();
		JLabel title = new JLabel();
		title.setText("Inventory");
		JList inventoryList = new JList(inventoryLM);
		inventoryList.setVisibleRowCount(10);
		inventoryLM.addElement("Inventory:");
		
		return new JScrollPane(inventoryList);
	}
	
	public void showInventory(ArrayList<Item> list) {
		for (int i = 0; i < list.size(); i++) {
			inventoryLM.addElement(list.get(i).getName());
		}
		panel.updateUI();
	}

	//show health,streetcred, inventory, etc.
}
