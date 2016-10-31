public class QuestController{

	private GameModel gameModel;
	private QuestView view;
	private QuestDecisionPoint activeNode;
	
	/**
	 * Constructor, takes in the game model and a quest view
	 * @param model is a GameModel object, used to get quest data.
	 * @param newView is a QuestView object, it is this view that the QuestController
	 * is manipulating
	 */
	
	public QuestController(GameModel model, QuestView newView) {
		gameModel = model;
		view = newView;
		activeNode = gameModel.getActiveQuest();
		
		//displaying the initial quest scenario right off the bat
		view.displayTextAndButton(activeNode.getDecisionText());
		activeNode = activeNode.generateChild();
	}
	
	/**
	 * This method will initialize a QuestView.
	 * @param view is a QuestView that is to be initialized
	 * @param title is the title of the quest
	 */
	public void initializeQuestView(QuestView view, String title) {
		view.initialize(title);
	}
	
	/**
	 * This method will cause the active node to update, based on the decision the user
	 * makes (as indicated by a button click), and will update the model
	 * @param update
	 */
	public void makeDecision(boolean update) {
		QuestDecisionPoint child = activeNode.getChosenChild(update);
		activeNode = child;
		updateModel();
	}
	
	/**
	 * This method will progress the quest's 'story' forward.
	 * If the active node has children and no decision is needed, the view will be prompted
	 * to display both text and a next button. The active node is then updated.
	 * If the active node has no children (is a final outcome) and no decision is needed from the player,
	 * just text is displayed (no Next button). Otherwise, a player decision is needed
	 * and the view will display text (no next button) and will display yes/no buttons.
	 */
	public void progressQuest() {
		if (activeNode.hasChildren() && (!activeNode.getPlayerDecisionNeeded())) {
			view.displayTextAndButton(activeNode.getDecisionText());
			QuestDecisionPoint child = activeNode.generateChild();
			activeNode = child;
		} else if (!activeNode.hasChildren() && (!activeNode.getPlayerDecisionNeeded())) {
			view.displayJustText(activeNode.getDecisionText());
		} else{
			view.displayJustText(activeNode.getDecisionText());
			view.promptChoice();
		}
		
		updateModel();
	}
	
	/**
	 * This method will take the active node into account. If the active node has a health/street cred benefit
	 * the benefits are noted, and the gameModel is updated as a result.
	 */
	public void updateModel() {
		if (activeNode.getHasHealthEffect()) {
			gameModel.getPlayer().increaseHealthBy(activeNode.getHealthEffect());
		} if (activeNode.getHasSCEffect()) {
			gameModel.getPlayer().increaseStreetCredBy(activeNode.getSCEffect());
		}
	}	
}
