import java.util.Timer;
import java.lang.*;

public class GameController{

	private GameView view;
	//private QuestController questController;
	private GameModel gameModel;
	private Player player;
	boolean quest1ButtonActive = false;
	boolean quest2ButtonActive = false;

	public GameController(GameView gView, QuestController qController, GameModel gModel) {
		view = gView;
		view.setGameController(this);
		gameModel = gModel;
		player = gameModel.getPlayer();
	}

	public void createQuest() {

	}

	public void deleteQuest() {

	}

	public boolean isOver() {

		return true;
	}

	public void runGame() {
		initializeGame();	
		while(true) {
			updateStreetCred();
			unlockQuest(quest1ButtonActive, 10);
			unlockQuest(quest2ButtonActive, 20);
		}
	}
	
	private void unlockQuest(boolean questVisible, int minStreetCred) {
		if ((player.getStreetCred() == minStreetCred) && (questVisible == false) && (view.hasStarted())) {
			if (minStreetCred == 10) {
				view.showQuest1Button();
				quest1ButtonActive = true;
			} else if (minStreetCred == 20) {
				view.showQuest2Button();
				quest2ButtonActive = true;
			}		
		}
	}
	
	private void updateStreetCred() {
		view.showStreetCred(player.getStreetCred());
	}
	
	private void initializeGame() {
		view.showStartButton();
		Timer t = new Timer();
		t.schedule(player, 0, 1000);
		view.showHealth(player.getHealth());
		view.showInventory(player.getInventory());
	}

	public void runQuest(String title, QuestView view) {
		view.initialize(title);
		QuestController controller = new QuestController(gameModel, view);
		controller.readQuestModel();
	}//runQuest() method

//-----------------------------------------------------------------------------------------	

	public void fillTreeplantingQuest() throws CloneNotSupportedException{
		QuestDecisionPoint firstHour = new QuestDecisionPoint(false,false,"A bright new day begins! Time to start planting Trees!");
		QuestDecisionPoint standardHour = new QuestDecisionPoint(false,false,"Another gruelling hour passes, at least it isn't raining.");
		QuestDecisionPoint rainyHour = new QuestDecisionPoint(false,false,"It's raining");
		QuestDecisionPoint turkeyAttack = new QuestDecisionPoint(false,false,"A wild turkey attacks! Run! Run as fast as you can!");
		QuestDecisionPoint cry = new QuestDecisionPoint(false,false,"You sit down alone in the forest and cry for a while.");
		QuestDecisionPoint activeAddition = firstHour;
		QuestDecisionPoint testAddition = firstHour;
		for(int i=0; i<7; i++){
			testAddition.addChild((QuestDecisionPoint)standardHour.clone(), 80);
			testAddition.addChild((QuestDecisionPoint)rainyHour.clone(), 30);
			testAddition.addChild((QuestDecisionPoint)turkeyAttack.clone(), 5);
			testAddition.addChild((QuestDecisionPoint)cry.clone(), 10);
			QuestDecisionPoint newPoint = testAddition.generateChild();
			activeAddition.addChild(newPoint, 100);
			activeAddition = newPoint;
		}//for
		QuestDecisionPoint dayOver = new QuestDecisionPoint(true,false,"The day is over! It's time to collect your well earned street cred! Would you like do perform a tick check?");
		QuestDecisionPoint Check = new QuestDecisionPoint(false, true, "Good idea, tick check's help you avoid Lyme Disease!");
		Check.setHealthEffect(1);
		Check.setSCEffect(190);
		dayOver.addChild(Check, 100);
		QuestDecisionPoint noCheck = new QuestDecisionPoint(false, true, "Bad idea, you might get Lyme disease!");
		noCheck.setHealthEffect(-10);
		noCheck.setSCEffect(200);
		dayOver.addChild(noCheck, 100);
		activeAddition.addChild(dayOver, 100);
		gameModel.addQuest(firstHour);
	}//fillTreePlantingQuest() method
	
	
//-----------------------------------------------------------------------------------------
	
	
	
	public void updateModel() {

	}

	public void updateView() {

	}

}
