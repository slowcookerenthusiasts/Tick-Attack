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
	
	private JPanel panel;
	private JScrollPane questText;
	private DefaultListModel<String> questTextLM;
	private boolean yesButtonClicked;
	private boolean noButtonClicked;
	private JPanel choiceButtons = new JPanel();
	private QuestController questController;
	
	
	public void initialize(String questTitle) {
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		pack();
		setSize(400,400);
		setVisible(true);

		JLabel title = new JLabel();
		title.setText(questTitle);

		questText = makeQuestTextDisplay();
		panel.add(title);
		panel.add(questText);
		panel.add(makeExitButton());
		panel.updateUI();
	}
	
	public void setQC(QuestController qCont) {
		questController = qCont;
	}
	
	private JScrollPane makeQuestTextDisplay() {
		questTextLM = new DefaultListModel<String>();
		JList<String> questTextList = new JList<String>(questTextLM);
		questTextList.setVisibleRowCount(10);
		questTextLM.addElement("Events thus far: ");
		
		return new JScrollPane(questTextList);
	}
	
	public void displayTextAndButton(String inputText) {
		questTextLM.addElement(inputText);
		panel.add(makeNextButton());
		panel.updateUI();	
	}
	
	public void displayJustText(String inputText) {
		questTextLM.addElement(inputText);
		panel.updateUI();
	}
	
	private JButton makeNextButton() {
		JButton nextButton = new JButton();
		JLabel label = new JLabel("Next...");
		label.setHorizontalAlignment(JButton.CENTER);
		label.setVerticalAlignment(JButton.CENTER);
		nextButton.add(label);
		nextButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				panel.remove(nextButton);
				questController.progressQuest();
				panel.updateUI();
			}
			});
		return nextButton;
	}
	
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
	
	public void promptChoice() {
		choiceButtons.add(makeYesButton());
		choiceButtons.add(makeNoButton());
		panel.add(choiceButtons);
		panel.updateUI();
	}
	
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
