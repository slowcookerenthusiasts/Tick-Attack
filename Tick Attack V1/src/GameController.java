import java.util.Timer;
import java.lang.*;

public class GameController{

	private GameView view;
	//private QuestController questController;
	private GameModel gameModel;
	private Player player;
	boolean quest1ButtonActive = false;
	boolean quest2ButtonActive = false;

	public GameController(GameView gView, GameModel gModel) {
		view = gView;
		view.setGameController(this);
		gameModel = gModel;
		player = gameModel.getPlayer();
		fillTreeplantingQuest();
		fillSmugglingQuest();
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
			view.showHealth(player.getHealth());
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
		view.showInventory(player.getInventory());
	}

	public void runQuest(String title, QuestView view) {
		if (title.equals("Tree planting quest")) {
			gameModel.setActiveQuest(gameModel.getQuestDirectory().get(0));
		} else if (title.equals("Antibiotic smuggling quest")) {
			gameModel.setActiveQuest(gameModel.getQuestDirectory().get(1));
		}
		view.initialize(title);
		QuestController controller = new QuestController(gameModel, view);
		view.setQC(controller);
		//controller.readQuestModel();
	}//runQuest() method

//-----------------------------------------------------------------------------------------	

	public void fillTreeplantingQuest(){
		QuestDecisionPoint firstHour = new QuestDecisionPoint(false,false,"A bright new day begins! Time to start planting Trees!");
		QuestDecisionPoint standardHour = new QuestDecisionPoint(false,false,"Another gruelling hour passes, at least it isn't raining.");
		QuestDecisionPoint rainyHour = new QuestDecisionPoint(false,false,"It's raining");
		QuestDecisionPoint turkeyAttack = new QuestDecisionPoint(false,false,"A wild turkey attacks! Run! Run as fast as you can!");
		QuestDecisionPoint cry = new QuestDecisionPoint(false,false,"You sit down alone in the forest and cry for a while.");
		QuestDecisionPoint activeAddition = firstHour;
		QuestDecisionPoint testAddition = firstHour;
		for(int i=0; i<7; i++){
			testAddition.addChild(standardHour, 80);
			testAddition.addChild(rainyHour, 30);
			testAddition.addChild(turkeyAttack, 5);
			testAddition.addChild(cry, 10);
			QuestDecisionPoint newPoint = testAddition.generateChild();
			activeAddition.addChild(newPoint, 100);
			activeAddition = newPoint;
		}//for
		QuestDecisionPoint dayOver = new QuestDecisionPoint(true,false,"The day is over! It's time to collect your well earned street cred! Would you like do perform a tick check?");
		QuestDecisionPoint check = new QuestDecisionPoint(false, true, "Good idea, tick check's help you avoid Lyme Disease!");
		check.setHealthEffect(1);
		check.setSCEffect(190);
		dayOver.addChild(check, 100);
		QuestDecisionPoint noCheck = new QuestDecisionPoint(false, true, "Bad idea, you might get Lyme disease!");
		noCheck.setHealthEffect(-10);
		noCheck.setSCEffect(200);
		dayOver.addChild(noCheck, 100);
		activeAddition.addChild(dayOver, 100);
		gameModel.addQuest(firstHour);
	}//fillTreePlantingQuest() method
	
	
	public void fillSmugglingQuest(){
		QuestDecisionPoint startPoint = new QuestDecisionPoint(false,false,"You have decided to try and smuggle some Lyme antibiotics accross the border from Maine");
		QuestDecisionPoint secondPoint = new QuestDecisionPoint(false,false,"For questionable reasons it illegal to bring this medicine into New Brunswick from the US.");
		QuestDecisionPoint thirdPoint = new QuestDecisionPoint(false,false,"If you are caught the border guards will not be happy! :( ");
		QuestDecisionPoint fourthPoint = new QuestDecisionPoint(false,false,"What a bright and beautiful day to smuggle som antibiotics!");
		QuestDecisionPoint fifthPoint = new QuestDecisionPoint(false,false,"You have driven up to the border crossing a border guard approaches your car...");
		QuestDecisionPoint sixthPoint = new QuestDecisionPoint(false,false,"...The guard decides to search your car...");
		QuestDecisionPoint failurePoint = new QuestDecisionPoint(false,false,"Rats! You got caught and lost the antibiotics...let's hope no one diesas a result.");
		QuestDecisionPoint victoryPoint = new QuestDecisionPoint(false,false,"Success you made it out with antibiotics!");
		sixthPoint.addChild(failurePoint, 50);
		sixthPoint.addChild(victoryPoint, 50);
		fifthPoint.addChild(sixthPoint, 100);
		fourthPoint.addChild(fifthPoint, 100);
		thirdPoint.addChild(fourthPoint, 100);
		secondPoint.addChild(thirdPoint, 100);
		startPoint.addChild(secondPoint, 100);
		gameModel.addQuest(startPoint);
	}//fillSmugglingQuest() method	
	
//-----------------------------------------------------------------------------------------
	
	
	
	public void updateModel() {

	}

	public void updateView() {

	}

}
