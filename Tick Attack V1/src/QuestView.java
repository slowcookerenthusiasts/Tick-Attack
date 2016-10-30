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
	private ButtonOptions yesButtonClicked = ButtonOptions.NONE;
	private ButtonOptions noButtonClicked = ButtonOptions.NONE;
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
	
	public enum ButtonOptions {
		TRUE, FALSE, NONE;
	}
	private JScrollPane makeQuestTextDisplay() {
		questTextLM = new DefaultListModel<String>();
		JList<String> questTextList = new JList<String>(questTextLM);
		questTextList.setVisibleRowCount(10);
		questTextLM.addElement("Events thus far: ");
		
		return new JScrollPane(questTextList);
	}
	
	public void displayText(String inputText) {
		questTextLM.addElement(inputText);
		panel.add(makeNextButton());
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
				yesButtonClicked = ButtonOptions.TRUE;
				noButtonClicked = ButtonOptions.FALSE;
				panel.remove(choiceButtons);
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
				yesButtonClicked = ButtonOptions.FALSE;
				noButtonClicked = ButtonOptions.TRUE;
				panel.remove(choiceButtons);
				panel.updateUI();
			}
			});
		return noButton;
		
	}
	
	public boolean getChoice() {	
		if (noButtonClicked == ButtonOptions.TRUE) {
			return false;
		} else if (yesButtonClicked == ButtonOptions.TRUE) {
			return true;
		} else {
			return false;
		}
		
	}

}
