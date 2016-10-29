import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class QuestView extends JFrame{
	
	private JPanel panel;
	
	public void initialize(String questTitle) {
		panel = (JPanel) getContentPane();
		panel.setLayout(new FlowLayout());
		pack();
		setSize(400,400);
		setVisible(true);
		
		JLabel title = new JLabel();
		title.setText(questTitle);
		panel.add(title);
		panel.updateUI();
	}

}
