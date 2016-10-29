import java.util.Timer;

public class GameController{

	private GameView view;
	//private QuestController questController;
	private GameModel gameModel;
	private Player player;
	boolean quest1ButtonActive = false;
	boolean quest2ButtonActive = false;

	public GameController(GameView gView, QuestController qController, GameModel gModel) {
		view = gView;
		//questController = qController;
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

	public void runQuest() {

	}

	public void updateModel() {

	}

	public void updateView() {

	}

}
