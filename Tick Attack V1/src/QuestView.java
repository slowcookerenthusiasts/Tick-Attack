import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class QuestView extends JFrame{
	
	private JPanel panel; //the main view for the quest
	private JScrollPane questText; //shows the events happening in the quest
	private DefaultListModel<String> questTextLM;
	private JPanel choiceButtons = new JPanel(); //needed for yes/no buttons
	private QuestController questController;
	
	/**
	 * This method will perform the basic tasks needed to set up a quest view,
	 * including initializing the main view for the quest, displaying the title of the
	 * quest, adding the questText scroll pane, and creating and adding an exit button
	 * @param questTitle is a String that indicated the name of the quest. Needed in order
	 * to display the title of the quest as a JLabel.
	 */
	
	public void initialize(String questTitle) {
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		pack();
		setSize(400,400);
		setVisible(true);

		JLabel title = new JLabel(questTitle);

		questText = makeQuestTextDisplay();
		panel.add(title);
		panel.add(questText);
		
		panel.add(makeExitButton());
		
		panel.updateUI(); //refreshing the view so the new components appear on the screen.
	}
	
	/**
	 * This method will set the quest controller for this view.
	 * @param qCont is a QuestController object that the view can interact with.
	 */
	public void setQC(QuestController qCont) {
		questController = qCont;
	}
	
	/**
	 * This method will create a JScrollPane that is used to display
	 * the events of a particular quest (in a text-based format).
	 * @return a JScrollPane that is used to display the text of an event for
	 * the quest.
	 */
	private JScrollPane makeQuestTextDisplay() {
		questTextLM = new DefaultListModel<String>();
		JList<String> questTextList = new JList<String>(questTextLM);
		questTextList.setVisibleRowCount(10);
		questTextLM.addElement("Events thus far: ");
		
		return new JScrollPane(questTextList);
	}
	
	/**
	 * This method will display the event text by adding it to the questTextLM.
	 * This method will also create and add a 'Next' button to the panel and
	 * update the view.
	 * @param inputText is a String that indicates the text of a particular quest event.
	 */
	
	public void displayTextAndButton(String inputText) {
		questTextLM.addElement(inputText);
		panel.add(makeNextButton());
		panel.updateUI();	
	}
	
	/**
	 * This method will display the event text by adding it to the questTextLM.
	 * The method will then update the panel to reflect these changes.
	 * @param inputText is a String that indicates the text of a particular quest event.
	 */
	
	public void displayJustText(String inputText) {
		questTextLM.addElement(inputText);
		panel.updateUI();
	}
	
	/**
	 * This method will create a 'Next' button. This button is used to advance the
	 * quest events forward, and when it is clicked, will notify the quest controller
	 * to progress forward in the 'story.' The panel is also updated.
	 * @return a JButton that is a 'Next' button.
	 */
	private JButton makeNextButton() {
		JButton nextButton = new JButton();
		JLabel label = new JLabel("Next...");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		nextButton.add(label);
		nextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panel.remove(nextButton);
				questController.progressQuest(); //progressing the story
				panel.updateUI();
			}
			});
		return nextButton;
	}
	
	/**
	 * This method will create an 'Exit' button, which the user can press
	 * at any point to exit the quest, closing the quest view.
	 * @return a JButton that works as an Exit button
	 */
	
	private JButton makeExitButton() {
		JButton exitButton = new JButton();
		JLabel label = new JLabel("Exit Quest");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		exitButton.add(label);
		exitButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panel.removeAll();
				dispose();
			}
			});
		return exitButton;
	}
	
	/**
	 * This method will create a yes and no button, and add them to the choiceButtons panel.
	 * This panel is needed to remove both buttons at the same time without complications
	 * after the user presses one of them. The panel is updated to reflect these changes.
	 */
	
	public void promptChoice() {
		choiceButtons.add(makeYesButton());
		choiceButtons.add(makeNoButton());
		panel.add(choiceButtons);
		panel.updateUI();
	}
	
	/**
	 * This method will create a 'yes' button. Clicking the button will prompt
	 * the quest controller to make a decision, based on the value 'true.' The yes and
	 * no buttons are removed (by removing choiceButtons) and the story is progressed.
	 * The panel is then updated.
	 * @return a JButton, that serves as a 'Yes' button.
	 */
	private JButton makeYesButton() {
		JButton yesButton = new JButton();
		JLabel label = new JLabel("Yes");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		yesButton.add(label);
		yesButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questController.makeDecision(true);
				panel.remove(choiceButtons);
				questController.progressQuest();
				panel.updateUI();
			}
			});
		return yesButton;
		
	}
	
	/**
	 * This method will create a 'no' button. Clicking the button will prompt
	 * the quest controller to make a decision, based on the value 'false.' The yes and
	 * no buttons are removed (by removing choiceButtons) and the story is progressed.
	 * The panel is then updated.
	 * @return a JButton, that serves as a 'No' button.
	 */
	
	private JButton makeNoButton() {
		JButton noButton = new JButton();
		JLabel label = new JLabel("No");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		noButton.add(label);
		noButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				questController.makeDecision(false);
				panel.remove(choiceButtons);
				questController.progressQuest();
				panel.updateUI();
			}
			});
		return noButton;
		
	}
}
