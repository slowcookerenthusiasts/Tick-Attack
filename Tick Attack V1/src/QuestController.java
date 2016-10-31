public class QuestController{

	private GameModel gameModel;
	private QuestView view;
	private QuestDecisionPoint activeNode;
	
	public QuestController(GameModel model, QuestView newView ) {
		gameModel = model;
		view = newView;
		activeNode = gameModel.getActiveQuest();
		view.displayTextAndButton(activeNode.getDecisionText());
		activeNode = activeNode.generateChild();
	}
	
//-----------------------------------------------------------------------------------------	
	
	public void initializeQuestView(QuestView view, String title) {
		view.initialize(title);
	}
	
	public void makeDecision(boolean update) {
		QuestDecisionPoint child = activeNode.getChosenChild(update);
		activeNode = child;
	}
	
	public void progressQuest() {
		if (activeNode.hasChildren() && (!activeNode.getPlayerDecisionNeeded())) {
			view.displayTextAndButton(activeNode.getDecisionText());
			QuestDecisionPoint child = activeNode.generateChild();
			activeNode = child;
			return;
		} else if (!activeNode.hasChildren() && (!activeNode.getPlayerDecisionNeeded())) {
			view.displayJustText(activeNode.getDecisionText());
			return;
		}
		
		if (activeNode.getPlayerDecisionNeeded()){
			view.displayJustText(activeNode.getDecisionText());
			view.promptChoice();
		}
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
