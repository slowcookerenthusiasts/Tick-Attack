public class QuestController {

	private GameModel gameModel;
	private QuestView quest1View;
	private QuestView quest2View;
	private QuestDecisionPoint quest1Model;
	private QuestDecisionPoint quest2Model;
	
	public QuestController() {
		quest1View = new QuestView();
		quest2View = new QuestView();
		quest1Model = new QuestDecisionPoint();
		quest2Model = new QuestDecisionPoint();	
	}
	
	public void initializeQuestView(QuestView view, String title) {
		view.initialize(title);
	}
	
	public QuestView getFirstQuestView() {
		return quest1View;
	}
	
	public QuestView getSecondQuestView() {
		return quest2View;
	}
	
	public void setReward() {
		
	}
	
	public void setPenalty() {
		
	}
	
	public void readQuestModel() {
		
	}
	
	public void setGoal() {
		
	}
	
	public boolean isSuccess() {
		return true;
	}
	
	public void runCombat() {
		
	}
}
