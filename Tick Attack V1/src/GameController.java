import java.util.Timer;

public class GameController{

	private GameView view;
	private QuestController questController;
	private GameModel gameModel;
	private Player player;
	boolean calledQ1 = false;
	boolean calledQ2 = false;

	public GameController(GameView gView, QuestController qController, GameModel gModel) {
		view = gView;
		questController = qController;
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
			unlockQuest(calledQ1, 10);
			unlockQuest(calledQ2, 20);
		}
	}
	
	private void unlockQuest(boolean hasBeenCalled, int minStreetCred) {
		if ((player.getStreetCred() == minStreetCred) && (hasBeenCalled == false) && (view.hasStarted())) {
			if (minStreetCred == 10) {
				view.showQuest1Button();
				calledQ1 = true;
			} else if (minStreetCred == 20) {
				view.showQuest2Button();
				calledQ2 = true;
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
