import java.util.Timer;
import java.util.TimerTask;


public class GameController{

	private GameView view;
	private QuestController questController;
	private GameModel gameModel;

	public GameController(GameView gView, QuestController qController, GameModel gModel) {
		view = gView;
		questController = qController;
		gameModel = gModel;
	}

	public void createQuest() {

	}

	public void deleteQuest() {

	}

	public boolean isOver() {

		return true;
	}

	public void run() {
		view.showStartButton();
		Player player = gameModel.getPlayer();
		Timer t = new Timer();
		t.schedule(player, 0, 1000);
		view.showHealth(player.getHealth());
		view.showInventory(player.getInventory());
		boolean calledQ1 = false;
		boolean calledQ2 = false;
		
		while(true) {
			view.showStreetCred(player.getStreetCred());
			if((player.getStreetCred() == 10) && (calledQ1 == false)) {
				view.showQuest1Button();
				calledQ1 = true;
			} else if ((player.getStreetCred() == 20) && (calledQ2 == false)) {
				view.showQuest2Button();
				calledQ2 = true;
			}
		}
	}

	public void runQuest() {

	}

	public void updateModel() {

	}

	public void updateView() {

	}

}
