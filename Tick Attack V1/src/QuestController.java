public class QuestController {

	private GameModel gameModel;
	private QuestView view;
	
	public QuestController(GameModel model, QuestView newView ) {
		gameModel = model;
		view = newView;
	}
	
//-----------------------------------------------------------------------------------------	
	
	public void initializeQuestView(QuestView view, String title) {
		view.initialize(title);
	}

//-----------------------------------------------------------------------------------------

	
	public void readQuestModel() {
		for (int i = 0; i < 20; i++) {
			if (i%2 ==0) {
				view.displayText("Hi! I am testing the view! freyhfjrenkjieuhrgngjenjngrijehgunrguhnen");
			} else {
				view.displayText("Yo!!!! This is sick!! whuvdhgbeyvftwvghbfuerhbfehrfrnefunbbruuebfrnufe");
			}
		}

	}
	
	//commented this out just for testing purposes
	
//	public void readQuestModel() {
//		QuestDecisionPoint activeNode = gameModel.getActiveQuest();
//		while(activeNode.hasChildren()){
//			view.displayText(activeNode.getDecisionText());
//			if(activeNode.getPlayerDecisionNeeded()){
//				boolean choice = view.getChoice();
//				QuestDecisionPoint child = activeNode.getChosenChild(choice);
//				activeNode = child;
//			}//if
//			else{
//				QuestDecisionPoint child = activeNode.generateChild();
//				activeNode = child;
//			}//else
//		}//while
//	}
//	
//-----------------------------------------------------------------------------------------	
/**
 * Currently an unfilled method, meant to exist so that more advanced quests featuring combat can be instantiated.
 * 	
 */
	public void runCombat() {
		
	}
	
//-----------------------------------------------------------------------------------------	
}
