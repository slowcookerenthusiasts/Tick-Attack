import java.util.Timer;

public class GameController{

	private GameView view;
	private GameModel gameModel;
	private Player player;
	private boolean quest1ButtonActive = false;
	private boolean quest2ButtonActive = false;
	private boolean hasStarted = false;

	/**
	 * Constructor that will perform some initializing and update the data for the quests by calling
	 * fillTreeplantingQuest and fillSmugglingQuest
	 * @param gView is a GameView the controller interacts with
	 * @param gModel is a GameModel the controller interacts with
	 */
	public GameController(GameView gView, GameModel gModel) {
		view = gView;
		view.setGameController(this);
		gameModel = gModel;
		player = gameModel.getPlayer();
		fillTreeplantingQuest();
		fillSmugglingQuest();
	}

	/**
	 * This will update the start state
	 * @param value is a boolean, indicating whether the game has started or not.
	 */
	public void changeStartState(boolean value) {
		hasStarted = value;
	}

	/**
	 * This method will check to see whether the game is over (which occurs when the
	 * player's street cred is no longer positive.
	 * @return a boolean, indicating whether the game is over or not.
	 */
	public boolean isOver() {
		if (player.getHealth() <=0){
		return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will actually run the game by first initializing it, and displaying
	 * elements such as street cred, inventory and health on the view. When the player dies,
	 * the game stops
	 */
	public void runGame() {
		initializeGame();	
		while(!isOver()) {
			updateStreetCred();
			view.showHealth(player.getHealth());
			unlockQuest(quest1ButtonActive, 10);
			unlockQuest(quest2ButtonActive, 20);
		}
		
		player.setHealth(0);
		view.showHealth(player.getHealth());
		try {
			view.removeFromView(view.getQuest1Button());
			view.removeFromView(view.getQuest2Button());
		} catch (Exception e) {
			
		}
		return;
	}
	
	/**
	 * This method will set quest 1's active state, meaning it will tell the controller
	 * whether the quest 1 button can be displayed to the view.
	 * @param value is a boolean indicating whether the button can be displayed
	 */
	public void setQuest1ButtonActive(boolean value) {
		quest1ButtonActive = value;
	}
	
	/**
	 * This method will set quest 2's active state, meaning it will tell the controller
	 * whether the quest 2 button can be displayed to the view.
	 * @param value is a boolean indicating whether the button can be displayed
	 */
	public void setQuest2ButtonActive(boolean value) {
		quest2ButtonActive = value;
	}
	
	/**
	 * This method will check to see whether a quest can be unlocked (thus, its start button can be displaye
	 * to the view). It checks by making sure the game has started, the quest is allowed to be active, and it
	 * meets the street cred requirements.
	 * @param questVisible is a boolean indicating that the quest may be displayed
	 * @param minStreetCred is an integer indicating the minimum street cred requirements
	 */
	private void unlockQuest(boolean questVisible, int minStreetCred) {
		if ((player.getStreetCred() % minStreetCred == 0) && (questVisible == false) && (hasStarted)) {
			if (minStreetCred == 10) {
				view.showQuest1Button();
				quest1ButtonActive = true;
			} else if (minStreetCred == 20) {
				view.showQuest2Button();
				quest2ButtonActive = true;
			}		
		}
	}
	
	/**
	 * This method will update the view's street cred label based on the number given in the model
	 */
	private void updateStreetCred() {
		view.showStreetCred(player.getStreetCred());
	}
	
	/**
	 * This helper method will initialize the game by showing the start button, and inventory. It
	 * will also start the timers, which are used to gradually increase street cred (1 per second) and
	 * gradually decrease street cred (-1 per 5 seconds)
	 */
	private void initializeGame() {
		view.showStartButton();
		Timer t = new Timer();
		t.schedule(new PlayerSCIncreaser(player), 0, 1000);
		
		Timer healthT = new Timer();
		healthT.schedule(new PlayerHealthIncreaser(player), 0, 5000);
		view.showInventory(player.getInventory());
	}

	/**
	 * This method will run a quest by setting the model's active quest to be the
	 * quest indicated. The quest view and controller are initialized.
	 * @param title is a String indicating the name of the quest
	 * @param view is a QuestView object that will display the data for the quest
	 */
	public void runQuest(String title, QuestView view) {
		if (title.equals("Tree planting quest")) {
			gameModel.setActiveQuest(gameModel.getQuestDirectory().get(0));
		} else if (title.equals("Antibiotic smuggling quest")) {
			gameModel.setActiveQuest(gameModel.getQuestDirectory().get(1));
		}
		view.initialize(title);
		QuestController controller = new QuestController(gameModel, view);
		view.setQC(controller);
	}

	/**
	 * This method will add a new QuestDecisionPoint to the GameModel's questDirectory which is the
	 * root of a tree of QuestDecisionPoint nodes which represent a tree planting quest. When this root 
	 * node is the activeQuest in the GameModel the questController will use the corresponding tree (and hence
	 * the tree planting quest) for quest events. Note that the root QuestDeicisionPoitn for this quest will be 
	 * added at the end of the directory.
	 */
	public void fillTreeplantingQuest(){
		QuestDecisionPoint firstHour = new QuestDecisionPoint(false,false,"A bright new day begins! Time to start planting some trees!");
		QuestDecisionPoint standardHour = new QuestDecisionPoint(false,false,"Another gruelling hour passes, at least it isn't raining...");
		QuestDecisionPoint rainyHour = new QuestDecisionPoint(false,false,"...It's raining.");
		QuestDecisionPoint turkeyAttack = new QuestDecisionPoint(false,false,"A wild turkey attacks! Run! Run as fast as you can!");
		QuestDecisionPoint cry = new QuestDecisionPoint(false,false,"You sit down alone in the forest and cry for a while. :(");
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
		}
		QuestDecisionPoint dayOver = new QuestDecisionPoint(true,false,"The day is over! It's time to collect your well earned street cred! Would you like do perform a tick check?");
		QuestDecisionPoint check = new QuestDecisionPoint(false, true, "Good idea, tick checks help you avoid Lyme Disease!");
		check.setHealthEffect(10);
		check.setSCEffect(80);
		dayOver.addChild(check, 100);
		QuestDecisionPoint noCheck = new QuestDecisionPoint(false, true, "Bad idea, you might get Lyme Disease!");
		noCheck.setHealthEffect(-30);
		noCheck.setSCEffect(200);
		dayOver.addChild(noCheck, 100);
		activeAddition.addChild(dayOver, 100);
		gameModel.addQuest(firstHour);
	}
	
	/**
	 * This method will add a new QuestDecisionPoint to the GameModel's questDirectory which is the
	 * root of a tree of QuestDecisionPoint nodes which represent an antibiotic smuggling quest. When this root 
	 * node is the activeQuest in the GameModel the questController will use the corresponding tree (and hence
	 * the antibiotic smuggling quest) for quest events. Note that the root QuestDeicisionPoitn for this quest will be 
	 * added at the end of the directory.
	 */
	public void fillSmugglingQuest(){
		QuestDecisionPoint startPoint = new QuestDecisionPoint(false,false,"You have decided to try and smuggle some Lyme antibiotics accross the border from Maine.");
		QuestDecisionPoint secondPoint = new QuestDecisionPoint(false,false,"For questionable reasons, it is illegal to bring this medicine into New Brunswick from the US.");
		QuestDecisionPoint thirdPoint = new QuestDecisionPoint(false,false,"If you are caught, the border guards will not be happy! :( ");
		QuestDecisionPoint fourthPoint = new QuestDecisionPoint(false,false,"What a bright and beautiful day to smuggle some antibiotics!");
		QuestDecisionPoint fifthPoint = new QuestDecisionPoint(false,false,"You have driven up to the border crossing, a border guard approaches your car...");
		QuestDecisionPoint sixthPoint = new QuestDecisionPoint(true,false,"Would you like to try and distract the guard?");
		QuestDecisionPoint sixthPointChildTrue = new QuestDecisionPoint(false,false,"The guard grows suspicious...");
		QuestDecisionPoint sixthPointChildFalse = new QuestDecisionPoint(false,false,"The guard smiles and lets you by, as you start to drive he glances over...");
		QuestDecisionPoint seventhPoint = new QuestDecisionPoint(false,false,"...The guard decides to search your car...");
		QuestDecisionPoint caughtPoint = new QuestDecisionPoint(true,false,"Rats! You got caught!! Do you want to jump out of the car and make a run for it?");
		QuestDecisionPoint caughtPointTrue1 = new QuestDecisionPoint(false,false,"You leap from the car and sprint towards the nearest Canadian tree line!");
		QuestDecisionPoint caughtPointTrue2a = new QuestDecisionPoint(false,false,"You are immediately tackled and arrested. :(");
		QuestDecisionPoint caughtPointTrue2b = new QuestDecisionPoint(false,false,"Using your ninja skills you dodge and weave past Canadian border security and escape into the forest!");
		QuestDecisionPoint caughtPointTrue2c = new QuestDecisionPoint(false,false,"A wild moose breaks from the forest, scaring away the border security, you leap unto its back and ride it to safety!");
		QuestDecisionPoint failurePoint = new QuestDecisionPoint(false,false,"You lost the antibiotics...let's hope no one dies as a result.");
		QuestDecisionPoint victoryPoint = new QuestDecisionPoint(false,true,"Success!!! You made it out with the antibiotics!");
		
		failurePoint.setSCEffect(-300);
		victoryPoint.setSCEffect(500);
		victoryPoint.setHealthEffect(40);
		
		caughtPointTrue2c.addChild(victoryPoint, 100);
		caughtPointTrue2b.addChild(victoryPoint, 100);
		caughtPointTrue2a.addChild(failurePoint, 100);
		caughtPointTrue1.addChild(caughtPointTrue2a, 40);
		caughtPointTrue1.addChild(caughtPointTrue2b, 40);
		caughtPointTrue1.addChild(caughtPointTrue2c, 20);
		caughtPoint.addChild(caughtPointTrue1, 50);
		caughtPoint.addChild(failurePoint, 50);
		seventhPoint.addChild(caughtPoint, 90);
		seventhPoint.addChild(victoryPoint, 10);
		sixthPointChildTrue.addChild(seventhPoint, 50);
		sixthPointChildFalse.addChild(victoryPoint, 50);
		sixthPointChildFalse.addChild(seventhPoint, 50);
		sixthPoint.addChild(sixthPointChildTrue, 50);
		sixthPoint.addChild(sixthPointChildFalse, 50);
		fifthPoint.addChild(sixthPoint, 100);
		fourthPoint.addChild(fifthPoint, 100);
		thirdPoint.addChild(fourthPoint, 100);
		secondPoint.addChild(thirdPoint, 100);
		startPoint.addChild(secondPoint, 100);
		gameModel.addQuest(startPoint);
	}

}
