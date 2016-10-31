import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.*;

public class GameView extends JFrame implements IGameView{
	
	private JPanel panel;
	private JLabel health;
	private JLabel streetCred;
	private JScrollPane inventory;
	private JButton quest1Button;
	private JButton quest2Button;
	private QuestView quest1View;
	private QuestView quest2View;
	private JButton startButton;
	private GameController gameController;
	
	private DefaultListModel<String> inventoryLM;
	
	/**
	 * Constructor, the view is initialized to make a start button, streetCred label,
	 * health label, and inventory list
	 */
	
	public GameView()  {
		
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

	/**
	 * Setter, will set the view's game controller to be
	 * @param gc
	 */
	public void setGameController(GameController gc) {
		gameController = gc;
	}
	
	/**
	 * Getter for quest 1 button
	 */
	public JButton getQuest1Button() {
		return quest1Button;
	}
	
	/**
	 * Getter for quest 1 button
	 */
	public JButton getQuest2Button() {
		return quest2Button;
	}
	
	/**
	 * This method will add the startButton to the view and update it.
	 */
	public void showStartButton() {
		panel.add(startButton);
		panel.updateUI();
	}
	
	/**
	 * This method will create a start button. If clicked, the button will disappear, showing
	 * the player's health, street cred, and inventory.
	 * @return a JButton, which is a start button
	 */
	private JButton makeStartButton() {
		JButton startButton = new JButton();
		JLabel startText = new JLabel();
		
		startText.setText("Let's start!");
		startText.setHorizontalAlignment(JButton.CENTER);
		startText.setVerticalAlignment(JButton.CENTER);
		startButton.add(startText);
		
		startButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gameController.changeStartState(true);
				removeFromView(startButton);
				panel.add(health);
				panel.add(streetCred);
				panel.add(inventory);
				panel.updateUI();
			}
			}); 
		return startButton;
	}
	
	/**
	 * This method will remove a component from the view - as required by the IGameView interface
	 */
	public void removeFromView(Component c) {
		panel.remove(c);
		panel.updateUI();
	}
	
	/**
	 * This method will make a street cred label
	 * @return the label created (as a JLabel)
	 */
	private JLabel makeStreetCredLabel() {
		JLabel streetCredLabel = new JLabel("Current street cred: ");
		return streetCredLabel;
	}
	
	/**
	 * This method will make a health label
	 * @return a JLabel indicating the player's health
	 */
	private JLabel makeHealthLabel() {
		JLabel healthLabel = new JLabel("Current health points: ");
		return healthLabel;
	}
	
	/**
	 * This method will display the player's health through the use of the health label created
	 * Required by IGameView.
	 */
	public void showHealth(int value) {
		health.setText("Current health points: " + value + "\n");	
		panel.updateUI();
	}
	
	/**
	 * This method will display the player's street cred through the use of the street cred label created
	 * Required by IGameView.
	 */
	public void showStreetCred(int value) {
		streetCred.setText("Current street cred: " + value + "\n");
		panel.updateUI();
	}
	
	/**
	 * This method will create a JScrollPane that shows the player's inventory items.
	 * @return a JScrollPane to be used to show inventory
	 */
	public JScrollPane makeInventoryList() {
		inventoryLM = new DefaultListModel<String>();
		JList<String> inventoryList = new JList<String>(inventoryLM);
		inventoryList.setVisibleRowCount(10);
		inventoryLM.addElement("Inventory:");
		return new JScrollPane(inventoryList);
	}
	
	/**
	 * This method is used to show the player's inventory items - as required by IGameView
	 */
	public void showInventory(ArrayList<Item> list) {
		for (int i = 0; i < list.size(); i++) {
			inventoryLM.addElement(list.get(i).getName());
		}
		panel.updateUI();
	}
	
	/**
	 * This method will create and add a button for the first quest and update the panel
	 */
	public void showQuest1Button() {
		panel.add(makeQuest1Button());
		gameController.setQuest1ButtonActive(true);
		panel.updateUI();
	}
	
	/**
	 * This method will create a button for the first quest. Clicking the button will
	 * tell the game controller to start the quest, and remove the button from the view.
	 * @return a JButton that is used to start a quest
	 */
	public JButton makeQuest1Button() {
		quest1View = new QuestView();
		quest1Button = new JButton();
		JLabel label = new JLabel("Start tree planting quest");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		quest1Button.add(label);
		quest1Button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gameController.runQuest("Tree planting quest", quest1View);
				gameController.setQuest1ButtonActive(false);
				removeFromView(quest1Button);
			}
			});
		return quest1Button;
	}
	
	/**
	 * This method will create and add a button for the second quest and update the panel
	 */
	public void showQuest2Button() {
		panel.add(makeQuest2Button());
		gameController.setQuest1ButtonActive(true);
		panel.updateUI();
	}
	
	/**
	 * This method will create a button for the second quest. Clicking the button will
	 * tell the game controller to start the quest, and remove the button from the view.
	 * @return a JButton that is used to start a quest
	 */
	public JButton makeQuest2Button() {
		quest2View = new QuestView();
		quest2Button = new JButton();
		JLabel label = new JLabel("Start antibiotic smuggling quest");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		quest2Button.add(label);
		quest2Button.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				gameController.runQuest("Antibiotic smuggling quest",quest2View);
				gameController.setQuest2ButtonActive(false);
				removeFromView(quest2Button);
			}
			});
		return quest2Button;
	}

}
