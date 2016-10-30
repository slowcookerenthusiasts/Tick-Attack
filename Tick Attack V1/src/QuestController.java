import java.util.TimerTask;

public class QuestController{

	private GameModel gameModel;
	private QuestView view;
	private QuestDecisionPoint activeNode;
	
	public QuestController(GameModel model, QuestView newView ) {
		gameModel = model;
		view = newView;
		activeNode = gameModel.getActiveQuest();
		view.displayText(activeNode.getDecisionText());
		activeNode = activeNode.generateChild();
	}
	
//-----------------------------------------------------------------------------------------	
	
	public void initializeQuestView(QuestView view, String title) {
		view.initialize(title);
	}
	
	public void test() {
		System.out.println("Yo!");
	}
	
	public void progressQuest() {
		if (activeNode.hasChildren()) {
			view.displayText(activeNode.getDecisionText());
		} else {
			view.displayOutcome(activeNode.getDecisionText());
			return;
		}
		if (activeNode.getPlayerDecisionNeeded()){
			view.promptChoice();
			boolean choice = view.getChoice();
			QuestDecisionPoint child = activeNode.getChosenChild(choice);
			activeNode = child;
		}//if
		else{
			QuestDecisionPoint child = activeNode.generateChild();
			activeNode = child;
		}//else
	}

//-----------------------------------------------------------------------------------------

	public void readQuestModel() {
		QuestDecisionPoint activeNode = gameModel.getActiveQuest();
		while(activeNode.hasChildren()){
				view.displayText(activeNode.getDecisionText());
			if (activeNode.getPlayerDecisionNeeded()){
				view.promptChoice();
				boolean choice = view.getChoice();
				QuestDecisionPoint child = activeNode.getChosenChild(choice);
				activeNode = child;
			}//if
			else{
				QuestDecisionPoint child = activeNode.generateChild();
				activeNode = child;
			}//else
		}//while
	}
	
	
//-----------------------------------------------------------------------------------------	
/**
 * Currently an unfilled method, meant to exist so that more advanced quests featuring combat can be instantiated.
 * 	
 */
	public void runCombat() {
		
	}
	
//-----------------------------------------------------------------------------------------	
}
