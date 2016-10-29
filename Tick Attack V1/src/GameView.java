import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class GameView extends JFrame implements IGameView{
	
	private boolean hasStarted = false; //this should actually go with the game
	private JPanel panel;
	private JLabel health;
	private JLabel streetCred;
	private JScrollPane inventory;
	private JButton quest1Button;
	private JButton quest2Button;
	private QuestView quest1View;
	private QuestView quest2View;
	private JButton startButton;
	private QuestController questController;
	
	private DefaultListModel<String> inventoryLM;
	
	public GameView(String title, QuestController qc)  {
		
		 questController = qc;
		 quest1View = questController.getFirstQuestView();
		 quest2View = questController.getSecondQuestView();
		
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		
		startButton = makeStartButton();
		
		streetCred = makeStreetCredLabel();
		health = makeHealthLabel();
		inventory = makeInventoryList();
		
		
		pack();
		setSize(400,400);
		setVisible(true);
	}
	
	public void showStartButton() {
		panel.add(startButton);
		panel.updateUI();
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
				hasStarted = true;
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
	
	public boolean hasStarted() { //this should go with the game
		return hasStarted;
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
		inventoryLM = new DefaultListModel<String>();
		JLabel title = new JLabel();
		title.setText("Inventory");
		JList<String> inventoryList = new JList<String>(inventoryLM);
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
	
	public void showQuest1Button() {
		panel.add(makeQuest1Button());
		panel.updateUI();
	}
	
	public JButton makeQuest1Button() {
		quest1Button = new JButton();
		JLabel label = new JLabel("Start tree planting quest");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		quest1Button.add(label);
		quest1Button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quest1View.initialize("Tree planting quest");
				removeFromView(quest1Button);
			}
			});
		return quest1Button;
	}
	
	public void showQuest2Button() {
		panel.add(makeQuest2Button());
		panel.updateUI();
	}
	
	public JButton makeQuest2Button() {
		quest2Button = new JButton();
		JLabel label = new JLabel("Start antibiotic smuggling quest");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		quest2Button.add(label);
		quest2Button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				quest2View.initialize("Antibiotic smuggling quest");
				removeFromView(quest2Button);
			}
			});
		return quest2Button;
	}

}
